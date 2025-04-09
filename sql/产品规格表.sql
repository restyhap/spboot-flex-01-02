CREATE TABLE tb_products (
    `id` varchar(255) NOT NULL COMMENT '序列',
    tccode VARCHAR(20) COMMENT '产品代码',
    supplier VARCHAR(50) COMMENT '供应商',
    supplier_code VARCHAR(20) COMMENT '供应商代码',
    supplier_name VARCHAR(100) COMMENT '供应商名称',
    fire_standard VARCHAR(50) COMMENT '防火标准',
    fob_20_container_price DECIMAL(10,2) COMMENT '20尺柜FOB价格',
    fob_40_container_price DECIMAL(10,2) COMMENT '40尺柜FOB价格',
    shipping_port VARCHAR(50) COMMENT '发货港口'
) COMMENT '产品基本信息表';

-- 面料信息表
CREATE TABLE tb_upholstery (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    fabric_manufacturer VARCHAR(100) COMMENT '面料制造商',
    colour_code VARCHAR(50) COMMENT '颜色代码',
    leather_grade VARCHAR(50) COMMENT '皮革等级',
    usage_per_chair DECIMAL(10,2) COMMENT '每把椅子用料(m²)'
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
    carton_volume DECIMAL(10,3) COMMENT '箱体体积(m³)'
) COMMENT '包装信息表';

-- 生产和物流信息表
CREATE TABLE tb_production_logistics (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    production_time INT COMMENT '生产时间(天)',
    effective_volume DECIMAL(10,3) COMMENT '有效体积(m³)',
    loading_quantity_20gp INT COMMENT '20尺标准柜装载数量',
    loading_quantity_40hc INT COMMENT '40尺高柜装载数量',
    net_weight DECIMAL(5,2) COMMENT '净重(kg)',
    gross_weight DECIMAL(5,2) COMMENT '毛重(kg)'
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
    overall_height_max INT COMMENT '整体最大高度(mm)'
) COMMENT '产品尺寸表';

-- 座椅内部结构表
CREATE TABLE tb_seat_inner_components (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    material_code VARCHAR(50) COMMENT '材料代码',
    thickness INT COMMENT '厚度(mm)',
    layers_count INT COMMENT '层数',
    dimensions VARCHAR(50) COMMENT '尺寸规格'
) COMMENT '座椅内部结构表';

-- 背部内部结构表
CREATE TABLE tb_seat_inner_components (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    material_code VARCHAR(50) COMMENT '材料代码',
    thickness INT COMMENT '厚度(mm)',
    layers_count INT COMMENT '层数',
    dimensions VARCHAR(50) COMMENT '尺寸规格'
) COMMENT '背部内部结构表';


-- 背部外部结构表
CREATE TABLE tb_back_outer_components (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    material VARCHAR(100) COMMENT '材料',
    dimensions VARCHAR(100) COMMENT '尺寸(宽x深/宽x高)',
    manufacturer_name VARCHAR(100) COMMENT '制造商名称'
) COMMENT '背部外部结构表';

-- 座椅外部结构表
CREATE TABLE tb_back_outer_components (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    material VARCHAR(100) COMMENT '材料',
    dimensions VARCHAR(100) COMMENT '尺寸(宽x深/宽x高)',
    manufacturer_name VARCHAR(100) COMMENT '制造商名称'
) COMMENT '座椅外部结构表';

-- 扶手信息表
CREATE TABLE tb_arms (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    material VARCHAR(100) COMMENT '材料',
    type VARCHAR(50) COMMENT '类型',
    manufacturer VARCHAR(100) COMMENT '制造商',
    description TEXT COMMENT '描述',
    arm_height_from_seat INT COMMENT '扶手距座面高度(mm)',
    arm_height_from_floor INT COMMENT '扶手距地面高度(mm)'
) COMMENT '扶手信息表';

-- 泡棉信息表
CREATE TABLE tb_foam_details (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    seat_density INT COMMENT '座椅密度(kg/m³)',
    back_density INT COMMENT '靠背密度(kg/m³)',
    seat_thickness INT COMMENT '座椅厚度(mm)',
    back_thickness INT COMMENT '靠背厚度(mm)'
) COMMENT '泡棉信息表';

-- 脚轮信息表
CREATE TABLE tb_castors (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    pin_thickness INT COMMENT '销轴直径(mm)',
    wheel_diameter INT COMMENT '轮子直径(mm)'
) COMMENT '脚轮信息表';

-- 底座信息表
CREATE TABLE tb_base (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    size_diameter INT COMMENT '底盘直径(mm)',
    material VARCHAR(50) COMMENT '材料',
    type VARCHAR(50) COMMENT '类型'
) COMMENT '底座信息表';

-- 气压棒信息表
CREATE TABLE tb_gas_lift (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    gas_lift_class VARCHAR(20) COMMENT '气压等级',
    casing_length INT COMMENT '外管长度(mm)',
    extension_size INT COMMENT '行程(mm)',
    taper INT COMMENT '锥度(mm)'
) COMMENT '气压棒信息表';

-- 气压罩信息表
CREATE TABLE tb_gas_lift_cover (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    material VARCHAR(50) COMMENT '材料',
    colour VARCHAR(50) COMMENT '颜色'
) COMMENT '气压罩信息表';

-- 机构信息表
CREATE TABLE tb_mechanism (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    description VARCHAR(100) COMMENT '描述',
    levers_count INT COMMENT '手柄数量',
    locking_positions VARCHAR(50) COMMENT '锁定位置',
    model_no VARCHAR(50) COMMENT '型号',
    supplier_name VARCHAR(100) COMMENT '机构供应商'
) COMMENT '机构信息表';

-- 配件信息表
CREATE TABLE tb_fittings (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id INT COMMENT '产品ID',
    fitting_number INT COMMENT '配件编号',
    description VARCHAR(100) COMMENT '描述',
    quantity INT COMMENT '数量',
    material VARCHAR(50) COMMENT '材料'
) COMMENT '配件信息表';

-- 规格图片表
CREATE TABLE `tb_prod_image`  (
    `id` int NOT NULL COMMENT '规格_图片表 ID',
    `prod_id` int NOT NULL COMMENT '规格表ID',
    `front_img_path` varchar(255) NULL COMMENT '正视图路径',
    `side_img_path` varchar(255) NULL COMMENT '侧视图路径',
    `back_view_path` varchar(255) NULL COMMENT '背视图路径',
    `angle_view_path` varchar(255) NULL COMMENT '角视图路径',
    PRIMARY KEY (`id`)
) COMMENT = '规格图片表';


