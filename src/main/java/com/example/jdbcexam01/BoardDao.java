package com.example.jdbcexam01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Dao : Data Access Object의 약자로 데이터를 조회하기 위한 클래스
public class BoardDao {
    List<Board> getBoards(Connection conn, int start, int size) throws SQLException {

        List<Board> list = new ArrayList<>();

        String sql = "SELECT id, name, title, read_count, created FROM board ORDER BY created DESC LIMIT ?, ?"; // 2. 실행할 SQL문 준비
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, start );   // ?에 데이터 바인딩
        ps.setInt(2, size );   // ?에 데이터 바인딩
        // 3. SQL 실행하고 결과 가져옴
        ResultSet rs = ps.executeQuery();

        while(rs.next()) { // rs.next(): 레코드 한 줄씩 가져옴
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String title = rs.getString("title");
            long readCount = rs.getLong("read_count");
            Date created = rs.getDate("created");

            Board board = new Board(id, name, title, null, readCount, created);

            list.add(board);
        }

        DBUtil.close(rs, ps);

        return list;
    }

    // 글쓰기(INSERT): 외부로부터 name, title, content 값을 받아들여 저장한다.
    // 여기에서는 name, title, content 값이 board에 담겨져있다고 가정한다.
    public int addBoard(Connection conn, Board board) throws SQLException {
        int updateCount = 0;

        String sql = "INSERT INTO board(name, title, content) VALUES(?, ?, ?)"; // 2. 실행할 SQL문 준비
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, board.getName() );   // ?에 데이터 바인딩
        ps.setString(2, board.getTitle() );   // ?에 데이터 바인딩
        ps.setString(3, board.getContent() );   // ?에 데이터 바인딩

        updateCount = ps.executeUpdate();   // SQL 실행하고 실행된 건 수를 반환한다.
        // INSERT, UPDATE, DELETE문을 실행할 때에는 executeQuery() 메서드가 아니라 executeUpdate() 메서드를 사용한다.

        DBUtil.close(ps); // 4. SQL 실행 후 DB 연결 해제, INSERT문이므로 ps, conn 2가지만 닫아주면 된다.

        return updateCount;
    }

    // 게시물 조회
    public Board getBoard(Connection conn, long id) throws SQLException {

        Board board = null;

        String sql = "SELECT name, title, content, read_count, created FROM board WHERE id = ?"; // 2. 실행할 SQL문 준비
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);   // ?에 데이터 바인딩
        // 3. SQL 실행하고 결과 가져옴
        ResultSet rs = ps.executeQuery();

        while (rs.next()) { // rs.next(): 레코드 한 줄씩 가져옴
            String name = rs.getString("name");
            String title = rs.getString("title");
            String content = rs.getString("content");
            long readCount = rs.getLong("read_count");
            Date created = rs.getDate("created");

            board = new Board(id, name, title, content, readCount, created);

        }

        DBUtil.close(rs, ps);
        return board;
    }

    // 해당id의 조회수 증가
    public int updateReadCount(Connection conn, long id) throws SQLException {

        int updateCount = 0;

        String sql = "UPDATE board SET read_count = read_count + 1 WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        updateCount = ps.executeUpdate();

        DBUtil.close(ps);
        return updateCount;
    }
}
