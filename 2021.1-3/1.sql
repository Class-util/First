DROP TABLE IF EXISTS exam_result;
CREATE TABLE exam_result (
    id INT,
    name VARCHAR(20),
    chinese DECIMAL(3,1),
    math DECIMAL(3,1),
    english DECIMAL(3,1)
);
INSERT INTO exam_result (id,name, chinese, math, english) VALUES
(1,'������', 67, 98, 56),
(2,'�����', 87.5, 78, 77),
(3,'������', 88, 98, 90),
(4,'���ϵ�', 82, 84, 67),
(5,'������', 55.5, 85, 45),
(6,'��Ȩ', 70, 73, 78.5),
(7,'�ι���', 75, 65, 30);

-- ��ѯӢ�ﲻ�����ͬѧ��Ӣ��ɼ� ( < 60 )
-- ��ѯ���ĳɼ�����Ӣ��ɼ���ͬѧ
-- ��ѯ�ܷ��� 200 �����µ�ͬѧ
-- ��ѯ���ĳɼ�����80�֣���Ӣ��ɼ�����80�ֵ�ͬѧ
-- ��ѯ���ĳɼ��� [80, 90] �ֵ�ͬѧ�����ĳɼ�
-- ��ѯ��ѧ�ɼ��� 58 ���� 59 ���� 98 ���� 99 �ֵ�ͬѧ����ѧ�ɼ�
-- ��ѯ qq_mail ��֪��ͬѧ����

 select * from exam_result where english < 60;
 select * from exam_result where chinese > english;
 select * from exam_result where english + chinese + math < 200;
 select * from exam_result where chinese > 80 and english > 80;
 select * from exam_result where chinese between 80 and 90;
 select * from exam_result where math in (58,59,98,99);
 select name from exam_result where qq_mail is NOT NULL;
 
 
 
 update book set price = 61 where name = 'java���ļ���'; 
 insert into book values ('java���ļ���','Cay S.Horstman',56.43,'���������')
 delete from product where price > 60 or  storage < 200;
 update product set price = price + 50 where storage > 30;
 insert into product (name,price,storage,'desc') values ('ѧ�����',18.91,101,null)
 select * from user where (id between 1 and 200 or id between 300 and 500) and accout is not null or amount > 1000;
 select * from book where author is not null or (price > 50 and publish_data > 2019);
 select * from article where title is null or create_data > '2019.1.1';
 select * from article where create_data between '2019-01-01 10:30:00' and '2019-00-10 04:02:00';
 select name,age from student where name like '��%' and age between 18 and 25; 
 
 
 
 
 