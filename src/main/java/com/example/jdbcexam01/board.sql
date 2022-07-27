CREATE TABLE board(
  id INT(11) AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  read_count INT(11) NOT NULL default 0,
  created DATETIME default now(),
  CONSTRAINT board_OK PRIMARY KEY(id)
);