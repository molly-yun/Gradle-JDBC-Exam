package com.example.jdbcexam01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.sql.DriverManager.getConnection;

public class ConnectTest01 {
    public static void main(String[] args) {
        // 트랜잭션 시작
        // try( connection 얻어오기 ) -> 이렇게 하면 connection이 완료된 뒤에 자동으로 close된다.
        try(Connection conn = getConnection("jdbc:mysql://127.0.0.1:3308/study_db?useSSL=false&serverTimezone=Asia/Seoul&useUnicode=true&character_set_server-utf8mb4","root","1234")){
            //System.out.println(conn);   // null 또는 exception이 발생하면 연결 실패한 것
            //System.out.println(conn.getClass().getName());  // Connection 인터페이스의 구현부를 알 수 있다.

            // SQL 실행
            // - SQL 작성
            String sql = "SELECT first_name, last_name, salary FROM employees";

            // - PreparedStatement: SQL을 DBMS에서 실행할 준비
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                // executeQuery(): 준비된 SQL 실행. 실행된 결과는 DBMS에 있다.
                // ResultSet: DBMS 안에 있는 실행결과를 참조

                while(rs.next()){   // rs.next(): 레코드 한 줄의 실행결과를 가져옴(boolean)
                    // rs가 읽어들인 레코드에 속한 컬럼 쪼개기
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    double salary = rs.getDouble("salary");

                    System.out.println(firstName + "," + lastName + "," + salary);
                }
                rs.close();
            }catch (Exception ex){
                System.out.println("SQL 실행 오류: " + ex);
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
