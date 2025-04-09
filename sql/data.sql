-- 定义图片URL变量
SET @img1 = 'https://img.shetu66.com/2023/04/25/1682391094827084.png';
SET @img2 = 'https://img.shetu66.com/2023/04/25/1682410877994833.png';
SET @img3 = 'https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/04/ChMkJlbKyFOILgqwAAU1Zymsk68AALIAgFwgVgABTV_720.jpg';
SET @img4 = 'https://img.shetu66.com/2023/04/25/1682391086456995.png';
SET @img5 = 'https://ts1.cn.mm.bing.net/th/id/R-C.26fa5434823e0afae3f9b576b61b3df0?rik=1ki5rrqJXLS00w&riu=http%3a%2f%2fpic.52112.com%2f180420%2f180420_32%2fJ9xjxe1jIg_small.jpg&ehk=a8hQQlllEncpFeXgnFZ1a7fIII7lcz2ph6WLdtzS51k%3d&risl=&pid=ImgRaw&r=0';

-- 定义图片URL变量
SET @img1 = 'https://img.shetu66.com/2023/04/25/1682391094827084.png';
SET @img2 = 'https://img.shetu66.com/2023/04/25/1682410877994833.png';
SET @img3 = 'https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/04/ChMkJlbKyFOILgqwAAU1Zymsk68AALIAgFwgVgABTV_720.jpg';
SET @img4 = 'https://img.shetu66.com/2023/04/25/1682391086456995.png';
SET @img5 = 'https://ts1.cn.mm.bing.net/th/id/R-C.26fa5434823e0afae3f9b576b61b3df0?rik=1ki5rrqJXLS00w&riu=http%3a%2f%2fpic.52112.com%2f180420%2f180420_32%2fJ9xjxe1jIg_small.jpg&ehk=a8hQQlllEncpFeXgnFZ1a7fIII7lcz2ph6WLdtzS51k%3d&risl=&pid=ImgRaw&r=0';

-- 删除已存在的表
DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_permissions;
DROP TABLE IF EXISTS tb_quotation;
DROP TABLE IF EXISTS tb_products;
DROP TABLE IF EXISTS tb_upholstery;
DROP TABLE IF EXISTS tb_carton_details;
DROP TABLE IF EXISTS tb_production_logistics;
DROP TABLE IF EXISTS tb_product_dimensions;
DROP TABLE IF EXISTS tb_seat_inner_components;
DROP TABLE IF EXISTS tb_seat_outer_components;
DROP TABLE IF EXISTS tb_back_inner_components;
DROP TABLE IF EXISTS tb_back_outer_components;
DROP TABLE IF EXISTS tb_arms;
DROP TABLE IF EXISTS tb_foam_details;
DROP TABLE IF EXISTS tb_castors;
DROP TABLE IF EXISTS tb_bases;
DROP TABLE IF EXISTS tb_gas_lift;
DROP TABLE IF EXISTS tb_gas_lift_cover;
DROP TABLE IF EXISTS tb_mechanism;
DROP TABLE IF EXISTS tb_fittings;
DROP TABLE IF EXISTS tb_product_images;
DROP TABLE IF EXISTS tb_qc_reports;
DROP TABLE IF EXISTS tb_defects;
DROP TABLE IF EXISTS tb_defect_images;

-- 创建表结构

CREATE TABLE tb_permissions (
    id CHAR(17) PRIMARY KEY,
    role_type INT NOT NULL COMMENT '角色类型：0-管理员，1-供应商，2-员工',
    
    -- 规格表权限
    prod_view INT(1) DEFAULT 0 COMMENT '规格表-查看：0-禁用，1-启用',
    prod_create INT(1) DEFAULT 0 COMMENT '规格表-创建：0-禁用，1-启用',
    prod_edit INT(1) DEFAULT 0 COMMENT '规格表-编辑：0-禁用，1-启用',
    prod_delete INT(1) DEFAULT 0 COMMENT '规格表-删除：0-禁用，1-启用',
    
    -- 质检表权限
    spec_view INT(1) DEFAULT 0 COMMENT '质检表-查看：0-禁用，1-启用',
    spec_create INT(1) DEFAULT 0 COMMENT '质检表-创建：0-禁用，1-启用',
    spec_edit INT(1) DEFAULT 0 COMMENT '质检表-编辑：0-禁用，1-启用',
    spec_delete INT(1) DEFAULT 0 COMMENT '质检表-删除：0-禁用，1-启用',
    
    -- 报价单权限
    quote_view INT(1) DEFAULT 0 COMMENT '报价单-查看：0-禁用，1-启用',
    quote_create INT(1) DEFAULT 0 COMMENT '报价单-创建：0-禁用，1-启用',
    quote_edit INT(1) DEFAULT 0 COMMENT '报价单-编辑：0-禁用，1-启用',
    quote_delete INT(1) DEFAULT 0 COMMENT '报价单-删除：0-禁用，1-启用',
    
    -- 系统设置权限
    settings_users INT(1) DEFAULT 0 COMMENT '系统设置-用户管理：0-禁用，1-启用',
    settings_permissions INT(1) DEFAULT 0 COMMENT '系统设置-权限设置：0-禁用，1-启用'
);

-- 插入三种角色的默认权限
INSERT INTO tb_permissions VALUES
-- 管理员权限（全部启用）
('20250224180000001', 0, 
 1, 1, 1, 1,  -- 规格表全部权限
 1, 1, 1, 1,  -- 质检表全部权限
 1, 1, 1, 1,  -- 报价单全部权限
 1, 1         -- 系统设置全部权限
),

-- 供应商权限
('20250224180000002', 1,
 1, 0, 0, 0,  -- 规格表只读
 1, 1, 1, 0,  -- 质检表部分权限
 1, 1, 1, 0,  -- 报价单部分权限
 0, 0         -- 无系统设置权限
),

-- 员工权限
('20250224180000003', 2,
 1, 1, 1, 0,  -- 规格表部分权限
 1, 1, 1, 0,  -- 质检表部分权限
 1, 1, 1, 0,  -- 报价单部分权限
 0, 0         -- 无系统设置权限
);

-- 用户表
CREATE TABLE tb_user (
    id VARCHAR(32) NOT NULL UNIQUE COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) COMMENT '真实姓名',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '电话',
    role_type TINYINT NOT NULL DEFAULT 2 COMMENT '角色类型：0-管理员，1-供应商，2-员工',
    status TINYINT DEFAULT 1 COMMENT '状态: 0-禁用, 1-启用',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP ,
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- 插入测试数据
INSERT INTO tb_user (id, username, password, real_name, email, phone, role_type) VALUES
-- 管理员
('a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6', 'admin', '123456', '系统管理员', 'admin@example.com', '13800138000', 0),
-- 供应商
('b1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p7', 'supplier1', '123456', '张三供应商', 'supplier1@example.com', '13800138001', 1),
('c1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p8', 'supplier2', '123456', '李四供应商', 'supplier2@example.com', '13800138002', 1),
-- 员工
('d1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p9', 'employee1', '123456', '王五员工', 'employee1@example.com', '13800138003', 2),
('e1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p0', 'employee2', '123456', '赵六员工', 'employee2@example.com', '13800138004', 2);



-- 报价单表
CREATE TABLE `tb_quotation` (
  `id` varchar(255) NOT NULL COMMENT '序列',
  `image` varchar(255) NULL COMMENT '图片',
  `supplier` varchar(255) NULL COMMENT '供应商',
  `supplier_item_code` varchar(255) NULL COMMENT '供应商项目代码',
  `specification_details` varchar(255) NULL COMMENT '规格详细信息',
  `sample_lead_time` datetime NULL COMMENT '样品交付周期',
  `overall_dimensions_width` int NOT NULL COMMENT '总体尺寸宽度',
  `overall_dimensions_depth` int NULL COMMENT '总体尺寸深度',
  `overall_dimensions_height` int NULL COMMENT '总体尺寸高度',
  `box_dimensions_width` int NULL COMMENT '箱体尺寸宽度',
  `box_dimensions_depth` int NULL COMMENT '箱体尺寸深度',
  `box_dimensions_height` int NULL COMMENT '箱体尺寸高度',
  `box_weight_net_weighth` int NULL COMMENT '箱体重量净重',
  `net_weight_gross_weight` varchar(255) NULL COMMENT '箱体重量毛重',
  `effective_vol` varchar(255) NULL COMMENT '有效体积',
  `loading_qty` int NULL COMMENT '装载量',
  `moq` varchar(255) NULL COMMENT '最小订单量',
  `fob_price` decimal(10, 2) NULL COMMENT '供货商成本价',
  `currency` int NULL DEFAULT 0 COMMENT '货币单位',
  `bifma_tested` int NULL DEFAULT 0 COMMENT '测试标准',
  `cad_block_available` int NULL DEFAULT 0 COMMENT '3D模块',
  `product_data_available` int NULL DEFAULT 0 COMMENT '产品数据',
  `product_images_available` int NULL DEFAULT 0 COMMENT '产品图片',
  `sales_contacts` varchar(255) NOT NULL COMMENT '销售',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `valid_period` varchar(255) NOT NULL COMMENT '有效期',
  `port` varchar(255) NOT NULL COMMENT '港口',
  `remark` text NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT = '报价单';

-- 产品基本信息表
CREATE TABLE tb_products (
    `id` varchar(255) NOT NULL COMMENT '序列',
    tccode VARCHAR(20) COMMENT '产品代码',
    supplier VARCHAR(50) COMMENT '供应商',
    supplier_code VARCHAR(20) COMMENT '供应商代码',
    supplier_name VARCHAR(100) COMMENT '供应商名称',
    fire_standard VARCHAR(50) COMMENT '防火标准',
    fob_20_container_price DECIMAL(10,2) COMMENT '20尺柜FOB价格',
    fob_40_container_price DECIMAL(10,2) COMMENT '40尺柜FOB价格',
    shipping_port VARCHAR(50) COMMENT '发货港口',
    PRIMARY KEY (`id`)
) COMMENT '产品基本信息表';

-- 面料信息表
CREATE TABLE tb_upholstery (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    fabric_manufacturer VARCHAR(100) COMMENT '面料制造商',
    colour_code VARCHAR(50) COMMENT '颜色代码',
    leather_grade VARCHAR(50) COMMENT '皮革等级',
    usage_per_chair DECIMAL(10,2) COMMENT '每把椅子用料(m²)',
    PRIMARY KEY (`id`)
) COMMENT '面料信息表';

-- 包装信息表
CREATE TABLE tb_carton_details (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    width INT COMMENT '包装箱宽度(mm)',
    depth INT COMMENT '包装箱深度(mm)',
    height INT COMMENT '包装箱高度(mm)',
    board_type VARCHAR(50) COMMENT '纸板类型',
    items_per_carton INT COMMENT '每箱数量',
    carton_volume DECIMAL(10,3) COMMENT '箱体体积(m³)',
    PRIMARY KEY (`id`)
) COMMENT '包装信息表';

-- 生产和物流信息表
CREATE TABLE tb_production_logistics (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    production_time INT COMMENT '生产时间(天)',
    effective_volume DECIMAL(10,3) COMMENT '有效体积(m³)',
    loading_quantity_20gp INT COMMENT '20尺标准柜装载数量',
    loading_quantity_40hc INT COMMENT '40尺高柜装载数量',
    net_weight DECIMAL(5,2) COMMENT '净重(kg)',
    gross_weight DECIMAL(5,2) COMMENT '毛重(kg)',
    PRIMARY KEY (`id`)
) COMMENT '生产和物流信息表';

-- 产品尺寸表
CREATE TABLE tb_product_dimensions (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    seat_width INT COMMENT '座位宽度(mm)',
    seat_depth INT COMMENT '座位深度(mm)',
    seat_height_min INT COMMENT '座位最小高度(mm)',
    seat_height_max INT COMMENT '座位最大高度(mm)',
    back_width INT COMMENT '靠背宽度(mm)',
    back_height INT COMMENT '靠背高度(mm)',
    back_height_from_seat INT COMMENT '靠背高度从座位(mm)',
    overall_width INT COMMENT '整体宽度(mm)',
    overall_depth INT COMMENT '整体深度(mm)',
    overall_height_min INT COMMENT '整体最小高度(mm)',
    overall_height_max INT COMMENT '整体最大高度(mm)',
    PRIMARY KEY (`id`)
) COMMENT '产品尺寸表';

-- 座椅内部结构表
CREATE TABLE tb_seat_inner_components (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    material_code VARCHAR(50) COMMENT '材料代码',
    thickness INT COMMENT '厚度(mm)',
    layers_count INT COMMENT '层数',
    dimensions VARCHAR(50) COMMENT '尺寸规格',
    PRIMARY KEY (`id`)
) COMMENT '座椅内部结构表';

-- 背部内部结构表
CREATE TABLE tb_back_inner_components (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    material_code VARCHAR(50) COMMENT '材料代码',
    thickness INT COMMENT '厚度(mm)',
    layers_count INT COMMENT '层数',
    dimensions VARCHAR(50) COMMENT '尺寸规格',
    PRIMARY KEY (`id`)
) COMMENT '背部内部结构表';

-- 座椅外部结构表
CREATE TABLE tb_seat_outer_components (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    material VARCHAR(100) COMMENT '材料',
    dimensions VARCHAR(100) COMMENT '尺寸(宽x深/宽x高)',
    manufacturer_name VARCHAR(100) COMMENT '制造商名称',
    PRIMARY KEY (`id`)
) COMMENT '座椅外部结构表';

-- 背部外部结构表
CREATE TABLE tb_back_outer_components (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    material VARCHAR(100) COMMENT '材料',
    dimensions VARCHAR(100) COMMENT '尺寸(宽x深/宽x高)',
    manufacturer_name VARCHAR(100) COMMENT '制造商名称',
    PRIMARY KEY (`id`)
) COMMENT '背部外部结构表';

-- 扶手信息表
CREATE TABLE tb_arms (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    material VARCHAR(100) COMMENT '材料',
    type VARCHAR(50) COMMENT '类型',
    manufacturer VARCHAR(100) COMMENT '制造商',
    description TEXT COMMENT '描述',
    arm_height_from_seat INT COMMENT '扶手距座面高度(mm)',
    arm_height_from_floor INT COMMENT '扶手距地面高度(mm)',
    PRIMARY KEY (`id`)
) COMMENT '扶手信息表';

-- 泡棉信息表
CREATE TABLE tb_foam_details (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    seat_density INT COMMENT '座椅密度(kg/m³)',
    back_density INT COMMENT '靠背密度(kg/m³)',
    seat_thickness INT COMMENT '座椅厚度(mm)',
    back_thickness INT COMMENT '靠背厚度(mm)',
    PRIMARY KEY (`id`)
) COMMENT '泡棉信息表';

-- 脚轮信息表
CREATE TABLE tb_castors (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    pin_thickness INT COMMENT '销轴直径(mm)',
    wheel_diameter INT COMMENT '轮子直径(mm)',
    PRIMARY KEY (`id`)
) COMMENT '脚轮信息表';

-- 底座信息表
CREATE TABLE tb_bases (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    size_diameter INT COMMENT '底盘直径(mm)',
    material VARCHAR(50) COMMENT '材料',
    type VARCHAR(50) COMMENT '类型',
    PRIMARY KEY (`id`)
) COMMENT '底座信息表';

-- 气压棒信息表
CREATE TABLE tb_gas_lift (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    gas_lift_class VARCHAR(20) COMMENT '气压等级',
    casing_length INT COMMENT '外管长度(mm)',
    extension_size INT COMMENT '行程(mm)',
    taper INT COMMENT '锥度(mm)',
    PRIMARY KEY (`id`)
) COMMENT '气压棒信息表';

-- 气压罩信息表
CREATE TABLE tb_gas_lift_cover (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    material VARCHAR(50) COMMENT '材料',
    colour VARCHAR(50) COMMENT '颜色',
    PRIMARY KEY (`id`)
) COMMENT '气压罩信息表';

-- 机构信息表
CREATE TABLE tb_mechanism (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    levers_count INT COMMENT '手柄数量',
    locking_positions VARCHAR(50) COMMENT '锁定位置',
    model_no VARCHAR(50) COMMENT '型号',
    supplier_name VARCHAR(100) COMMENT '机构供应商',
    PRIMARY KEY (`id`)
) COMMENT '机构信息表';

-- 配件信息表
CREATE TABLE tb_fittings (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `product_id` varchar(255) NOT NULL COMMENT '产品ID',
    fitting_number INT COMMENT '配件编号',
    description VARCHAR(100) COMMENT '描述',
    quantity INT COMMENT '数量',
    material VARCHAR(50) COMMENT '材料',
    PRIMARY KEY (`id`)
) COMMENT '配件信息表';

-- 规格图片表
CREATE TABLE `tb_product_images`  (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `prod_id` varchar(255) NOT NULL COMMENT '规格表ID',
    `front_img_path` varchar(255) NULL COMMENT '正视图路径',
    `side_img_path` varchar(255) NULL COMMENT '侧视图路径',
    `back_img_path` varchar(255) NULL COMMENT '背视图路径',
    `angle_img_path` varchar(255) NULL COMMENT '角视图路径',
    PRIMARY KEY (`id`)
) COMMENT = '规格图片表';

-- 质检报告基本信息表
CREATE TABLE tb_qc_reports (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `model_code` VARCHAR(50) COMMENT '型号代码',
    `factory_code` VARCHAR(50) COMMENT '工厂代码',
    `supplier` VARCHAR(50) COMMENT '供应商',
    `client` VARCHAR(50) COMMENT '客户',
    `po_number` VARCHAR(50) COMMENT 'PO编号',
    `inspection_date` DATE COMMENT '检验日期',
    `order_qty` INT COMMENT '订单数量',
    `report_date` DATE COMMENT '报告日期',
    `inspect_qty` INT COMMENT '检验数量',
    `qc_officer` VARCHAR(50) COMMENT '质检员',
    `pass_fail` ENUM('Pass', 'Fail') COMMENT '通过/失败',
    `second_qc_date` DATE COMMENT '二次质检日期',
    `comments` TEXT COMMENT '评价内容',
    -- 产品外观图片
    `stocks_in_warehouse` VARCHAR(255) COMMENT '仓库库存图片',
    `sampling_of_products_quantity` VARCHAR(255) COMMENT '产品抽样数量图片',
    `shipping_marks` VARCHAR(255) COMMENT '运输标记图片',
    `barcode` VARCHAR(255) COMMENT '条形码图片',
    `packing_outside` VARCHAR(255) COMMENT '外包装图片',
    `packing_inside` VARCHAR(255) COMMENT '内包装图片',
    -- 椅子组件图片
    `chair_components_packed` VARCHAR(255) COMMENT '椅子组件-已包装图片',
    `chair_components_unpacked` VARCHAR(255) COMMENT '椅子组件-未包装图片',
    -- 配件包图片
    `fitting_pack_packed` VARCHAR(255) COMMENT '配件包-已包装图片',
    `fitting_pack_unpacked` VARCHAR(255) COMMENT '配件包-未包装图片',
    -- 标签和说明图片
    `production_label` VARCHAR(255) COMMENT '生产标签图片',
    `assembly_instructions` VARCHAR(255) COMMENT '组装说明图片',
    -- 组件图片
    `image_of_components_seat` VARCHAR(255) COMMENT '组件图片-座椅',
    `image_of_components_back` VARCHAR(255) COMMENT '组件图片-靠背',
    `image_of_components_base` VARCHAR(255) COMMENT '组件图片-底座',
    `image_of_components_castors` VARCHAR(255) COMMENT '组件图片-脚轮',
    `image_of_components_gas_lift_cover` VARCHAR(255) COMMENT '组件图片-气压棒外罩',
    `image_of_components_gas_lift_stamp` VARCHAR(255) COMMENT '组件图片-气压棒标记',
    `image_of_components_armrest` VARCHAR(255) COMMENT '组件图片-扶手',
    `image_of_component_mechanism` VARCHAR(255) COMMENT '组件图片-机构',
    `image_of_components_headrest` VARCHAR(255) COMMENT '组件图片-头枕',
    -- 成品图片
    `image_of_product_built_front` VARCHAR(255) COMMENT '成品图片-正视图',
    `image_of_product_built_side` VARCHAR(255) COMMENT '成品图片-侧视图',
    `image_of_product_built_back` VARCHAR(255) COMMENT '成品图片-背视图',
    `image_of_product_built_45_degree` VARCHAR(255) COMMENT '成品图片-45度视图',
    `front_image_of_product_built_compare_1` VARCHAR(255) COMMENT '成品图片-样品对比图1',
    `front_image_of_product_built_compare_2` VARCHAR(255) COMMENT '成品图片-样品对比图2',
    -- 功能检查图片
    `function_check_seat_height_extension` VARCHAR(255) COMMENT '功能检查-座椅高度调节',
    `function_check_mechanism_adjustment` VARCHAR(255) COMMENT '功能检查-机构调节',
    `function_check_armrest_adjustment` VARCHAR(255) COMMENT '功能检查-扶手调节',
    `function_check_headrest_adjustment` VARCHAR(255) COMMENT '功能检查-头枕调节',
    `function_check_other1` VARCHAR(255) COMMENT '功能检查-其他1',
    `function_check_other2` VARCHAR(255) COMMENT '功能检查-其他2',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT '质检报告基本信息表';

-- 缺陷记录表
CREATE TABLE tb_defects (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `report_id` varchar(255) NOT NULL COMMENT '质检报告ID',
    defect_title VARCHAR(100) COMMENT '缺陷标题',
    defect_description TEXT COMMENT '缺陷描述',
    improvement_suggestion TEXT COMMENT '改进建议',
    inspector VARCHAR(50) COMMENT '检查人员',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT '缺陷记录表';

-- 缺陷图片关联表
CREATE TABLE tb_defect_images (
    `id` varchar(255) NOT NULL COMMENT '序列',
    `defect_id` varchar(255) NOT NULL COMMENT '缺陷ID',
    image_path VARCHAR(255) COMMENT '图片路径',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT '缺陷图片关联表';

-- 插入报价单测试数据
INSERT INTO tb_quotation (id, image, supplier, supplier_item_code, specification_details, sample_lead_time, 
overall_dimensions_width, overall_dimensions_depth, overall_dimensions_height, box_dimensions_width, 
box_dimensions_depth, box_dimensions_height, box_weight_net_weighth, net_weight_gross_weight, effective_vol, 
loading_qty, moq, fob_price, currency, bifma_tested, cad_block_available, product_data_available, 
product_images_available, sales_contacts, create_time, valid_period, port, remark) VALUES 
(202501011201001, @img1, '供应商A', 'SA001', '人体工学办公椅-基础款', '2025-02-01 10:00:00', 650, 600, 1200, 680, 630, 1250, 15, '18kg', '0.45m³', 200, '100把/月', 99.99, 0, 1, 1, 1, 1, '张三', '2025-01-01 12:01:01', '30天', '宁波港', '标准配置'),
(202501011201002, @img2, '供应商A', 'SA002', '人体工学办公椅-豪华版', '2025-02-02 10:00:00', 660, 610, 1220, 690, 640, 1270, 16, '19kg', '0.47m³', 180, '100把/月', 129.99, 0, 1, 1, 1, 1, '张三', '2025-01-01 12:01:02', '30天', '宁波港', '带头枕'),
(202501011201003, @img3, '供应商B', 'SB001', '电竞椅-基础款', '2025-02-03 10:00:00', 670, 620, 1240, 700, 650, 1290, 17, '20kg', '0.49m³', 160, '80把/月', 149.99, 0, 1, 1, 1, 1, '李四', '2025-01-01 12:01:03', '30天', '上海港', '赛车款式'),
(202501011201004, @img4, '供应商B', 'SB002', '电竞椅-豪华版', '2025-02-04 10:00:00', 680, 630, 1260, 710, 660, 1310, 18, '21kg', '0.51m³', 150, '80把/月', 169.99, 0, 1, 1, 1, 1, '李四', '2025-01-01 12:01:04', '30天', '上海港', '真皮材质'),
(202501011201005, @img5, '供应商C', 'SC001', '老板椅-经典款', '2025-02-05 10:00:00', 690, 640, 1280, 720, 670, 1330, 19, '22kg', '0.53m³', 140, '60把/月', 189.99, 0, 1, 1, 1, 1, '王五', '2025-01-01 12:01:05', '30天', '广州港', '高端商务'),
(202501011201006, @img1, '供应商C', 'SC002', '老板椅-豪华版', '2025-02-06 10:00:00', 700, 650, 1300, 730, 680, 1350, 20, '23kg', '0.55m³', 130, '60把/月', 209.99, 0, 1, 1, 1, 1, '王五', '2025-01-01 12:01:06', '30天', '广州港', '真皮座椅'),
(202501011201007, @img2, '供应商D', 'SD001', '会议椅-标准款', '2025-02-07 10:00:00', 640, 590, 1180, 670, 620, 1230, 14, '17kg', '0.43m³', 220, '120把/月', 79.99, 0, 1, 1, 1, 1, '赵六', '2025-01-01 12:01:07', '30天', '深圳港', '可叠放'),
(202501011201008, @img3, '供应商D', 'SD002', '会议椅-豪华款', '2025-02-08 10:00:00', 645, 595, 1190, 675, 625, 1240, 15, '18kg', '0.44m³', 210, '120把/月', 89.99, 0, 1, 1, 1, 1, '赵六', '2025-01-01 12:01:08', '30天', '深圳港', '带扶手'),
(202501011201009, @img4, '供应商E', 'SE001', '休闲椅-简约款', '2025-02-09 10:00:00', 630, 580, 1170, 660, 610, 1220, 13, '16kg', '0.42m³', 230, '150把/月', 69.99, 0, 1, 1, 1, 1, '孙七', '2025-01-01 12:01:09', '30天', '厦门港', '现代风格'),
(202501011201010, @img5, '供应商E', 'SE002', '休闲椅-艺术款', '2025-02-10 10:00:00', 635, 585, 1175, 665, 615, 1225, 14, '17kg', '0.43m³', 220, '150把/月', 79.99, 0, 1, 1, 1, 1, '孙七', '2025-01-01 12:01:10', '30天', '厦门港', '北欧风格'),
(202501011201011, @img1, '供应商F', 'SF001', '工作椅-基础款', '2025-02-11 10:00:00', 640, 590, 1180, 670, 620, 1230, 14, '17kg', '0.43m³', 220, '100把/月', 89.99, 0, 1, 1, 1, 1, '周八', '2025-01-01 12:01:11', '30天', '青岛港', '网布材质'),
(202501011201012, @img2, '供应商F', 'SF002', '工作椅-升级款', '2025-02-12 10:00:00', 645, 595, 1185, 675, 625, 1235, 15, '18kg', '0.44m³', 210, '100把/月', 99.99, 0, 1, 1, 1, 1, '周八', '2025-01-01 12:01:12', '30天', '青岛港', '加厚坐垫'),
(202501011201013, @img3, '供应商G', 'SG001', '电竞椅-专业版', '2025-02-13 10:00:00', 680, 630, 1260, 710, 660, 1310, 18, '21kg', '0.51m³', 150, '80把/月', 179.99, 0, 1, 1, 1, 1, '吴九', '2025-01-01 12:01:13', '30天', '天津港', '电竞专用'),
(202501011201014, @img4, '供应商G', 'SG002', '电竞椅-至尊版', '2025-02-14 10:00:00', 685, 635, 1265, 715, 665, 1315, 19, '22kg', '0.52m³', 145, '80把/月', 199.99, 0, 1, 1, 1, 1, '吴九', '2025-01-01 12:01:14', '30天', '天津港', '可躺式'),
(202501011201015, @img5, '供应商H', 'SH001', '人体工学椅-精英版', '2025-02-15 10:00:00', 655, 605, 1205, 685, 635, 1255, 16, '19kg', '0.46m³', 190, '90把/月', 159.99, 0, 1, 1, 1, 1, '郑十', '2025-01-01 12:01:15', '30天', '大连港', '高端定制'),
(202501011201016, @img1, '供应商H', 'SH002', '人体工学椅-尊享版', '2025-02-16 10:00:00', 660, 610, 1210, 690, 640, 1260, 17, '20kg', '0.47m³', 180, '90把/月', 179.99, 0, 1, 1, 1, 1, '郑十', '2025-01-01 12:01:16', '30天', '大连港', '智能调节'),
(202501011201017, @img2, '供应商I', 'SI001', '办公椅-经济款', '2025-02-17 10:00:00', 630, 580, 1170, 660, 610, 1220, 13, '16kg', '0.42m³', 230, '150把/月', 59.99, 0, 1, 1, 1, 1, '陈一', '2025-01-01 12:01:17', '30天', '福州港', '性价比高'),
(202501011201018, @img3, '供应商I', 'SI002', '办公椅-舒适款', '2025-02-18 10:00:00', 635, 585, 1175, 665, 615, 1225, 14, '17kg', '0.43m³', 220, '150把/月', 69.99, 0, 1, 1, 1, 1, '陈一', '2025-01-01 12:01:18', '30天', '福州港', '加厚海绵'),
(202501011201019, @img4, '供应商J', 'SJ001', '会议椅-简约款', '2025-02-19 10:00:00', 625, 575, 1165, 655, 605, 1215, 12, '15kg', '0.41m³', 240, '200把/月', 49.99, 0, 1, 1, 1, 1, '林二', '2025-01-01 12:01:19', '30天', '厦门港', '可折叠'),
(202501011201020, @img5, '供应商J', 'SJ002', '会议椅-商务款', '2025-02-20 10:00:00', 630, 580, 1170, 660, 610, 1220, 13, '16kg', '0.42m³', 230, '200把/月', 59.99, 0, 1, 1, 1, 1, '林二', '2025-01-01 12:01:20', '30天', '厦门港', '带写字板'),
(202501011201021, @img1, '供应商K', 'SK001', '老板椅-总裁版', '2025-02-21 10:00:00', 695, 645, 1285, 725, 675, 1335, 20, '23kg', '0.54m³', 135, '50把/月', 299.99, 0, 1, 1, 1, 1, '黄三', '2025-01-01 12:01:21', '30天', '广州港', '真皮定制'),
(202501011201022, @img2, '供应商K', 'SK002', '老板椅-董事版', '2025-02-22 10:00:00', 700, 650, 1290, 730, 680, 1340, 21, '24kg', '0.55m³', 130, '50把/月', 399.99, 0, 1, 1, 1, 1, '黄三', '2025-01-01 12:01:22', '30天', '广州港', '头层牛皮'),
(202501011201023, @img3, '供应商L', 'SL001', '休闲椅-布艺款', '2025-02-23 10:00:00', 640, 590, 1180, 670, 620, 1230, 14, '17kg', '0.43m³', 220, '100把/月', 89.99, 0, 1, 1, 1, 1, '赵四', '2025-01-01 12:01:23', '30天', '深圳港', '可拆洗'),
(202501011201024, @img4, '供应商L', 'SL002', '休闲椅-皮革款', '2025-02-24 10:00:00', 645, 595, 1185, 675, 625, 1235, 15, '18kg', '0.44m³', 210, '100把/月', 109.99, 0, 1, 1, 1, 1, '赵四', '2025-01-01 12:01:24', '30天', '深圳港', '防水处理'),
(202501011201025, @img5, '供应商M', 'SM001', '工作椅-人体工学款', '2025-02-25 10:00:00', 650, 600, 1200, 680, 630, 1250, 15, '18kg', '0.45m³', 200, '120把/月', 129.99, 0, 1, 1, 1, 1, '钱五', '2025-01-01 12:01:25', '30天', '宁波港', '腰部支撑'),
(202501011201026, @img1, '供应商M', 'SM002', '工作椅-智能款', '2025-02-26 10:00:00', 655, 605, 1205, 685, 635, 1255, 16, '19kg', '0.46m³', 190, '120把/月', 149.99, 0, 1, 1, 1, 1, '钱五', '2025-01-01 12:01:26', '30天', '宁波港', '智能调节'),
(202501011201027, @img2, '供应商N', 'SN001', '电竞椅-入门版', '2025-02-27 10:00:00', 675, 625, 1255, 705, 655, 1305, 17, '20kg', '0.50m³', 155, '80把/月', 159.99, 0, 1, 1, 1, 1, '孙六', '2025-01-01 12:01:27', '30天', '上海港', '基础功能'),
(202501011201028, @img3, '供应商N', 'SN002', '电竞椅-专业版', '2025-02-28 10:00:00', 680, 630, 1260, 710, 660, 1310, 18, '21kg', '0.51m³', 150, '80把/月', 179.99, 0, 1, 1, 1, 1, '孙六', '2025-01-01 12:01:28', '30天', '上海港', '4D扶手'),
(202501011201029, @img4, '供应商O', 'SO001', '会议椅-培训款', '2025-02-28 10:00:00', 620, 570, 1160, 650, 600, 1210, 12, '15kg', '0.40m³', 250, '200把/月', 45.99, 0, 1, 1, 1, 1, '周七', '2025-01-01 12:01:29', '30天', '天津港', '可叠放'),
(202501011201030, @img5, '供应商O', 'SO002', '会议椅-会客款', '2025-03-01 10:00:00', 625, 575, 1165, 655, 605, 1215, 13, '16kg', '0.41m³', 240, '200把/月', 55.99, 0, 1, 1, 1, 1, '周七', '2025-01-01 12:01:30', '30天', '天津港', '带扶手'),
(202501011029, @img4, '供应商Y', 'SUP-Y-001', '标准办公椅-Y型号', '2025-02-28 10:00:00',
    650, 580, 1150, 680, 620, 1200, 15, '15/17', '0.45',
    200, '100', 180.00, 1, 1, 1, 1, 1,
    '销售Y', NOW(), '60天', '宁波港', '备注Y');

-- 插入质检报告测试数据
INSERT INTO tb_qc_reports (
    id, model_code, factory_code, supplier, client, po_number, inspection_date, order_qty, 
    report_date, inspect_qty, qc_officer, pass_fail, second_qc_date, comments,
    stocks_in_warehouse, sampling_of_products_quantity, shipping_marks, barcode,
    packing_outside, packing_inside, chair_components_packed, chair_components_unpacked,
    fitting_pack_packed, fitting_pack_unpacked, production_label, assembly_instructions,
    image_of_components_seat, image_of_components_back, image_of_components_base,
    image_of_components_castors, image_of_components_gas_lift_cover, image_of_components_gas_lift_stamp,
    image_of_components_armrest, image_of_component_mechanism, image_of_components_headrest,
    image_of_product_built_front, image_of_product_built_side, image_of_product_built_back,
    image_of_product_built_45_degree, front_image_of_product_built_compare_1,
    front_image_of_product_built_compare_2, function_check_seat_height_extension,
    function_check_mechanism_adjustment, function_check_armrest_adjustment,
    function_check_headrest_adjustment, function_check_other1, function_check_other2,
    created_at, updated_at
) VALUES
(202501021401001, 'MC001', 'FC001', '供应商A', '客户A', 'PO001', '2025-01-02', 1000, '2025-01-02', 100, '张三', 'Pass', NULL, '产品质量良好，符合标准',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:01:01', '2025-01-02 14:01:01'),

(202501021401002, 'MC002', 'FC002', '供应商B', '客户B', 'PO002', '2025-01-02', 800, '2025-01-02', 80, '李四', 'Pass', NULL, '产品外观完好，功能正常',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:02:01', '2025-01-02 14:02:01'),

(202501021401003, 'MC003', 'FC003', '供应商C', '客户C', 'PO003', '2025-01-02', 1200, '2025-01-02', 120, '王五', 'Fail', '2025-01-03', '气压棒存在问题，需要返工',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:03:01', '2025-01-02 14:03:01'),

(202501021401004, 'MC004', 'FC004', '供应商D', '客户D', 'PO004', '2025-01-02', 500, '2025-01-02', 50, '赵六', 'Pass', NULL, '符合客户要求规格',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:04:01', '2025-01-02 14:04:01'),

(202501021401005, 'MC005', 'FC005', '供应商E', '客户E', 'PO005', '2025-01-02', 1500, '2025-01-02', 150, '钱七', 'Pass', NULL, '包装完整，标识清晰',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:05:01', '2025-01-02 14:05:01'),

(202501021401006, 'MC006', 'FC006', '供应商F', '客户F', 'PO006', '2025-01-02', 2000, '2025-01-02', 200, '孙八', 'Fail', '2025-01-03', '座椅海绵硬度不达标',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:06:01', '2025-01-02 14:06:01'),

(202501021401007, 'MC007', 'FC007', '供应商G', '客户G', 'PO007', '2025-01-02', 900, '2025-01-02', 90, '周九', 'Pass', NULL, '产品尺寸符合要求',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:07:01', '2025-01-02 14:07:01'),

(202501021401008, 'MC008', 'FC008', '供应商H', '客户H', 'PO008', '2025-01-02', 700, '2025-01-02', 70, '吴十', 'Pass', NULL, '功能测试全部通过',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:08:01', '2025-01-02 14:08:01'),

(202501021401009, 'MC009', 'FC009', '供应商I', '客户I', 'PO009', '2025-01-02', 1100, '2025-01-02', 110, '郑十一', 'Pass', NULL, '材质符合环保标准',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:09:01', '2025-01-02 14:09:01'),

(202501021401010, 'MC010', 'FC010', '供应商J', '客户J', 'PO010', '2025-01-02', 600, '2025-01-02', 60, '王十二', 'Fail', '2025-01-03', '扶手连接处松动',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:10:01', '2025-01-02 14:10:01'),

(202501021401011, 'MC011', 'FC011', '供应商K', '客户K', 'PO011', '2025-01-02', 1300, '2025-01-02', 130, '李十三', 'Pass', NULL, '产品重量符合规格',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:11:01', '2025-01-02 14:11:01'),

(202501021401012, 'MC012', 'FC012', '供应商L', '客户L', 'PO012', '2025-01-02', 950, '2025-01-02', 95, '张十四', 'Pass', NULL, '组装说明书完整清晰',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:12:01', '2025-01-02 14:12:01'),

(202501021401013, 'MC013', 'FC013', '供应商M', '客户M', 'PO013', '2025-01-02', 850, '2025-01-02', 85, '赵十五', 'Pass', NULL, '包装防护良好',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:13:01', '2025-01-02 14:13:01'),

(202501021401014, 'MC014', 'FC014', '供应商N', '客户N', 'PO014', '2025-01-02', 750, '2025-01-02', 75, '钱十六', 'Fail', '2025-01-03', '座椅靠背角度调节异常',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:14:01', '2025-01-02 14:14:01'),

(202501021401015, 'MC015', 'FC015', '供应商O', '客户O', 'PO015', '2025-01-02', 1400, '2025-01-02', 140, '孙十七', 'Pass', NULL, '所有配件数量完整',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:15:01', '2025-01-02 14:15:01'),

(202501021401016, 'MC016', 'FC016', '供应商P', '客户P', 'PO016', '2025-01-02', 650, '2025-01-02', 65, '周十八', 'Pass', NULL, '产品表面处理良好',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:16:01', '2025-01-02 14:16:01'),

(202501021401017, 'MC017', 'FC017', '供应商Q', '客户Q', 'PO017', '2025-01-02', 1600, '2025-01-02', 160, '吴十九', 'Pass', NULL, '气压棒稳定性良好',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:17:01', '2025-01-02 14:17:01'),

(202501021401018, 'MC018', 'FC018', '供应商R', '客户R', 'PO018', '2025-01-02', 550, '2025-01-02', 55, '郑二十', 'Fail', '2025-01-03', '座椅面料有污渍',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:18:01', '2025-01-02 14:18:01'),

(202501021401019, 'MC019', 'FC019', '供应商S', '客户S', 'PO019', '2025-01-02', 1700, '2025-01-02', 170, '王二一', 'Pass', NULL, '扶手调节功能正常',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:19:01', '2025-01-02 14:19:01'),

(202501021401020, 'MC020', 'FC020', '供应商T', '客户T', 'PO020', '2025-01-02', 450, '2025-01-02', 45, '李二二', 'Pass', NULL, '滑轮转动顺畅',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:20:01', '2025-01-02 14:20:01'),

(202501021401021, 'MC021', 'FC021', '供应商U', '客户U', 'PO021', '2025-01-02', 1800, '2025-01-02', 180, '张二三', 'Pass', NULL, '靠背倾斜角度适中',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:21:01', '2025-01-02 14:21:01'),

(202501021401022, 'MC022', 'FC022', '供应商V', '客户V', 'PO022', '2025-01-02', 350, '2025-01-02', 35, '赵二四', 'Fail', '2025-01-03', '包装破损需要重新包装',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:22:01', '2025-01-02 14:22:01'),

(202501021401023, 'MC023', 'FC023', '供应商W', '客户W', 'PO023', '2025-01-02', 1900, '2025-01-02', 190, '钱二五', 'Pass', NULL, '头枕调节灵活',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:23:01', '2025-01-02 14:23:01'),

(202501021401024, 'MC024', 'FC024', '供应商X', '客户X', 'PO024', '2025-01-02', 250, '2025-01-02', 25, '孙二六', 'Pass', NULL, '扶手高度调节正常',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:24:01', '2025-01-02 14:24:01'),

(202501021401025, 'MC025', 'FC025', '供应商Y', '客户Y', 'PO025', '2025-01-02', 2000, '2025-01-02', 200, '周二七', 'Pass', NULL, '座椅高度调节正常',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:25:01', '2025-01-02 14:25:01'),

(202501021401026, 'MC026', 'FC026', '供应商Z', '客户Z', 'PO026', '2025-01-02', 150, '2025-01-02', 15, '吴二八', 'Fail', '2025-01-03', '气压棒漏气',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:26:01', '2025-01-02 14:26:01'),

(202501021401027, 'MC027', 'FC027', '供应商AA', '客户AA', 'PO027', '2025-01-02', 2100, '2025-01-02', 210, '郑二九', 'Pass', NULL, '产品整体稳定性好',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:27:01', '2025-01-02 14:27:01'),

(202501021401028, 'MC028', 'FC028', '供应商BB', '客户BB', 'PO028', '2025-01-02', 50, '2025-01-02', 5, '王三十', 'Pass', NULL, '所有功能正常',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:28:01', '2025-01-02 14:28:01'),

(202501021401029, 'MC029', 'FC029', '供应商CC', '客户CC', 'PO029', '2025-01-02', 2200, '2025-01-02', 220, '李三一', 'Pass', NULL, '符合人体工程学要求',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:29:01', '2025-01-02 14:29:01'),

(202501021401030, 'MC030', 'FC030', '供应商DD', '客户DD', 'PO030', '2025-01-02', 2300, '2025-01-02', 230, '张三二', 'Pass', NULL, '产品整体质量优良',
@img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3, @img4, @img5, @img1, @img2, @img3,
'2025-01-02 14:30:01', '2025-01-02 14:30:01'); 

-- 插入缺陷记录测试数据
INSERT INTO tb_defects (id, report_id, defect_title, defect_description, improvement_suggestion, inspector, created_at, updated_at) VALUES
(202501021402001, 202501021401002, '包装破损', '外箱多处破损，可能影响产品保护', '建议使用更结实的包装材料', '李四', '2025-01-02 14:02:01', '2025-01-02 14:02:01'),
(202501021402002, 202501021401002, '尺寸偏差', '座椅高度超出标准范围', '调整生产设备参数', '李四', '2025-01-02 14:02:02', '2025-01-02 14:02:02'),
(202501021402003, 202501021401004, '螺丝松动', '扶手连接处螺丝容易松动', '更换防松螺丝，加强扭矩控制', '赵六', '2025-01-02 14:02:03', '2025-01-02 14:02:03'),
(202501021402004, 202501021401006, '气压棒不稳', '座椅升降不稳定', '更换优质气压棒', '周八', '2025-01-02 14:02:04', '2025-01-02 14:02:04'),
(202501021402005, 202501021401007, '布料瑕疵', '座椅表面有多处织物瑕疵', '提高面料质检标准', '吴九', '2025-01-02 14:02:05', '2025-01-02 14:02:05'),
(202501021402006, 202501021401007, '缝线不齐', '靠背处缝线歪斜', '加强车缝工艺培训', '吴九', '2025-01-02 14:02:06', '2025-01-02 14:02:06'),
(202501021402007, 202501021401008, '色差问题', '批次间颜色差异明显', '严格控制染色工艺', '郑十', '2025-01-02 14:02:07', '2025-01-02 14:02:07'),
(202501021402008, 202501021401009, '尺寸问题', '座椅深度不达标', '修正模具尺寸', '陈一', '2025-01-02 14:02:08', '2025-01-02 14:02:08'),
(202501021402009, 202501021401009, '重量偏差', '产品重量超标', '优化材料使用', '陈一', '2025-01-02 14:02:09', '2025-01-02 14:02:09'),
(202501021402010, 202501021401010, '表面划痕', '扶手表面有划痕', '改进包装防护措施', '林二', '2025-01-02 14:02:10', '2025-01-02 14:02:10'),
(202501021402011, 202501021401010, '组装困难', '部件配合度不够', '提高零件精度', '林二', '2025-01-02 14:02:11', '2025-01-02 14:02:11'),
(202501021402012, 202501021401010, '标签歪斜', '产品标签贴附歪斜', '改进贴标工艺', '林二', '2025-01-02 14:02:12', '2025-01-02 14:02:12'),
(202501021402013, 202501021401028, '包装破损', '外箱多处破损，影响产品保护', '加强包装材料强度', '李四', '2025-01-02 14:02:13', '2025-01-02 14:02:13'),
(202501021402014, 202501021401028, '螺丝松动', '扶手连接处螺丝不稳固', '使用防松螺丝', '李四', '2025-01-02 14:02:14', '2025-01-02 14:02:14'),
(202501021402015, 202501021401028, '气压棒问题', '升降不稳定', '更换高品质气压棒', '李四', '2025-01-02 14:02:15', '2025-01-02 14:02:15'),
(202501021402016, 202501021401030, '面料瑕疵', '座椅表面有污渍', '提高面料质检标准', '赵六', '2025-01-02 14:02:16', '2025-01-02 14:02:16'),
(202501021402017, 202501021401030, '尺寸偏差', '座椅高度超出标准', '调整生产参数', '赵六', '2025-01-02 14:02:17', '2025-01-02 14:02:17'),
(202501021402018, 202501021401030, '组装问题', '部件配合不当', '优化组装工艺', '赵六', '2025-01-02 14:02:18', '2025-01-02 14:02:18');

-- 插入缺陷图片关联测试数据
INSERT INTO tb_defect_images (id, defect_id, image_path, created_at, updated_at) VALUES
(202501021403001, 202501021402001, @img1, '2025-01-02 14:03:01', '2025-01-02 14:03:01'),
(202501021403002, 202501021402001, @img2, '2025-01-02 14:03:02', '2025-01-02 14:03:02'),
(202501021403003, 202501021402002, @img3, '2025-01-02 14:03:03', '2025-01-02 14:03:03'),
(202501021403004, 202501021402003, @img4, '2025-01-02 14:03:04', '2025-01-02 14:03:04'),
(202501021403005, 202501021402004, @img5, '2025-01-02 14:03:05', '2025-01-02 14:03:05'),
(202501021403006, 202501021402005, @img1, '2025-01-02 14:03:06', '2025-01-02 14:03:06'),
(202501021403007, 202501021402006, @img2, '2025-01-02 14:03:07', '2025-01-02 14:03:07'),
(202501021403008, 202501021402007, @img3, '2025-01-02 14:03:08', '2025-01-02 14:03:08'),
(202501021403009, 202501021402008, @img4, '2025-01-02 14:03:09', '2025-01-02 14:03:09'),
(202501021403010, 202501021402009, @img5, '2025-01-02 14:03:10', '2025-01-02 14:03:10'),
(202501021403011, 202501021402010, @img1, '2025-01-02 14:03:11', '2025-01-02 14:03:11'),
(202501021403012, 202501021402011, @img2, '2025-01-02 14:03:12', '2025-01-02 14:03:12'),
(202501021403013, 202501021402012, @img3, '2025-01-02 14:03:13', '2025-01-02 14:03:13'),
(202501021403014, 202501021402013, @img1, '2025-01-02 14:03:14', '2025-01-02 14:03:14'),
(202501021403015, 202501021402013, @img2, '2025-01-02 14:03:15', '2025-01-02 14:03:15'),
(202501021403016, 202501021402014, @img3, '2025-01-02 14:03:16', '2025-01-02 14:03:16'),
(202501021403017, 202501021402014, @img4, '2025-01-02 14:03:17', '2025-01-02 14:03:17'),
(202501021403018, 202501021402015, @img5, '2025-01-02 14:03:18', '2025-01-02 14:03:18'),
(202501021403019, 202501021402015, @img1, '2025-01-02 14:03:19', '2025-01-02 14:03:19'),
(202501021403020, 202501021402016, @img2, '2025-01-02 14:03:20', '2025-01-02 14:03:20'),
(202501021403021, 202501021402016, @img3, '2025-01-02 14:03:21', '2025-01-02 14:03:21'),
(202501021403022, 202501021402017, @img4, '2025-01-02 14:03:22', '2025-01-02 14:03:22'),
(202501021403023, 202501021402017, @img5, '2025-01-02 14:03:23', '2025-01-02 14:03:23'),
(202501021403024, 202501021402018, @img1, '2025-01-02 14:03:24', '2025-01-02 14:03:24'),
(202501021403025, 202501021402018, @img2, '2025-01-02 14:03:25', '2025-01-02 14:03:25');



-- 插入产品基本信息
INSERT INTO tb_products (id, tccode, supplier, supplier_code, supplier_name, fire_standard, fob_20_container_price, fob_40_container_price, shipping_port) VALUES
(202501031001001, 'TC001', '供应商A', 'SA001', '宁波办公家具有限公司', 'BS 5852', 1200.00, 2200.00, '宁波港'),
(202501031001002, 'TC002', '供应商B', 'SB001', '广州家具制造厂', 'BS 5852', 1300.00, 2300.00, '广州港'),
(202501031001003, 'TC003', '供应商C', 'SC001', '深圳家具有限公司', 'BS 5852', 1400.00, 2400.00, '深圳港'),
(202501031001004, 'TC004', '供应商D', 'SD001', '东莞办公家具有限公司', 'BS 5852', 1250.00, 2250.00, '深圳港'),
(202501031001005, 'TC005', '供应商E', 'SE001', '佛山家具制造厂', 'BS 5852', 1350.00, 2350.00, '广州港'),
(202501031001006, 'TC006', '供应商F', 'SF001', '中山家具有限公司', 'BS 5852', 1450.00, 2450.00, '广州港'),
(202501031001007, 'TC007', '供应商G', 'SG001', '惠州办公家具有限公司', 'BS 5852', 1500.00, 2500.00, '深圳港'),
(202501031001008, 'TC008', '供应商H', 'SH001', '上海家具制造厂', 'BS 5852', 1550.00, 2550.00, '上海港'),
(202501031001009, 'TC009', '供应商I', 'SI001', '杭州家具有限公司', 'BS 5852', 1600.00, 2600.00, '宁波港'),
(202501031001010, 'TC010', '供应商J', 'SJ001', '苏州办公家具有限公司', 'BS 5852', 1650.00, 2650.00, '上海港'),
(202501031001011, 'TC011', '供应商K', 'SK001', '无锡家具制造厂', 'BS 5852', 1700.00, 2700.00, '上海港'),
(202501031001012, 'TC012', '供应商L', 'SL001', '常州家具有限公司', 'BS 5852', 1750.00, 2750.00, '上海港'),
(202501031001013, 'TC013', '供应商M', 'SM001', '南京办公家具有限公司', 'BS 5852', 1800.00, 2800.00, '上海港'),
(202501031001014, 'TC014', '供应商N', 'SN001', '青岛家具制造厂', 'BS 5852', 1850.00, 2850.00, '青岛港'),
(202501031001015, 'TC015', '供应商O', 'SO001', '烟台家具有限公司', 'BS 5852', 1900.00, 2900.00, '青岛港'),
(202501031001016, 'TC016', '供应商P', 'SP001', '威海办公家具有限公司', 'BS 5852', 1950.00, 2950.00, '青岛港'),
(202501031001017, 'TC017', '供应商Q', 'SQ001', '天津家具制造厂', 'BS 5852', 2000.00, 3000.00, '天津港'),
(202501031001018, 'TC018', '供应商R', 'SR001', '北京家具有限公司', 'BS 5852', 2050.00, 3050.00, '天津港'),
(202501031001019, 'TC019', '供应商S', 'SS001', '廊坊办公家具有限公司', 'BS 5852', 2100.00, 3100.00, '天津港'),
(202501031001020, 'TC020', '供应商T', 'ST001', '沈阳家具制造厂', 'BS 5852', 2150.00, 3150.00, '大连港'),
(202501031001021, 'TC021', '供应商U', 'SU001', '大连家具有限公司', 'BS 5852', 2200.00, 3200.00, '大连港'),
(202501031001022, 'TC022', '供应商V', 'SV001', '长春办公家具有限公司', 'BS 5852', 2250.00, 3250.00, '大连港'),
(202501031001023, 'TC023', '供应商W', 'SW001', '哈尔滨家具制造厂', 'BS 5852', 2300.00, 3300.00, '大连港'),
(202501031001024, 'TC024', '供应商X', 'SX001', '武汉家具有限公司', 'BS 5852', 2350.00, 3350.00, '武汉港'),
(202501031001025, 'TC025', '供应商Y', 'SY001', '长沙办公家具有限公司', 'BS 5852', 2400.00, 3400.00, '武汉港'),
(202501031001026, 'TC026', '供应商Z', 'SZ001', '成都家具制造厂', 'BS 5852', 2450.00, 3450.00, '重庆港'),
(202501031001027, 'TC027', '供应商AA', 'SAA001', '重庆家具有限公司', 'BS 5852', 2500.00, 3500.00, '重庆港'),
(202501031001028, 'TC028', '供应商AB', 'SAB001', '西安办公家具有限公司', 'BS 5852', 2550.00, 3550.00, '重庆港'),
(202501031001029, 'TC029', '供应商AC', 'SAC001', '兰州家具制造厂', 'BS 5852', 2600.00, 3600.00, '重庆港'),
(202501031001030, 'TC030', '供应商AD', 'SAD001', '乌鲁木齐家具有限公司', 'BS 5852', 2650.00, 3650.00, '重庆港');

-- 插入面料信息
INSERT INTO tb_upholstery (id, product_id, fabric_manufacturer, colour_code, leather_grade, usage_per_chair) VALUES
(202501031101001, 202501031001001, '面料制造商A', 'CC001', 'A级', 2.5),
(202501031101002, 202501031001002, '面料制造商B', 'CC002', 'B级', 2.3),
(202501031101003, 202501031001003, '面料制造商C', 'CC003', 'A级', 2.4),
(202501031101004, 202501031001004, '面料制造商D', 'CC004', 'A级', 2.6),
(202501031101005, 202501031001005, '面料制造商E', 'CC005', 'B级', 2.4),
(202501031101006, 202501031001006, '面料制造商F', 'CC006', 'A级', 2.5),
(202501031101007, 202501031001007, '面料制造商G', 'CC007', 'B级', 2.3),
(202501031101008, 202501031001008, '面料制造商H', 'CC008', 'A级', 2.4),
(202501031101009, 202501031001009, '面料制造商I', 'CC009', 'B级', 2.5),
(202501031101010, 202501031001010, '面料制造商J', 'CC010', 'A级', 2.6),
(202501031101011, 202501031001011, '面料制造商K', 'CC011', 'B级', 2.8),
(202501031101012, 202501031001012, '面料制造商L', 'CC012', 'A级', 2.9),
(202501031101013, 202501031001013, '面料制造商M', 'CC013', 'B级', 2.7),
(202501031101014, 202501031001014, '面料制造商N', 'CC014', 'A级', 2.8),
(202501031101015, 202501031001015, '面料制造商O', 'CC015', 'B级', 2.5),
(202501031101016, 202501031001016, '面料制造商P', 'CC016', 'A级', 2.4),
(202501031101017, 202501031001017, '面料制造商Q', 'CC017', 'B级', 2.6),
(202501031101018, 202501031001018, '面料制造商R', 'CC018', 'A级', 2.5),
(202501031101019, 202501031001019, '面料制造商S', 'CC019', 'B级', 2.3),
(202501031101020, 202501031001020, '面料制造商T', 'CC020', 'A级', 2.4),
(202501031101021, 202501031001021, '面料制造商U', 'CC021', 'B级', 2.5),
(202501031101022, 202501031001022, '面料制造商V', 'CC022', 'A级', 2.6),
(202501031101023, 202501031001023, '面料制造商W', 'CC023', 'B级', 2.4),
(202501031101024, 202501031001024, '面料制造商X', 'CC024', 'A级', 2.5),
(202501031101025, 202501031001025, '面料制造商Y', 'CC025', 'B级', 2.3),
(202501031101026, 202501031001026, '面料制造商Z', 'CC026', 'A级', 2.4),
(202501031101027, 202501031001027, '面料制造商AA', 'CC027', 'B级', 2.5),
(202501031101028, 202501031001028, '面料制造商AB', 'CC028', 'A级', 2.6),
(202501031101029, 202501031001029, '面料制造商AC', 'CC029', 'B级', 2.4),
(202501031101030, 202501031001030, '面料制造商AD', 'CC030', 'A级', 2.5);

-- 插入包装信息
INSERT INTO tb_carton_details (id, product_id, width, depth, height, board_type, items_per_carton, carton_volume) VALUES
(202501031201001, 202501031001001, 600, 500, 1100, '五层瓦楞纸', 1, 0.33),
(202501031201002, 202501031001002, 620, 520, 1120, '五层瓦楞纸', 1, 0.36),
(202501031201003, 202501031001003, 640, 540, 1140, '五层瓦楞纸', 1, 0.39),
(202501031201004, 202501031001004, 625, 525, 1125, '五层瓦楞纸', 1, 0.34),
(202501031201005, 202501031001005, 630, 530, 1130, '五层瓦楞纸', 1, 0.35),
(202501031201006, 202501031001006, 635, 535, 1135, '五层瓦楞纸', 1, 0.36),
(202501031201007, 202501031001007, 640, 540, 1140, '五层瓦楞纸', 1, 0.37),
(202501031201008, 202501031001008, 645, 545, 1145, '五层瓦楞纸', 1, 0.38),
(202501031201009, 202501031001009, 650, 550, 1150, '五层瓦楞纸', 1, 0.39),
(202501031201010, 202501031001010, 655, 555, 1155, '五层瓦楞纸', 1, 0.40),
(202501031201011, 202501031001011, 660, 560, 1160, '五层瓦楞纸', 1, 0.41),
(202501031201012, 202501031001012, 665, 565, 1165, '五层瓦楞纸', 1, 0.42),
(202501031201013, 202501031001013, 670, 570, 1170, '五层瓦楞纸', 1, 0.43),
(202501031201014, 202501031001014, 675, 575, 1175, '五层瓦楞纸', 1, 0.44),
(202501031201015, 202501031001015, 680, 580, 1180, '五层瓦楞纸', 1, 0.45),
(202501031201016, 202501031001016, 685, 585, 1185, '五层瓦楞纸', 1, 0.46),
(202501031201017, 202501031001017, 690, 590, 1190, '五层瓦楞纸', 1, 0.47),
(202501031201018, 202501031001018, 695, 595, 1195, '五层瓦楞纸', 1, 0.48),
(202501031201019, 202501031001019, 700, 600, 1200, '五层瓦楞纸', 1, 0.49),
(202501031201020, 202501031001020, 705, 605, 1205, '五层瓦楞纸', 1, 0.50),
(202501031201021, 202501031001021, 710, 610, 1210, '五层瓦楞纸', 1, 0.51),
(202501031201022, 202501031001022, 715, 615, 1215, '五层瓦楞纸', 1, 0.52),
(202501031201023, 202501031001023, 720, 620, 1220, '五层瓦楞纸', 1, 0.53),
(202501031201024, 202501031001024, 725, 625, 1225, '五层瓦楞纸', 1, 0.54),
(202501031201025, 202501031001025, 730, 630, 1230, '五层瓦楞纸', 1, 0.55),
(202501031201026, 202501031001026, 735, 635, 1235, '五层瓦楞纸', 1, 0.56),
(202501031201027, 202501031001027, 740, 640, 1240, '五层瓦楞纸', 1, 0.57),
(202501031201028, 202501031001028, 745, 645, 1245, '五层瓦楞纸', 1, 0.58),
(202501031201029, 202501031001029, 750, 650, 1250, '五层瓦楞纸', 1, 0.59),
(202501031201030, 202501031001030, 755, 655, 1255, '五层瓦楞纸', 1, 0.60);

-- 插入生产和物流信息
INSERT INTO tb_production_logistics (id, product_id, production_time, effective_volume, loading_quantity_20gp, loading_quantity_40hc, net_weight, gross_weight) VALUES
(202501031301001, 202501031001001, 15, 0.33, 120, 280, 15.5, 17.5),
(202501031301002, 202501031001002, 15, 0.36, 110, 260, 16.0, 18.0),
(202501031301003, 202501031001003, 15, 0.39, 100, 240, 16.5, 18.5),
(202501031301004, 202501031001004, 15, 0.42, 90, 220, 17.0, 19.0),
(202501031301005, 202501031001005, 15, 0.45, 80, 200, 17.5, 19.5),
(202501031301006, 202501031001006, 15, 0.48, 70, 180, 18.0, 20.0),
(202501031301007, 202501031001007, 15, 0.51, 60, 160, 18.5, 20.5),
(202501031301008, 202501031001008, 15, 0.54, 50, 140, 19.0, 21.0),
(202501031301009, 202501031001009, 15, 0.57, 40, 120, 19.5, 21.5),
(202501031301010, 202501031001010, 15, 0.60, 30, 100, 20.0, 22.0),
(202501031301011, 202501031001011, 15, 0.63, 28, 95, 20.5, 22.5),
(202501031301012, 202501031001012, 15, 0.66, 26, 90, 21.0, 23.0),
(202501031301013, 202501031001013, 15, 0.69, 24, 85, 21.5, 23.5),
(202501031301014, 202501031001014, 15, 0.72, 22, 80, 22.0, 24.0),
(202501031301015, 202501031001015, 15, 0.75, 20, 75, 22.5, 24.5),
(202501031301016, 202501031001016, 15, 0.78, 18, 70, 23.0, 25.0),
(202501031301017, 202501031001017, 15, 0.81, 16, 65, 23.5, 25.5),
(202501031301018, 202501031001018, 15, 0.84, 14, 60, 24.0, 26.0),
(202501031301019, 202501031001019, 15, 0.87, 12, 55, 24.5, 26.5),
(202501031301020, 202501031001020, 15, 0.90, 10, 50, 25.0, 27.0),
(202501031301021, 202501031001021, 15, 0.93, 9, 45, 25.5, 27.5),
(202501031301022, 202501031001022, 15, 0.96, 8, 40, 26.0, 28.0),
(202501031301023, 202501031001023, 15, 0.99, 7, 35, 26.5, 28.5),
(202501031301024, 202501031001024, 15, 1.02, 6, 30, 27.0, 29.0),
(202501031301025, 202501031001025, 15, 1.05, 5, 25, 27.5, 29.5),
(202501031301026, 202501031001026, 15, 1.08, 4, 20, 28.0, 30.0),
(202501031301027, 202501031001027, 15, 1.11, 3, 15, 28.5, 30.5),
(202501031301028, 202501031001028, 15, 1.14, 2, 10, 29.0, 31.0),
(202501031301029, 202501031001029, 15, 1.17, 1, 5, 29.5, 31.5),
(202501031301030, 202501031001030, 15, 1.20, 1, 5, 30.0, 32.0);

-- 插入产品尺寸信息
INSERT INTO tb_product_dimensions (id, product_id, seat_width, seat_depth, seat_height_min, seat_height_max, back_width, back_height, back_height_from_seat, overall_width, overall_depth, overall_height_min, overall_height_max) VALUES
(202501031401001, 202501031001001, 500, 480, 420, 520, 460, 560, 600, 660, 660, 1150, 1250),
(202501031401002, 202501031001002, 510, 490, 430, 530, 470, 570, 610, 670, 670, 1160, 1260),
(202501031401003, 202501031001003, 520, 500, 440, 540, 480, 580, 620, 680, 680, 1170, 1270),
(202501031401004, 202501031001004, 530, 510, 450, 550, 490, 590, 630, 690, 690, 1180, 1280),
(202501031401005, 202501031001005, 540, 520, 460, 560, 500, 600, 640, 700, 700, 1190, 1290),
(202501031401006, 202501031001006, 550, 530, 470, 570, 510, 610, 650, 710, 710, 1200, 1300),
(202501031401007, 202501031001007, 560, 540, 480, 580, 520, 620, 660, 720, 720, 1210, 1310),
(202501031401008, 202501031001008, 570, 550, 490, 590, 530, 630, 670, 730, 730, 1220, 1320),
(202501031401009, 202501031001009, 580, 560, 500, 600, 540, 640, 680, 740, 740, 1230, 1330),
(202501031401010, 202501031001010, 590, 570, 510, 610, 550, 650, 690, 750, 750, 1240, 1340),
(202501031401011, 202501031001011, 600, 580, 520, 620, 560, 660, 700, 760, 760, 1250, 1350),
(202501031401012, 202501031001012, 610, 590, 530, 630, 570, 670, 710, 770, 770, 1260, 1360),
(202501031401013, 202501031001013, 620, 600, 540, 640, 580, 680, 720, 780, 780, 1270, 1370),
(202501031401014, 202501031001014, 630, 610, 550, 650, 590, 690, 730, 790, 790, 1280, 1380),
(202501031401015, 202501031001015, 640, 620, 560, 660, 600, 700, 740, 800, 800, 1290, 1390),
(202501031401016, 202501031001016, 650, 630, 570, 670, 610, 710, 750, 810, 810, 1300, 1400),
(202501031401017, 202501031001017, 660, 640, 580, 680, 620, 720, 760, 820, 820, 1310, 1410),
(202501031401018, 202501031001018, 670, 650, 590, 690, 630, 730, 770, 830, 830, 1320, 1420),
(202501031401019, 202501031001019, 680, 660, 600, 700, 640, 740, 780, 840, 840, 1330, 1430),
(202501031401020, 202501031001020, 690, 670, 610, 710, 650, 750, 790, 850, 850, 1340, 1440),
(202501031401021, 202501031001021, 700, 680, 620, 720, 660, 760, 800, 860, 860, 1350, 1450),
(202501031401022, 202501031001022, 710, 690, 630, 730, 670, 770, 810, 870, 870, 1360, 1460),
(202501031401023, 202501031001023, 720, 700, 640, 740, 680, 780, 820, 880, 880, 1370, 1470),
(202501031401024, 202501031001024, 730, 710, 650, 750, 690, 790, 830, 890, 890, 1380, 1480),
(202501031401025, 202501031001025, 740, 720, 660, 760, 700, 800, 840, 900, 900, 1390, 1490),
(202501031401026, 202501031001026, 750, 730, 670, 770, 710, 810, 850, 910, 910, 1400, 1500),
(202501031401027, 202501031001027, 760, 740, 680, 780, 720, 820, 860, 920, 920, 1410, 1510),
(202501031401028, 202501031001028, 770, 750, 690, 790, 730, 830, 870, 930, 930, 1420, 1520),
(202501031401029, 202501031001029, 780, 760, 700, 800, 740, 840, 880, 940, 940, 1430, 1530),
(202501031401030, 202501031001030, 790, 770, 710, 810, 750, 850, 890, 950, 950, 1440, 1540);

-- 插入座椅内部结构信息
INSERT INTO tb_seat_inner_components (id, product_id, material_code, thickness, layers_count, dimensions) VALUES
(202501031501001, 202501031001001, 'PP001', 20, 3, '500x480'),
(202501031501002, 202501031001002, 'PP002', 22, 3, '510x490'),
(202501031501003, 202501031001003, 'PP003', 24, 3, '520x500'),
(202501031501004, 202501031001004, 'PP004', 20, 3, '530x510'),
(202501031501005, 202501031001005, 'PP005', 22, 3, '540x520'),
(202501031501006, 202501031001006, 'PP006', 24, 3, '550x530'),
(202501031501007, 202501031001007, 'PP007', 20, 3, '560x540'),
(202501031501008, 202501031001008, 'PP008', 22, 3, '570x550'),
(202501031501009, 202501031001009, 'PP009', 24, 3, '580x560'),
(202501031501010, 202501031001010, 'PP010', 20, 3, '590x570'),
(202501031501011, 202501031001011, 'PP011', 22, 3, '600x580'),
(202501031501012, 202501031001012, 'PP012', 24, 3, '610x590'),
(202501031501013, 202501031001013, 'PP013', 20, 3, '620x600'),
(202501031501014, 202501031001014, 'PP014', 22, 3, '630x610'),
(202501031501015, 202501031001015, 'PP015', 24, 3, '640x620'),
(202501031501016, 202501031001016, 'PP016', 20, 3, '650x630'),
(202501031501017, 202501031001017, 'PP017', 22, 3, '660x640'),
(202501031501018, 202501031001018, 'PP018', 24, 3, '670x650'),
(202501031501019, 202501031001019, 'PP019', 20, 3, '680x660'),
(202501031501020, 202501031001020, 'PP020', 22, 3, '690x670'),
(202501031501021, 202501031001021, 'PP021', 24, 3, '700x680'),
(202501031501022, 202501031001022, 'PP022', 20, 3, '710x690'),
(202501031501023, 202501031001023, 'PP023', 22, 3, '720x700'),
(202501031501024, 202501031001024, 'PP024', 24, 3, '730x710'),
(202501031501025, 202501031001025, 'PP025', 20, 3, '740x720'),
(202501031501026, 202501031001026, 'PP026', 22, 3, '750x730'),
(202501031501027, 202501031001027, 'PP027', 24, 3, '760x740'),
(202501031501028, 202501031001028, 'PP028', 20, 3, '770x750'),
(202501031501029, 202501031001029, 'PP029', 22, 3, '780x760'),
(202501031501030, 202501031001030, 'PP030', 24, 3, '790x770');

-- 插入背部内部结构信息
INSERT INTO tb_back_inner_components (id, product_id, material_code, thickness, layers_count, dimensions) VALUES
(202501031601001, 202501031001001, 'BWD001', 12, 3, '460x560'),
(202501031601002, 202501031001002, 'YWD001', 12, 3, '470x570'),
(202501031601003, 202501031001003, 'BWD002', 12, 3, '480x580'),
(202501031601004, 202501031001004, 'YWD002', 12, 3, '490x590'),
(202501031601005, 202501031001005, 'BWD003', 12, 3, '500x600'),
(202501031601006, 202501031001006, 'YWD003', 12, 3, '510x610'),
(202501031601007, 202501031001007, 'BWD004', 12, 3, '520x620'),
(202501031601008, 202501031001008, 'YWD004', 12, 3, '530x630'),
(202501031601009, 202501031001009, 'BWD005', 12, 3, '540x640'),
(202501031601010, 202501031001010, 'YWD005', 12, 3, '550x650'),
(202501031601011, 202501031001011, 'BWD006', 12, 3, '560x660'),
(202501031601012, 202501031001012, 'YWD006', 12, 3, '570x670'),
(202501031601013, 202501031001013, 'BWD007', 12, 3, '580x680'),
(202501031601014, 202501031001014, 'YWD007', 12, 3, '590x690'),
(202501031601015, 202501031001015, 'BWD008', 12, 3, '600x700'),
(202501031601016, 202501031001016, 'YWD008', 12, 3, '610x710'),
(202501031601017, 202501031001017, 'BWD009', 12, 3, '620x720'),
(202501031601018, 202501031001018, 'YWD009', 12, 3, '630x730'),
(202501031601019, 202501031001019, 'BWD010', 12, 3, '640x740'),
(202501031601020, 202501031001020, 'YWD010', 12, 3, '650x750'),
(202501031601021, 202501031001021, 'BWD011', 12, 3, '660x760'),
(202501031601022, 202501031001022, 'YWD011', 12, 3, '670x770'),
(202501031601023, 202501031001023, 'BWD012', 12, 3, '680x780'),
(202501031601024, 202501031001024, 'YWD012', 12, 3, '690x790'),
(202501031601025, 202501031001025, 'BWD013', 12, 3, '700x800'),
(202501031601026, 202501031001026, 'YWD013', 12, 3, '710x810'),
(202501031601027, 202501031001027, 'BWD014', 12, 3, '720x820'),
(202501031601028, 202501031001028, 'YWD014', 12, 3, '730x830'),
(202501031601029, 202501031001029, 'BWD015', 12, 3, '740x840'),
(202501031601030, 202501031001030, 'YWD015', 12, 3, '750x850');

-- 插入背部外部结构信息
INSERT INTO tb_back_outer_components (id, product_id, material, dimensions, manufacturer_name) VALUES
(202501031701001, 202501031001001, 'PP塑料', '460x560', '制造商A'),
(202501031701002, 202501031001002, 'PP塑料', '470x570', '制造商B'),
(202501031701003, 202501031001003, 'PP塑料', '480x580', '制造商C'),
(202501031701004, 202501031001004, 'PP塑料', '490x590', '制造商D'),
(202501031701005, 202501031001005, 'PP塑料', '500x600', '制造商E'),
(202501031701006, 202501031001006, 'PP塑料', '510x610', '制造商F'),
(202501031701007, 202501031001007, 'PP塑料', '520x620', '制造商G'),
(202501031701008, 202501031001008, 'PP塑料', '530x630', '制造商H'),
(202501031701009, 202501031001009, 'PP塑料', '540x640', '制造商I'),
(202501031701010, 202501031001010, 'PP塑料', '550x650', '制造商J'),
(202501031701011, 202501031001011, 'PP塑料', '560x660', '制造商K'),
(202501031701012, 202501031001012, 'PP塑料', '570x670', '制造商L'),
(202501031701013, 202501031001013, 'PP塑料', '580x680', '制造商M'),
(202501031701014, 202501031001014, 'PP塑料', '590x690', '制造商N'),
(202501031701015, 202501031001015, 'PP塑料', '600x700', '制造商O'),
(202501031701016, 202501031001016, 'PP塑料', '610x710', '制造商P'),
(202501031701017, 202501031001017, 'PP塑料', '620x720', '制造商Q'),
(202501031701018, 202501031001018, 'PP塑料', '630x730', '制造商R'),
(202501031701019, 202501031001019, 'PP塑料', '640x740', '制造商S'),
(202501031701020, 202501031001020, 'PP塑料', '650x750', '制造商T'),
(202501031701021, 202501031001021, 'PP塑料', '660x760', '制造商U'),
(202501031701022, 202501031001022, 'PP塑料', '670x770', '制造商V'),
(202501031701023, 202501031001023, 'PP塑料', '680x780', '制造商W'),
(202501031701024, 202501031001024, 'PP塑料', '690x790', '制造商X'),
(202501031701025, 202501031001025, 'PP塑料', '700x800', '制造商Y'),
(202501031701026, 202501031001026, 'PP塑料', '710x810', '制造商Z'),
(202501031701027, 202501031001027, 'PP塑料', '720x820', '制造商AA'),
(202501031701028, 202501031001028, 'PP塑料', '730x830', '制造商AB'),
(202501031701029, 202501031001029, 'PP塑料', '740x840', '制造商AC'),
(202501031701030, 202501031001030, 'PP塑料', '750x850', '制造商AD');

-- 插入座椅外部结构信息
INSERT INTO tb_seat_outer_components (id, product_id, material, dimensions, manufacturer_name) VALUES
(202501031801001, 202501031001001, 'PP塑料+钢架', '500x480', '制造商A'),
(202501031801002, 202501031001002, 'PP塑料+钢架', '510x490', '制造商B'),
(202501031801003, 202501031001003, 'PP塑料+钢架', '520x500', '制造商C'),
(202501031801004, 202501031001004, 'PP塑料+钢架', '530x510', '制造商D'),
(202501031801005, 202501031001005, 'PP塑料+钢架', '540x520', '制造商E'),
(202501031801006, 202501031001006, 'PP塑料+钢架', '550x530', '制造商F'),
(202501031801007, 202501031001007, 'PP塑料+钢架', '560x540', '制造商G'),
(202501031801008, 202501031001008, 'PP塑料+钢架', '570x550', '制造商H'),
(202501031801009, 202501031001009, 'PP塑料+钢架', '580x560', '制造商I'),
(202501031801010, 202501031001010, 'PP塑料+钢架', '590x570', '制造商J'),
(202501031801011, 202501031001011, 'PP塑料+钢架', '600x580', '制造商K'),
(202501031801012, 202501031001012, 'PP塑料+钢架', '610x590', '制造商L'),
(202501031801013, 202501031001013, 'PP塑料+钢架', '620x600', '制造商M'),
(202501031801014, 202501031001014, 'PP塑料+钢架', '630x610', '制造商N'),
(202501031801015, 202501031001015, 'PP塑料+钢架', '640x620', '制造商O'),
(202501031801016, 202501031001016, 'PP塑料+钢架', '650x630', '制造商P'),
(202501031801017, 202501031001017, 'PP塑料+钢架', '660x640', '制造商Q'),
(202501031801018, 202501031001018, 'PP塑料+钢架', '670x650', '制造商R'),
(202501031801019, 202501031001019, 'PP塑料+钢架', '680x660', '制造商S'),
(202501031801020, 202501031001020, 'PP塑料+钢架', '690x670', '制造商T'),
(202501031801021, 202501031001021, 'PP塑料+钢架', '700x680', '制造商U'),
(202501031801022, 202501031001022, 'PP塑料+钢架', '710x690', '制造商V'),
(202501031801023, 202501031001023, 'PP塑料+钢架', '720x700', '制造商W'),
(202501031801024, 202501031001024, 'PP塑料+钢架', '730x710', '制造商X'),
(202501031801025, 202501031001025, 'PP塑料+钢架', '740x720', '制造商Y'),
(202501031801026, 202501031001026, 'PP塑料+钢架', '750x730', '制造商Z'),
(202501031801027, 202501031001027, 'PP塑料+钢架', '760x740', '制造商AA'),
(202501031801028, 202501031001028, 'PP塑料+钢架', '770x750', '制造商AB'),
(202501031801029, 202501031001029, 'PP塑料+钢架', '780x760', '制造商AC'),
(202501031801030, 202501031001030, 'PP塑料+钢架', '790x770', '制造商AD');

-- 插入扶手信息
INSERT INTO tb_arms (id, product_id, material, type, manufacturer, description, arm_height_from_seat, arm_height_from_floor) VALUES
(202501031901001, 202501031001001, 'PP+钢架', '3D扶手', '制造商A', '可调节高度和角度', 200, 620),
(202501031901002, 202501031001002, 'PP+钢架', '3D扶手', '制造商B', '可调节高度和角度', 210, 630),
(202501031901003, 202501031001003, 'PP+钢架', '4D扶手', '制造商C', '可调节高度、角度和宽度', 220, 640),
(202501031901004, 202501031001004, 'PP+钢架', '3D扶手', '制造商D', '可调节高度和角度', 200, 620),
(202501031901005, 202501031001005, 'PP+钢架', '4D扶手', '制造商E', '可调节高度、角度和宽度', 210, 630),
(202501031901006, 202501031001006, 'PP+钢架', '3D扶手', '制造商F', '可调节高度和角度', 220, 640),
(202501031901007, 202501031001007, 'PP+钢架', '4D扶手', '制造商G', '可调节高度、角度和宽度', 200, 620),
(202501031901008, 202501031001008, 'PP+钢架', '3D扶手', '制造商H', '可调节高度和角度', 210, 630),
(202501031901009, 202501031001009, 'PP+钢架', '4D扶手', '制造商I', '可调节高度、角度和宽度', 220, 640),
(202501031901010, 202501031001010, 'PP+钢架', '3D扶手', '制造商J', '可调节高度和角度', 200, 620),
(202501031901011, 202501031001011, 'PP+钢架', '4D扶手', '制造商K', '可调节高度、角度和宽度', 210, 630),
(202501031901012, 202501031001012, 'PP+钢架', '3D扶手', '制造商L', '可调节高度和角度', 220, 640),
(202501031901013, 202501031001013, 'PP+钢架', '4D扶手', '制造商M', '可调节高度、角度和宽度', 200, 620),
(202501031901014, 202501031001014, 'PP+钢架', '3D扶手', '制造商N', '可调节高度和角度', 210, 630),
(202501031901015, 202501031001015, 'PP+钢架', '4D扶手', '制造商O', '可调节高度、角度和宽度', 220, 640),
(202501031901016, 202501031001016, 'PP+钢架', '3D扶手', '制造商P', '可调节高度和角度', 200, 620),
(202501031901017, 202501031001017, 'PP+钢架', '4D扶手', '制造商Q', '可调节高度、角度和宽度', 210, 630),
(202501031901018, 202501031001018, 'PP+钢架', '3D扶手', '制造商R', '可调节高度和角度', 220, 640),
(202501031901019, 202501031001019, 'PP+钢架', '4D扶手', '制造商S', '可调节高度、角度和宽度', 200, 620),
(202501031901020, 202501031001020, 'PP+钢架', '3D扶手', '制造商T', '可调节高度和角度', 210, 630),
(202501031901021, 202501031001021, 'PP+钢架', '4D扶手', '制造商U', '可调节高度、角度和宽度', 220, 640),
(202501031901022, 202501031001022, 'PP+钢架', '3D扶手', '制造商V', '可调节高度和角度', 200, 620),
(202501031901023, 202501031001023, 'PP+钢架', '4D扶手', '制造商W', '可调节高度、角度和宽度', 210, 630),
(202501031901024, 202501031001024, 'PP+钢架', '3D扶手', '制造商X', '可调节高度和角度', 220, 640),
(202501031901025, 202501031001025, 'PP+钢架', '4D扶手', '制造商Y', '可调节高度、角度和宽度', 200, 620),
(202501031901026, 202501031001026, 'PP+钢架', '3D扶手', '制造商Z', '可调节高度和角度', 210, 630),
(202501031901027, 202501031001027, 'PP+钢架', '4D扶手', '制造商AA', '可调节高度、角度和宽度', 220, 640),
(202501031901028, 202501031001028, 'PP+钢架', '3D扶手', '制造商AB', '可调节高度和角度', 200, 620),
(202501031901029, 202501031001029, 'PP+钢架', '4D扶手', '制造商AC', '可调节高度、角度和宽度', 210, 630),
(202501031901030, 202501031001030, 'PP+钢架', '3D扶手', '制造商AD', '可调节高度和角度', 220, 640);

-- 插入泡棉信息
INSERT INTO tb_foam_details (id, product_id, description, seat_density, back_density, seat_thickness, back_thickness) VALUES
(202501032001001, 202501031001001, '高密度海绵', 45, 35, 50, 30),
(202501032001002, 202501031001002, '高密度海绵', 45, 35, 52, 32),
(202501032001003, 202501031001003, '高密度海绵', 45, 35, 54, 34),
(202501032001004, 202501031001004, '高密度海绵', 45, 35, 50, 30),
(202501032001005, 202501031001005, '高密度海绵', 45, 35, 52, 32),
(202501032001006, 202501031001006, '高密度海绵', 45, 35, 54, 34),
(202501032001007, 202501031001007, '高密度海绵', 45, 35, 50, 30),
(202501032001008, 202501031001008, '高密度海绵', 45, 35, 52, 32),
(202501032001009, 202501031001009, '高密度海绵', 45, 35, 54, 34),
(202501032001010, 202501031001010, '高密度海绵', 45, 35, 50, 30),
(202501032001011, 202501031001011, '高密度海绵', 45, 35, 52, 32),
(202501032001012, 202501031001012, '高密度海绵', 45, 35, 54, 34),
(202501032001013, 202501031001013, '高密度海绵', 45, 35, 50, 30),
(202501032001014, 202501031001014, '高密度海绵', 45, 35, 52, 32),
(202501032001015, 202501031001015, '高密度海绵', 45, 35, 54, 34),
(202501032001016, 202501031001016, '高密度海绵', 45, 35, 50, 30),
(202501032001017, 202501031001017, '高密度海绵', 45, 35, 52, 32),
(202501032001018, 202501031001018, '高密度海绵', 45, 35, 54, 34),
(202501032001019, 202501031001019, '高密度海绵', 45, 35, 50, 30),
(202501032001020, 202501031001020, '高密度海绵', 45, 35, 52, 32),
(202501032001021, 202501031001021, '高密度海绵', 45, 35, 54, 34),
(202501032001022, 202501031001022, '高密度海绵', 45, 35, 50, 30),
(202501032001023, 202501031001023, '高密度海绵', 45, 35, 52, 32),
(202501032001024, 202501031001024, '高密度海绵', 45, 35, 54, 34),
(202501032001025, 202501031001025, '高密度海绵', 45, 35, 50, 30),
(202501032001026, 202501031001026, '高密度海绵', 45, 35, 52, 32),
(202501032001027, 202501031001027, '高密度海绵', 45, 35, 54, 34),
(202501032001028, 202501031001028, '高密度海绵', 45, 35, 50, 30),
(202501032001029, 202501031001029, '高密度海绵', 45, 35, 52, 32),
(202501032001030, 202501031001030, '高密度海绵', 45, 35, 54, 34);

-- 插入脚轮信息
INSERT INTO tb_castors (id, product_id, description, pin_thickness, wheel_diameter) VALUES
(202501032101001, 202501031001001, '尼龙脚轮', 11, 60),
(202501032101002, 202501031001002, 'PU脚轮', 11, 60),
(202501032101003, 202501031001003, '尼龙脚轮', 11, 60),
(202501032101004, 202501031001004, 'PU脚轮', 11, 60),
(202501032101005, 202501031001005, '尼龙脚轮', 11, 60),
(202501032101006, 202501031001006, 'PU脚轮', 11, 60),
(202501032101007, 202501031001007, '尼龙脚轮', 11, 60),
(202501032101008, 202501031001008, 'PU脚轮', 11, 60),
(202501032101009, 202501031001009, '尼龙脚轮', 11, 60),
(202501032101010, 202501031001010, 'PU脚轮', 11, 60),
(202501032101011, 202501031001011, '尼龙脚轮', 11, 60),
(202501032101012, 202501031001012, 'PU脚轮', 11, 60),
(202501032101013, 202501031001013, '尼龙脚轮', 11, 60),
(202501032101014, 202501031001014, 'PU脚轮', 11, 60),
(202501032101015, 202501031001015, '尼龙脚轮', 11, 60),
(202501032101016, 202501031001016, 'PU脚轮', 11, 60),
(202501032101017, 202501031001017, '尼龙脚轮', 11, 60),
(202501032101018, 202501031001018, 'PU脚轮', 11, 60),
(202501032101019, 202501031001019, '尼龙脚轮', 11, 60),
(202501032101020, 202501031001020, 'PU脚轮', 11, 60),
(202501032101021, 202501031001021, '尼龙脚轮', 11, 60),
(202501032101022, 202501031001022, 'PU脚轮', 11, 60),
(202501032101023, 202501031001023, '尼龙脚轮', 11, 60),
(202501032101024, 202501031001024, 'PU脚轮', 11, 60),
(202501032101025, 202501031001025, '尼龙脚轮', 11, 60),
(202501032101026, 202501031001026, 'PU脚轮', 11, 60),
(202501032101027, 202501031001027, '尼龙脚轮', 11, 60),
(202501032101028, 202501031001028, 'PU脚轮', 11, 60),
(202501032101029, 202501031001029, '尼龙脚轮', 11, 60),
(202501032101030, 202501031001030, 'PU脚轮', 11, 60);

-- 插入底座信息
INSERT INTO tb_bases (id, product_id, description, size_diameter, material, type) VALUES
(202501032201001, 202501031001001, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201002, 202501031001002, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201003, 202501031001003, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201004, 202501031001004, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201005, 202501031001005, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201006, 202501031001006, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201007, 202501031001007, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201008, 202501031001008, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201009, 202501031001009, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201010, 202501031001010, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201011, 202501031001011, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201012, 202501031001012, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201013, 202501031001013, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201014, 202501031001014, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201015, 202501031001015, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201016, 202501031001016, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201017, 202501031001017, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201018, 202501031001018, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201019, 202501031001019, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201020, 202501031001020, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201021, 202501031001021, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201022, 202501031001022, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201023, 202501031001023, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201024, 202501031001024, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201025, 202501031001025, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201026, 202501031001026, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201027, 202501031001027, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201028, 202501031001028, '铝合金底座', 680, '铝合金', '标准型'),
(202501032201029, 202501031001029, '铝合金底座', 680, '铝合金', '豪华型'),
(202501032201030, 202501031001030, '铝合金底座', 680, '铝合金', '标准型');

-- 插入气压棒信息
INSERT INTO tb_gas_lift (id, product_id, description, gas_lift_class, casing_length, extension_size, taper) VALUES
(202501032301001, 202501031001001, '标准气压棒', '4级', 200, 100, 28),
(202501032301002, 202501031001002, '标准气压棒', '4级', 200, 100, 28),
(202501032301003, 202501031001003, '标准气压棒', '4级', 200, 100, 28),
(202501032301004, 202501031001004, '标准气压棒', '4级', 200, 100, 28),
(202501032301005, 202501031001005, '高级气压棒', '4级', 220, 120, 28),
(202501032301006, 202501031001006, '标准气压棒', '4级', 200, 100, 28),
(202501032301007, 202501031001007, '高级气压棒', '4级', 220, 120, 28),
(202501032301008, 202501031001008, '标准气压棒', '4级', 200, 100, 28),
(202501032301009, 202501031001009, '高级气压棒', '4级', 220, 120, 28),
(202501032301010, 202501031001010, '标准气压棒', '4级', 200, 100, 28),
(202501032301011, 202501031001011, '高级气压棒', '4级', 220, 120, 28),
(202501032301012, 202501031001012, '标准气压棒', '4级', 200, 100, 28),
(202501032301013, 202501031001013, '高级气压棒', '4级', 220, 120, 28),
(202501032301014, 202501031001014, '标准气压棒', '4级', 200, 100, 28),
(202501032301015, 202501031001015, '高级气压棒', '4级', 220, 120, 28),
(202501032301016, 202501031001016, '标准气压棒', '4级', 200, 100, 28),
(202501032301017, 202501031001017, '高级气压棒', '4级', 220, 120, 28),
(202501032301018, 202501031001018, '标准气压棒', '4级', 200, 100, 28),
(202501032301019, 202501031001019, '高级气压棒', '4级', 220, 120, 28),
(202501032301020, 202501031001020, '标准气压棒', '4级', 200, 100, 28),
(202501032301021, 202501031001021, '高级气压棒', '4级', 220, 120, 28),
(202501032301022, 202501031001022, '标准气压棒', '4级', 200, 100, 28),
(202501032301023, 202501031001023, '高级气压棒', '4级', 220, 120, 28),
(202501032301024, 202501031001024, '标准气压棒', '4级', 200, 100, 28),
(202501032301025, 202501031001025, '高级气压棒', '4级', 220, 120, 28),
(202501032301026, 202501031001026, '标准气压棒', '4级', 200, 100, 28),
(202501032301027, 202501031001027, '高级气压棒', '4级', 220, 120, 28),
(202501032301028, 202501031001028, '标准气压棒', '4级', 200, 100, 28),
(202501032301029, 202501031001029, '高级气压棒', '4级', 220, 120, 28),
(202501032301030, 202501031001030, '标准气压棒', '4级', 200, 100, 28);

-- 插入气压罩信息
INSERT INTO tb_gas_lift_cover (id, product_id, description, material, colour) VALUES
(202501032401001, 202501031001001, '标准气压罩', 'PP', '黑色'),
(202501032401002, 202501031001002, '标准气压罩', 'PP', '黑色'),
(202501032401003, 202501031001003, '标准气压罩', 'PP', '黑色'),
(202501032401004, 202501031001004, '标准气压罩', 'PP', '黑色'),
(202501032401005, 202501031001005, '豪华气压罩', 'ABS', '银色'),
(202501032401006, 202501031001006, '标准气压罩', 'PP', '黑色'),
(202501032401007, 202501031001007, '豪华气压罩', 'ABS', '银色'),
(202501032401008, 202501031001008, '标准气压罩', 'PP', '黑色'),
(202501032401009, 202501031001009, '豪华气压罩', 'ABS', '银色'),
(202501032401010, 202501031001010, '标准气压罩', 'PP', '黑色'),
(202501032401011, 202501031001011, '豪华气压罩', 'ABS', '银色'),
(202501032401012, 202501031001012, '标准气压罩', 'PP', '黑色'),
(202501032401013, 202501031001013, '豪华气压罩', 'ABS', '银色'),
(202501032401014, 202501031001014, '标准气压罩', 'PP', '黑色'),
(202501032401015, 202501031001015, '豪华气压罩', 'ABS', '银色'),
(202501032401016, 202501031001016, '标准气压罩', 'PP', '黑色'),
(202501032401017, 202501031001017, '豪华气压罩', 'ABS', '银色'),
(202501032401018, 202501031001018, '标准气压罩', 'PP', '黑色'),
(202501032401019, 202501031001019, '豪华气压罩', 'ABS', '银色'),
(202501032401020, 202501031001020, '标准气压罩', 'PP', '黑色'),
(202501032401021, 202501031001021, '豪华气压罩', 'ABS', '银色'),
(202501032401022, 202501031001022, '标准气压罩', 'PP', '黑色'),
(202501032401023, 202501031001023, '豪华气压罩', 'ABS', '银色'),
(202501032401024, 202501031001024, '标准气压罩', 'PP', '黑色'),
(202501032401025, 202501031001025, '豪华气压罩', 'ABS', '银色'),
(202501032401026, 202501031001026, '标准气压罩', 'PP', '黑色'),
(202501032401027, 202501031001027, '豪华气压罩', 'ABS', '银色'),
(202501032401028, 202501031001028, '标准气压罩', 'PP', '黑色'),
(202501032401029, 202501031001029, '豪华气压罩', 'ABS', '银色'),
(202501032401030, 202501031001030, '标准气压罩', 'PP', '黑色');

-- 插入机构信息
INSERT INTO tb_mechanism (id, product_id, description, levers_count, locking_positions, model_no, supplier_name) VALUES
(202501032501001, 202501031001001, '标准机构', 3, '5档', 'M001', '机构供应商A'),
(202501032501002, 202501031001002, '标准机构', 3, '5档', 'M002', '机构供应商B'),
(202501032501003, 202501031001003, '豪华机构', 4, '6档', 'M003', '机构供应商C'),
(202501032501004, 202501031001004, '标准机构', 3, '5档', 'M004', '机构供应商D'),
(202501032501005, 202501031001005, '豪华机构', 4, '6档', 'M005', '机构供应商E'),
(202501032501006, 202501031001006, '标准机构', 3, '5档', 'M006', '机构供应商F'),
(202501032501007, 202501031001007, '豪华机构', 4, '6档', 'M007', '机构供应商G'),
(202501032501008, 202501031001008, '标准机构', 3, '5档', 'M008', '机构供应商H'),
(202501032501009, 202501031001009, '豪华机构', 4, '6档', 'M009', '机构供应商I'),
(202501032501010, 202501031001010, '标准机构', 3, '5档', 'M010', '机构供应商J'),
(202501032501011, 202501031001011, '豪华机构', 4, '6档', 'M011', '机构供应商K'),
(202501032501012, 202501031001012, '标准机构', 3, '5档', 'M012', '机构供应商L'),
(202501032501013, 202501031001013, '豪华机构', 4, '6档', 'M013', '机构供应商M'),
(202501032501014, 202501031001014, '标准机构', 3, '5档', 'M014', '机构供应商N'),
(202501032501015, 202501031001015, '豪华机构', 4, '6档', 'M015', '机构供应商O'),
(202501032501016, 202501031001016, '标准机构', 3, '5档', 'M016', '机构供应商P'),
(202501032501017, 202501031001017, '豪华机构', 4, '6档', 'M017', '机构供应商Q'),
(202501032501018, 202501031001018, '标准机构', 3, '5档', 'M018', '机构供应商R'),
(202501032501019, 202501031001019, '豪华机构', 4, '6档', 'M019', '机构供应商S'),
(202501032501020, 202501031001020, '标准机构', 3, '5档', 'M020', '机构供应商T'),
(202501032501021, 202501031001021, '豪华机构', 4, '6档', 'M021', '机构供应商U'),
(202501032501022, 202501031001022, '标准机构', 3, '5档', 'M022', '机构供应商V'),
(202501032501023, 202501031001023, '豪华机构', 4, '6档', 'M023', '机构供应商W'),
(202501032501024, 202501031001024, '标准机构', 3, '5档', 'M024', '机构供应商X'),
(202501032501025, 202501031001025, '豪华机构', 4, '6档', 'M025', '机构供应商Y'),
(202501032501026, 202501031001026, '标准机构', 3, '5档', 'M026', '机构供应商Z'),
(202501032501027, 202501031001027, '豪华机构', 4, '6档', 'M027', '机构供应商AA'),
(202501032501028, 202501031001028, '标准机构', 3, '5档', 'M028', '机构供应商AB'),
(202501032501029, 202501031001029, '豪华机构', 4, '6档', 'M029', '机构供应商AC'),
(202501032501030, 202501031001030, '标准机构', 3, '5档', 'M030', '机构供应商AD');

-- 插入配件信息
INSERT INTO tb_fittings (id, product_id, fitting_number, description, quantity, material) VALUES
(202501032601001, 202501031001001, 1, 'M6螺丝', 8, '碳钢'),
(202501032601002, 202501031001002, 1, 'M6螺丝', 8, '碳钢'),
(202501032601003, 202501031001003, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601004, 202501031001004, 1, 'M6螺丝', 8, '碳钢'),
(202501032601005, 202501031001005, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601006, 202501031001006, 1, 'M6螺丝', 8, '碳钢'),
(202501032601007, 202501031001007, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601008, 202501031001008, 1, 'M6螺丝', 8, '碳钢'),
(202501032601009, 202501031001009, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601010, 202501031001010, 1, 'M6螺丝', 8, '碳钢'),
(202501032601011, 202501031001011, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601012, 202501031001012, 1, 'M6螺丝', 8, '碳钢'),
(202501032601013, 202501031001013, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601014, 202501031001014, 1, 'M6螺丝', 8, '碳钢'),
(202501032601015, 202501031001015, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601016, 202501031001016, 1, 'M6螺丝', 8, '碳钢'),
(202501032601017, 202501031001017, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601018, 202501031001018, 1, 'M6螺丝', 8, '碳钢'),
(202501032601019, 202501031001019, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601020, 202501031001020, 1, 'M6螺丝', 8, '碳钢'),
(202501032601021, 202501031001021, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601022, 202501031001022, 1, 'M6螺丝', 8, '碳钢'),
(202501032601023, 202501031001023, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601024, 202501031001024, 1, 'M6螺丝', 8, '碳钢'),
(202501032601025, 202501031001025, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601026, 202501031001026, 1, 'M6螺丝', 8, '碳钢'),
(202501032601027, 202501031001027, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601028, 202501031001028, 1, 'M6螺丝', 8, '碳钢'),
(202501032601029, 202501031001029, 1, 'M6螺丝', 8, '不锈钢'),
(202501032601030, 202501031001030, 1, 'M6螺丝', 8, '碳钢');

-- 插入产品图片信息
INSERT INTO tb_product_images (id, prod_id, front_img_path, side_img_path, back_img_path, angle_img_path) VALUES
(202501032701001, 202501031001001, @img1, @img2, @img3, @img4),
(202501032701002, 202501031001002, @img2, @img3, @img4, @img5),
(202501032701003, 202501031001003, @img3, @img4, @img5, @img1),
(202501032701004, 202501031001004, @img4, @img5, @img1, @img2),
(202501032701005, 202501031001005, @img5, @img1, @img2, @img3),
(202501032701006, 202501031001006, @img1, @img2, @img3, @img4),
(202501032701007, 202501031001007, @img2, @img3, @img4, @img5),
(202501032701008, 202501031001008, @img3, @img4, @img5, @img1),
(202501032701009, 202501031001009, @img4, @img5, @img1, @img2),
(202501032701010, 202501031001010, @img5, @img1, @img2, @img3),
(202501032701011, 202501031001011, @img1, @img2, @img3, @img4),
(202501032701012, 202501031001012, @img2, @img3, @img4, @img5),
(202501032701013, 202501031001013, @img3, @img4, @img5, @img1),
(202501032701014, 202501031001014, @img4, @img5, @img1, @img2),
(202501032701015, 202501031001015, @img5, @img1, @img2, @img3),
(202501032701016, 202501031001016, @img1, @img2, @img3, @img4),
(202501032701017, 202501031001017, @img2, @img3, @img4, @img5),
(202501032701018, 202501031001018, @img3, @img4, @img5, @img1),
(202501032701019, 202501031001019, @img4, @img5, @img1, @img2),
(202501032701020, 202501031001020, @img5, @img1, @img2, @img3),
(202501032701021, 202501031001021, @img1, @img2, @img3, @img4),
(202501032701022, 202501031001022, @img2, @img3, @img4, @img5),
(202501032701023, 202501031001023, @img3, @img4, @img5, @img1),
(202501032701024, 202501031001024, @img4, @img5, @img1, @img2),
(202501032701025, 202501031001025, @img5, @img1, @img2, @img3),
(202501032701026, 202501031001026, @img1, @img2, @img3, @img4),
(202501032701027, 202501031001027, @img2, @img3, @img4, @img5),
(202501032701028, 202501031001028, @img3, @img4, @img5, @img1),
(202501032701029, 202501031001029, @img4, @img5, @img1, @img2),
(202501032701030, 202501031001030, @img5, @img1, @img2, @img3);


SELECT COUNT(*) s_count 
FROM tb_quotation 
WHERE (
    (id LIKE '%S%') OR 
    (supplier LIKE '%S%') OR 
    (supplier_item_code LIKE '%S%') OR 
    (specification_details LIKE '%S%') OR 
    (net_weight_gross_weight LIKE '%S%') OR 
    (effective_vol LIKE '%S%') OR 
    (moq LIKE '%S%') OR 
    (sales_contacts LIKE '%S%') OR 
    (valid_period LIKE '%S%') OR 
    (port LIKE '%S%') OR 
    (remark LIKE '%S%')
);

select * from tb_products;

