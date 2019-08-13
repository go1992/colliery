/*
Navicat MySQL Data Transfer

Source Server         : 47.103.35.95
Source Server Version : 80015
Source Host           : 47.103.35.95:3306
Source Database       : coal_mine_kh

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-08-07 13:49:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aqfx_csxg
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_csxg`;
CREATE TABLE `aqfx_csxg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '辨识时间 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fxd` varchar(256) DEFAULT NULL COMMENT '风险点 ',
  `dtwzdd` varchar(257) DEFAULT NULL COMMENT '地图位置定点 ',
  `zrdw` varchar(258) DEFAULT NULL COMMENT '责任单位 ',
  `fxdj` varchar(259) DEFAULT NULL COMMENT '风险等级 ',
  `fxfl` varchar(260) DEFAULT NULL COMMENT '风险分类 ',
  `xzrs` varchar(261) DEFAULT NULL COMMENT '限制人数 ',
  `fxms` varchar(263) DEFAULT NULL COMMENT '风险描述 ',
  `gkcs` varchar(264) DEFAULT NULL COMMENT '管控措施 ',
  `zyfzr` varchar(265) DEFAULT NULL COMMENT '主要负责人 ',
  `fgfzr` varchar(266) DEFAULT NULL COMMENT '分管负责人 ',
  `fxlx` varchar(255) DEFAULT NULL COMMENT '类型(年度风险,专项风险)',
  `xgrq` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  `csbh` varchar(255) DEFAULT NULL COMMENT '措施变化',
  `ysid` bigint(20) DEFAULT NULL COMMENT '原始数据id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全风险管控-管控措施修改记录';

-- ----------------------------
-- Table structure for aqfx_fxgz
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_fxgz`;
CREATE TABLE `aqfx_fxgz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '辨识日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `leixing` varchar(255) DEFAULT NULL COMMENT '类型 ',
  `fxd` varchar(255) DEFAULT NULL COMMENT '风险点 ',
  `zrdw` varchar(255) DEFAULT NULL COMMENT '责任单位 ',
  `fxdj` varchar(255) DEFAULT NULL COMMENT '风险等级 ',
  `fxfl` varchar(255) DEFAULT NULL COMMENT '风险分类 ',
  `xzrs` varchar(255) DEFAULT NULL COMMENT '限制人数 ',
  `fxms` varchar(255) DEFAULT NULL COMMENT '风险描述 ',
  `zgcs` varchar(255) DEFAULT NULL COMMENT '整改措施 ',
  `gzjl` varchar(255) DEFAULT NULL COMMENT '跟踪记录 ',
  `gzsm` varchar(255) DEFAULT NULL COMMENT '跟踪说明 ',
  `gzry` varchar(255) DEFAULT NULL COMMENT '跟踪人员 ',
  `gzsj` varchar(255) DEFAULT NULL COMMENT '跟踪时间 ',
  `fakuan` varchar(255) DEFAULT NULL COMMENT '罚款 ',
  `xiaoguo` varchar(255) DEFAULT NULL COMMENT '效果 ',
  `sfsb` varchar(255) DEFAULT NULL COMMENT '是否上报 ',
  `sfxj` varchar(255) DEFAULT NULL COMMENT '是否消警 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控-风险跟踪及报表';

-- ----------------------------
-- Table structure for aqfx_gkxg
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_gkxg`;
CREATE TABLE `aqfx_gkxg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `zzr` varchar(255) DEFAULT NULL COMMENT '组织人 ',
  `zhiwu` varchar(255) DEFAULT NULL COMMENT '职务 ',
  `leibie` varchar(255) DEFAULT NULL COMMENT '类别 ',
  `gkzdssqk` varchar(255) DEFAULT NULL COMMENT '管控重点实施情况 ',
  `czwt` varchar(255) DEFAULT NULL COMMENT '存在问题 ',
  `gkcsdzqk` varchar(255) DEFAULT NULL COMMENT '管控措施调整情况 ',
  `ydzd` varchar(255) DEFAULT NULL COMMENT '月度重点 ',
  `zrfg` varchar(255) DEFAULT NULL COMMENT '责任分工 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控-管控效果及分析';

-- ----------------------------
-- Table structure for aqfx_jbcs_fxlx
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_jbcs_fxlx`;
CREATE TABLE `aqfx_jbcs_fxlx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fxlx` varchar(255) DEFAULT NULL COMMENT '风险类型 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控-基本参数设置-风险类型设置';

-- ----------------------------
-- Table structure for aqfx_jbcs_kld
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_jbcs_kld`;
CREATE TABLE `aqfx_jbcs_kld` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `kld` varchar(255) DEFAULT NULL COMMENT '矿领导 ',
  `zhiwu` varchar(255) DEFAULT NULL COMMENT '职务 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控-基本参数设置-矿领导设置';

-- ----------------------------
-- Table structure for aqfx_ldgz
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_ldgz`;
CREATE TABLE `aqfx_ldgz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '辨识日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `weizhi` varchar(255) DEFAULT NULL COMMENT '位置 ',
  `dbld` varchar(255) DEFAULT NULL COMMENT '带班领导 ',
  `djry` varchar(255) DEFAULT NULL COMMENT '对接人员 ',
  `rjsj` varchar(255) DEFAULT NULL COMMENT '入井时间 ',
  `sjsj` varchar(255) DEFAULT NULL COMMENT '升井时间 ',
  `banci` varchar(255) DEFAULT NULL COMMENT '班次 ',
  `xzlx` varchar(255) DEFAULT NULL COMMENT '行走录像 ',
  `lednr` varchar(255) DEFAULT NULL COMMENT 'LED内容 ',
  `zdfxgkzg` varchar(255) DEFAULT NULL COMMENT '重大风险管控整改 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全风险管控-领导跟踪检查';

-- ----------------------------
-- Table structure for aqfx_ndfx
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_ndfx`;
CREATE TABLE `aqfx_ndfx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '辨识时间 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fxd` varchar(256) DEFAULT NULL COMMENT '风险点 ',
  `dtwzdd` varchar(257) DEFAULT NULL COMMENT '地图位置定点 ',
  `zrdw` varchar(258) DEFAULT NULL COMMENT '责任单位 ',
  `fxdj` varchar(259) DEFAULT NULL COMMENT '风险等级 ',
  `fxfl` varchar(260) DEFAULT NULL COMMENT '风险分类 ',
  `xzrs` varchar(261) DEFAULT NULL COMMENT '限制人数 ',
  `fxms` varchar(263) DEFAULT NULL COMMENT '风险描述 ',
  `gkcs` varchar(264) DEFAULT NULL COMMENT '管控措施 ',
  `zyfzr` varchar(265) DEFAULT NULL COMMENT '主要负责人 ',
  `fgfzr` varchar(266) DEFAULT NULL COMMENT '分管负责人 ',
  `fxlx` varchar(255) DEFAULT NULL COMMENT '类型(年度风险,专项风险)',
  `datetype` varchar(16) DEFAULT NULL COMMENT '辨识时间类型',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全风险管控-年度风险辨识';

-- ----------------------------
-- Table structure for colliery_auth
-- ----------------------------
DROP TABLE IF EXISTS `colliery_auth`;
CREATE TABLE `colliery_auth` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `auth_name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `auth_level` int(5) DEFAULT NULL COMMENT '权限级别',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_base_config
-- ----------------------------
DROP TABLE IF EXISTS `colliery_base_config`;
CREATE TABLE `colliery_base_config` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `value` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(10) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `modify_user` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_department
-- ----------------------------
DROP TABLE IF EXISTS `colliery_department`;
CREATE TABLE `colliery_department` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `department_name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `auth_ids` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id列表(以逗号分隔)',
  `coal_mine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属煤矿',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_entry_dispatcher
-- ----------------------------
DROP TABLE IF EXISTS `colliery_entry_dispatcher`;
CREATE TABLE `colliery_entry_dispatcher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `mine_people` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL COMMENT '行走路线',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_file_white_list
-- ----------------------------
DROP TABLE IF EXISTS `colliery_file_white_list`;
CREATE TABLE `colliery_file_white_list` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `coal` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '煤矿名称',
  `depart` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称 ',
  `system` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件所属系统',
  `menu` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件所属菜单',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_handover
-- ----------------------------
DROP TABLE IF EXISTS `colliery_handover`;
CREATE TABLE `colliery_handover` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL COMMENT '班次',
  `succession_dispatcher` varchar(255) DEFAULT NULL COMMENT '接班调度员',
  `handover_dispatcher` varchar(255) DEFAULT NULL,
  `handover_date` datetime DEFAULT NULL COMMENT '交接时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '本班情况及遗留问题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_key_project
-- ----------------------------
DROP TABLE IF EXISTS `colliery_key_project`;
CREATE TABLE `colliery_key_project` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `shift` varchar(255) DEFAULT NULL COMMENT '班次',
  `lane_name` varchar(255) DEFAULT NULL COMMENT '巷道名称',
  `mine_area_name` varchar(255) DEFAULT NULL COMMENT '采区名称',
  `construction_team` varchar(255) DEFAULT NULL COMMENT '施工队',
  `total_engineering` varchar(255) DEFAULT NULL,
  `class_schedule` varchar(255) DEFAULT NULL COMMENT '班进度',
  `cumulative_completion` varchar(255) DEFAULT NULL COMMENT '累计完成',
  `remaining_work` varchar(255) DEFAULT NULL,
  `schedule_operator` varchar(255) DEFAULT NULL COMMENT '值班调度员',
  `remark` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_raw_coal_production
-- ----------------------------
DROP TABLE IF EXISTS `colliery_raw_coal_production`;
CREATE TABLE `colliery_raw_coal_production` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `mine_peoples` varchar(255) DEFAULT NULL,
  `output` varchar(255) DEFAULT NULL,
  `sales_volume` varchar(255) DEFAULT NULL COMMENT '销售量',
  `stock` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原煤产量',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_role
-- ----------------------------
DROP TABLE IF EXISTS `colliery_role`;
CREATE TABLE `colliery_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `auth_level` int(5) DEFAULT NULL COMMENT '权限等级',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_safety_info
-- ----------------------------
DROP TABLE IF EXISTS `colliery_safety_info`;
CREATE TABLE `colliery_safety_info` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `shift` varchar(20) DEFAULT NULL COMMENT '班次',
  `date` date DEFAULT NULL,
  `duty_manager` varchar(20) DEFAULT NULL COMMENT '值班矿长',
  `mine_manager` varchar(20) DEFAULT NULL,
  `scheduling_operater` varchar(20) DEFAULT NULL COMMENT '调度值班员',
  `monitor_operater` varchar(20) DEFAULT NULL COMMENT '监控值班员',
  `output` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '产量',
  `mine_peoples` varchar(20) DEFAULT '' COMMENT '入井人数',
  `working_condition` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作情况',
  `mining_advancement_condition` varchar(255) DEFAULT NULL COMMENT '采面推进情况',
  `digging_length` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '掘进进尺',
  `maintenance_length` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '维修进尺',
  `daily_output` varchar(255) DEFAULT NULL,
  `daily_digging_length` varchar(20) DEFAULT NULL,
  `daily_maintenance_length` varchar(20) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_schedule_duty
-- ----------------------------
DROP TABLE IF EXISTS `colliery_schedule_duty`;
CREATE TABLE `colliery_schedule_duty` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `dispatcher` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `handover_date` datetime DEFAULT NULL,
  `class_leader` varchar(255) DEFAULT NULL,
  `mine_peoples` varchar(255) DEFAULT NULL,
  `tunneling_team` varchar(255) DEFAULT NULL,
  `machine_team` varchar(255) DEFAULT NULL,
  `manager` varchar(255) DEFAULT NULL,
  `coal_mining_face` varchar(255) DEFAULT NULL,
  `tunneling_face` varchar(255) DEFAULT NULL,
  `output` varchar(255) DEFAULT NULL,
  `digging_length` varchar(255) DEFAULT NULL,
  `maintenance_length` varchar(255) DEFAULT NULL,
  `remark1` varchar(255) DEFAULT NULL,
  `remark2` varchar(255) DEFAULT NULL,
  `remark3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_sign_info
-- ----------------------------
DROP TABLE IF EXISTS `colliery_sign_info`;
CREATE TABLE `colliery_sign_info` (
  `id` int(255) NOT NULL,
  `unsafe_id` int(255) DEFAULT NULL COMMENT '对应隐患数据的id',
  `sign_status` varchar(255) DEFAULT NULL COMMENT '确认状态:0分发未确认,1已确认,2分发待确认',
  `sign_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '确认日期',
  `sign_depart_id` int(255) DEFAULT NULL COMMENT '分发部门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_unsafe_info
-- ----------------------------
DROP TABLE IF EXISTS `colliery_unsafe_info`;
CREATE TABLE `colliery_unsafe_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `depart_id` int(20) DEFAULT NULL COMMENT '处理的部门Id',
  `strat_depart_id` int(20) DEFAULT NULL,
  `plan_name` varchar(20) DEFAULT NULL,
  `coal_mine` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据所属煤矿 ',
  `check_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '检查类型 ',
  `location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '隐患地点 ',
  `content` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '隐患内容 ',
  `reform_measure` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '整改措施 ',
  `reform_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '整改日期 ',
  `duty_depart` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '责任单位 ',
  `duty_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '责任人 ',
  `type` varchar(255) DEFAULT NULL COMMENT '隐患类型 ',
  `level` varchar(255) DEFAULT NULL COMMENT '隐患级别 ',
  `fine_reason` varchar(255) DEFAULT NULL COMMENT '罚款理由 ',
  `fine_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '罚款金额 ',
  `fine_find` varchar(255) DEFAULT NULL COMMENT '罚款查找 ',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '隐患状态 0:未处理,1:已处理,2:超时',
  `distributed_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分发状态 0:未分发 1:已分发',
  `sign_status` varchar(255) DEFAULT NULL,
  `deal_process` varchar(255) DEFAULT NULL COMMENT '处理过程跟踪 ',
  `check_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '检查人 ',
  `check_time` datetime DEFAULT NULL COMMENT '检查时间 ',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_user` varchar(20) DEFAULT NULL,
  `extra1` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `extra2` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `extra3` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-隐患录入';

-- ----------------------------
-- Table structure for colliery_unsafe_plan
-- ----------------------------
DROP TABLE IF EXISTS `colliery_unsafe_plan`;
CREATE TABLE `colliery_unsafe_plan` (
  `plan_id` int(255) NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计划名称',
  `plan_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排查内容',
  `plan_depart_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排查部门',
  `plan_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排查类型',
  `plan_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排查地点',
  `plan_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '计划日期',
  `create_user` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_user
-- ----------------------------
DROP TABLE IF EXISTS `colliery_user`;
CREATE TABLE `colliery_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `role_id` int(20) DEFAULT NULL COMMENT '角色id',
  `depart_id` int(20) DEFAULT NULL COMMENT '部门id',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `user_pwd` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `file_auth` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件管理权限',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改时间',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`user_name`) USING BTREE COMMENT '用户名唯一'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dc_jbcs_kqwz
-- ----------------------------
DROP TABLE IF EXISTS `dc_jbcs_kqwz`;
CREATE TABLE `dc_jbcs_kqwz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `xjwz` varchar(255) DEFAULT NULL COMMENT '巡检位置 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地测信息管理-基本参数-矿区位置';

-- ----------------------------
-- Table structure for dc_jbcs_yh
-- ----------------------------
DROP TABLE IF EXISTS `dc_jbcs_yh`;
CREATE TABLE `dc_jbcs_yh` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `yhmc` varchar(255) DEFAULT NULL COMMENT '用户名称 ',
  `yhmm` varchar(255) DEFAULT NULL COMMENT '用户密码 ',
  `yhfl` varchar(255) DEFAULT NULL COMMENT '用户分类 ',
  `xssx` varchar(255) DEFAULT NULL COMMENT '显示顺序 ',
  `dlmc` varchar(255) DEFAULT NULL COMMENT '登陆名称 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地测信息管理-基本参数-用户';

-- ----------------------------
-- Table structure for dc_swdz_zkpc
-- ----------------------------
DROP TABLE IF EXISTS `dc_swdz_zkpc`;
CREATE TABLE `dc_swdz_zkpc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期',
  `kkrq` timestamp NULL DEFAULT NULL COMMENT '开孔日期',
  `zkwz` varchar(255) DEFAULT NULL COMMENT '钻孔位置',
  `zkbh` varchar(255) DEFAULT NULL COMMENT '钻孔编号',
  `swqk` varchar(255) DEFAULT NULL COMMENT '水位情况',
  `knzk` varchar(255) DEFAULT NULL COMMENT '孔内状况',
  `kkqk` varchar(255) DEFAULT NULL COMMENT '孔口情况',
  `sjks` varchar(255) DEFAULT NULL COMMENT '设计孔深',
  `shijks` varchar(255) DEFAULT NULL COMMENT '实际孔深',
  `jlr` varchar(255) DEFAULT NULL COMMENT '记录人',
  `shhr` varchar(255) DEFAULT NULL COMMENT '审核人',
  `ywxq` varchar(255) DEFAULT NULL COMMENT '有无险情',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='地测信息管理-水文地质钻孔排查';

-- ----------------------------
-- Table structure for dc_swxq_yjcl
-- ----------------------------
DROP TABLE IF EXISTS `dc_swxq_yjcl`;
CREATE TABLE `dc_swxq_yjcl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `zrdw` varchar(256) DEFAULT NULL COMMENT '责任单位 ',
  `didian` varchar(257) DEFAULT NULL COMMENT '地点 ',
  `qkms` varchar(258) DEFAULT NULL COMMENT '情况描述 ',
  `clcs` varchar(259) DEFAULT NULL COMMENT '处理措施 ',
  `wxdj` varchar(260) DEFAULT NULL COMMENT '危险等级 ',
  `zlxg` varchar(261) DEFAULT NULL COMMENT '治理效果 ',
  `jlr` varchar(262) DEFAULT NULL COMMENT '记录人 ',
  `shhr` varchar(263) DEFAULT NULL COMMENT '审核人 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='地测信息管理-水文险情及预警处理';

-- ----------------------------
-- Table structure for ddxx_jb_scdw
-- ----------------------------
DROP TABLE IF EXISTS `ddxx_jb_scdw`;
CREATE TABLE `ddxx_jb_scdw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `scdw` varchar(255) DEFAULT NULL COMMENT '生产单位 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度信息管理-基本信息设置-生产单位设置';

-- ----------------------------
-- Table structure for ddxx_scdd
-- ----------------------------
DROP TABLE IF EXISTS `ddxx_scdd`;
CREATE TABLE `ddxx_scdd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `lrry` varchar(255) DEFAULT NULL COMMENT '录入人员 ',
  `scdw` varchar(255) DEFAULT NULL COMMENT '生产单位 ',
  `banci` varchar(255) DEFAULT NULL COMMENT '班次 ',
  `sgdwrs` varchar(255) DEFAULT NULL COMMENT '施工单位人数 ',
  `jxqbrs` varchar(255) DEFAULT NULL COMMENT '井下全部人数 ',
  `chanliang` varchar(255) DEFAULT NULL COMMENT '产量 ',
  `sgdd` varchar(255) DEFAULT NULL COMMENT '施工地点 ',
  `jinchi` varchar(255) DEFAULT NULL COMMENT '进尺 ',
  `aqxxjwt` varchar(255) DEFAULT NULL COMMENT '安全信息及问题 ',
  `qtsx` varchar(255) DEFAULT NULL COMMENT '其他事项 ',
  `ddy` varchar(255) DEFAULT NULL COMMENT '调度员 ',
  `jky` varchar(255) DEFAULT NULL COMMENT '监控员 ',
  `zbld` varchar(255) DEFAULT NULL COMMENT '值班领导 ',
  `sfsh` varchar(255) DEFAULT NULL COMMENT '是否审核 ',
  `sfsg` varchar(255) DEFAULT NULL COMMENT '是否事故 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='调度信息管理-安全生产调度报表';

-- ----------------------------
-- Table structure for ddxx_scsg
-- ----------------------------
DROP TABLE IF EXISTS `ddxx_scsg`;
CREATE TABLE `ddxx_scsg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `lrry` varchar(255) DEFAULT NULL COMMENT '录入人员 ',
  `sgdd` varchar(255) DEFAULT NULL COMMENT '施工地点 ',
  `banci` varchar(255) DEFAULT NULL COMMENT '班次 ',
  `sgyy` varchar(255) DEFAULT NULL COMMENT '事故原因 ',
  `sgjb` varchar(255) DEFAULT NULL COMMENT '事故级别 ',
  `swrs` varchar(255) DEFAULT NULL COMMENT '伤亡人数 ',
  `jjss` varchar(255) DEFAULT NULL COMMENT '经济损失 ',
  `clgc` varchar(255) DEFAULT NULL COMMENT '处理过程 ',
  `ldps` varchar(255) DEFAULT NULL COMMENT '领导批示 ',
  `cljg` varchar(255) DEFAULT NULL COMMENT '处理结果 ',
  `zrzj` varchar(255) DEFAULT NULL COMMENT '责任追究 ',
  `ddy` varchar(255) DEFAULT NULL COMMENT '调度员 ',
  `jky` varchar(255) DEFAULT NULL COMMENT '监控员 ',
  `zbld` varchar(255) DEFAULT NULL COMMENT '值班领导 ',
  `sfsbsj` varchar(255) DEFAULT NULL COMMENT '是否上报上级 ',
  `sfclwb` varchar(255) DEFAULT NULL COMMENT '是否处理完毕 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度信息管理-生产安全事故处理追踪';

-- ----------------------------
-- Table structure for pxgl_sjlb
-- ----------------------------
DROP TABLE IF EXISTS `pxgl_sjlb`;
CREATE TABLE `pxgl_sjlb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '培训时间',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `jhpxrs` varchar(255) DEFAULT NULL COMMENT '计划培训人数',
  `sjpxrs` varchar(255) DEFAULT NULL COMMENT '实际培训人数',
  `cjry` varchar(255) DEFAULT NULL COMMENT '参加人员',
  `rydw` varchar(255) DEFAULT NULL COMMENT '人员单位',
  `pxnr` varchar(255) DEFAULT NULL COMMENT '培训内容',
  `pxsj` timestamp NULL DEFAULT NULL COMMENT '排查日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='培训管理-数据列表';

-- ----------------------------
-- Table structure for sbgl_sblb
-- ----------------------------
DROP TABLE IF EXISTS `sbgl_sblb`;
CREATE TABLE `sbgl_sblb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `sbbm` varchar(255) DEFAULT NULL COMMENT '设备编码',
  `sbmc` varchar(255) DEFAULT NULL COMMENT '设备名称',
  `ggxh` varchar(255) DEFAULT NULL COMMENT '规格型号',
  `sccj` varchar(255) DEFAULT NULL COMMENT '生产厂家',
  `ccrq` varchar(255) DEFAULT NULL COMMENT '出厂日期',
  `ccbh` varchar(255) DEFAULT NULL COMMENT '出厂编号',
  `zyjscs` varchar(255) DEFAULT NULL COMMENT '主要技术参数',
  `yzdd` varchar(255) DEFAULT NULL COMMENT '原值',
  `ljrq` varchar(255) DEFAULT NULL COMMENT '领交日期',
  `ljjysm` varchar(255) DEFAULT NULL COMMENT '领交简要说明',
  `sydd` varchar(255) DEFAULT NULL COMMENT '使用地点',
  `sbzt` varchar(255) DEFAULT NULL COMMENT '设备状态',
  `bjrdd` varchar(255) DEFAULT NULL COMMENT '包机人',
  `sydw` varchar(255) DEFAULT NULL COMMENT '使用单位',
  `syfs` varchar(255) DEFAULT NULL COMMENT '使用方式',
  `bzdd` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='设备管理-设备列表';

-- ----------------------------
-- Table structure for scgl_scjh
-- ----------------------------
DROP TABLE IF EXISTS `scgl_scjh`;
CREATE TABLE `scgl_scjh` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `jhlx` varchar(255) DEFAULT NULL COMMENT '计划类型(年,月,季)',
  `jhzcl` varchar(255) DEFAULT NULL COMMENT '计划总产量',
  `jhkssj` timestamp NULL DEFAULT NULL COMMENT '计划开始时间',
  `jhjssj` timestamp NULL DEFAULT NULL COMMENT '计划结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='生产管理-生产计划';

-- ----------------------------
-- Table structure for scgl_sjcl
-- ----------------------------
DROP TABLE IF EXISTS `scgl_sjcl`;
CREATE TABLE `scgl_sjcl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '录入时间',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `drcl` varchar(255) DEFAULT NULL COMMENT '当日产量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='生产管理-实际产量';

-- ----------------------------
-- Table structure for security_risk_year
-- ----------------------------
DROP TABLE IF EXISTS `security_risk_year`;
CREATE TABLE `security_risk_year` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `identification_date` timestamp NULL DEFAULT NULL COMMENT '辨识时间 ',
  `coal_mine_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据所属煤矿 ',
  `reserved_field1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段a ',
  `reserved_field2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段b ',
  `reserved_field3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段c ',
  `risk_point` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '风险点 ',
  `map_location` varchar(257) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地图位置定点 ',
  `duty_department` varchar(258) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '责任单位 ',
  `risk_level` varchar(259) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '风险等级 ',
  `risk_sort` varchar(260) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '风险分类 ',
  `limit_peoples` varchar(261) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '限制人数 ',
  `risk_desc` varchar(263) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '风险描述 ',
  `control_measures` varchar(264) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管控措施 ',
  `main_dutyPerson` varchar(265) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主要负责人 ',
  `second_dutyPerson` varchar(266) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分管负责人 ',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型(年度风险,专项风险)',
  `date_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '辨识时间类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全风险管控-年度风险辨识';

-- ----------------------------
-- Table structure for wxygl_sjlb
-- ----------------------------
DROP TABLE IF EXISTS `wxygl_sjlb`;
CREATE TABLE `wxygl_sjlb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '填报日期',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `qymc` varchar(255) DEFAULT NULL COMMENT '企业名称',
  `qybh` varchar(255) DEFAULT NULL COMMENT '企业编号',
  `zdwxymc` varchar(255) DEFAULT NULL COMMENT '重大危险源名称',
  `zdwxydj` varchar(255) DEFAULT NULL COMMENT '重大危险源等级',
  `zdwxylx` varchar(255) DEFAULT NULL COMMENT '重大危险源类型',
  `zdwxybm` varchar(255) DEFAULT NULL COMMENT '重大危险源编码',
  `sfba` varchar(255) DEFAULT NULL COMMENT '是否备案',
  `babh` varchar(255) DEFAULT NULL COMMENT '备案编号',
  `barq` timestamp NULL DEFAULT NULL COMMENT '备案日期',
  `tbrxm` varchar(255) DEFAULT NULL COMMENT '填报人姓名',
  `tbrdh` varchar(255) DEFAULT NULL COMMENT '填报人电话',
  `zbhj` varchar(255) DEFAULT NULL COMMENT '周边环境',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='危险源管理-数据列表';

-- ----------------------------
-- Table structure for xtgn_qxlb
-- ----------------------------
DROP TABLE IF EXISTS `xtgn_qxlb`;
CREATE TABLE `xtgn_qxlb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `qxlx` varchar(255) DEFAULT NULL COMMENT '权限类型(api-接口权限,ui-界面权限)',
  `qxzdd` varchar(255) DEFAULT NULL COMMENT '权限值(/user/login)',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `cjrdd` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统功能-权限列表';

-- ----------------------------
-- Table structure for xtgn_qyfj
-- ----------------------------
DROP TABLE IF EXISTS `xtgn_qyfj`;
CREATE TABLE `xtgn_qyfj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父级id  ',
  `parentids` varchar(255) DEFAULT NULL COMMENT '所有父级id,以逗号隔开(,0,1,2,)',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `qymdd` varchar(255) DEFAULT NULL COMMENT '区域名',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '添加日期',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `cjrdd` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='系统功能-区域分级';

-- ----------------------------
-- Table structure for xtgn_yhlb
-- ----------------------------
DROP TABLE IF EXISTS `xtgn_yhlb`;
CREATE TABLE `xtgn_yhlb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `yhmdd` varchar(255) DEFAULT NULL COMMENT '用户名',
  `yhmm` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `cjsj` varchar(255) DEFAULT NULL,
  `cjrdd` varchar(255) DEFAULT NULL COMMENT '创建人',
  `ssqymc` varchar(255) DEFAULT NULL COMMENT '用户所属区域的名称 qylb里面的名称',
  PRIMARY KEY (`id`),
  KEY `to_qyfj` (`ssmk`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='系统功能-用户列表';

-- ----------------------------
-- Table structure for xtgn_yhqx
-- ----------------------------
DROP TABLE IF EXISTS `xtgn_yhqx`;
CREATE TABLE `xtgn_yhqx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `yhid` varchar(255) DEFAULT NULL COMMENT '用户id',
  `qxid` varchar(255) DEFAULT NULL COMMENT '权限id',
  `cjsj` varchar(255) DEFAULT NULL,
  `cjrdd` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统功能-用户权限';

-- ----------------------------
-- Table structure for yhpc_fktk
-- ----------------------------
DROP TABLE IF EXISTS `yhpc_fktk`;
CREATE TABLE `yhpc_fktk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fkyj` varchar(255) DEFAULT NULL COMMENT '罚款依据 ',
  `fknr` varchar(255) DEFAULT NULL COMMENT '罚款内容 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-隐患罚款条款设置';

-- ----------------------------
-- Table structure for yhpc_yhlr
-- ----------------------------
DROP TABLE IF EXISTS `yhpc_yhlr`;
CREATE TABLE `yhpc_yhlr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '检查时间 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `jcr` varchar(255) DEFAULT NULL COMMENT '检查人 ',
  `jclx` varchar(255) DEFAULT NULL COMMENT '检查类型 ',
  `yhdd` varchar(255) DEFAULT NULL COMMENT '隐患地点 ',
  `zgrq` varchar(255) DEFAULT NULL COMMENT '整改日期 ',
  `yhnr` varchar(255) DEFAULT NULL COMMENT '隐患内容 ',
  `zgcs` varchar(255) DEFAULT NULL COMMENT '整改措施 ',
  `zrdw` varchar(255) DEFAULT NULL COMMENT '责任单位 ',
  `zrr` varchar(255) DEFAULT NULL COMMENT '责任人 ',
  `yhlx` varchar(255) DEFAULT NULL COMMENT '隐患类型 ',
  `yhjb` varchar(255) DEFAULT NULL COMMENT '隐患级别 ',
  `fkly` varchar(255) DEFAULT NULL COMMENT '罚款理由 ',
  `fkje` varchar(255) DEFAULT NULL COMMENT '罚款金额 ',
  `fkcz` varchar(255) DEFAULT NULL COMMENT '罚款查找 ',
  `yhzt` varchar(255) DEFAULT NULL COMMENT '隐患状态 ',
  `clgcgz` varchar(255) DEFAULT NULL COMMENT '处理过程跟踪 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-隐患录入';

-- ----------------------------
-- Table structure for yhpc_yhlx
-- ----------------------------
DROP TABLE IF EXISTS `yhpc_yhlx`;
CREATE TABLE `yhpc_yhlx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `sglx` varchar(255) DEFAULT NULL COMMENT '事故类型 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-隐患类型设置';

-- ----------------------------
-- Table structure for yhpc_yjxx
-- ----------------------------
DROP TABLE IF EXISTS `yhpc_yjxx`;
CREATE TABLE `yhpc_yjxx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fbnr` varchar(255) DEFAULT NULL COMMENT '发布内容 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-安全预警信息发布';

-- 初始化部门
INSERT INTO `colliery_department` VALUES ('1', '超级部门', '1,2,3,4,5', null, null, null, null, null);
-- 初始化模块权限
INSERT INTO `colliery_auth` VALUES ('1', '系统设置-增删改权限', '1', 'system', '2019-05-05 16:06:49', 'system', '2019-05-05 16:06:49');
INSERT INTO `colliery_auth` VALUES ('2', '地测防治水管理-增删改权限', '1', 'system', '2019-05-05 16:07:42', 'system', '2019-05-05 16:07:42');
INSERT INTO `colliery_auth` VALUES ('3', '安全风险管控-增删改权限', '1', 'system', '2019-05-02 21:37:08', 'system', '2019-05-02 21:37:08');
INSERT INTO `colliery_auth` VALUES ('4', '隐患治理-增删改权限', '1', 'system', '2019-05-02 21:39:16', 'system', '2019-05-02 21:39:16');
INSERT INTO `colliery_auth` VALUES ('5', '调度信息管理-增删改权限', '1', 'system', '2019-05-05 16:06:49', 'system', '2019-05-05 16:06:49');
-- 初始化用户
INSERT INTO `colliery_user` VALUES ('1', '1', '1', 'admin', '$2a$10$pK4lZcnlUSwKwEjun3EGS.7N49jc20rl8tK69HYOjBv4kzjJDFXfa', 'true', 'system', NULL, NULL, '2019-05-27 14:14:09');
-- 初始化角色
INSERT INTO `colliery_role` VALUES ('1', '超级管理员', '1', 'system', null, null, null);
/*
Navicat MySQL Data Transfer

Source Server         : 47.103.35.95
Source Server Version : 80015
Source Host           : 47.103.35.95:3306
Source Database       : coal_mine_kh

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-08-07 13:49:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aqfx_csxg
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_csxg`;
CREATE TABLE `aqfx_csxg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '辨识时间 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fxd` varchar(256) DEFAULT NULL COMMENT '风险点 ',
  `dtwzdd` varchar(257) DEFAULT NULL COMMENT '地图位置定点 ',
  `zrdw` varchar(258) DEFAULT NULL COMMENT '责任单位 ',
  `fxdj` varchar(259) DEFAULT NULL COMMENT '风险等级 ',
  `fxfl` varchar(260) DEFAULT NULL COMMENT '风险分类 ',
  `xzrs` varchar(261) DEFAULT NULL COMMENT '限制人数 ',
  `fxms` varchar(263) DEFAULT NULL COMMENT '风险描述 ',
  `gkcs` varchar(264) DEFAULT NULL COMMENT '管控措施 ',
  `zyfzr` varchar(265) DEFAULT NULL COMMENT '主要负责人 ',
  `fgfzr` varchar(266) DEFAULT NULL COMMENT '分管负责人 ',
  `fxlx` varchar(255) DEFAULT NULL COMMENT '类型(年度风险,专项风险)',
  `xgrq` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  `csbh` varchar(255) DEFAULT NULL COMMENT '措施变化',
  `ysid` bigint(20) DEFAULT NULL COMMENT '原始数据id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全风险管控-管控措施修改记录';

-- ----------------------------
-- Table structure for aqfx_fxgz
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_fxgz`;
CREATE TABLE `aqfx_fxgz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '辨识日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `leixing` varchar(255) DEFAULT NULL COMMENT '类型 ',
  `fxd` varchar(255) DEFAULT NULL COMMENT '风险点 ',
  `zrdw` varchar(255) DEFAULT NULL COMMENT '责任单位 ',
  `fxdj` varchar(255) DEFAULT NULL COMMENT '风险等级 ',
  `fxfl` varchar(255) DEFAULT NULL COMMENT '风险分类 ',
  `xzrs` varchar(255) DEFAULT NULL COMMENT '限制人数 ',
  `fxms` varchar(255) DEFAULT NULL COMMENT '风险描述 ',
  `zgcs` varchar(255) DEFAULT NULL COMMENT '整改措施 ',
  `gzjl` varchar(255) DEFAULT NULL COMMENT '跟踪记录 ',
  `gzsm` varchar(255) DEFAULT NULL COMMENT '跟踪说明 ',
  `gzry` varchar(255) DEFAULT NULL COMMENT '跟踪人员 ',
  `gzsj` varchar(255) DEFAULT NULL COMMENT '跟踪时间 ',
  `fakuan` varchar(255) DEFAULT NULL COMMENT '罚款 ',
  `xiaoguo` varchar(255) DEFAULT NULL COMMENT '效果 ',
  `sfsb` varchar(255) DEFAULT NULL COMMENT '是否上报 ',
  `sfxj` varchar(255) DEFAULT NULL COMMENT '是否消警 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控-风险跟踪及报表';

-- ----------------------------
-- Table structure for aqfx_gkxg
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_gkxg`;
CREATE TABLE `aqfx_gkxg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `zzr` varchar(255) DEFAULT NULL COMMENT '组织人 ',
  `zhiwu` varchar(255) DEFAULT NULL COMMENT '职务 ',
  `leibie` varchar(255) DEFAULT NULL COMMENT '类别 ',
  `gkzdssqk` varchar(255) DEFAULT NULL COMMENT '管控重点实施情况 ',
  `czwt` varchar(255) DEFAULT NULL COMMENT '存在问题 ',
  `gkcsdzqk` varchar(255) DEFAULT NULL COMMENT '管控措施调整情况 ',
  `ydzd` varchar(255) DEFAULT NULL COMMENT '月度重点 ',
  `zrfg` varchar(255) DEFAULT NULL COMMENT '责任分工 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控-管控效果及分析';

-- ----------------------------
-- Table structure for aqfx_jbcs_fxlx
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_jbcs_fxlx`;
CREATE TABLE `aqfx_jbcs_fxlx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fxlx` varchar(255) DEFAULT NULL COMMENT '风险类型 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控-基本参数设置-风险类型设置';

-- ----------------------------
-- Table structure for aqfx_jbcs_kld
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_jbcs_kld`;
CREATE TABLE `aqfx_jbcs_kld` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `kld` varchar(255) DEFAULT NULL COMMENT '矿领导 ',
  `zhiwu` varchar(255) DEFAULT NULL COMMENT '职务 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控-基本参数设置-矿领导设置';

-- ----------------------------
-- Table structure for aqfx_ldgz
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_ldgz`;
CREATE TABLE `aqfx_ldgz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '辨识日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `weizhi` varchar(255) DEFAULT NULL COMMENT '位置 ',
  `dbld` varchar(255) DEFAULT NULL COMMENT '带班领导 ',
  `djry` varchar(255) DEFAULT NULL COMMENT '对接人员 ',
  `rjsj` varchar(255) DEFAULT NULL COMMENT '入井时间 ',
  `sjsj` varchar(255) DEFAULT NULL COMMENT '升井时间 ',
  `banci` varchar(255) DEFAULT NULL COMMENT '班次 ',
  `xzlx` varchar(255) DEFAULT NULL COMMENT '行走录像 ',
  `lednr` varchar(255) DEFAULT NULL COMMENT 'LED内容 ',
  `zdfxgkzg` varchar(255) DEFAULT NULL COMMENT '重大风险管控整改 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全风险管控-领导跟踪检查';

-- ----------------------------
-- Table structure for aqfx_ndfx
-- ----------------------------
DROP TABLE IF EXISTS `aqfx_ndfx`;
CREATE TABLE `aqfx_ndfx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '辨识时间 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fxd` varchar(256) DEFAULT NULL COMMENT '风险点 ',
  `dtwzdd` varchar(257) DEFAULT NULL COMMENT '地图位置定点 ',
  `zrdw` varchar(258) DEFAULT NULL COMMENT '责任单位 ',
  `fxdj` varchar(259) DEFAULT NULL COMMENT '风险等级 ',
  `fxfl` varchar(260) DEFAULT NULL COMMENT '风险分类 ',
  `xzrs` varchar(261) DEFAULT NULL COMMENT '限制人数 ',
  `fxms` varchar(263) DEFAULT NULL COMMENT '风险描述 ',
  `gkcs` varchar(264) DEFAULT NULL COMMENT '管控措施 ',
  `zyfzr` varchar(265) DEFAULT NULL COMMENT '主要负责人 ',
  `fgfzr` varchar(266) DEFAULT NULL COMMENT '分管负责人 ',
  `fxlx` varchar(255) DEFAULT NULL COMMENT '类型(年度风险,专项风险)',
  `datetype` varchar(16) DEFAULT NULL COMMENT '辨识时间类型',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全风险管控-年度风险辨识';

-- ----------------------------
-- Table structure for colliery_auth
-- ----------------------------
DROP TABLE IF EXISTS `colliery_auth`;
CREATE TABLE `colliery_auth` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `auth_name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `auth_level` int(5) DEFAULT NULL COMMENT '权限级别',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_base_config
-- ----------------------------
DROP TABLE IF EXISTS `colliery_base_config`;
CREATE TABLE `colliery_base_config` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `value` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(10) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `modify_user` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_department
-- ----------------------------
DROP TABLE IF EXISTS `colliery_department`;
CREATE TABLE `colliery_department` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `department_name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `auth_ids` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id列表(以逗号分隔)',
  `coal_mine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属煤矿',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_entry_dispatcher
-- ----------------------------
DROP TABLE IF EXISTS `colliery_entry_dispatcher`;
CREATE TABLE `colliery_entry_dispatcher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `mine_people` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL COMMENT '行走路线',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_file_white_list
-- ----------------------------
DROP TABLE IF EXISTS `colliery_file_white_list`;
CREATE TABLE `colliery_file_white_list` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `coal` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '煤矿名称',
  `depart` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称 ',
  `system` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件所属系统',
  `menu` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件所属菜单',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_handover
-- ----------------------------
DROP TABLE IF EXISTS `colliery_handover`;
CREATE TABLE `colliery_handover` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL COMMENT '班次',
  `succession_dispatcher` varchar(255) DEFAULT NULL COMMENT '接班调度员',
  `handover_dispatcher` varchar(255) DEFAULT NULL,
  `handover_date` datetime DEFAULT NULL COMMENT '交接时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '本班情况及遗留问题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_key_project
-- ----------------------------
DROP TABLE IF EXISTS `colliery_key_project`;
CREATE TABLE `colliery_key_project` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `shift` varchar(255) DEFAULT NULL COMMENT '班次',
  `lane_name` varchar(255) DEFAULT NULL COMMENT '巷道名称',
  `mine_area_name` varchar(255) DEFAULT NULL COMMENT '采区名称',
  `construction_team` varchar(255) DEFAULT NULL COMMENT '施工队',
  `total_engineering` varchar(255) DEFAULT NULL,
  `class_schedule` varchar(255) DEFAULT NULL COMMENT '班进度',
  `cumulative_completion` varchar(255) DEFAULT NULL COMMENT '累计完成',
  `remaining_work` varchar(255) DEFAULT NULL,
  `schedule_operator` varchar(255) DEFAULT NULL COMMENT '值班调度员',
  `remark` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_raw_coal_production
-- ----------------------------
DROP TABLE IF EXISTS `colliery_raw_coal_production`;
CREATE TABLE `colliery_raw_coal_production` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `mine_peoples` varchar(255) DEFAULT NULL,
  `output` varchar(255) DEFAULT NULL,
  `sales_volume` varchar(255) DEFAULT NULL COMMENT '销售量',
  `stock` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原煤产量',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_role
-- ----------------------------
DROP TABLE IF EXISTS `colliery_role`;
CREATE TABLE `colliery_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `auth_level` int(5) DEFAULT NULL COMMENT '权限等级',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_safety_info
-- ----------------------------
DROP TABLE IF EXISTS `colliery_safety_info`;
CREATE TABLE `colliery_safety_info` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `shift` varchar(20) DEFAULT NULL COMMENT '班次',
  `date` date DEFAULT NULL,
  `duty_manager` varchar(20) DEFAULT NULL COMMENT '值班矿长',
  `mine_manager` varchar(20) DEFAULT NULL,
  `scheduling_operater` varchar(20) DEFAULT NULL COMMENT '调度值班员',
  `monitor_operater` varchar(20) DEFAULT NULL COMMENT '监控值班员',
  `output` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '产量',
  `mine_peoples` varchar(20) DEFAULT '' COMMENT '入井人数',
  `working_condition` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作情况',
  `mining_advancement_condition` varchar(255) DEFAULT NULL COMMENT '采面推进情况',
  `digging_length` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '掘进进尺',
  `maintenance_length` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '维修进尺',
  `daily_output` varchar(255) DEFAULT NULL,
  `daily_digging_length` varchar(20) DEFAULT NULL,
  `daily_maintenance_length` varchar(20) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_schedule_duty
-- ----------------------------
DROP TABLE IF EXISTS `colliery_schedule_duty`;
CREATE TABLE `colliery_schedule_duty` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `dispatcher` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `handover_date` datetime DEFAULT NULL,
  `class_leader` varchar(255) DEFAULT NULL,
  `mine_peoples` varchar(255) DEFAULT NULL,
  `tunneling_team` varchar(255) DEFAULT NULL,
  `machine_team` varchar(255) DEFAULT NULL,
  `manager` varchar(255) DEFAULT NULL,
  `coal_mining_face` varchar(255) DEFAULT NULL,
  `tunneling_face` varchar(255) DEFAULT NULL,
  `output` varchar(255) DEFAULT NULL,
  `digging_length` varchar(255) DEFAULT NULL,
  `maintenance_length` varchar(255) DEFAULT NULL,
  `remark1` varchar(255) DEFAULT NULL,
  `remark2` varchar(255) DEFAULT NULL,
  `remark3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_sign_info
-- ----------------------------
DROP TABLE IF EXISTS `colliery_sign_info`;
CREATE TABLE `colliery_sign_info` (
  `id` int(255) NOT NULL,
  `unsafe_id` int(255) DEFAULT NULL COMMENT '对应隐患数据的id',
  `sign_status` varchar(255) DEFAULT NULL COMMENT '确认状态:0分发未确认,1已确认,2分发待确认',
  `sign_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '确认日期',
  `sign_depart_id` int(255) DEFAULT NULL COMMENT '分发部门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_unsafe_info
-- ----------------------------
DROP TABLE IF EXISTS `colliery_unsafe_info`;
CREATE TABLE `colliery_unsafe_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `depart_id` int(20) DEFAULT NULL COMMENT '处理的部门Id',
  `strat_depart_id` int(20) DEFAULT NULL,
  `plan_name` varchar(20) DEFAULT NULL,
  `coal_mine` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据所属煤矿 ',
  `check_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '检查类型 ',
  `location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '隐患地点 ',
  `content` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '隐患内容 ',
  `reform_measure` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '整改措施 ',
  `reform_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '整改日期 ',
  `duty_depart` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '责任单位 ',
  `duty_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '责任人 ',
  `type` varchar(255) DEFAULT NULL COMMENT '隐患类型 ',
  `level` varchar(255) DEFAULT NULL COMMENT '隐患级别 ',
  `fine_reason` varchar(255) DEFAULT NULL COMMENT '罚款理由 ',
  `fine_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '罚款金额 ',
  `fine_find` varchar(255) DEFAULT NULL COMMENT '罚款查找 ',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '隐患状态 0:未处理,1:已处理,2:超时',
  `distributed_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分发状态 0:未分发 1:已分发',
  `sign_status` varchar(255) DEFAULT NULL,
  `deal_process` varchar(255) DEFAULT NULL COMMENT '处理过程跟踪 ',
  `check_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '检查人 ',
  `check_time` datetime DEFAULT NULL COMMENT '检查时间 ',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_user` varchar(20) DEFAULT NULL,
  `extra1` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `extra2` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `extra3` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-隐患录入';

-- ----------------------------
-- Table structure for colliery_unsafe_plan
-- ----------------------------
DROP TABLE IF EXISTS `colliery_unsafe_plan`;
CREATE TABLE `colliery_unsafe_plan` (
  `plan_id` int(255) NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计划名称',
  `plan_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排查内容',
  `plan_depart_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排查部门',
  `plan_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排查类型',
  `plan_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排查地点',
  `plan_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '计划日期',
  `create_user` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for colliery_user
-- ----------------------------
DROP TABLE IF EXISTS `colliery_user`;
CREATE TABLE `colliery_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `role_id` int(20) DEFAULT NULL COMMENT '角色id',
  `depart_id` int(20) DEFAULT NULL COMMENT '部门id',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `user_pwd` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `file_auth` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件管理权限',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改时间',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`user_name`) USING BTREE COMMENT '用户名唯一'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dc_jbcs_kqwz
-- ----------------------------
DROP TABLE IF EXISTS `dc_jbcs_kqwz`;
CREATE TABLE `dc_jbcs_kqwz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `xjwz` varchar(255) DEFAULT NULL COMMENT '巡检位置 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地测信息管理-基本参数-矿区位置';

-- ----------------------------
-- Table structure for dc_jbcs_yh
-- ----------------------------
DROP TABLE IF EXISTS `dc_jbcs_yh`;
CREATE TABLE `dc_jbcs_yh` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `yhmc` varchar(255) DEFAULT NULL COMMENT '用户名称 ',
  `yhmm` varchar(255) DEFAULT NULL COMMENT '用户密码 ',
  `yhfl` varchar(255) DEFAULT NULL COMMENT '用户分类 ',
  `xssx` varchar(255) DEFAULT NULL COMMENT '显示顺序 ',
  `dlmc` varchar(255) DEFAULT NULL COMMENT '登陆名称 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地测信息管理-基本参数-用户';

-- ----------------------------
-- Table structure for dc_swdz_zkpc
-- ----------------------------
DROP TABLE IF EXISTS `dc_swdz_zkpc`;
CREATE TABLE `dc_swdz_zkpc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期',
  `kkrq` timestamp NULL DEFAULT NULL COMMENT '开孔日期',
  `zkwz` varchar(255) DEFAULT NULL COMMENT '钻孔位置',
  `zkbh` varchar(255) DEFAULT NULL COMMENT '钻孔编号',
  `swqk` varchar(255) DEFAULT NULL COMMENT '水位情况',
  `knzk` varchar(255) DEFAULT NULL COMMENT '孔内状况',
  `kkqk` varchar(255) DEFAULT NULL COMMENT '孔口情况',
  `sjks` varchar(255) DEFAULT NULL COMMENT '设计孔深',
  `shijks` varchar(255) DEFAULT NULL COMMENT '实际孔深',
  `jlr` varchar(255) DEFAULT NULL COMMENT '记录人',
  `shhr` varchar(255) DEFAULT NULL COMMENT '审核人',
  `ywxq` varchar(255) DEFAULT NULL COMMENT '有无险情',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='地测信息管理-水文地质钻孔排查';

-- ----------------------------
-- Table structure for dc_swxq_yjcl
-- ----------------------------
DROP TABLE IF EXISTS `dc_swxq_yjcl`;
CREATE TABLE `dc_swxq_yjcl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `zrdw` varchar(256) DEFAULT NULL COMMENT '责任单位 ',
  `didian` varchar(257) DEFAULT NULL COMMENT '地点 ',
  `qkms` varchar(258) DEFAULT NULL COMMENT '情况描述 ',
  `clcs` varchar(259) DEFAULT NULL COMMENT '处理措施 ',
  `wxdj` varchar(260) DEFAULT NULL COMMENT '危险等级 ',
  `zlxg` varchar(261) DEFAULT NULL COMMENT '治理效果 ',
  `jlr` varchar(262) DEFAULT NULL COMMENT '记录人 ',
  `shhr` varchar(263) DEFAULT NULL COMMENT '审核人 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='地测信息管理-水文险情及预警处理';

-- ----------------------------
-- Table structure for ddxx_jb_scdw
-- ----------------------------
DROP TABLE IF EXISTS `ddxx_jb_scdw`;
CREATE TABLE `ddxx_jb_scdw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `scdw` varchar(255) DEFAULT NULL COMMENT '生产单位 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度信息管理-基本信息设置-生产单位设置';

-- ----------------------------
-- Table structure for ddxx_scdd
-- ----------------------------
DROP TABLE IF EXISTS `ddxx_scdd`;
CREATE TABLE `ddxx_scdd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `lrry` varchar(255) DEFAULT NULL COMMENT '录入人员 ',
  `scdw` varchar(255) DEFAULT NULL COMMENT '生产单位 ',
  `banci` varchar(255) DEFAULT NULL COMMENT '班次 ',
  `sgdwrs` varchar(255) DEFAULT NULL COMMENT '施工单位人数 ',
  `jxqbrs` varchar(255) DEFAULT NULL COMMENT '井下全部人数 ',
  `chanliang` varchar(255) DEFAULT NULL COMMENT '产量 ',
  `sgdd` varchar(255) DEFAULT NULL COMMENT '施工地点 ',
  `jinchi` varchar(255) DEFAULT NULL COMMENT '进尺 ',
  `aqxxjwt` varchar(255) DEFAULT NULL COMMENT '安全信息及问题 ',
  `qtsx` varchar(255) DEFAULT NULL COMMENT '其他事项 ',
  `ddy` varchar(255) DEFAULT NULL COMMENT '调度员 ',
  `jky` varchar(255) DEFAULT NULL COMMENT '监控员 ',
  `zbld` varchar(255) DEFAULT NULL COMMENT '值班领导 ',
  `sfsh` varchar(255) DEFAULT NULL COMMENT '是否审核 ',
  `sfsg` varchar(255) DEFAULT NULL COMMENT '是否事故 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='调度信息管理-安全生产调度报表';

-- ----------------------------
-- Table structure for ddxx_scsg
-- ----------------------------
DROP TABLE IF EXISTS `ddxx_scsg`;
CREATE TABLE `ddxx_scsg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `lrry` varchar(255) DEFAULT NULL COMMENT '录入人员 ',
  `sgdd` varchar(255) DEFAULT NULL COMMENT '施工地点 ',
  `banci` varchar(255) DEFAULT NULL COMMENT '班次 ',
  `sgyy` varchar(255) DEFAULT NULL COMMENT '事故原因 ',
  `sgjb` varchar(255) DEFAULT NULL COMMENT '事故级别 ',
  `swrs` varchar(255) DEFAULT NULL COMMENT '伤亡人数 ',
  `jjss` varchar(255) DEFAULT NULL COMMENT '经济损失 ',
  `clgc` varchar(255) DEFAULT NULL COMMENT '处理过程 ',
  `ldps` varchar(255) DEFAULT NULL COMMENT '领导批示 ',
  `cljg` varchar(255) DEFAULT NULL COMMENT '处理结果 ',
  `zrzj` varchar(255) DEFAULT NULL COMMENT '责任追究 ',
  `ddy` varchar(255) DEFAULT NULL COMMENT '调度员 ',
  `jky` varchar(255) DEFAULT NULL COMMENT '监控员 ',
  `zbld` varchar(255) DEFAULT NULL COMMENT '值班领导 ',
  `sfsbsj` varchar(255) DEFAULT NULL COMMENT '是否上报上级 ',
  `sfclwb` varchar(255) DEFAULT NULL COMMENT '是否处理完毕 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度信息管理-生产安全事故处理追踪';

-- ----------------------------
-- Table structure for pxgl_sjlb
-- ----------------------------
DROP TABLE IF EXISTS `pxgl_sjlb`;
CREATE TABLE `pxgl_sjlb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '培训时间',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `jhpxrs` varchar(255) DEFAULT NULL COMMENT '计划培训人数',
  `sjpxrs` varchar(255) DEFAULT NULL COMMENT '实际培训人数',
  `cjry` varchar(255) DEFAULT NULL COMMENT '参加人员',
  `rydw` varchar(255) DEFAULT NULL COMMENT '人员单位',
  `pxnr` varchar(255) DEFAULT NULL COMMENT '培训内容',
  `pxsj` timestamp NULL DEFAULT NULL COMMENT '排查日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='培训管理-数据列表';

-- ----------------------------
-- Table structure for sbgl_sblb
-- ----------------------------
DROP TABLE IF EXISTS `sbgl_sblb`;
CREATE TABLE `sbgl_sblb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `sbbm` varchar(255) DEFAULT NULL COMMENT '设备编码',
  `sbmc` varchar(255) DEFAULT NULL COMMENT '设备名称',
  `ggxh` varchar(255) DEFAULT NULL COMMENT '规格型号',
  `sccj` varchar(255) DEFAULT NULL COMMENT '生产厂家',
  `ccrq` varchar(255) DEFAULT NULL COMMENT '出厂日期',
  `ccbh` varchar(255) DEFAULT NULL COMMENT '出厂编号',
  `zyjscs` varchar(255) DEFAULT NULL COMMENT '主要技术参数',
  `yzdd` varchar(255) DEFAULT NULL COMMENT '原值',
  `ljrq` varchar(255) DEFAULT NULL COMMENT '领交日期',
  `ljjysm` varchar(255) DEFAULT NULL COMMENT '领交简要说明',
  `sydd` varchar(255) DEFAULT NULL COMMENT '使用地点',
  `sbzt` varchar(255) DEFAULT NULL COMMENT '设备状态',
  `bjrdd` varchar(255) DEFAULT NULL COMMENT '包机人',
  `sydw` varchar(255) DEFAULT NULL COMMENT '使用单位',
  `syfs` varchar(255) DEFAULT NULL COMMENT '使用方式',
  `bzdd` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='设备管理-设备列表';

-- ----------------------------
-- Table structure for scgl_scjh
-- ----------------------------
DROP TABLE IF EXISTS `scgl_scjh`;
CREATE TABLE `scgl_scjh` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `jhlx` varchar(255) DEFAULT NULL COMMENT '计划类型(年,月,季)',
  `jhzcl` varchar(255) DEFAULT NULL COMMENT '计划总产量',
  `jhkssj` timestamp NULL DEFAULT NULL COMMENT '计划开始时间',
  `jhjssj` timestamp NULL DEFAULT NULL COMMENT '计划结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='生产管理-生产计划';

-- ----------------------------
-- Table structure for scgl_sjcl
-- ----------------------------
DROP TABLE IF EXISTS `scgl_sjcl`;
CREATE TABLE `scgl_sjcl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '录入时间',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `drcl` varchar(255) DEFAULT NULL COMMENT '当日产量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='生产管理-实际产量';

-- ----------------------------
-- Table structure for security_risk_year
-- ----------------------------
DROP TABLE IF EXISTS `security_risk_year`;
CREATE TABLE `security_risk_year` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `identification_date` timestamp NULL DEFAULT NULL COMMENT '辨识时间 ',
  `coal_mine_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据所属煤矿 ',
  `reserved_field1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段a ',
  `reserved_field2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段b ',
  `reserved_field3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段c ',
  `risk_point` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '风险点 ',
  `map_location` varchar(257) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地图位置定点 ',
  `duty_department` varchar(258) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '责任单位 ',
  `risk_level` varchar(259) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '风险等级 ',
  `risk_sort` varchar(260) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '风险分类 ',
  `limit_peoples` varchar(261) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '限制人数 ',
  `risk_desc` varchar(263) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '风险描述 ',
  `control_measures` varchar(264) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管控措施 ',
  `main_dutyPerson` varchar(265) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主要负责人 ',
  `second_dutyPerson` varchar(266) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分管负责人 ',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型(年度风险,专项风险)',
  `date_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '辨识时间类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全风险管控-年度风险辨识';

-- ----------------------------
-- Table structure for wxygl_sjlb
-- ----------------------------
DROP TABLE IF EXISTS `wxygl_sjlb`;
CREATE TABLE `wxygl_sjlb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '填报日期',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `qymc` varchar(255) DEFAULT NULL COMMENT '企业名称',
  `qybh` varchar(255) DEFAULT NULL COMMENT '企业编号',
  `zdwxymc` varchar(255) DEFAULT NULL COMMENT '重大危险源名称',
  `zdwxydj` varchar(255) DEFAULT NULL COMMENT '重大危险源等级',
  `zdwxylx` varchar(255) DEFAULT NULL COMMENT '重大危险源类型',
  `zdwxybm` varchar(255) DEFAULT NULL COMMENT '重大危险源编码',
  `sfba` varchar(255) DEFAULT NULL COMMENT '是否备案',
  `babh` varchar(255) DEFAULT NULL COMMENT '备案编号',
  `barq` timestamp NULL DEFAULT NULL COMMENT '备案日期',
  `tbrxm` varchar(255) DEFAULT NULL COMMENT '填报人姓名',
  `tbrdh` varchar(255) DEFAULT NULL COMMENT '填报人电话',
  `zbhj` varchar(255) DEFAULT NULL COMMENT '周边环境',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='危险源管理-数据列表';

-- ----------------------------
-- Table structure for xtgn_qxlb
-- ----------------------------
DROP TABLE IF EXISTS `xtgn_qxlb`;
CREATE TABLE `xtgn_qxlb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `qxlx` varchar(255) DEFAULT NULL COMMENT '权限类型(api-接口权限,ui-界面权限)',
  `qxzdd` varchar(255) DEFAULT NULL COMMENT '权限值(/user/login)',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `cjrdd` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统功能-权限列表';

-- ----------------------------
-- Table structure for xtgn_qyfj
-- ----------------------------
DROP TABLE IF EXISTS `xtgn_qyfj`;
CREATE TABLE `xtgn_qyfj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父级id  ',
  `parentids` varchar(255) DEFAULT NULL COMMENT '所有父级id,以逗号隔开(,0,1,2,)',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `qymdd` varchar(255) DEFAULT NULL COMMENT '区域名',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '添加日期',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `cjrdd` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='系统功能-区域分级';

-- ----------------------------
-- Table structure for xtgn_yhlb
-- ----------------------------
DROP TABLE IF EXISTS `xtgn_yhlb`;
CREATE TABLE `xtgn_yhlb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `yhmdd` varchar(255) DEFAULT NULL COMMENT '用户名',
  `yhmm` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `cjsj` varchar(255) DEFAULT NULL,
  `cjrdd` varchar(255) DEFAULT NULL COMMENT '创建人',
  `ssqymc` varchar(255) DEFAULT NULL COMMENT '用户所属区域的名称 qylb里面的名称',
  PRIMARY KEY (`id`),
  KEY `to_qyfj` (`ssmk`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='系统功能-用户列表';

-- ----------------------------
-- Table structure for xtgn_yhqx
-- ----------------------------
DROP TABLE IF EXISTS `xtgn_yhqx`;
CREATE TABLE `xtgn_yhqx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',
  `yhid` varchar(255) DEFAULT NULL COMMENT '用户id',
  `qxid` varchar(255) DEFAULT NULL COMMENT '权限id',
  `cjsj` varchar(255) DEFAULT NULL,
  `cjrdd` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统功能-用户权限';

-- ----------------------------
-- Table structure for yhpc_fktk
-- ----------------------------
DROP TABLE IF EXISTS `yhpc_fktk`;
CREATE TABLE `yhpc_fktk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fkyj` varchar(255) DEFAULT NULL COMMENT '罚款依据 ',
  `fknr` varchar(255) DEFAULT NULL COMMENT '罚款内容 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-隐患罚款条款设置';

-- ----------------------------
-- Table structure for yhpc_yhlr
-- ----------------------------
DROP TABLE IF EXISTS `yhpc_yhlr`;
CREATE TABLE `yhpc_yhlr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '检查时间 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `jcr` varchar(255) DEFAULT NULL COMMENT '检查人 ',
  `jclx` varchar(255) DEFAULT NULL COMMENT '检查类型 ',
  `yhdd` varchar(255) DEFAULT NULL COMMENT '隐患地点 ',
  `zgrq` varchar(255) DEFAULT NULL COMMENT '整改日期 ',
  `yhnr` varchar(255) DEFAULT NULL COMMENT '隐患内容 ',
  `zgcs` varchar(255) DEFAULT NULL COMMENT '整改措施 ',
  `zrdw` varchar(255) DEFAULT NULL COMMENT '责任单位 ',
  `zrr` varchar(255) DEFAULT NULL COMMENT '责任人 ',
  `yhlx` varchar(255) DEFAULT NULL COMMENT '隐患类型 ',
  `yhjb` varchar(255) DEFAULT NULL COMMENT '隐患级别 ',
  `fkly` varchar(255) DEFAULT NULL COMMENT '罚款理由 ',
  `fkje` varchar(255) DEFAULT NULL COMMENT '罚款金额 ',
  `fkcz` varchar(255) DEFAULT NULL COMMENT '罚款查找 ',
  `yhzt` varchar(255) DEFAULT NULL COMMENT '隐患状态 ',
  `clgcgz` varchar(255) DEFAULT NULL COMMENT '处理过程跟踪 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-隐患录入';

-- ----------------------------
-- Table structure for yhpc_yhlx
-- ----------------------------
DROP TABLE IF EXISTS `yhpc_yhlx`;
CREATE TABLE `yhpc_yhlx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `sglx` varchar(255) DEFAULT NULL COMMENT '事故类型 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-隐患类型设置';

-- ----------------------------
-- Table structure for yhpc_yjxx
-- ----------------------------
DROP TABLE IF EXISTS `yhpc_yjxx`;
CREATE TABLE `yhpc_yjxx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期 ',
  `ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿 ',
  `ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a ',
  `ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b ',
  `ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c ',
  `fbnr` varchar(255) DEFAULT NULL COMMENT '发布内容 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隐患排查治理-安全预警信息发布';

-- 初始化部门
INSERT INTO `colliery_department` VALUES ('1', '超级部门', '1,2,3,4,5', null, null, null, null, null);
-- 初始化模块权限
INSERT INTO `colliery_auth` VALUES ('1', '系统设置-增删改权限', '1', 'system', '2019-05-05 16:06:49', 'system', '2019-05-05 16:06:49');
INSERT INTO `colliery_auth` VALUES ('2', '地测防治水管理-增删改权限', '1', 'system', '2019-05-05 16:07:42', 'system', '2019-05-05 16:07:42');
INSERT INTO `colliery_auth` VALUES ('3', '安全风险管控-增删改权限', '1', 'system', '2019-05-02 21:37:08', 'system', '2019-05-02 21:37:08');
INSERT INTO `colliery_auth` VALUES ('4', '隐患治理-增删改权限', '1', 'system', '2019-05-02 21:39:16', 'system', '2019-05-02 21:39:16');
INSERT INTO `colliery_auth` VALUES ('5', '调度信息管理-增删改权限', '1', 'system', '2019-05-05 16:06:49', 'system', '2019-05-05 16:06:49');
-- 初始化用户
INSERT INTO `colliery_user` VALUES ('1', '1', '1', 'admin', '$2a$10$pK4lZcnlUSwKwEjun3EGS.7N49jc20rl8tK69HYOjBv4kzjJDFXfa', 'true', 'system', NULL, NULL, '2019-05-27 14:14:09');
-- 初始化角色
INSERT INTO `colliery_role` VALUES ('1', '超级管理员', '1', 'system', null, null, null);
