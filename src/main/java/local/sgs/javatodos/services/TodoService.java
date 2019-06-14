package local.sgs.javatodos.services;


import local.sgs.javatodos.models.Todo;

import java.util.List;

public interface TodoService
{
    List<Todo> findAll();

    Todo findTodoById(long id);

    List<Todo> findByUserName(String username);

    void delete(long id);

    local.sgs.javatodos.models.Todo save(Todo todo);
}
