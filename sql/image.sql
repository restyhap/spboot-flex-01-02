CREATE TABLE `tb_images` (
  `id` varchar(255) NOT NULL COMMENT '序列',
  `prod_id` varchar(255) NOT NULL COMMENT '规格表ID',
  `front_img_path` varchar(255) NULL COMMENT '正视图路径',
  `side_img_path` varchar(255) NULL COMMENT '侧视图路径',
  `back_view_path` varchar(255) NULL COMMENT '背视图路径',
  `angle_view_path` varchar(255) NULL COMMENT '角视图路径',
  PRIMARY KEY (`id`)
) COMMENT = '规格图片表';

