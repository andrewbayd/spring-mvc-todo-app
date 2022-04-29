package com.todoapp.todo.rest;

import com.todoapp.todo.Todo;
import com.todoapp.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestTodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(path = "/list-todos")
    public List<Todo> retrieveAllTodos() {
        return todoService.retrieveToDos("test");
    }

    @RequestMapping(path = "/list-todos/{id}")
    public Todo retrieveAllTodo(@PathVariable int id) {
        return todoService.retrieveTodo(id);
    }
}
