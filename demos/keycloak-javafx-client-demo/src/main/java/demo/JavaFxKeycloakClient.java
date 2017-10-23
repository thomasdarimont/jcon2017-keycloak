package demo;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class JavaFxKeycloakClient {

	public static void main(String[] args) throws Exception {

		// AdapterConfig config = new AdapterConfig();
		// HashMap<String, Object> credentials = new HashMap<>();
		// credentials.put("client_id", "demo-client");
		// credentials.put("client_secret",
		// "a3ec3ec7-4c09-4457-ad83-b1002ad3ba9a");
		// credentials.put("username", "tester");
		// credentials.put("password", "test");
		// credentials.put("provider", "secret");
		// config.setCredentials(credentials);
		// config.setRealm("direct-access-client-example");
		// config.setAuthServerUrl("http://localhost:8081/auth");
		// config.setSslRequired("external");
		// config.setResource("demo-client");
		// config.setUseResourceRoleMappings(true);
		//
		// KeycloakDeployment deployment =
		// KeycloakDeploymentBuilder.build(config);

		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		formData.add("client_id", "demo-client");
		formData.add("client_secret", "a3ec3ec7-4c09-4457-ad83-b1002ad3ba9a");
		formData.add("username", "user");
		formData.add("password", "test");
		formData.add("grant_type", "password");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(formData,
				headers);

		String tokenUrl = "http://localhost:8081/auth/realms/direct-access-client-example/protocol/openid-connect/token";
		ResponseEntity<Map> response = rt.postForEntity(tokenUrl, request, Map.class);

		System.out.println(response.getBody());

		// AdapterRSATokenVerifier.verifyToken("", deployment);
	}

}
