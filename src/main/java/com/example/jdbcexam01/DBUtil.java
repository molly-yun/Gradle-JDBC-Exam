package com.example.jdbcexam01;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        // 1. DBMS 접속
        // DB Connection 설정 문자열은 하드코딩보다는 환경설정 부분으로 따로 관리해주는 것이 좋다 -> 구글링해보고 적용하기
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/study_db?useSSL=false&serverTimezone=Asia/Seoul&useUnicode=true&character_set_server-utf8mb4","root","1234");
        return conn;
    }

    // 2. DBMS 접속 해제
    // INSERT, UPDATE, DELETE문은 PreparedStatement, Connection 2가지를 close 해주면 된다.
    public static void close(PreparedStatement ps, Connection conn) throws SQLException {
        ps.close();
        conn.close();
    }

    // SELECT문은 PreparedStatement, Connection, ResultSet 3가지를 close 해주어야 한다.
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        rs.close();
        close(ps, conn);    // 중복 코드를 없애기 위해 위에 만들어놓은 close 메서드 사용
    }
}
