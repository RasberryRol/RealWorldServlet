package com.realworldservlet.servlets;

import com.mysql.cj.util.StringInspector;
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
import java.sql.SQLException;


@WebServlet(name="login", urlPatterns = "/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        password = EncryptionUtil.encode(password);

        try{
            Connection conn = DBUtil.getConnection();
            User u = UserBusiness.login(conn, username, password);

            if(u != null){
                req.getSession().setAttribute("CURRENT_USER", u);
                conn.close();
                req.setAttribute("Home", " active");
                req.getRequestDispatcher("/WEB-INF/home/index.jsp").forward(req, resp);
            }else {
                req.setAttribute("err", "Wrong username or password. Try again!");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("err", "System error: " + e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
    }
}
