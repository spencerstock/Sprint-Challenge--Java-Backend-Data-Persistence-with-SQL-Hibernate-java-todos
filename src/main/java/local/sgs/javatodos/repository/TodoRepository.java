package local.sgs.javatodos.repository;

import local.sgs.javatodos.models.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long>
{

}