package com.example.jdbcexam01;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
* 서비스가 가지는 메서드는 여러개의 Dao 메서드를 실행할 수 있어야 한다.
* 한 번에 같이 실행되어야 하는 여러개의 Dao 메서드는 하나의 트랜잭션으로 묶여야 한다.
* 여러개의 메서드가 하나의 트랜잭션으로 실행되기 위해서는 같은 Connection을 사용해야 한다.
* */
public class BoardService {
    public List<Board> getBoards(int start, int size) {
        BoardDao boardDao = new BoardDao();
        List<Board> boards = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            boards = boardDao.getBoards(conn, start, size);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            DBUtil.close(conn); // SELECT 문 실행 시엔 commit, rollback 필요 없음
        }

        return boards;
    }

    public int addBoard(Board board) {
        BoardDao boardDao = new BoardDao();
        Connection conn = null;
        int updateCount = 0;

        try {
            conn = DBUtil.getConnection();
            updateCount = boardDao.addBoard(conn, board);
            conn.commit();
        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException();
        } finally {
            DBUtil.close(conn);
        }

        return updateCount;
    }

    public Board getBoard(long id) {
        BoardDao boardDao = new BoardDao();
        Connection conn = null;
        Board board = null;

        try {
            conn = DBUtil.getConnection();
            board = boardDao.getBoard(conn, id);    // 같은 Connection을 사용하기 위해 같은 conn 변수를 입력받도록 해준다.
            boardDao.updateReadCount(conn, id);   // addBoard()에서 updateCount는 리턴해주는데 왜 얘는 안하지?
            conn.commit();  // getBoard() 메서드와 updateReadCount() 메서드가 모두 제대로 실행되면 commit
        } catch (Exception ex) {
            try {
                conn.rollback();    // 두 가지 메서드 중 하나라도 제대로 실행이 안되면 rollback
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBUtil.close(conn);
        }

        return board;
    }

}
