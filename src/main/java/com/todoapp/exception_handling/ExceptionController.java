package com.todoapp.exception_handling;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private Log logger = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public String handle(HttpServletRequest request, Exception e) {
        logger.error("Request " + request.getRequestURL() + " threw an exception: " + e);
        return "error";
    }
}
