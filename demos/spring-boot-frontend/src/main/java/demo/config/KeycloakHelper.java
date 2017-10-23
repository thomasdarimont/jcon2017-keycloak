package demo.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;

@RequiredArgsConstructor
public class KeycloakHelper {
  private final KeycloakSpringBootProperties keycloakSpringBootProperties;

  private final String clientLinkTemplate = "%s/realms/%s/clients/%s/redirect";

  public String getClientLink(String clientId) {
    return String.format(clientLinkTemplate, keycloakSpringBootProperties.getAuthServerUrl(), keycloakSpringBootProperties.getRealm(), clientId);
  }
}
