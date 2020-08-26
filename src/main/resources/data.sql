INSERT INTO USERS(id, email, name, password) VALUES(1, 'user@email.com', 'User 1', '12345');

INSERT INTO POSTS(id, likes, message, post_date, title, author_id) VALUES(1, 0, 'test message', '2020-08-25 18:00:00', 'title 1', 1);
INSERT INTO POST_COMMENTS(id, message, author_id, post_id) VALUES(1, 'test comment', 1, 1);

INSERT INTO VIDEOS(id, likes, title, upload_date, author_id) VALUES(1, 0, 'video title', '2020-08-25 18:00:00', 1);
INSERT INTO VIDEO_COMMENTS(id, message, author_id, video_id) VALUES(1, 'video comment test', 1, 1);