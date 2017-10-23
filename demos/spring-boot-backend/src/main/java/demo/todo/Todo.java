package demo.todo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
class Todo {

  @Id
  @GeneratedValue
  Long id;

  String name;

  String description;

  boolean completed;

  LocalDateTime createdAt;

  LocalDateTime updatedAt;

  String ownerId;
}
