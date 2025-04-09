-- 定义图片URL变量
SET @img1 = 'https://img.shetu66.com/2023/04/25/1682391094827084.png';
SET @img2 = 'https://img.shetu66.com/2023/04/25/1682410877994833.png';
SET @img3 = 'https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/04/ChMkJlbKyFOILgqwAAU1Zymsk68AALIAgFwgVgABTV_720.jpg';
SET @img4 = 'https://img.shetu66.com/2023/04/25/1682391086456995.png';
SET @img5 = 'https://ts1.cn.mm.bing.net/th/id/R-C.26fa5434823e0afae3f9b576b61b3df0?rik=1ki5rrqJXLS00w&riu=http%3a%2f%2fpic.52112.com%2f180420%2f180420_32%2fJ9xjxe1jIg_small.jpg&ehk=a8hQQlllEncpFeXgnFZ1a7fIII7lcz2ph6WLdtzS51k%3d&risl=&pid=ImgRaw&r=0';

-- 修改表结构，将ID字段类型改为varchar(255)
ALTER TABLE tb_quotation MODIFY COLUMN id varchar(255);
ALTER TABLE tb_quotation_details MODIFY COLUMN id varchar(255);
ALTER TABLE tb_quotation_details MODIFY COLUMN quotation_id varchar(255);

-- 插入报价单信息
INSERT INTO tb_quotation (id, customer_name, customer_company, quotation_date, valid_until, currency, payment_terms, delivery_terms, sample_lead_time, mass_production_lead_time, port_of_loading, minimum_order_quantity, status, created_at, updated_at) VALUES
(202501031901001, '张三', '上海办公家具有限公司', '2025-01-01', '2025-02-01', 'USD', '30% 订金, 发货前付清', 'FOB', 15, 30, '宁波港', 100, '有效', NOW(), NOW()),
(202501031901002, '李四', '北京家具贸易有限公司', '2025-01-02', '2025-02-02', 'USD', '50% 订金, 发货前付清', 'FOB', 20, 35, '天津港', 200, '有效', NOW(), NOW()),
(202501031901003, '王五', '广州家具进出口公司', '2025-01-03', '2025-02-03', 'USD', '40% 订金, 发货前付清', 'FOB', 25, 40, '广州港', 150, '有效', NOW(), NOW()),
(202501031901004, 'John Smith', 'US Furniture Co.', '2025-01-04', '2025-02-04', 'USD', '30% 订金, 发货前付清', 'FOB', 15, 30, '深圳港', 300, '有效', NOW(), NOW()),
(202501031901005, 'Mary Johnson', 'UK Office Ltd.', '2025-01-05', '2025-02-05', 'USD', '50% 订金, 发货前付清', 'FOB', 20, 35, '上海港', 250, '有效', NOW(), NOW());

-- 插入报价单明细
INSERT INTO tb_quotation_details (id, quotation_id, product_id, unit_price, quantity, total_price, remarks) VALUES
(202501032001001, 202501031901001, 202501031001001, 150.00, 100, 15000.00, '标准配置'),
(202501032001002, 202501031901001, 202501031001002, 160.00, 50, 8000.00, '标准配置'),
(202501032001003, 202501031901002, 202501031001003, 170.00, 200, 34000.00, '豪华配置'),
(202501032001004, 202501031901002, 202501031001004, 155.00, 100, 15500.00, '标准配置'),
(202501032001005, 202501031901003, 202501031001005, 165.00, 150, 24750.00, '豪华配置'),
(202501032001006, 202501031901003, 202501031001006, 175.00, 50, 8750.00, '标准配置'),
(202501032001007, 202501031901004, 202501031001007, 180.00, 300, 54000.00, '豪华配置'),
(202501032001008, 202501031901004, 202501031001008, 158.00, 150, 23700.00, '标准配置'),
(202501032001009, 202501031901005, 202501031001009, 168.00, 250, 42000.00, '豪华配置'),
(202501032001010, 202501031901005, 202501031001010, 178.00, 100, 17800.00, '标准配置');










