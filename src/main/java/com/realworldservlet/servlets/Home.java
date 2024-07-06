package com.realworldservlet.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="home", urlPatterns = "/home")
public class Home extends CheckLoginServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validate(req, resp);
        req.setAttribute("HOME", " active");
        req.getRequestDispatcher("/WEB-INF/home/index.jsp").forward(req, resp);
    }
}
