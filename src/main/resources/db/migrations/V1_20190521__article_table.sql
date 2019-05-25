create table article (
article_id VARCHAR(255),
article_name VARCHAR(255),
article_author VARCHAR(255),
article_type VARCHAR(255),
category VARCHAR(255),
body TEXT ,
create_time DATETIME ON UPDATE CURRENT_TIMESTAMP
);
