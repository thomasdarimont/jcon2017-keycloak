package demo.todo;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.context.annotation.Bean;

public class TodoClientConfig {

  @Bean
  protected RequestInterceptor keycloakSecurityContextRequestInterceptor(KeycloakSecurityContext keycloakSecurityContext) {
    return new KeycloakSecurityContextRequestInterceptor(keycloakSecurityContext);
  }

  @RequiredArgsConstructor
  static class KeycloakSecurityContextRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final KeycloakSecurityContext keycloakSecurityContext;

    @Override
    public void apply(RequestTemplate template) {

      if (keycloakSecurityContext instanceof RefreshableKeycloakSecurityContext) {
        RefreshableKeycloakSecurityContext.class.cast(keycloakSecurityContext)
          .refreshExpiredToken(true);
      }

      template.header(AUTHORIZATION_HEADER, "Bearer " + keycloakSecurityContext.getTokenString());
    }
  }
}
