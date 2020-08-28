INSERT INTO USERS(id, email, image_url, name, password) VALUES(1, 'user@email.com', '', 'User 1', '$2a$10$2jATcEDylD/YEMRqvhZ.YudzHChCOcBXg581bTTyuMrAf6SVog32K');


INSERT INTO POSTS(id, likes, message, post_date, title, author_id) VALUES(1, 0, 'test message', '2020-08-25 18:00:00', 'title 1', 1);
INSERT INTO POST_COMMENTS(id, message, author_id, post_id) VALUES(1, 'test comment', 1, 1);

INSERT INTO VIDEOS(id, likes, thumb_url, title, upload_date, video_url, author_id) VALUES(1, 0, '', 'video title', '2020-08-25 18:00:00', '', 1);
INSERT INTO VIDEO_COMMENTS(id, message, author_id, video_id) VALUES(1, 'video comment test', 1, 1);