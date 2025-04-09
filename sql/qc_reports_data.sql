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