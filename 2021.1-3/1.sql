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