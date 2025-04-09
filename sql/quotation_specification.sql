-- 报价单
CREATE TABLE `tb_quotation`  (
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




