/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : spring_boot_distribute

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-11-02 21:39:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crm_order
-- ----------------------------
DROP TABLE IF EXISTS `crm_order`;
CREATE TABLE `crm_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_active` int(255) DEFAULT '1' COMMENT '是否有效（1=有效；0=无效）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_mobile` (`mobile`,`is_active`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='crm抢单记录表';

-- ----------------------------
-- Records of crm_order
-- ----------------------------
INSERT INTO `crm_order` VALUES ('60', '233', '15627284603', '2018-10-31 22:05:17', null, '1');
INSERT INTO `crm_order` VALUES ('61', '233', '15627284604', '2018-10-31 22:11:23', null, '1');
INSERT INTO `crm_order` VALUES ('72', '234', '15627284605', '2018-10-31 22:15:07', null, '1');
INSERT INTO `crm_order` VALUES ('73', '236', '15627284606', '2018-10-31 22:22:28', null, '1');
INSERT INTO `crm_order` VALUES ('138', '235', '15627284607', '2018-10-31 22:25:30', null, '1');

-- ----------------------------
-- Table structure for product_lock
-- ----------------------------
DROP TABLE IF EXISTS `product_lock`;
CREATE TABLE `product_lock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_no` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `stock` int(11) DEFAULT NULL COMMENT '库存量',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='产品信息-数据库级别lock测试';

-- ----------------------------
-- Records of product_lock
-- ----------------------------
INSERT INTO `product_lock` VALUES ('1', '10010', '1', '58', '2018-10-17 21:16:33', '2018-10-24 23:03:27');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('233', 'debug', 'debug@126.com', '2018-10-27 11:23:24', null);
INSERT INTO `user` VALUES ('234', 'SteadyJack', 'debug@126.com', '2018-10-27 11:23:24', null);
INSERT INTO `user` VALUES ('235', 'linsen', 'debug@126.com', '2018-10-27 11:23:24', null);
INSERT INTO `user` VALUES ('236', 'jack', 'debug@126.com', '2018-10-27 11:23:24', null);
