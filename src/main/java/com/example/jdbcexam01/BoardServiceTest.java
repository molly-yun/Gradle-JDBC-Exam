package com.example.jdbcexam01;

import java.util.List;

public class BoardServiceTest {
    public static void main(String[] args) {
        try {
            BoardService boardService = new BoardService();

            // 목록 조회
            List<Board> boards = boardService.getBoards(0, 30);

            for (Board board : boards) {
                System.out.println(board);
            }

            // 글쓰기
//            Board board = new Board("둘리", "여름이다", "빙하기 그립다" );
//            int updateCount = boardService.addBoard(board);
//            System.out.println(updateCount);

            // 해당 id 글 조회
//            Board board = boardService.getBoard(5L);
//            System.out.println(board);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


}
