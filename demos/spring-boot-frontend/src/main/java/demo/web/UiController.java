package demo.web;

import demo.todo.Todo;
import demo.todo.TodoClient;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequiredArgsConstructor
class UiController {

  private final TodoClient todoClient;

  private final KeycloakSecurityContext keycloakSecurityContext;

  private final KeycloakSpringBootProperties keycloakProperties;

  @GetMapping("/")
  String index() {
    return "index";
  }

  @GetMapping("/logout")
  String logout(HttpServletRequest request) throws Exception {
    request.logout();
    return "redirect:/todos";
  }

  @GetMapping("/account")
  String account() {

    String todoUri = linkTo(UiController.class).toUriComponentsBuilder().path("/todos").toUriString();

    UriComponentsBuilder accountUri = UriComponentsBuilder.fromHttpUrl(keycloakSecurityContext.getToken().getIssuer())
      .path("/account")
      .queryParam("referrer", keycloakProperties.getResource())
      .queryParam("referrer_uri", todoUri);

    return "redirect:" + accountUri.toUriString();
  }

  @GetMapping("/todos*")
  String todos(Model model) {

    Resources<Resource<Todo>> todos = todoClient.fetchTodos();
    model.addAttribute("todos", todos.getContent());

    return "todos";
  }
}
