package local.sgs.javatodos.controller;


import local.sgs.javatodos.models.Todo;
import local.sgs.javatodos.models.User;
import local.sgs.javatodos.services.TodoService;
import local.sgs.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController
{


    @Autowired
    private UserService userService;

    @Autowired
    TodoService todoService;

    @GetMapping(value = "/users/mine", produces = {"application/json"})
    public ResponseEntity<?> getMyTodos(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByUsername(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername());
        return new ResponseEntity<>(todoService.findAllById(currentUser.getUserid()), HttpStatus.OK);
    }


    @GetMapping(value = "/todos", produces = {"application/json"})
    public ResponseEntity<?> listAllTodos()
    {
        List<Todo> allTodos = todoService.findAll();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    @GetMapping(value = "/todo/{todoId}", produces = {"application/json"})
    public ResponseEntity<?> getTodo(@PathVariable Long todoId)
    {
        Todo q = todoService.findTodoById(todoId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }

    @GetMapping(value = "/username/{userName}", produces = {"application/json"})
    public ResponseEntity<?> findTodoByUserName(@PathVariable String userName)
    {
        List<Todo> theTodos = todoService.findByUserName(userName);
        return new ResponseEntity<>(theTodos, HttpStatus.OK);
    }


    @PostMapping(value = "/users/todo/{userid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> postNewTodo(@PathVariable long userid, @RequestBody Todo todo){
        todo.setUser(userService.findUserById(userid));
        return new ResponseEntity<>(todoService.save(todo), HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable long id)
    {
        todoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
