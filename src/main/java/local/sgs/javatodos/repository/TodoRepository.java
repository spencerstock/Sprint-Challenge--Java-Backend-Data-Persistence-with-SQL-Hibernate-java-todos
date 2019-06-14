package local.sgs.javatodos.repository;

import local.sgs.javatodos.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
}
