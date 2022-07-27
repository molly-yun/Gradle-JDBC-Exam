package com.example.jdbcexam01;

import java.sql.Date;

// 한 건의 board 데이터를 가질 수 있는 객체
public class Board {
    private long id;
    private String name;
    private String title;
    private String content;
    private long readCount;
    private Date created;

    public Board(){}

    public Board(long id, String name, String title, String content, long readCount, Date created) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.readCount = readCount;
        this.created = created;
    }

    // 자동으로 입력되지 않는 변수들만 받는 생성자
    public Board(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getReadCount() {
        return readCount;
    }

    public void setReadCount(long readCount) {
        this.readCount = readCount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", readCount=" + readCount +
                ", created=" + created +
                '}';
    }
}
