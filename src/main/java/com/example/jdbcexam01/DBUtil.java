package com.example.jdbcexam01;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        // 1. DBMS 접속
        // DB Connection 설정 문자열은 하드코딩보다는 환경설정 부분으로 따로 관리해주는 것이 좋다 -> 구글링해보고 적용하기
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/study_db?useSSL=false&serverTimezone=Asia/Seoul&useUnicode=true&character_set_server-utf8mb4","root","1234");
        conn.setAutoCommit(false);
        return conn;
    }

    // 2. DBMS 접속 해제
    // INSERT, UPDATE, DELETE문은 PreparedStatement, Connection 2가지를 close 해주면 된다.
    public static void close(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    public static void close(ResultSet rs, PreparedStatement ps) throws SQLException {
        rs.close();
        ps.close();
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
