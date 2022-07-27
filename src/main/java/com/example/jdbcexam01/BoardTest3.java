package com.example.jdbcexam01;

import java.util.List;

public class BoardTest3 {
    public static void main(String[] args) {
        BoardDao boardDao = new BoardDao();

        Board board = boardDao.getBoard(3);
        System.out.println(board);
    }
}
