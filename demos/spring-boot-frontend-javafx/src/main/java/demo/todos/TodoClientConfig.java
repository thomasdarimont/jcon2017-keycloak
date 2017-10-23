package demo.todos;

import demo.JavaFxFrontendApplication;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class TodoClientConfig {

  @Bean
  protected RequestInterceptor keycloakSecurityContextRequestInterceptor() {
    return new KeycloakSecurityContextRequestInterceptor();
  }

  static class KeycloakSecurityContextRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {

      try {
        String accessToken = JavaFxFrontendApplication.KEYCLOAK.getTokenString(10, TimeUnit.SECONDS);
        template.header(AUTHORIZATION_HEADER, "Bearer " + accessToken);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
