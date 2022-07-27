package com.example.jdbcexam01;

import java.util.List;

public class BoardTest {
    public static void main(String[] args) {
        BoardDao boardDao = new BoardDao();

        List<Board> boards = boardDao.getBoards(0, 30);
        for (Board board : boards) {
            System.out.println(board);
        }
    }
}
