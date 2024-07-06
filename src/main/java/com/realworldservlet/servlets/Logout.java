package com.realworldservlet.servlets;

import com.realworldservlet.businesses.UserBusiness;
import com.realworldservlet.models.User;
import com.realworldservlet.utils.DBUtil;
import com.realworldservlet.utils.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name="logout", urlPatterns = "/logout")
public class Logout extends HttpServlet{

        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
}
