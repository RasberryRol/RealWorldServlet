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
import java.util.List;

@WebServlet("/UserManagement")
public class UserManagement extends CheckLoginServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validate(req, resp);
        String action = req.getParameter("action");

        String caption= "";
        String link = "";
        String msg = "";
        String err = "";
        try {
            Connection conn = DBUtil.getConnection();
            req.setAttribute("UM", " active");
            if(action.equals("list")){
                List<User> list = UserBusiness.getUserList(conn);
                msg = "no data";

                if(list.size() > 0){
                    msg = list.size() + (list.size() > 1 ? " records" : "record");
                }
                String otherMsg = (String) req.getAttribute("MSG");
                msg = otherMsg + " - " + msg;

                err = (String) req.getAttribute("err");

                caption = "List of Users";
                req.setAttribute("list", list);
                link = "/WEB-INF/user/user.jsp";
            } else if (action.equals("new")) {
                caption = "Add New User";
                link = "/WEB-INF/user/user_new.jsp";

            } else if (action.equals("edit")) {
                User u = UserBusiness.getUserByName(conn, req.getParameter("userName"));
                req.setAttribute("u", u);
                caption = "Edit New User";
                link = "/WEB-INF/user/user_edit.jsp";
            }  else if (action.equals("save")) {
                User u = new User();
                u.setUserName(req.getParameter("userName"));
                u.setPassword(req.getParameter("password"));
                u.setEmail(req.getParameter("email"));
                u.setDisplayName(req.getParameter("displayName"));

                if(!UserBusiness.isUserExisted(conn, u.getUserName())){
                    err = u.getUserName() + " does not exist.";
                } else{
                    UserBusiness.editUser(conn, u);
                    msg = "Edited successfully!";
                }
                req.setAttribute("u", u);
                caption = "Add New User";
                link = "/WEB-INF/user/user_new.jsp";

            } else if (action.equals("insert")) {
                User u = new User();
                u.setUserName(req.getParameter("userName"));
                u.setPassword(EncryptionUtil.encode(req.getParameter("password")));
                u.setEmail(req.getParameter("email"));
                u.setDisplayName(req.getParameter("displayName"));

                if(!UserBusiness.isUserExisted(conn, u.getUserName())){
                    UserBusiness.insertUser(conn, u);
                    msg = "Inserted successfully!";
                } else{
                    err = "Duplicate user: '" + u.getUserName()+ "'";
                }
                caption = "Add New User";
                link = "/WEB-INF/user/user_new.jsp";
            }  else if (action.equals("delete")) {
                String userName = req.getParameter("userName");
                if(!UserBusiness.isUserExisted(conn, userName)){
                    err = "User: '" + userName + "' does not exist.";
                } else{
                    UserBusiness.deleteUser(conn, userName);
                    msg = "Successful deletion of user: '" + userName + "'";
                }
                caption = "List of Users";
                link = "/UserManagement?action=list";
            }  else if (action.equals("deleteList")) {
                StringBuilder userNames = new StringBuilder();

                for(String names : req.getParameterValues("userNames")){
                    userNames.append("'").append(names).append("',");
                }
                userNames.deleteCharAt(userNames.length() - 1);

                String userName = req.getParameter("userName");
                int result = UserBusiness.deleteUserByName(conn, userNames);
                msg = "Deleted " + result + " record(s)";

                caption = "List of Users";
                link = "/UserManagement?action=list";
            }
            conn.close();

            req.setAttribute("err", err);
            req.setAttribute("MSG", msg);
            req.setAttribute("CAPTION", caption);
            req.getRequestDispatcher(link).forward(req, resp);
        }catch (Exception e){
            req.setAttribute("ERROR", "System error: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }
    }



    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
