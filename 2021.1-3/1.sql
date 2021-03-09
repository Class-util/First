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
 
 
 
 update book set price = 61 where name = 'java核心技术'; 
 insert into book values ('java核心技术','Cay S.Horstman',56.43,'计算机技术')
 delete from product where price > 60 or  storage < 200;
 update product set price = price + 50 where storage > 30;
 insert into product (name,price,storage,'desc') values ('学生书包',18.91,101,null)
 select * from user where (id between 1 and 200 or id between 300 and 500) and accout is not null or amount > 1000;
 select * from book where author is not null or (price > 50 and publish_data > 2019);
 select * from article where title is null or create_data > '2019.1.1';
 select * from article where create_data between '2019-01-01 10:30:00' and '2019-00-10 04:02:00';
 select name,age from student where name like '张%' and age between 18 and 25; 
 
 
 
 
 