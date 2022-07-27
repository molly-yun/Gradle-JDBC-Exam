/*
package com.example.jdbcexam01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionTest02 {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.getConnection();   // 1. DB 연결
            String sql = "SELECT first_name, last_name, salary FROM employees WHERE salary > ? ORDER BY salary"; // 2. 실행할 SQL문 준비
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, 9000.0 );   // ?에 데이터 바인딩
            // 3. SQL 실행하고 결과 가져옴
            ResultSet rs = ps.executeQuery(); // ctrl + alt + V: 변수 추출하기, 실행한 SQL 쿼리 결과를 ResultSet에 반환한다.
            while(rs.next()){ // rs.next(): 레코드 한 줄씩 가져옴
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String salary = rs.getString("salary");
                System.out.println(first_name + "," + last_name + "," + salary);
            }

            DBUtil.close(rs, ps, conn); // 4. SQL 실행 후 DB 연결 해제
        } catch (Exception ex) {
            System.out.println("DB 관련 오류: " + ex.getMessage());
        }
    }
}
*/
