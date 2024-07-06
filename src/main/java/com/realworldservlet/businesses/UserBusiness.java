package com.realworldservlet.businesses;

import com.realworldservlet.models.User;
import com.realworldservlet.utils.EncryptionUtil;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBusiness {
    public static User login(Connection conn, String username, String password) throws SQLException {
        String sql = "select username, password, email, display_name from user " +
                "where username=? and password=?";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, username);
        stm.setString(2, password);

        ResultSet rs = stm.executeQuery();

        if(rs.next()){
            User u = new User();
            u.setUserName(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setDisplayName(rs.getString("display_Name"));

            return u;
        }
        return null;
    }

    public static List<User> getUserList(Connection conn) throws SQLException{
        String sql = "select username, password, email, display_name from user";
        System.out.println(sql);

        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<User> list = new ArrayList<User>();

        while(rs.next()){
            User user = new User();
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setDisplayName(rs.getString("display_name"));
            list.add(user);
        }
        return list;
    }

    public static boolean isUserExisted(Connection conn, String userName) throws SQLException {
        String sql = "select username from user where username=?";
        System.out.println(sql);

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, userName);
        ResultSet rs = stm.executeQuery();

        return rs.next(); //returns true if exists, but false if not
    }

    public static void insertUser(Connection conn, User u) throws SQLException {
        String sql = "insert into user(username, password, email, display_name) values(?, ?, ?, ?)";
        System.out.println(sql);

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, u.getUserName());
        stm.setString(2, u.getPassword());
        stm.setString(3, u.getEmail());
        stm.setString(4, u.getDisplayName());
        stm.executeUpdate();

    }

    public static void editUser(Connection conn, User u) throws SQLException, UnsupportedEncodingException {
        String sql = "UPDATE user set password=?, email=?, display_name=? where username=?";
        System.out.println(sql);

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, EncryptionUtil.encode(u.getPassword()));
        stm.setString(2, u.getEmail());
        stm.setString(3, u.getDisplayName());
        stm.setString(4, u.getUserName());
        stm.executeUpdate();
    }

    public static User getUserByName(Connection conn, String userName) throws SQLException {
        String sql = "select username, password, email, display_name from user " +
                "where username=?";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, userName);
        ResultSet rs = stm.executeQuery();

        if(rs.next()){
            User u = new User();
            u.setUserName(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setDisplayName(rs.getString("display_Name"));

            return u;
        }
        return null;
    }

    public static void deleteUser(Connection conn, String userName) throws SQLException {
        String sql = "delete from user where username=?";
        System.out.println(sql);

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, userName);
        stm.executeUpdate();
    }

    public static int deleteUserByName(Connection conn, StringBuilder userNames) throws SQLException {
        String sql = "delete from user where username in (" + userNames +")";
        System.out.println(sql);

        PreparedStatement stm = conn.prepareStatement(sql);

        return stm.executeUpdate();
    }
}
