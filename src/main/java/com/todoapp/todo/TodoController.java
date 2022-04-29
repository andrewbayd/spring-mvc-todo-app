package com.todoapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("username")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public String listTodos(ModelMap model) {
        model.addAttribute("todos", todoService.retrieveToDos(retrieveLoggedInUser()));
        return "todos";
    }

    private String retrieveLoggedInUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

    @RequestMapping(value = "/create-todo", method = RequestMethod.GET)
    public String showAddTodoForm(ModelMap model) {
        model.addAttribute("todo", new Todo(0, "test", "", new Date(), false));
        return "todo";
    }

    @RequestMapping(value = "/create-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        todoService.addToDo("test", todo.getDesc(), new Date(), false);
        model.clear(); // do not pass url parameters
        return "redirect:todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showTodo(ModelMap model, @RequestParam int id) {
        Todo todo = todoService.retrieveTodo(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        todoService.updateTodo(todo);
        return "redirect:todos";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(ModelMap model, @RequestParam int id) {
        todoService.deleteToDo(id);
        model.clear();
        return "redirect:todos";
    }
}
