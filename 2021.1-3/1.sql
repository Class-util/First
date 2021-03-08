DROP TABLE IF EXISTS exam_result;
CREATE TABLE exam_result (
    id INT,
    name VARCHAR(20),
    chinese DECIMAL(3,1),
    math DECIMAL(3,1),
    english DECIMAL(3,1)
);
INSERT INTO exam_result (id,name, chinese, math, english) VALUES
(1,'唐三藏', 67, 98, 56),
(2,'孙悟空', 87.5, 78, 77),
(3,'猪悟能', 88, 98, 90),
(4,'曹孟德', 82, 84, 67),
(5,'刘玄德', 55.5, 85, 45),
(6,'孙权', 70, 73, 78.5),
(7,'宋公明', 75, 65, 30);

-- 查询英语不及格的同学及英语成绩 ( < 60 )
-- 查询语文成绩好于英语成绩的同学
-- 查询总分在 200 分以下的同学
-- 查询语文成绩大于80分，且英语成绩大于80分的同学
-- 查询语文成绩在 [80, 90] 分的同学及语文成绩
-- 查询数学成绩是 58 或者 59 或者 98 或者 99 分的同学及数学成绩
-- 查询 qq_mail 已知的同学姓名

 select * from exam_result where english < 60;
 select * from exam_result where chinese > english;
 select * from exam_result where english + chinese + math < 200;
 select * from exam_result where chinese > 80 and english > 80;
 select * from exam_result where chinese between 80 and 90;
 select * from exam_result where math in (58,59,98,99);
 select name from exam_result where qq_mail is NOT NULL;