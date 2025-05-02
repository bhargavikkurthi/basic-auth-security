CREATE TABLE account (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  acc_no DECIMAL(10, 0) NOT NULL,
  holder_name VARCHAR(100) NOT NULL,
  start_date DATETIME NOT NULL,
  branch VARCHAR(100) NOT NULL,
  balance DECIMAL(20, 0) DEFAULT 0,
  transactions JSON,
  created_by VARCHAR(50) NOT NULL,
  created_date DATETIME NOT NULL,
  modified_by VARCHAR(50),
  modified_date DATETIME
);


CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(200) NOT NULL
);

select * from user_table;
select * from account;
