package demo.todo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {

  final Long id;

  final String name;

  final boolean completed;
}
