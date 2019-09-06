-- 商品分类
create table `liteshop_goods_category` (
  `id` int not null auto_increment,
  `parent_id` int DEFAULT 0 COMMENT '上级分类的编号：0表示一级分类',
  `name` varchar(64) not null comment '分类名称',
  `level` int(1) NOT NULL DEFAULT 1 COMMENT '分类级别：1->1级；2->2级',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '图标',
  `description` text COMMENT '描述',

  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者',
  `created_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新者',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';

INSERT INTO `liteshop_goods_category` (`id`,`parent_id`, `name`, `level`, `sort`)
VALUES
	(1,0,'服装',1,0),
	(2,0,'手机数码',1,1),
	(3,0,'家用电器',1,2),
	(11,1,'外套',2,0),
	(12,1,'T恤',2,1),
	(13,1,'休闲裤',2,2),
	(14,1,'牛仔裤',2,3),
	(15,1,'衬衫',2,4),
	(21,2,'手机配件',2,0),
	(22,2,'摄影摄像',2,1),
	(23,2,'影音娱乐',2,2),
	(24,2,'数码配件',2,3),
	(25,2,'手机通讯',2,4),
	(31,3,'电视',2,0),
	(32,3,'空调',2,1),
	(33,3,'洗衣机',2,2),
	(34,3,'冰箱',2,3);



-- 商品
create table `liteshop_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) not null comment '商品名称',
  `picture` varchar(255) NOT NULL DEFAULT '' comment '商品主图',
  `price` decimal(10,2) not null comment '价格',
  `original_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '市场价',
  `goods_sn` varchar(64) NOT NULL COMMENT '货号',
  `publish_status` int(1) NOT NULL DEFAULT '0' COMMENT '上架状态：1->上架、2->下架',
  `verify_status` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态：0->未审核；1->审核通过；2->审核不通过',
  `stock` int NOT NULL DEFAULT '0' comment '库存',
  `unit` varchar(16) NOT NULL COMMENT '单位',
  `description` varchar(64) NOT NULL DEFAULT '' comment '商品描述',
  `album_pictures` varchar(255) NOT NULL DEFAULT '' COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
  `detail_html` text COMMENT '产品详情网页内容',
  `detail_mobile_html` text COMMENT '移动端网页详情',


  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者',
  `created_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新者',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

INSERT INTO `liteshop_goods` (`id`, `name`, `picture`, `price`, `goods_sn`, `publish_status`, `verify_status`, `original_price`, `stock`, `unit`, `description`, `album_pictures`, `detail_html`, `detail_mobile_html`)
VALUES
	(1, '女式超柔软拉毛运动开衫', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 249.00, 'No86577', 1, 1, 280.00, 10, '件', '女式超柔软拉毛运动开衫', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', '', ''),
	(2, '小米 红米5A 全网通版 3GB+32GB 香槟金 移动联通电信4G手机 双卡双待', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', 649.00, '7437789', 1, 1, 649.00, 10, '', '8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '', '');



-- 商品和分类关系表
create table `liteshop_relation_goods_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL DEFAULT 0 comment '商品id',
  `goods_category_id`int NOT NULL DEFAULT 0 comment '商品分类id',

  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者',
  `created_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新者',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  primary key (`id`),
  UNIQUE KEY `uniq_goods_category` (`goods_id`, `goods_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品和分类关系表';