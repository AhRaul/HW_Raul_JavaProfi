package com.company.HW4_z2;

import java.sql.*;

class User {
    private final String login;
    private final String password;
    private  String nickname;
    private boolean active = true;
    ResultSet rs;
    Statement stmt;

    public User(String login, String password, String nickname) {
        //з1.
//        try {
//            Class.forName("org.sqlite.JDBC");
//            Connection co = DriverManager.getConnection (
//                    "jdbc:sqlite:users.db");
//            System.out.println("DB Connected");
//            this.stmt =  co.createStatement();
//            stmt.execute("INSERT INTO Students (login, password, nickname) VALUES (" + login + ", " + password + ", " + nickname + ");"); // по методичке, пока не понятно, почему ковычки подчеркиваются? добавил плюсы, не известна реакция
//            this.rs = stmt.executeQuery("SELECT * FROM users WHERE login = "+ login + ";");
//            co.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }

    public String getLogin() {
//        try {
//            return rs.getString(1);
//        } catch (SQLException e) {
//            return "Error to get login";
//        }
        return login;
    }

    public String getPassword() {
//        try {
//            return rs.getString(2);
//        } catch (SQLException e) {
//            return "Error to get password";
//        }
        return password;
    }

    public String getNickname() {
//        try {
//            return rs.getString(3);
//        } catch (SQLException e) {
//            return "Error to get nickname";
//        }
        return nickname;
    }

//    public void setNewNickname(String newNick, String nick) {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            Connection co = DriverManager.getConnection (
//                    "jdbc:sqlite:users.db");
//            System.out.println("DB Connected");
//            this.stmt =  co.createStatement();
//            stmt.execute("UPDATE Students SET nickname = " + newNick + "WHERE nick = "+ nick + ";");
//            this.rs = stmt.executeQuery("SELECT * FROM users WHERE nick = "+ newNick + ";");
//            co.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
