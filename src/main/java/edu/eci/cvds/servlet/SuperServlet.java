package edu.eci.cvds.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.MalformedInputException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.*;

import edu.eci.cvds.servlet.model.Todo;

@WebServlet(
    urlPatterns = "/lab5"
)
public class SuperServlet extends HttpServlet{
    static final long serialVersionUID = 35L;

    @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Writer responseWriter = resp.getWriter();
    try{
        Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
        if(optName.isPresent() && optName.get().isEmpty())throw new ServletException("Is Empty");
        int id = Integer.valueOf(optName.get());
        Todo info = Service.getTodo(id);
        List<Todo> todoList = new ArrayList<Todo>();
        resp.setStatus(HttpServletResponse.SC_OK);
        todoList.add(info);
        responseWriter.write(Service.todosToHTMLTable(todoList));
    }catch(FileNotFoundException e){
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }catch(MalformedInputException e){
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }catch(Exception e){
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
   }


   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Writer responseWriter = resp.getWriter();
    try{
        Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
        if(optName.isPresent() && optName.get().isEmpty())throw new ServletException("Is Empty");
        int id = Integer.valueOf(optName.get());
        Todo info = Service.getTodo(id);
        List<Todo> todoList = new ArrayList<Todo>();
        resp.setStatus(HttpServletResponse.SC_OK);
        todoList.add(info);
        responseWriter.write(Service.todosToHTMLTable(todoList));
    }catch(FileNotFoundException e){
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }catch(MalformedInputException e){
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }catch(Exception e){
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
    }
}
