package local.sgs.javatodos.repository;

import local.sgs.javatodos.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
