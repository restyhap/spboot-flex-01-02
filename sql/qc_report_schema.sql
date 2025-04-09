-- 质检报告基本信息表
CREATE TABLE tb_qc_reports (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    model_code VARCHAR(50) COMMENT '型号代码',
    factory_code VARCHAR(50) COMMENT '工厂代码',
    supplier VARCHAR(50) COMMENT '供应商',
    client VARCHAR(50) COMMENT '客户',
    po_number VARCHAR(50) COMMENT 'PO编号',
    inspection_date DATE COMMENT '检验日期',
    order_qty INT COMMENT '订单数量',
    report_date DATE COMMENT '报告日期',
    inspect_qty INT COMMENT '检验数量',
    qc_officer VARCHAR(50) COMMENT '质检员',
    pass_fail ENUM('Pass', 'Fail') COMMENT '通过/失败',
    second_qc_date DATE COMMENT '二次质检日期',
    comments TEXT COMMENT '评价内容',
    -- 产品外观图片
    stocks_in_warehouse VARCHAR(255) COMMENT '仓库库存图片',
    sampling_of_products_quantity VARCHAR(255) COMMENT '产品抽样数量图片',
    shipping_marks VARCHAR(255) COMMENT '运输标记图片',
    barcode VARCHAR(255) COMMENT '条形码图片',
    packing_outside VARCHAR(255) COMMENT '外包装图片',
    packing_inside VARCHAR(255) COMMENT '内包装图片',
    -- 椅子组件图片
    chair_components_packed VARCHAR(255) COMMENT '椅子组件-已包装图片',
    chair_components_unpacked VARCHAR(255) COMMENT '椅子组件-未包装图片',
    -- 配件包图片
    fitting_pack_packed VARCHAR(255) COMMENT '配件包-已包装图片',
    fitting_pack_unpacked VARCHAR(255) COMMENT '配件包-未包装图片',
    -- 标签和说明图片
    production_label VARCHAR(255) COMMENT '生产标签图片',
    assembly_instructions VARCHAR(255) COMMENT '组装说明图片',
    -- 组件图片
    image_of_components_seat VARCHAR(255) COMMENT '组件图片-座椅',
    image_of_components_back VARCHAR(255) COMMENT '组件图片-靠背',
    image_of_components_base VARCHAR(255) COMMENT '组件图片-底座',
    image_of_components_castors VARCHAR(255) COMMENT '组件图片-脚轮',
    image_of_components_gas_lift_cover VARCHAR(255) COMMENT '组件图片-气压棒外罩',
    image_of_components_gas_lift_stamp VARCHAR(255) COMMENT '组件图片-气压棒标记',
    image_of_components_armrest VARCHAR(255) COMMENT '组件图片-扶手',
    image_of_component_mechanism VARCHAR(255) COMMENT '组件图片-机构',
    image_of_components_headrest VARCHAR(255) COMMENT '组件图片-头枕',
    -- 成品图片
    image_of_product_built_front VARCHAR(255) COMMENT '成品图片-正视图',
    image_of_product_built_side VARCHAR(255) COMMENT '成品图片-侧视图',
    image_of_product_built_back VARCHAR(255) COMMENT '成品图片-背视图',
    image_of_product_built_45_degree VARCHAR(255) COMMENT '成品图片-45度视图',
    front_image_of_product_built_compare_1 VARCHAR(255) COMMENT '成品图片-样品对比图1',
    front_image_of_product_built_compare_2 VARCHAR(255) COMMENT '成品图片-样品对比图2',
    -- 功能检查图片
    function_check_seat_height_extension VARCHAR(255) COMMENT '功能检查-座椅高度调节',
    function_check_mechanism_adjustment VARCHAR(255) COMMENT '功能检查-机构调节',
    function_check_armrest_adjustment VARCHAR(255) COMMENT '功能检查-扶手调节',
    function_check_headrest_adjustment VARCHAR(255) COMMENT '功能检查-头枕调节',
    function_check_other1 VARCHAR(255) COMMENT '功能检查-其他1',
    function_check_other2 VARCHAR(255) COMMENT '功能检查-其他2',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '质检报告基本信息表';

-- 缺陷记录表
CREATE TABLE tb_defects (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    report_id INT COMMENT '质检报告ID',
    defect_title VARCHAR(100) COMMENT '缺陷标题',
    defect_description TEXT COMMENT '缺陷描述',
    improvement_suggestion TEXT COMMENT '改进建议',
    inspector VARCHAR(50) COMMENT '检查人员',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '缺陷记录表';

-- 缺陷图片关联表
CREATE TABLE tb_defect_images (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    defect_id INT COMMENT '缺陷ID',
    image_path VARCHAR(255) COMMENT '图片路径',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '缺陷图片关联表';

