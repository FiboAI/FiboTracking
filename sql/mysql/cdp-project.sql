SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'value',
  `status` tinyint DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `param_key` (`param_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (1, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"lishiwei\",\"password\":\"6f77dab91764f5355f245f27caef6d1a42e4754ebf677a52c02979133d919474\",\"salt\":\"acfRUlleb9tgqvusVLMY\",\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jun 29, 2022 2:31:07 PM\"}]', 509, '127.0.0.1', '2022-06-29 14:31:08');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (2, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"lishiwei\",\"roleIdList\":[1],\"createUserId\":1}]', 116, '127.0.0.1', '2022-06-29 14:42:21');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (3, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"lishiwei2\",\"roleIdList\":[1],\"createUserId\":1}]', 259, '127.0.0.1', '2022-06-29 14:42:25');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (4, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"lishiwei\",\"roleIdList\":[1],\"createUserId\":1}]', 361, '127.0.0.1', '2022-06-29 14:42:41');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (5, 'admin', '删除用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.delete()', '[{\"userId\":2}]', 58, '127.0.0.1', '2022-06-29 15:34:22');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (6, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"普通用户\",\"createUserId\":1,\"menuIdList\":[1],\"createTime\":\"Jun 29, 2022 4:01:40 PM\"}]', 982, '127.0.0.1', '2022-06-29 16:01:41');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (7, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"普通用户\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Jun 29, 2022 4:02:05 PM\"}]', 259, '127.0.0.1', '2022-06-29 16:02:06');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (8, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":4,\"roleName\":\"普通用户\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Jun 29, 2022 4:03:58 PM\"}]', 248, '127.0.0.1', '2022-06-29 16:03:58');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (9, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"普通用户\",\"createUserId\":1,\"menuIdList\":[]}]', 90, '127.0.0.1', '2022-06-29 16:04:39');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (10, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"普通用户\",\"createUserId\":1,\"menuIdList\":[2]}]', 286, '127.0.0.1', '2022-06-30 16:25:28');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (11, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":4,\"username\":\"测试\",\"password\":\"217588c3cd4ccedcc44dd9a8621ad5a4d116bbd894a3a520aa91802611ca70c4\",\"salt\":\"eGLfHOZ5kaYPPD8i7TsL\",\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jul 4, 2022 9:54:06 AM\",\"delFlag\":0}]', 98, '222.64.146.29', '2022-07-04 09:54:07');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (12, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":5,\"username\":\"测试删除\",\"password\":\"4b6e4e224da34be7084f8e7ff8df032adbf71ee82302559d6b03f0585398d0a6\",\"salt\":\"XyOwRBYXAofc2nEUVIpf\",\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jul 4, 2022 10:11:04 AM\",\"delFlag\":0}]', 10, '222.64.146.29', '2022-07-04 10:11:04');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (13, 'admin', '删除用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.delete()', '[{\"userId\":5}]', 4, '222.64.146.29', '2022-07-04 10:11:13');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (14, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":6,\"username\":\"删除测试\",\"password\":\"82fd49854006329a1e3400a592f49a906ab7e35465c9a552ed0cba6138f11225\",\"salt\":\"dSTchEyDS441HjuTibki\",\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jul 4, 2022 11:09:56 AM\",\"delFlag\":0}]', 5, '222.64.146.29', '2022-07-04 11:09:57');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (15, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":5,\"roleName\":\"测试\",\"createUserId\":1,\"menuIdList\":[2,3,1],\"createTime\":\"Jul 4, 2022 3:28:41 PM\",\"delFlag\":0}]', 96, '222.64.146.29', '2022-07-04 15:28:41');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (16, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":5,\"roleName\":\"测试\",\"createUserId\":1,\"menuIdList\":[2,3,1,4,5,6,7]}]', 17, '222.64.146.29', '2022-07-04 16:02:02');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (17, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":5,\"roleName\":\"测试1\",\"createUserId\":1,\"menuIdList\":[2,3,1,4,5,6,7]}]', 22, '222.64.146.29', '2022-07-04 16:02:11');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (18, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":6,\"roleName\":\"测试2\",\"createUserId\":1,\"menuIdList\":[3],\"createTime\":\"Jul 4, 2022 4:02:22 PM\",\"delFlag\":0}]', 6, '222.64.146.29', '2022-07-04 16:02:22');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (19, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":6,\"roleName\":\"测试21\",\"createUserId\":1,\"menuIdList\":[3]}]', 8, '222.64.146.29', '2022-07-04 16:04:03');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (20, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":7,\"roleName\":\"111\",\"createUserId\":1,\"menuIdList\":[2],\"createTime\":\"Jul 4, 2022 4:04:25 PM\",\"delFlag\":0}]', 5, '222.64.146.29', '2022-07-04 16:04:25');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (21, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":7,\"roleName\":\"1111\",\"createUserId\":1,\"menuIdList\":[2]}]', 6, '222.64.146.29', '2022-07-04 16:04:34');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (22, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":7,\"roleName\":\"111\",\"createUserId\":1,\"menuIdList\":[2]}]', 6, '222.64.146.29', '2022-07-04 16:05:17');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (23, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":8,\"roleName\":\"222\",\"createUserId\":1,\"menuIdList\":[2],\"createTime\":\"Jul 4, 2022 4:05:31 PM\",\"delFlag\":0}]', 9, '222.64.146.29', '2022-07-04 16:05:31');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (24, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":8,\"roleName\":\"222我去\",\"createUserId\":1,\"menuIdList\":[2]}]', 6, '222.64.146.29', '2022-07-04 16:05:36');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (25, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":8}]', 5, '222.64.146.29', '2022-07-04 16:05:39');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (26, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":7}]', 3, '222.64.146.29', '2022-07-04 16:05:59');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (27, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":6}]', 6, '222.64.146.29', '2022-07-04 16:06:01');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (28, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":9,\"roleName\":\"测试2\",\"createUserId\":1,\"menuIdList\":[2],\"createTime\":\"Jul 4, 2022 4:06:11 PM\",\"delFlag\":0}]', 11, '222.64.146.29', '2022-07-04 16:06:12');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (29, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":9,\"roleName\":\"测试2-1\",\"createUserId\":1,\"menuIdList\":[2]}]', 16, '222.64.146.29', '2022-07-04 16:06:18');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (30, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":7,\"username\":\"测\",\"password\":\"326960412c739d1b171f310343b6382cb656afaaeedeaf20748918f3e2cd587c\",\"salt\":\"gAa7KhnRX6G1H8uOIKTS\",\"roleIdList\":[1,9],\"createUserId\":1,\"createTime\":\"Jul 4, 2022 4:07:39 PM\",\"delFlag\":0}]', 18, '222.64.146.29', '2022-07-04 16:07:39');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (31, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":8,\"username\":\"测1\",\"password\":\"2a7bec3b96587c79117953d4fc708c91fbefb54370556cbbf136406f780e4e37\",\"salt\":\"MjdHb24PS6H7B2eKF0at\",\"roleIdList\":[1,9],\"createUserId\":1,\"createTime\":\"Jul 4, 2022 4:07:46 PM\",\"delFlag\":0}]', 7, '222.64.146.29', '2022-07-04 16:07:46');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (32, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":9,\"username\":\"测12\",\"password\":\"97bd304354950a2f59829f05ac1ee0fb0021c4f34d86107c0546ca486f084707\",\"salt\":\"Y3dmBDPESmZWbwaynh0m\",\"roleIdList\":[1,9],\"createUserId\":1,\"createTime\":\"Jul 4, 2022 4:07:52 PM\",\"delFlag\":0}]', 8, '222.64.146.29', '2022-07-04 16:07:53');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (33, 'admin', '删除用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.delete()', '[{\"userId\":9}]', 5, '222.64.146.29', '2022-07-04 16:08:01');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (34, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"测11\",\"password\":\"4a3151da0a439a1074044aa97ac61ef91bdeb52dce6e80836156300d4eca6efe\",\"salt\":\"Rhs0pSC67qRjl63kQgHu\",\"roleIdList\":[1,9],\"createUserId\":1,\"createTime\":\"Jul 4, 2022 4:08:04 PM\",\"delFlag\":0}]', 6, '222.64.146.29', '2022-07-04 16:08:05');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (35, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":11,\"username\":\"测111\",\"password\":\"4ab59e6e4fc2d3eee76585b6d01524db93dac6bd38b5e219796b36a79858dc8c\",\"salt\":\"hQO9moki6nrxAuuUcMq6\",\"roleIdList\":[1,9],\"createUserId\":1,\"createTime\":\"Jul 4, 2022 4:08:38 PM\",\"delFlag\":0}]', 15, '222.64.146.29', '2022-07-04 16:08:39');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (36, 'admin', '删除用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.delete()', '[{\"userId\":11}]', 4, '222.64.146.29', '2022-07-04 16:09:49');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (37, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":9,\"roleName\":\"测试2-12\",\"createUserId\":1,\"menuIdList\":[2]}]', 10, '222.64.146.29', '2022-07-04 16:12:34');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (38, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"lishiwei\",\"roleIdList\":[1],\"createUserId\":1}]', 997, '127.0.0.1', '2022-07-04 16:13:34');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (39, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"测11\",\"roleIdList\":[1],\"createUserId\":1}]', 165, '127.0.0.1', '2022-07-04 16:16:08');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (40, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":7,\"username\":\"测11111\",\"roleIdList\":[1,9],\"createUserId\":1}]', 8, '222.64.146.29', '2022-07-04 16:17:21');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (41, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":8,\"username\":\"测121212\",\"roleIdList\":[1,9],\"createUserId\":1}]', 7, '222.64.146.29', '2022-07-04 16:17:27');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (42, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"测11212121\",\"roleIdList\":[1],\"createUserId\":1}]', 6, '222.64.146.29', '2022-07-04 16:17:30');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (43, 'admin', '删除用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.delete()', '[{\"userId\":7}]', 4, '222.64.146.29', '2022-07-04 16:17:42');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (44, 'admin', '删除用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.delete()', '[{\"userId\":8}]', 5, '222.64.146.29', '2022-07-04 16:17:44');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (45, 'admin', '删除用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.delete()', '[{\"userId\":10}]', 3, '222.64.146.29', '2022-07-04 16:17:46');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (46, 'admin', '删除用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.delete()', '[{\"userId\":6}]', 4, '222.64.146.29', '2022-07-04 16:17:57');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (47, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":5}]', 4, '222.64.146.29', '2022-07-04 16:18:08');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (48, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":9}]', 3, '222.64.146.29', '2022-07-04 16:18:11');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (49, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"测11\",\"roleIdList\":[1],\"createUserId\":1}]', 383, '127.0.0.1', '2022-07-04 16:21:49');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (50, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"测111\",\"roleIdList\":[1],\"createUserId\":1}]', 149, '127.0.0.1', '2022-07-04 16:23:18');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (51, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"测111\",\"roleIdList\":[1],\"createUserId\":1}]', 125, '127.0.0.1', '2022-07-04 16:23:19');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (52, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":10,\"roleName\":\"1\",\"createUserId\":1,\"menuIdList\":[2],\"createTime\":\"Jul 4, 2022 4:46:58 PM\",\"delFlag\":0}]', 10, '222.64.146.29', '2022-07-04 16:46:59');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (53, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":11,\"roleName\":\"2\",\"createUserId\":1,\"menuIdList\":[3],\"createTime\":\"Jul 4, 2022 4:47:03 PM\",\"delFlag\":0}]', 5, '222.64.146.29', '2022-07-04 16:47:03');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (54, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":12,\"roleName\":\"3\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Jul 4, 2022 4:47:06 PM\",\"delFlag\":0}]', 5, '222.64.146.29', '2022-07-04 16:47:07');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (55, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":13,\"roleName\":\"4\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Jul 4, 2022 4:47:09 PM\",\"delFlag\":0}]', 5, '222.64.146.29', '2022-07-04 16:47:10');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (56, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":14,\"roleName\":\"5\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Jul 4, 2022 4:47:13 PM\",\"delFlag\":0}]', 13, '222.64.146.29', '2022-07-04 16:47:13');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (57, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":15,\"roleName\":\"6\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Jul 4, 2022 4:47:16 PM\",\"delFlag\":0}]', 4, '222.64.146.29', '2022-07-04 16:47:17');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (58, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":16,\"roleName\":\"7\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Jul 4, 2022 4:47:20 PM\",\"delFlag\":0}]', 4, '222.64.146.29', '2022-07-04 16:47:21');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (59, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":10}]', 4, '222.64.146.29', '2022-07-04 16:48:40');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (60, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":11}]', 3, '222.64.146.29', '2022-07-04 16:48:42');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (61, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":12}]', 3, '222.64.146.29', '2022-07-04 16:48:43');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (62, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":13}]', 3, '222.64.146.29', '2022-07-04 16:48:45');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (63, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":14}]', 3, '222.64.146.29', '2022-07-04 16:48:46');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (64, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":15}]', 4, '222.64.146.29', '2022-07-04 16:48:47');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (65, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":16}]', 11, '222.64.146.29', '2022-07-04 16:48:48');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (66, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"普通用户\",\"createUserId\":1,\"menuIdList\":[2,3,1]}]', 7, '222.64.146.29', '2022-07-04 16:49:40');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (67, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":4,\"roleName\":\"普通用户1\",\"createUserId\":1,\"menuIdList\":[2,3,1]}]', 9, '222.64.146.29', '2022-07-04 16:50:16');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (68, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":4,\"roleName\":\"普通用户\",\"createUserId\":1,\"menuIdList\":[2,3,1]}]', 7, '222.64.146.29', '2022-07-04 16:50:37');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (69, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":17,\"roleName\":\"1\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Jul 4, 2022 4:55:16 PM\",\"delFlag\":0}]', 4, '222.64.146.29', '2022-07-04 16:55:16');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (70, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":17,\"roleName\":\"1我未完成\",\"createUserId\":1,\"menuIdList\":[]}]', 5, '222.64.146.29', '2022-07-04 16:55:28');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (71, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":17,\"roleName\":\"1我未完成\",\"createUserId\":1,\"menuIdList\":[2]}]', 6, '222.64.146.29', '2022-07-04 16:55:32');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (72, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":17,\"roleName\":\"1我未完成\",\"createUserId\":1,\"menuIdList\":[2]}]', 6, '222.64.146.29', '2022-07-04 16:55:38');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (73, 'admin', '删除角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.delete()', '[{\"roleId\":17}]', 4, '222.64.146.29', '2022-07-04 16:55:44');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (74, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"普通用户1\",\"createUserId\":1,\"menuIdList\":[2]}]', 90, '127.0.0.1', '2022-07-04 19:04:39');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (75, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":4,\"username\":\"测试\",\"roleIdList\":[2],\"createUserId\":1}]', 32, '127.0.0.1', '2022-07-04 19:04:50');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (76, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":4,\"username\":\"lsw\",\"roleIdList\":[2],\"createUserId\":1}]', 25, '127.0.0.1', '2022-07-04 19:05:15');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (77, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":12,\"username\":\"ll\",\"password\":\"7d4120161d4c526d9dd1adcb99cfcd84b485059560c95f5802458289b6e5e2c3\",\"salt\":\"85Msx09sgy4pOSeqdJUa\",\"roleIdList\":[2],\"createUserId\":1,\"createTime\":\"Jul 8, 2022 10:09:50 AM\",\"delFlag\":0}]', 14, '222.64.146.29', '2022-07-08 10:09:51');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (78, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":13,\"username\":\"long\",\"password\":\"f7dc7f30c3f45aae0449b28edd41c72e4f5cc7139cee2a71cbba2189318739d0\",\"salt\":\"KQUcQQquKpf0fZgP1J7I\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jul 8, 2022 1:37:49 PM\",\"delFlag\":0}]', 137, '127.0.0.1', '2022-07-08 13:37:50');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (79, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":13,\"username\":\"long\",\"roleIdList\":[1,2],\"createUserId\":1}]', 29, '127.0.0.1', '2022-07-08 13:37:58');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (80, 'admin', '保存用户', 'cn.fibo.cdp.modules.sys.controller.SysUserController.save()', '[{\"userId\":13,\"username\":\"long\",\"roleIdList\":[2],\"createUserId\":1}]', 9, '127.0.0.1', '2022-07-08 13:38:36');
INSERT INTO `sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (81, 'admin', '保存角色', 'cn.fibo.cdp.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":4,\"roleName\":\"普通用户\",\"createUserId\":1,\"menuIdList\":[2,3,1,5]}]', 28, '222.64.146.29', '2022-07-11 09:13:54');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否启用 0:启用 1:禁用',
  `long_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '上级所有id',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (1, 0, '数据大盘', '1', NULL, 0, 'isComputer', 1, 0, '0,1');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (2, 1, '公共概览', '/overView/1', 'analysis:common:overview', 1, NULL, 2, 0, '0,1,2');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (3, 1, '我的概览', '/overview/home', 'analysis:overview', 1, NULL, 3, 0, '0,1,3');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (4, 0, '数据分析', '4', NULL, 0, 'isChart', 4, 0, '0,4');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (5, 4, '事件分析', '/analysis/event', 'analysis:event:analysis', 1, NULL, 5, 0, '0,4,5');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (6, 4, '漏斗分析', '/analysis/funnel', 'analysis:funnel:analysis', 1, NULL, 6, 0, '0,4,6');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (7, 4, '留存分析', '/analysis/remain', 'analysis:keep:analysis', 1, NULL, 7, 0, '0,4,7');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (8, 4, '归因分析', '8', NULL, 1, NULL, 8, 1, '0,4,8');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (9, 4, 'LTV分析', 'LTV', NULL, 1, NULL, 9, 1, '0,4,9');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (10, 0, '画像分析', '10', NULL, 0, 'isIdcard', 10, 0, '0,10');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (11, 10, '用户画像', '11', 'analysis:user:program', 1, NULL, 11, 1, '0,10,11');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (12, 10, '用户标签', '12', 'analysis:user:tag', 1, NULL, 12, 1, '0,10,12');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (13, 0, '数据管理', '13', NULL, 0, 'isNav', 13, 0, '0,13');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (14, 13, '事件管理', '/data/eventManagement', 'analysis:metadata:event', 1, NULL, 14, 0, '0,13,14');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (15, 13, '属性管理', '/data/propertyManagement', 'analysis:metadata:property', 1, NULL, 15, 0, '0,13,15');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (16, 0, '平台管理', '16', NULL, 0, 'isDesktop', 16, 0, '0,16');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (17, 16, '账号管理', '/admin/accountManagement', 'sys:user', 1, NULL, 17, 0, '0,16,17');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (18, 16, '角色管理', '/admin/roleManagement', 'sys:role', 1, NULL, 18, 0, '0,16,18');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (19, 0, '数据回传', '19', NULL, 0, 'isRelation', 19, 0, '0,19');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (20, 19, '回传管理', '/passback/management', 'pass:back:platform', 1, NULL, 20, 1, '0,19,20');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (21, 19, '回传策略', '/passback/strategies', 'pass:back:strategy', 1, NULL, 21, 1, '0,19,21');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `disabled`, `long_id`) VALUES (22, 19, '回传记录', '/passback/records', 'pass:back:record', 1, NULL, 22, 1, '0,19,22');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (1, '管理员', NULL, 1, '2022-06-29 14:17:33', 0);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (2, '普通用户1', NULL, 1, '2022-06-29 16:01:41', 0);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (3, '普通用户', NULL, 1, '2022-06-29 16:02:06', 0);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (4, '普通用户', NULL, 1, '2022-06-29 16:03:58', 0);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (5, '测试1', NULL, 1, '2022-07-04 15:28:41', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (6, '测试21', NULL, 1, '2022-07-04 16:02:22', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (7, '111', NULL, 1, '2022-07-04 16:04:25', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (8, '222我去', NULL, 1, '2022-07-04 16:05:31', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (9, '测试2-12', NULL, 1, '2022-07-04 16:06:12', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (10, '1', NULL, 1, '2022-07-04 16:46:59', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (11, '2', NULL, 1, '2022-07-04 16:47:03', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (12, '3', NULL, 1, '2022-07-04 16:47:07', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (13, '4', NULL, 1, '2022-07-04 16:47:10', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (14, '5', NULL, 1, '2022-07-04 16:47:13', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (15, '6', NULL, 1, '2022-07-04 16:47:17', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (16, '7', NULL, 1, '2022-07-04 16:47:21', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`, `del_flag`) VALUES (17, '1我未完成', NULL, 1, '2022-07-04 16:55:16', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (13, 5, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (14, 5, 3);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (15, 5, 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (16, 5, 4);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (17, 5, 5);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (18, 5, 6);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (19, 5, 7);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (21, 6, 3);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (24, 7, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (26, 8, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (29, 9, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (30, 10, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (31, 11, 3);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (32, 3, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (33, 3, 3);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (34, 3, 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (42, 17, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (43, 2, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (44, 4, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (45, 4, 3);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (46, 4, 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (47, 4, 5);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@fibo.cn', '175212345676', 1, 1, '2016-11-11 11:11:11', 0);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (2, 'lishiwei', '6f77dab91764f5355f245f27caef6d1a42e4754ebf677a52c02979133d919474', 'acfRUlleb9tgqvusVLMY', NULL, NULL, 1, 1, '2022-06-29 14:31:07', 1);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (4, 'lsw', '217588c3cd4ccedcc44dd9a8621ad5a4d116bbd894a3a520aa91802611ca70c4', 'eGLfHOZ5kaYPPD8i7TsL', NULL, NULL, 1, 1, '2022-07-04 09:54:07', 0);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (5, '测试删除', '4b6e4e224da34be7084f8e7ff8df032adbf71ee82302559d6b03f0585398d0a6', 'XyOwRBYXAofc2nEUVIpf', NULL, NULL, NULL, 1, '2022-07-04 10:11:04', 1);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (6, '删除测试', '82fd49854006329a1e3400a592f49a906ab7e35465c9a552ed0cba6138f11225', 'dSTchEyDS441HjuTibki', NULL, NULL, NULL, 1, '2022-07-04 11:09:57', 1);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (7, '测11111', '326960412c739d1b171f310343b6382cb656afaaeedeaf20748918f3e2cd587c', 'gAa7KhnRX6G1H8uOIKTS', NULL, NULL, NULL, 1, '2022-07-04 16:07:39', 1);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (8, '测121212', '2a7bec3b96587c79117953d4fc708c91fbefb54370556cbbf136406f780e4e37', 'MjdHb24PS6H7B2eKF0at', NULL, NULL, NULL, 1, '2022-07-04 16:07:46', 1);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (9, '测12', '97bd304354950a2f59829f05ac1ee0fb0021c4f34d86107c0546ca486f084707', 'Y3dmBDPESmZWbwaynh0m', NULL, NULL, NULL, 1, '2022-07-04 16:07:53', 1);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (10, '测111', '4a3151da0a439a1074044aa97ac61ef91bdeb52dce6e80836156300d4eca6efe', 'Rhs0pSC67qRjl63kQgHu', NULL, NULL, NULL, 1, '2022-07-04 16:08:05', 1);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (11, '测111', '4ab59e6e4fc2d3eee76585b6d01524db93dac6bd38b5e219796b36a79858dc8c', 'hQO9moki6nrxAuuUcMq6', NULL, NULL, NULL, 1, '2022-07-04 16:08:39', 1);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (12, 'll', '7d4120161d4c526d9dd1adcb99cfcd84b485059560c95f5802458289b6e5e2c3', '85Msx09sgy4pOSeqdJUa', NULL, NULL, 1, 1, '2022-07-08 10:09:50', 0);
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`, `del_flag`) VALUES (13, 'long', 'f7dc7f30c3f45aae0449b28edd41c72e4f5cc7139cee2a71cbba2189318739d0', 'KQUcQQquKpf0fZgP1J7I', NULL, NULL, 1, 1, '2022-07-08 13:37:50', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (6, 5, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (7, 6, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (12, 9, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (13, 9, 9);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (16, 11, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (17, 11, 9);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (18, 2, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (20, 7, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (21, 7, 9);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (22, 8, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (23, 8, 9);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (27, 10, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (29, 4, 2);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (30, 12, 2);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (34, 13, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_funnel_data
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_funnel_data`;
CREATE TABLE `t_analysis_funnel_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '漏斗名称',
  `window_period` int DEFAULT NULL COMMENT '窗口期 仅漏斗分析存在',
  `status` tinyint(1) DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_analysis_funnel_data
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (3, '漏斗', 3, 0, NULL, 1, 1, '2022-05-15 20:32:45', 1, '2022-05-16 19:33:28');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (4, '漏斗', 3, 0, NULL, 1, 1, '2022-05-15 20:34:01', 1, '2022-05-15 20:34:01');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (5, '1234', 7, 0, NULL, 1, 1, '2022-05-27 16:53:27', 1, '2022-05-27 16:53:27');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (6, '123123', 7, 0, NULL, 1, 1, '2022-05-27 16:54:31', 1, '2022-05-27 16:54:31');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (7, '', 7, 0, NULL, 1, 1, '2022-05-27 16:55:15', 1, '2022-05-27 16:55:15');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (8, 'd', 7, 0, NULL, 1, 1, '2022-05-27 16:55:36', 1, '2022-05-27 16:55:36');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (9, '1232', 7, 0, NULL, 1, 1, '2022-05-27 17:48:56', 1, '2022-05-27 17:48:56');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (10, '123', 7, 0, NULL, 1, 1, '2022-05-27 17:49:20', 1, '2022-05-27 17:49:20');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (11, '123', 7, 0, NULL, 1, 1, '2022-05-27 17:52:53', 1, '2022-05-27 17:52:53');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (12, '1', 7, 0, NULL, 1, 1, '2022-05-27 17:53:26', 1, '2022-05-27 17:53:26');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (13, '324', 7, 0, NULL, 1, 1, '2022-05-27 17:53:47', 1, '2022-05-27 17:53:47');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (14, 'qwe', 7, 0, NULL, 1, 1, '2022-05-27 17:58:48', 1, '2022-05-27 17:58:48');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (15, '324', 7, 0, NULL, 1, 1, '2022-05-27 17:59:47', 1, '2022-05-27 17:59:47');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (16, '123121', 7, 0, NULL, 1, 1, '2022-05-27 18:04:43', 1, '2022-05-27 18:04:43');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (17, '123123', 7, 0, NULL, 1, 1, '2022-05-27 18:05:53', 1, '2022-05-27 18:05:53');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (18, '12321', 3, 0, NULL, 1, 1, '2022-05-27 18:07:33', 1, '2022-05-27 18:07:33');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (19, '12321', 7, 0, NULL, 1, 1, '2022-05-27 18:08:22', 1, '2022-05-27 18:08:22');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (20, '12', 7, 0, NULL, 1, 1, '2022-05-27 18:08:47', 1, '2022-05-27 18:08:47');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (21, '123', 7, 0, NULL, 1, 1, '2022-05-27 18:16:53', 1, '2022-05-27 18:16:53');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (22, '123', 7, 0, NULL, 1, 1, '2022-05-27 18:17:29', 1, '2022-05-27 18:17:29');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (23, '123', 7, 0, NULL, 1, 1, '2022-05-27 18:19:30', 1, '2022-05-27 18:19:30');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (24, '12312', 7, 0, NULL, 1, 1, '2022-05-27 18:21:03', 1, '2022-05-27 18:21:03');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (25, 's', 7, 0, NULL, 1, 1, '2022-05-27 18:21:11', 1, '2022-05-27 18:21:11');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (26, '123', 7, 0, NULL, 1, 1, '2022-05-27 18:23:10', 1, '2022-05-27 18:23:10');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (27, '3122', 7, 0, NULL, 1, 1, '2022-05-30 13:45:26', 1, '2022-05-30 13:45:26');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (28, '订单交易漏斗', 0, 0, NULL, 1, 0, '2022-06-07 10:11:18', 1, '2022-06-07 10:11:18');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (29, '全站漏斗转化2', 7, 0, NULL, 1, 1, '2022-06-07 10:12:47', 1, '2022-06-07 10:12:47');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (30, 'jjj', 7, 0, NULL, 1, 1, '2022-06-07 16:18:00', 1, '2022-06-07 16:18:00');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (31, 'aada', 7, 0, NULL, 1, 1, '2022-06-07 18:16:31', 1, '2022-06-07 18:16:31');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (32, 'nm', 7, 0, NULL, 1, 1, '2022-06-07 18:17:09', 1, '2022-06-07 18:17:09');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (33, '66', 7, 0, NULL, 1, 1, '2022-06-07 10:11:18', 1, '2022-06-07 10:11:18');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (34, 'AAA', 7, 0, NULL, 1, 1, '2022-06-09 18:03:27', 1, '2022-06-09 18:03:27');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (35, '访客登录率', 3, 0, NULL, 1, 0, '2022-06-14 18:35:13', 1, '2022-06-14 18:35:13');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (36, '支付漏斗', 7, 0, NULL, 1, 1, '2022-06-15 15:25:01', 1, '2022-06-15 15:25:01');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (37, '258', 30, 0, NULL, 1, 0, '2022-06-16 13:23:37', 1, '2022-06-16 13:23:37');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (38, '添加漏斗1', 7, 0, NULL, 1, 0, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (39, '357', 7, 0, NULL, 1, 0, '2022-06-16 14:51:00', 1, '2022-06-16 14:51:00');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (40, '11', 0, 0, NULL, 1, 0, '2022-06-16 17:11:19', 1, '2022-06-16 17:11:19');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (41, '全站流量', 0, 0, NULL, 1, 0, '2022-06-24 14:51:53', 1, '2022-06-24 14:51:53');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (42, '支付ceshi', 30, 0, NULL, 1, 0, '2022-06-24 14:56:00', 1, '2022-06-24 14:56:00');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (43, '11111ces', 7, 0, NULL, 1, 1, '2022-06-24 17:44:42', 1, '2022-06-24 17:44:42');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (44, '111测试', 7, 0, NULL, 1, 1, '2022-06-16 14:51:00', 1, '2022-06-16 14:51:00');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (45, '恶趣味热无群1', 7, 0, NULL, 1, 1, '2022-06-16 14:51:00', 1, '2022-06-16 14:51:00');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (46, 'sa\'d\'sa\'d', 7, 0, NULL, 1, 1, '2022-06-27 10:54:45', 1, '2022-06-27 10:54:45');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (47, '1111111打发大水', 7, 0, NULL, 1, 1, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (48, '科技大厦克拉岛1', 7, 0, NULL, 1, 1, '2022-06-27 10:58:39', 1, '2022-06-27 10:58:39');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (49, '2额', 7, 0, NULL, 1, 1, '2022-06-27 10:59:10', 1, '2022-06-27 10:59:10');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (50, '九分裤付款', 7, 0, NULL, 1, 1, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (51, '很快就好客家话‘’', 7, 0, NULL, 1, 1, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (52, '温热无热哇', 7, 0, NULL, 1, 1, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (53, '；k\'n\'l\'k\'n', 7, 0, NULL, 1, 1, '2022-06-27 11:13:41', 1, '2022-06-27 11:13:41');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (54, '斤斤计较', 7, 0, NULL, 1, 1, '2022-06-27 11:19:58', 1, '2022-06-27 11:19:58');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (55, '匹配55', 7, 0, NULL, 1, 1, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (56, '我打算大56', 7, 0, NULL, 1, 1, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (57, '高科技高科技', 7, 0, NULL, 1, 1, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (58, '就回个话58', 7, 0, NULL, 1, 1, '2022-06-27 11:34:00', 1, '2022-06-27 11:34:00');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (59, '看就看见59', 7, 0, NULL, 1, 1, '2022-06-27 11:34:29', 1, '2022-06-27 11:34:29');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (60, '111121321321', 7, 0, NULL, 1, 1, '2022-06-27 11:35:29', 1, '2022-06-27 11:35:29');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (61, '123', 7, 0, NULL, 1, 1, '2022-06-27 11:36:45', 1, '2022-06-27 11:36:45');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (62, '1123231', 7, 0, NULL, 1, 1, '2022-06-27 11:37:50', 1, '2022-06-27 11:37:50');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (63, '45564564564', 7, 0, NULL, 1, 1, '2022-06-16 14:51:00', 1, '2022-06-16 14:51:00');
INSERT INTO `t_analysis_funnel_data` (`id`, `name`, `window_period`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (64, '11111111', 7, 0, NULL, 1, 1, '2022-06-16 14:40:50', 1, '2022-06-16 14:40:50');
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_funnel_step_data
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_funnel_step_data`;
CREATE TABLE `t_analysis_funnel_step_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `funnel_id` bigint DEFAULT NULL COMMENT '关联漏斗配置表',
  `sort` int DEFAULT NULL COMMENT '漏斗步骤序号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '步骤名称',
  `event_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事件英文名',
  `where_uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据筛选条件ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_analysis_funnel_step_data
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (2, 4, 1, 'distinctId', '$pageview', '7c82b6a0d6564b339f8a9763acba97f8');
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (10, 6, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (11, 6, 1, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (12, 6, 2, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (13, 7, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (14, 8, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (15, 9, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (16, 9, 1, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (17, 9, 2, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (18, 10, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (19, 10, 1, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (20, 11, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (21, 12, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (22, 13, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (23, 14, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (24, 15, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (25, 16, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (26, 17, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (27, 18, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (28, 19, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (29, 20, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (30, 21, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (31, 22, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (32, 23, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (33, 24, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (34, 25, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (35, 25, 1, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (36, 26, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (37, 27, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (54, 30, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (55, 31, 0, '小程序页面浏览', '$MPViewScreen', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (56, 31, 1, '全站新用户访问', 'new_visitor_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (63, 33, 0, '登录', '登录', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (68, 3, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (69, 3, 1, '注册结果', 'RegisterResult', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (70, 3, 2, '登录', '登录', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (71, 3, 3, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (77, 34, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (78, 34, 1, '小程序页面浏览', '$MPViewScreen', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (79, 34, 2, '全站新用户访问', 'new_visitor_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (83, 32, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (84, 32, 1, '小程序启动', '$MPLaunch', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (85, 5, 0, '注册结果', 'RegisterResult', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (86, 5, 1, '登录', '登录', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (87, 29, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (88, 29, 1, '浏览商品详情页', '商品详情浏览', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (89, 29, 2, '提交订单', '提交订单', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (90, 29, 3, '支付订单', '订单支付', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (103, 36, 0, '支付订单', '订单支付', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (108, 35, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (109, 35, 1, '登录', '登录', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (110, 37, 0, '全站新用户访问', 'new_visitor_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (124, 40, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (175, 41, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (176, 41, 1, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (185, 28, 0, '登录', '登录', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (186, 28, 1, '浏览商品详情页', '商品详情浏览', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (187, 28, 2, '提交订单', '提交订单', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (188, 28, 3, '支付订单', '订单支付', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (205, 43, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (223, 44, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (225, 46, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (227, 47, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (229, 49, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (238, 50, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (243, 51, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (246, 52, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (247, 53, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (251, 54, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (264, 57, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (267, 42, 0, '支付订单', '订单支付', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (268, 42, 1, '放弃支付', '放弃支付', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (271, 61, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (272, 45, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (276, 62, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (280, 63, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (281, 60, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (282, 59, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (283, 58, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (284, 56, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (285, 55, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (286, 48, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (288, 38, 0, '小程序进入后台', '$MPHide', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (289, 64, 0, '全站流量', 'visit_xn', NULL);
INSERT INTO `t_analysis_funnel_step_data` (`id`, `funnel_id`, `sort`, `name`, `event_en`, `where_uuid`) VALUES (290, 39, 0, '马上抢', '马上抢', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_keep_data
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_keep_data`;
CREATE TABLE `t_analysis_keep_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `start_event` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '起始行为事件英文名',
  `end_event` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '后续行为事件英文名',
  `start_where_uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '起始行为事件筛选条件',
  `end_where_uuid` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '后续行为事件筛选条件',
  `model_id` bigint DEFAULT NULL COMMENT '分析模型ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_analysis_keep_data
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (1, 'visit_xn', 'visit_xn', NULL, NULL, 3);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (2, 'visit_xn', 'visit_xn', NULL, NULL, 177);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (3, 'visit_xn', 'visit_xn', NULL, NULL, 178);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (4, 'visit_xn', 'visit_xn', NULL, NULL, 179);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (5, 'visit_xn', 'visit_xn', NULL, NULL, 180);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (6, 'visit_xn', 'visit_xn', NULL, NULL, 181);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (7, '$MPViewScreen', '$MPViewScreen', NULL, NULL, 184);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (8, 'visit_xn', '登录', NULL, NULL, 185);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (9, 'visit_xn', 'visit_xn', NULL, NULL, 188);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (10, 'visit_xn', 'visit_xn', NULL, NULL, 193);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (11, 'visit_xn', 'visit_xn', NULL, NULL, 200);
INSERT INTO `t_analysis_keep_data` (`id`, `start_event`, `end_event`, `start_where_uuid`, `end_where_uuid`, `model_id`) VALUES (12, 'visit_xn', 'visit_xn', NULL, NULL, 201);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_metadata_customsql_data
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_metadata_customsql_data`;
CREATE TABLE `t_analysis_metadata_customsql_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `query_sql` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'sql语句',
  `params` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '参数设置',
  `model_id` bigint DEFAULT NULL COMMENT '分析模型ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='属性定义表';

-- ----------------------------
-- Records of t_analysis_metadata_customsql_data
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_metadata_customsql_data` (`id`, `query_sql`, `params`, `model_id`) VALUES (1, '是否首日访问', '$is_first_day', NULL);
INSERT INTO `t_analysis_metadata_customsql_data` (`id`, `query_sql`, `params`, `model_id`) VALUES (11, 'select * from events limit 0,10', NULL, 189);
INSERT INTO `t_analysis_metadata_customsql_data` (`id`, `query_sql`, `params`, `model_id`) VALUES (12, 'select distinct_id,type from events e2 limit 0,10', NULL, 196);
INSERT INTO `t_analysis_metadata_customsql_data` (`id`, `query_sql`, `params`, `model_id`) VALUES (13, 'select distinct_id,type from events e2 limit 0,10', NULL, 197);
INSERT INTO `t_analysis_metadata_customsql_data` (`id`, `query_sql`, `params`, `model_id`) VALUES (15, 'SELECT tbd.platform_type \"版本\",\n       tbd.commodity_id \"商品ID\",\n       tbd.commodity_name \"商品名称\",\n       MAX(orders.orderCount) \"提交订单数量\",\n       MAX(orders.orderPrice)/100 \"提交订单金额\",\n       MAX(orders.orderCommodityQuantity) \"提交总件数\",\n       MAX(porders.payOrderCount) \"支付订单数量\",\n       MAX(porders.payOrderPrice)/100 \"支付订单金额\",\n       MAX(porders.payCommodityQuantity) \"支付总件数\",\n       MAX(pl.duration) \"商情页平均停留时长(S)\",\n       count(1) \"商品PV\",\n       count(DISTINCT user_id) \"商品UV\"\nFROM tracking.events tbd\nLEFT JOIN\n  ( SELECT tb1.platform_type,\n           tb1.commodity_id,\n           round(avg(`$event_duration`),2) duration\n   FROM tracking.events tb1\n   WHERE \n   (tb1.event = \'$WebPageLeave\' or tb1.event = \'$MPPageLeave\')\n   AND \n   tb1.project = \'hippo_shop\'\n     AND tb1.platform_type !=\'\'\n     AND tb1.commodity_id !=\'\'\n     AND (tb1.$url_path LIKE \'%pages/detail/detail%\' or tb1.`$url` like \'%pages/detail/detail%\' )\n      AND tb1.flume_time BETWEEN \'{{startTime}}\' AND \'{{endTime}}\'\n   GROUP BY tb1.platform_type,\n            tb1.commodity_id) pl ON pl.commodity_id = tbd.commodity_id\nAND tbd.platform_type = pl.platform_type\nleft JOIN\n  ( SELECT tb1.platform_type,\n           tb1.commodity_id,\n           count(DISTINCT tb1.order_id) orderCount,\n           sum(tb1.total_price_of_commodity) orderPrice,\n           sum(tb1.commodity_quantity) orderCommodityQuantity\n   FROM tracking.events tb1\n   WHERE tb1.event = \'提交订单详情\'\n     AND tb1.project = \'hippo_shop\'\n     AND tb1.platform_type !=\'\'\n     AND tb1.commodity_id !=\'\'\n     AND tb1.flume_time BETWEEN \'{{startTime}}\' AND \'{{endTime}}\'\n   GROUP BY tb1.platform_type,\n            tb1.commodity_id) orders ON orders.commodity_id = tbd.commodity_id\nAND tbd.platform_type = orders.platform_type\nleft JOIN\n  ( SELECT tb1.platform_type,\n           tb1.commodity_id,\n           count(DISTINCT tb1.order_id) payOrderCount,\n           sum(tb1.total_price_of_commodity) payOrderPrice,\n           sum(tb1.commodity_quantity) payCommodityQuantity\n   FROM tracking.events tb1\n   WHERE tb1.event = \'支付订单详情\'\n     AND tb1.project = \'hippo_shop\'\n     AND tb1.platform_type !=\'\'\n     AND tb1.commodity_id !=\'\'\n     AND tb1.flume_time BETWEEN \'{{startTime}}\' AND \'{{endTime}}\'\n   GROUP BY tb1.platform_type,\n            tb1.commodity_id) porders ON porders.commodity_id = tbd.commodity_id\nAND tbd.platform_type = porders.platform_type\nWHERE tbd.project = \'hippo_shop\'\n  AND tbd.platform_type !=\'\'\n  AND tbd.commodity_id !=\'\'\n  AND (tbd.event = \'商品详情浏览\')\n  AND tbd.flume_time BETWEEN \'{{startTime}}\' AND \'{{endTime}}\'\nGROUP BY tbd.platform_type,\n         tbd.commodity_id,\n         tbd.commodity_name\nORDER BY \"提交订单数量\" DESC,\n         \"提交订单金额\" DESC,\n         \"商情页平均停留时长(S)\" DESC', NULL, 199);
INSERT INTO `t_analysis_metadata_customsql_data` (`id`, `query_sql`, `params`, `model_id`) VALUES (16, 'SELECT tbd.platform_type \"版本\",\n       tbd.commodity_id \"商品ID\",\n       tbd.commodity_name \"商品名称\",\n       MAX(orders.orderCount) \"提交订单数量\",\n       MAX(orders.orderPrice)/100 \"提交订单金额\",\n       MAX(orders.orderCommodityQuantity) \"提交总件数\",\n       MAX(porders.payOrderCount) \"支付订单数量\",\n       MAX(porders.payOrderPrice)/100 \"支付订单金额\",\n       MAX(porders.payCommodityQuantity) \"支付总件数\",\n       MAX(pl.duration) \"商情页平均停留时长(S)\",\n       count(1) \"商品PV\",\n       count(DISTINCT user_id) \"商品UV\"\nFROM tracking.events tbd\nLEFT JOIN\n  ( SELECT tb1.platform_type,\n           tb1.commodity_id,\n           round(avg(`$event_duration`),2) duration\n   FROM tracking.events tb1\n   WHERE \n   (tb1.event = \'$WebPageLeave\' or tb1.event = \'$MPPageLeave\')\n   AND \n   tb1.project = \'hippo_shop\'\n     AND tb1.platform_type !=\'\'\n     AND tb1.commodity_id !=\'\'\n     AND (tb1.$url_path LIKE \'%pages/detail/detail%\' or tb1.`$url` like \'%pages/detail/detail%\' )\n      AND tb1.flume_time BETWEEN \'{{startTime}}\' AND \'{{endTime}}\'\n   GROUP BY tb1.platform_type,\n            tb1.commodity_id) pl ON pl.commodity_id = tbd.commodity_id\nAND tbd.platform_type = pl.platform_type\nleft JOIN\n  ( SELECT tb1.platform_type,\n           tb1.commodity_id,\n           count(DISTINCT tb1.order_id) orderCount,\n           sum(tb1.total_price_of_commodity) orderPrice,\n           sum(tb1.commodity_quantity) orderCommodityQuantity\n   FROM tracking.events tb1\n   WHERE tb1.event = \'提交订单详情\'\n     AND tb1.project = \'hippo_shop\'\n     AND tb1.platform_type !=\'\'\n     AND tb1.commodity_id !=\'\'\n     AND tb1.flume_time BETWEEN \'{{startTime}}\' AND \'{{endTime}}\'\n   GROUP BY tb1.platform_type,\n            tb1.commodity_id) orders ON orders.commodity_id = tbd.commodity_id\nAND tbd.platform_type = orders.platform_type\nleft JOIN\n  ( SELECT tb1.platform_type,\n           tb1.commodity_id,\n           count(DISTINCT tb1.order_id) payOrderCount,\n           sum(tb1.total_price_of_commodity) payOrderPrice,\n           sum(tb1.commodity_quantity) payCommodityQuantity\n   FROM tracking.events tb1\n   WHERE tb1.event = \'支付订单详情\'\n     AND tb1.project = \'hippo_shop\'\n     AND tb1.platform_type !=\'\'\n     AND tb1.commodity_id !=\'\'\n     AND tb1.flume_time BETWEEN \'{{startTime}}\' AND \'{{endTime}}\'\n   GROUP BY tb1.platform_type,\n            tb1.commodity_id) porders ON porders.commodity_id = tbd.commodity_id\nAND tbd.platform_type = porders.platform_type\nWHERE tbd.project = \'hippo_shop\'\n  AND tbd.platform_type !=\'\'\n  AND tbd.commodity_id !=\'\'\n  AND (tbd.event = \'商品详情浏览\')\n  AND tbd.flume_time BETWEEN \'{{startTime}}\' AND \'{{endTime}}\'\nGROUP BY tbd.platform_type,\n         tbd.commodity_id,\n         tbd.commodity_name\nORDER BY \"提交订单数量\" DESC,\n         \"提交订单金额\" DESC,\n         \"商情页平均停留时长(S)\" DESC', NULL, 203);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_metadata_event_data
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_metadata_event_data`;
CREATE TABLE `t_analysis_metadata_event_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_cn` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事件中文',
  `name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事件英文名',
  `group_id` bigint DEFAULT NULL COMMENT '分组ID',
  `status` tinyint(1) DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `sort` int DEFAULT NULL COMMENT '排序值',
  `is_virtual` tinyint(1) DEFAULT NULL COMMENT '是否是虚拟事件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='事件定义表';

-- ----------------------------
-- Records of t_analysis_metadata_event_data
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (1, '全站流量', 'visit_xn', 1, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-05-17 02:25:01', 1, 1);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (2, '注册结果', 'RegisterResult', 2, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-06-30 17:28:17', 2, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (3, '支付订单', '订单支付', 3, 0, '123', 1, 0, '2022-05-17 02:25:01', 1, '2022-06-30 17:45:09', 3, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (4, '支付订单详情', '支付订单详情', 3, 0, '00012', 1, 0, '2022-05-17 02:25:01', 1, '2022-06-30 17:34:50', 4, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (5, '提交订单', '提交订单', 3, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-05-17 02:25:01', 5, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (8, '小程序页面浏览', '$MPViewScreen', 1, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-05-17 02:25:01', 8, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (9, '浏览商品详情页', '商品详情浏览', 4, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-05-17 02:25:01', 9, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (10, '全站新用户访问', 'new_visitor_xn', 1, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-05-17 02:25:01', 10, 1);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (12, '小程序启动', '$MPLaunch', 1, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-05-17 02:25:01', 12, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (13, 'Web浏览页面', '$pageview', 1, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-05-17 02:25:01', 13, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (15, '立即购买', '立即购买', 4, 0, NULL, 1, 0, '2022-05-17 02:25:01', 1, '2022-05-17 02:25:01', 15, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (16, '小程序进入后台', '$MPHide', 4, 0, NULL, 1, 0, '2022-05-17 19:29:38', 1, '2022-05-17 19:29:42', 16, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (17, '放弃支付', '放弃支付', 3, 0, NULL, 1, 0, '2022-06-02 14:08:30', 1, '2022-06-02 14:08:37', 17, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (18, '登录', '登录', 2, 0, NULL, 1, 0, '2022-06-02 15:59:04', 1, '2022-06-02 15:59:17', 18, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (19, '马上抢', '马上抢', 4, 0, NULL, 1, 0, '2022-06-02 16:06:34', 1, '2022-06-02 16:06:39', 19, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (20, '继续支付', '继续支付', 3, 0, NULL, 1, 0, '2022-06-02 16:08:02', 1, '2022-06-02 16:08:06', 20, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (23, '提交订单详情', '提交订单详情', 3, 0, NULL, 1, 0, '2022-06-28 14:59:34', 1, '2022-06-28 14:59:38', 6, 1);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (24, 'nameCn', 'nameEn', 1, 0, 'remarks', NULL, 1, NULL, 1, '2022-07-01 10:13:00', 1111, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (25, '佛挡杀佛', 'kjfdalkdsj', 1, 0, '000', 1, 1, '2022-06-30 17:30:10', 1, '2022-07-01 10:14:20', 1234, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (26, '三大', 'asd', 4, 0, '000', 1, 1, '2022-06-30 17:31:29', 1, '2022-07-01 10:16:03', 111, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (27, '行行行', 'xxx', 2, 0, '0123', 1, 1, '2022-07-01 09:03:29', 1, '2022-07-01 09:03:29', NULL, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (28, '十九大可结算', 'mnfdslknds', 1, 0, '012', 1, 0, '2022-07-01 10:09:39', 1, '2022-07-01 15:06:56', 101, 0);
INSERT INTO `t_analysis_metadata_event_data` (`id`, `name_cn`, `name_en`, `group_id`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`, `is_virtual`) VALUES (29, '', '', NULL, 0, '', 1, 1, '2022-07-01 15:08:05', 1, '2022-07-01 15:08:05', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_metadata_event_group
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_metadata_event_group`;
CREATE TABLE `t_analysis_metadata_event_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分组名称',
  `status` tinyint(1) DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `sort` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='事件分组';

-- ----------------------------
-- Records of t_analysis_metadata_event_group
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_metadata_event_group` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`) VALUES (1, '访问流量', 0, NULL, 1, 0, '2022-05-14 22:48:05', 1, '2022-05-14 22:48:10', 1);
INSERT INTO `t_analysis_metadata_event_group` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`) VALUES (2, '登录注册', 0, NULL, 1, 0, '2022-05-17 09:47:42', 1, '2022-05-17 09:47:46', 2);
INSERT INTO `t_analysis_metadata_event_group` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`) VALUES (3, '订单交易', 0, NULL, 1, 0, '2022-05-17 09:48:03', 1, '2022-05-17 09:48:06', 3);
INSERT INTO `t_analysis_metadata_event_group` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `sort`) VALUES (4, '用户行为', 0, NULL, 1, 0, '2022-05-17 09:49:06', 1, '2022-05-17 09:49:10', 4);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_metadata_event_property_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_metadata_event_property_rel`;
CREATE TABLE `t_analysis_metadata_event_property_rel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `event_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事件ID',
  `property_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性定义ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='事件属性关联关系表';

-- ----------------------------
-- Records of t_analysis_metadata_event_property_rel
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (3, '$MPLaunch', '$is_first_day');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (4, '$pageview', '$is_first_day');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (5, '$MPHide', '$event_duration');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (8, '提交订单', 'order_amount');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (9, '提交订单详情', 'commodity_quantity');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (18, '支付订单详情', '$is_first_day');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (19, '支付订单详情', 'is_success');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (25, '订单支付', 'order_actual_amount');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (26, '订单支付', 'platform_type');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (32, 'nameEn', 'nameEn');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (41, 'mnfdslknds', 'order_actual_amount');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (42, 'mnfdslknds', '$event_duration');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (43, 'mnfdslknds', 'platform_type');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (44, 'mnfdslknds', 'commodity_quantity');
INSERT INTO `t_analysis_metadata_event_property_rel` (`id`, `event_en`, `property_en`) VALUES (45, 'mnfdslknds', 'order_amount');
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_metadata_event_virtual_data
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_metadata_event_virtual_data`;
CREATE TABLE `t_analysis_metadata_event_virtual_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `virtual_event_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '虚拟事件英文名',
  `name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事件英文名',
  `where_uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '该属性的筛选条件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='虚拟事件明细表';

-- ----------------------------
-- Records of t_analysis_metadata_event_virtual_data
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_metadata_event_virtual_data` (`id`, `virtual_event_en`, `name_en`, `where_uuid`) VALUES (1, 'visit_xn', '$pageview', NULL);
INSERT INTO `t_analysis_metadata_event_virtual_data` (`id`, `virtual_event_en`, `name_en`, `where_uuid`) VALUES (2, 'visit_xn', '$MPViewScreen', NULL);
INSERT INTO `t_analysis_metadata_event_virtual_data` (`id`, `virtual_event_en`, `name_en`, `where_uuid`) VALUES (4, 'new_visitor_xn', '$MPViewScreen', '537d0494568c68e80c8979646f2de33');
INSERT INTO `t_analysis_metadata_event_virtual_data` (`id`, `virtual_event_en`, `name_en`, `where_uuid`) VALUES (5, 'new_visitor_xn', '$pageview', '4f835cf95e6f169732335a6b93f0afea');
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_metadata_property_data
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_metadata_property_data`;
CREATE TABLE `t_analysis_metadata_property_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_cn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性中文名',
  `name_en` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性英文名',
  `field_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性类型',
  `status` tinyint(1) DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_group_view` tinyint(1) DEFAULT NULL COMMENT '是否支持按...查看',
  `sort` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='属性定义表';

-- ----------------------------
-- Records of t_analysis_metadata_property_data
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (1, '是否首日访问', '$is_first_day', 'bool', 0, '01', 1, 0, '2022-05-15 12:47:58', 1, '2022-07-04 10:13:18', 1, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (2, '是否成功', 'is_success', 'bool', 0, NULL, 1, 0, '2022-05-17 15:22:53', 1, '2022-05-17 15:22:57', 1, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (3, '订单实付金额', 'order_actual_amount', 'number', 0, NULL, 1, 0, '2022-05-17 15:27:27', 1, '2022-05-17 15:27:30', 0, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (4, '停留时长', '$event_duration', 'number', 0, NULL, 1, 0, '2022-05-17 15:35:46', 1, '2022-05-17 15:35:50', 0, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (5, '端口', 'platform_type', 'string', 0, '111', 1, 0, '2022-05-17 17:37:51', 1, '2022-07-04 18:58:48', 1, 10);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (6, '省份', '$province', 'string', 0, NULL, 1, 0, '2022-05-20 14:53:14', 1, '2022-05-20 14:53:17', 1, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (7, '渠道编号', 'channel', 'string', 0, NULL, 1, 0, '2022-05-27 10:43:07', 1, '2022-05-27 10:43:11', 1, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (8, '商品名称', 'commodity_name', 'string', 0, NULL, 1, 0, '2022-06-28 14:47:05', 1, '2022-06-28 14:47:08', 1, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (9, '商品数量', 'commodity_quantity', 'number', 0, NULL, 1, 0, '2022-06-28 14:55:11', 1, '2022-06-28 14:55:14', 0, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (10, '提交订单金额', 'order_amount', 'number', 0, NULL, 1, 0, '2022-06-28 15:08:41', 1, '2022-06-28 15:08:46', 0, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (11, 'nameCn', 'nameEn', 'string', NULL, NULL, NULL, 1, NULL, 1, '2022-06-28 18:31:17', 0, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (12, 'type', 'type', 'string', 0, NULL, 1, 0, '2022-06-30 18:37:00', 1, '2022-06-30 18:37:00', 0, NULL);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (15, '操作系统', '$os', 'string', 0, '打算的', 1, 1, '2022-07-01 14:25:55', 1, '2022-07-01 14:25:55', 0, 2);
INSERT INTO `t_analysis_metadata_property_data` (`id`, `name_cn`, `name_en`, `field_type`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `is_group_view`, `sort`) VALUES (16, '设备型号', '$model', 'string', 0, '000', 1, 1, '2022-07-01 14:39:11', 1, '2022-07-01 14:39:11', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_model_data
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_model_data`;
CREATE TABLE `t_analysis_model_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` tinyint DEFAULT NULL COMMENT '分型模型类型 0:事件 1:漏斗 2:留存 3:自定义',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模型名称',
  `time_granularity` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '时间粒度day、week、month、hour、minute  ',
  `time_value` int DEFAULT NULL COMMENT '时间值',
  `chart_type` tinyint DEFAULT NULL COMMENT '0:线图 1:柱状图 2:环图 3:堆积图 4:表格 5:数值 仅事件分析存在 6:漏斗分析',
  `where_uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '全局筛选uuid',
  `is_avg` tinyint(1) DEFAULT NULL COMMENT '是否同时显示均值',
  `is_sum` tinyint(1) DEFAULT NULL COMMENT '是否同时合计',
  `is_year_to_year` tinyint(1) DEFAULT NULL COMMENT '是否同时显示同环比',
  `window_size` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '窗口尺寸',
  `status` tinyint(1) DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `overview_id` bigint DEFAULT NULL COMMENT '归属概览ID',
  `calc_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '按...计算,users按人数、total按次数',
  `sort` int DEFAULT NULL COMMENT '所在概览的序号',
  `start_time` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '结束时间',
  `funnel_id` bigint DEFAULT NULL COMMENT '漏斗ID',
  `relation` varchar(5) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '全局筛选逻辑关联AND、OR',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_analysis_model_data
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (15, 0, '全站访问流量', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-05-31 10:56:54', 1, '2022-05-31 10:56:54', 3, NULL, 0, '2022-05-01 00:00:00.000', '2022-05-07 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (17, 0, '全站新用户访问数量', 'day', NULL, 5, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-05-31 12:55:14', 1, '2022-05-31 12:55:14', 3, NULL, 4, '2022-05-15 00:00:00.000', '2022-05-21 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (19, 0, '全站流量', 'day', NULL, 5, NULL, 1, 1, 1, 'large', 0, NULL, 1, 0, '2022-05-31 13:17:54', 1, '2022-05-31 13:17:54', 3, NULL, 6, '2022-05-15 00:00:00.000', '2022-05-28 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (20, 0, '全站/小程序占比', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-05-31 13:27:24', 1, '2022-05-31 13:27:24', 3, NULL, 5, '2022-05-22 00:00:00.000', '2022-05-28 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (21, 0, '全站流量/web浏览占比', 'day', NULL, 0, NULL, 1, 1, 1, 'large', 0, NULL, 1, 0, '2022-05-31 14:25:36', 1, '2022-05-31 14:25:36', 3, NULL, 7, '2022-05-22 00:00:00.000', '2022-05-28 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (25, 0, '全站新用户访问', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-05-31 21:42:29', 1, '2022-05-31 21:42:29', 3, NULL, NULL, '2022-05-15 00:00:00.000', '2022-05-28 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (26, 0, '全站新用户访问', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-05-31 21:57:58', 1, '2022-05-31 21:57:58', 3, NULL, NULL, '2022-05-15 00:00:00.000', '2022-05-28 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (29, 0, '测试删除', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-05-31 22:06:43', 1, '2022-05-31 22:06:43', 3, NULL, NULL, '2022-05-01 00:00:00.000', '2022-05-24 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (36, 1, '2', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-07 16:09:21', 1, '2022-06-07 16:09:21', 3, NULL, NULL, '2022-06-07', '2022-06-07', 3, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (37, 1, '32', 'day', NULL, 6, 'c407a6cf032546508a3ceff2aa781987', NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-08 14:09:10', 1, '2022-06-08 14:09:10', 3, NULL, NULL, '2022-06-08', '2022-06-08', 3, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (38, 0, '1', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-06-08 15:02:11', 1, '2022-06-08 15:02:11', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (39, 0, '2', 'day', NULL, 1, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-08 15:02:38', 1, '2022-06-08 15:02:38', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (40, 0, '3', 'day', NULL, 3, NULL, 1, 1, 1, 'normal', 0, NULL, 1, 0, '2022-06-08 15:03:25', 1, '2022-06-08 15:03:25', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (42, 0, '3', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-06-08 15:04:39', 1, '2022-06-08 15:04:39', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (43, 0, '4', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-06-08 15:04:44', 1, '2022-06-08 15:04:44', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (45, 0, '7', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-06-08 15:07:19', 1, '2022-06-08 15:07:19', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (46, 0, '8', 'day', NULL, 0, NULL, 1, 1, 0, 'large', 0, NULL, 1, 0, '2022-06-08 15:07:47', 1, '2022-06-08 15:07:47', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (47, 0, '9', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-06-08 15:08:03', 1, '2022-06-08 15:08:03', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (48, 1, '56', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-08 15:31:43', 1, '2022-06-08 15:31:43', 19, NULL, NULL, '2022-06-08', '2022-06-08', 3, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (49, 0, '123333', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-08 17:08:02', 1, '2022-06-08 17:08:02', 19, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (50, 0, '123222', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-08 17:08:18', 1, '2022-06-08 17:08:18', 18, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-08 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (52, 1, '332', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-08 17:20:14', 1, '2022-06-08 17:20:14', 19, NULL, NULL, '2022-06-08', '2022-06-08', 8, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (53, 1, '453', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-08 17:21:25', 1, '2022-06-08 17:21:25', 19, NULL, NULL, '2022-06-08', '2022-06-08', 3, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (54, 1, '66', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-08 17:22:03', 1, '2022-06-08 17:22:03', 19, NULL, NULL, '2022-06-01', '2022-06-07', 32, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (57, 1, '112334', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-09 10:30:47', 1, '2022-06-09 10:30:47', 19, NULL, NULL, '2022-05-02', '2022-05-30', 32, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (59, 1, 'nm首次访问23之不访问', 'day', NULL, 6, '5935d7b29214470eaf9d1418c1a51563', NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-09 15:58:36', 1, '2022-06-09 16:59:45', 19, NULL, NULL, '2022-06-02', '2022-06-09', 32, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (60, 0, 'AAAAAA', 'month', NULL, 1, NULL, 1, 1, 1, 'large', 0, NULL, 1, 0, '2022-06-10 10:46:09', 1, '2022-06-10 10:46:09', 19, NULL, NULL, '2022-04-01 00:00:00.000', '2022-06-10 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (61, 0, 'BBBB', 'month', NULL, 0, '943c013e08cd48f592321cdf338e0327', 1, 1, 0, 'large', 0, NULL, 1, 0, '2022-06-10 10:47:05', 1, '2022-06-10 11:09:31', 19, NULL, NULL, '2022-03-01 00:00:00.000', '2022-06-09 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (67, 0, '@@@', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-13 10:59:40', 1, '2022-06-13 10:59:40', 23, NULL, NULL, '2022-06-13 00:00:00.000', '2022-06-13 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (68, 0, '托尔斯泰', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-13 15:56:38', 1, '2022-06-13 15:56:38', 27, NULL, NULL, '2022-06-13 00:00:00.000', '2022-06-13 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (73, 0, '全站流量', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-13 18:11:35', 1, '2022-06-15 10:21:23', 1, NULL, 1, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (74, 0, '全站日活', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-13 18:12:16', 1, '2022-06-15 10:21:36', 1, NULL, 2, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (75, 0, '登录用户数', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-13 18:13:33', 1, '2022-06-24 16:19:17', 1, NULL, 3, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (76, 0, '支付人数', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-13 18:14:13', 1, '2022-06-15 10:24:08', 1, NULL, 8, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (77, 0, '订单量', 'day', NULL, 1, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-13 18:20:28', 1, '2022-06-14 18:43:33', 1, NULL, 6, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (78, 0, '新访问用户数', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-13 18:22:12', 1, '2022-06-14 21:43:45', 1, NULL, 4, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (79, 0, '提交订单数', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-13 18:23:20', 1, '2022-06-15 10:24:00', 1, NULL, 7, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (80, 0, '放弃支付用户数', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-13 18:23:43', 1, '2022-06-24 16:21:17', 1, NULL, 9, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (82, 1, '000000', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-14 14:32:16', 1, '2022-06-14 14:32:16', 18, NULL, NULL, '2022-06-14', '2022-06-14', 5, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (84, 0, 'GMV', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-14 18:08:12', 1, '2022-06-15 19:25:05', 1, NULL, 5, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (85, 1, '交易转化漏斗', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-14 18:19:36', 1, '2022-06-30 13:10:17', 1, NULL, 11, '2022-06-06', '2022-06-12', 28, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (87, 1, '访客登录率', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-14 18:38:41', 1, '2022-06-29 14:31:40', 1, NULL, 12, '2022-06-06', '2022-06-12', 35, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (88, 0, 'GMV每小时变化趋势', 'hour', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-14 18:42:59', 1, '2022-07-12 16:27:56', 1, NULL, 60, '2022-06-23 00:00:00.000', '2022-06-23 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (89, 0, '过去7天新访问用户数', 'day', NULL, 1, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-14 18:55:05', 1, '2022-06-15 10:26:07', 1, NULL, 62, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (103, 0, '', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-15 11:21:36', 1, '2022-06-15 11:21:36', 18, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-14 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (120, 0, '111', 'day', NULL, 3, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-15 13:16:38', 1, '2022-06-15 13:16:38', 20, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-14 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (130, 0, '测试', 'month', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-15 13:34:15', 1, '2022-06-16 14:01:54', 29, NULL, NULL, '2022-04-06 00:00:00.000', '2022-04-30 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (134, 0, '88888', 'day', NULL, 5, NULL, 1, 1, 1, 'normal', 0, NULL, 1, 0, '2022-06-15 14:21:49', 1, '2022-06-15 14:21:49', 20, NULL, NULL, '2022-06-03 00:00:00.000', '2022-06-03 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (136, 0, '', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-15 14:22:59', 1, '2022-06-15 14:22:59', 20, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-14 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (138, 0, '', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-15 14:27:59', 1, '2022-06-15 14:27:59', 29, NULL, NULL, '2022-06-05 00:00:00.000', '2022-06-05 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (139, 0, '客单价', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-15 14:48:59', 1, '2022-06-15 19:25:13', 1, NULL, 10, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (140, 0, '', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-06-15 15:00:23', 1, '2022-06-15 15:00:23', 20, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-14 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (142, 0, '小程序平均使用时长（秒）', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-15 15:43:43', 1, '2022-06-24 16:23:47', 1, NULL, 9999, '2022-06-06 00:00:00.000', '2022-06-12 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (143, 0, '', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-06-15 15:44:30', 1, '2022-06-15 15:45:03', 29, NULL, NULL, '2022-06-08 00:00:00.000', '2022-06-14 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (153, 0, '测试概览-测试概览-测试概览-测试概览-测试概览-测试概览-测试概览', 'month', NULL, 0, '461dbcee96f448f2854865ff50cafefd', 1, 1, 1, 'normal', 0, NULL, 1, 0, '2022-06-16 10:51:11', 1, '2022-06-16 10:54:22', 30, NULL, 9999, '2022-05-01 00:00:00.000', '2022-06-01 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (154, 1, '测试漏斗分析1', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-16 11:26:37', 1, '2022-06-16 14:47:12', 30, NULL, 9999, '2022-05-01', '2022-06-01', 35, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (156, 0, '215', 'day', NULL, 5, NULL, 1, 1, 1, 'large', 0, NULL, 1, 0, '2022-06-16 13:13:31', 1, '2022-06-16 13:15:16', 30, NULL, 9999, '2022-05-01 00:00:00.000', '2022-05-31 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (157, 0, '369', 'day', NULL, 1, '76ed567614ec45359386d0edd8ea6f9a', 1, 1, 0, 'large', 0, NULL, 1, 0, '2022-06-16 13:56:31', 1, '2022-06-16 13:58:58', 30, NULL, 9999, '2022-06-01 00:00:00.000', '2022-06-15 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (158, 0, '测试名称长度-测试名称长度-测试名称长度-测试名称长度-测试名称长度-测试名称长度', 'day', NULL, 5, '0586bd0dd21449868ea1aa826e42a31f', 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-06-16 14:02:20', 1, '2022-06-16 14:02:20', 30, NULL, 9999, '2022-06-01 00:00:00.000', '2022-06-16 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (162, 0, 'list', 'minute', NULL, 0, 'd751dc89c29a4199b200237ef5b07bc4', 1, 1, 0, 'large', 0, NULL, 1, 0, '2022-06-16 16:48:36', 1, '2022-06-16 16:49:09', 30, NULL, 9999, '2022-06-09 00:00:00.000', '2022-06-15 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (163, 0, '', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-17 10:39:55', 1, '2022-06-17 10:39:55', 29, NULL, 9999, '2022-06-10 00:00:00.000', '2022-06-16 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (165, 0, '', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-17 11:07:44', 1, '2022-06-17 11:17:48', 33, NULL, 9999, '2022-06-10 00:00:00.000', '2022-06-16 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (166, 0, '111', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-17 11:17:54', 1, '2022-06-17 12:10:44', 33, NULL, 9999, '2022-06-10 00:00:00.000', '2022-06-16 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (167, 1, '', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-17 11:18:02', 1, '2022-06-17 11:18:02', 33, NULL, 9999, '2022-06-10', '2022-06-16', 28, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (169, 1, '', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-17 11:35:03', 1, '2022-06-17 11:35:03', 33, NULL, 9999, '2022-06-10', '2022-06-16', 28, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (170, 0, '1', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-17 14:37:09', 1, '2022-06-17 14:37:09', 33, NULL, 9999, '2022-06-10 00:00:00.000', '2022-06-16 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (171, 1, '1范德萨发', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-17 15:36:06', 1, '2022-06-17 15:36:54', 33, NULL, 9999, '2022-06-10', '2022-06-16', 28, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (186, 1, '44', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-23 18:43:55', 1, '2022-06-23 18:43:55', 29, NULL, 9999, '2022-06-16', '2022-06-22', 28, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (187, 0, '44', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-23 18:44:10', 1, '2022-06-23 18:44:10', 29, NULL, 9999, '2022-06-16 00:00:00.000', '2022-06-22 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (188, 2, '全站留存', 'day', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-23 19:09:33', 1, '2022-06-23 19:09:33', 1, NULL, 9999, '2022-06-16', '2022-06-22', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (189, 3, '10条数据', NULL, NULL, 8, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-24 13:34:10', 1, '2022-06-24 13:50:24', 3, NULL, 9999, '2022-05-30 00:00', '2022-05-30 10:00', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (190, 0, '渠道A-5月流量', 'day', NULL, 0, 'ce4cef6c889c4d41b365589debc647dd', 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-24 16:10:09', 1, '2022-06-24 16:11:44', 35, NULL, 9999, '2022-05-01 00:00:00.000', '2022-05-31 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (191, 0, '渠道A-5月GMV', 'day', NULL, 1, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-24 16:10:41', 1, '2022-06-24 16:11:32', 35, NULL, 9999, '2022-05-01 00:00:00.000', '2022-05-28 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (192, 0, '每小时访问量', 'minute', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-24 16:12:16', 1, '2022-06-24 16:13:54', 35, NULL, 9999, '2022-06-22 00:00:00.000', '2022-06-22 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (193, 2, '用户留存情况', 'day', 7, 7, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-24 16:28:09', 1, '2022-06-24 16:28:09', 1, NULL, 9999, '2022-06-01', '2022-06-07', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (194, 0, '支付宝小程序各渠道UV', 'day', NULL, 0, 'da436ba3ee1e4be78c312fa0bff3ffcf', 1, 1, 0, 'large', 0, NULL, 1, 0, '2022-06-24 17:22:06', 1, '2022-06-24 17:27:12', 1, NULL, 9999, '2022-06-01 00:00:00.000', '2022-06-24 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (195, 0, 'H5各渠道UV', 'day', NULL, 0, 'abc51294638b4333b80938a039f34c4b', 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-06-24 17:26:21', 1, '2022-06-24 17:27:53', 35, NULL, 9999, '2022-06-17 00:00:00.000', '2022-06-23 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (197, 3, 'ytuytut', NULL, NULL, 8, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, '2022-06-28 16:19:21', 1, '2022-06-28 16:19:21', 37, NULL, 9999, '2022-06-21', '2022-06-27', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (198, 0, 'cdc', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-06-28 16:22:48', 1, '2022-06-28 16:22:48', 37, NULL, 9999, '2022-06-21 00:00:00.000', '2022-06-27 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (199, 3, 'hzzj1', NULL, NULL, 8, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-29 13:36:13', 1, '2022-06-29 14:34:55', 37, NULL, 9999, '2022-06-01', '2022-06-04', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (200, 2, 'hzzjhzzj', 'day', 7, 7, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, '2022-06-29 14:45:17', 1, '2022-06-29 14:45:17', 37, NULL, 9999, '2022-06-22', '2022-06-28', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (201, 2, 'cs', 'day', 7, 7, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, '2022-06-29 14:47:02', 1, '2022-06-29 14:47:02', 37, NULL, 9999, '2022-06-22', '2022-06-28', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (202, 1, 'ld', 'day', NULL, 6, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-29 14:47:59', 1, '2022-06-29 14:47:59', 37, NULL, 9999, '2022-06-22', '2022-06-28', 28, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (203, 3, '商品销量榜单', NULL, NULL, 8, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 0, '2022-06-29 16:43:53', 1, '2022-06-29 16:43:53', 1, NULL, 9999, '2022-06-22', '2022-06-28', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (204, 0, '放弃支付', 'day', NULL, 0, NULL, 1, 1, 1, 'normal', 0, NULL, 1, 0, '2022-07-06 17:10:58', 1, '2022-07-06 17:12:27', 1, NULL, 9999, '2022-06-29 00:00:00.000', '2022-07-05 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (205, 0, '全站流量', 'day', NULL, 5, NULL, 1, 1, 1, 'mini', 0, NULL, 1, 0, '2022-07-06 17:14:35', 1, '2022-07-06 17:17:32', 39, NULL, 9999, '2022-06-29 00:00:00.000', '2022-07-05 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (206, 0, '提单支付', 'day', NULL, 0, NULL, 1, 1, 0, 'normal', 0, NULL, 1, 0, '2022-07-06 17:17:04', 1, '2022-07-06 17:17:04', 39, NULL, 9999, '2022-07-01 00:00:00.000', '2022-07-06 23:59:59.999', NULL, NULL);
INSERT INTO `t_analysis_model_data` (`id`, `type`, `name`, `time_granularity`, `time_value`, `chart_type`, `where_uuid`, `is_avg`, `is_sum`, `is_year_to_year`, `window_size`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_id`, `calc_type`, `sort`, `start_time`, `end_time`, `funnel_id`, `relation`) VALUES (207, 0, '日活用户', 'day', NULL, 5, NULL, 1, 1, 0, 'mini', 0, NULL, 1, 0, '2022-07-06 17:20:34', 1, '2022-07-06 17:20:34', 39, NULL, 9999, '2022-06-29 00:00:00.000', '2022-07-05 23:59:59.999', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_model_group
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_model_group`;
CREATE TABLE `t_analysis_model_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `model_id` bigint DEFAULT NULL COMMENT '模型ID',
  `property_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性英文名',
  `sort` int DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_analysis_model_group
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (15, 3, 'platform_type', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (16, 3, '$province', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (17, 60, 'is_success', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (25, 61, 'is_success', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (32, 32, 'platform_type', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (33, 174, 'platform_type', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (34, 175, 'platform_type', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (35, 181, 'platform_type', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (41, 194, 'channel', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (42, 195, 'platform_type', NULL);
INSERT INTO `t_analysis_model_group` (`id`, `model_id`, `property_en`, `sort`) VALUES (43, 201, 'platform_type', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_model_select
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_model_select`;
CREATE TABLE `t_analysis_model_select` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `model_id` bigint DEFAULT NULL COMMENT '分析模型ID',
  `event_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事件英文名',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `property_en` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性英文名',
  `calc_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '计算方式总次数total、用户数users、',
  `sort` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '排序值字母',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=364 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_analysis_model_select
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (13, 2, 'visit_xn', '全站流量的总次数', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (16, 1, 'visit_xn', '全站流量的总次数', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (17, 4, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (18, 5, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (19, 6, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (20, 7, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (21, 8, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (22, 9, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (23, 10, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (24, 11, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (25, 12, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (26, 13, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (27, 14, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (28, 15, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (29, 16, '$MPViewScreen', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (30, 17, 'new_visitor_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (31, 18, '$MPLaunch', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (32, 19, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (33, 20, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (34, 20, '$MPViewScreen', '小程序页面浏览', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (35, 21, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (36, 21, '$pageview', '指标标题-2', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (37, 22, '$MPViewScreen', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (38, 23, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (40, 25, 'new_visitor_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (41, 26, 'new_visitor_xn', '全站新用户访问', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (42, 27, '$MPLaunch', '小程序启动', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (43, 28, 'new_visitor_xn', '全站新用户名访问', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (44, 29, '订单支付', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (45, 30, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (46, 31, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (48, 33, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (49, 38, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (50, 39, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (51, 40, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (52, 41, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (53, 42, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (54, 43, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (55, 44, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (56, 45, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (57, 46, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (58, 47, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (59, 49, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (60, 50, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (61, 51, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (62, 56, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (63, 58, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (64, 60, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (65, 60, 'new_visitor_xn', '全站新用户访问', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (80, 61, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (81, 61, '$MPLaunch', '小程序启动', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (84, 62, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (85, 63, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (86, 64, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (87, 65, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (88, 66, 'visit_xn', '指标标题-1', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (93, 67, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (94, 67, 'new_visitor_xn', '全站新用户访问', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (105, 24, '$MPViewScreen', '小程序页面访问', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (106, 68, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (107, 32, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (111, 69, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (112, 71, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (117, 70, 'visit_xn', '全站流量', NULL, 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (169, 77, '订单支付', '支付订单', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (193, 78, 'new_visitor_xn', '全站新用户访问', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (195, 73, 'visit_xn', '全站流量', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (196, 74, 'visit_xn', '全站流量', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (201, 79, '提交订单', '提交订单', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (202, 76, '订单支付', '支付订单', NULL, 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (204, 81, '继续支付', '继续支付', NULL, 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (207, 89, 'new_visitor_xn', '全站新用户访问', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (208, 90, '订单支付', '支付订单', 'order_actual_amount', 'propleAvg', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (209, 91, '订单支付', '支付订单', 'order_actual_amount', 'propleAvg', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (210, 92, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (211, 93, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (212, 94, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (213, 95, 'RegisterResult', '注册结果', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (214, 96, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (215, 97, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (216, 98, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (218, 100, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (220, 101, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (223, 99, '订单支付', '支付订单', 'order_actual_amount', 'peopleAvg', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (224, 103, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (225, 102, 'visit_xn', '全站流量', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (226, 104, '登录', '登录', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (227, 105, '订单支付', '支付订单', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (228, 106, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (229, 107, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (230, 108, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (231, 109, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (233, 110, 'visit_xn', '全站流量', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (234, 111, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (235, 112, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (236, 114, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (237, 115, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (238, 116, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (239, 117, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (240, 119, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (241, 120, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (242, 122, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (243, 123, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (244, 124, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (246, 126, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (247, 127, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (248, 125, 'visit_xn', '全站流量', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (249, 129, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (251, 131, 'visit_xn', '全站流量', '$is_first_day', 'count', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (253, 132, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (254, 133, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (255, 134, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (256, 135, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (257, 136, 'visit_xn', '全站流量', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (259, 137, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (260, 138, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (264, 140, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (270, 143, 'visit_xn', '全站流量', '$is_first_day', 'count', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (276, 144, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (278, 141, '订单支付', '支付订单', 'platform_type', 'count', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (282, 145, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (283, 146, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (284, 147, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (286, 148, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (287, 149, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (289, 150, '提交订单', '提交订单', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (290, 151, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (291, 84, '订单支付', '支付订单的实付金额', 'order_actual_amount', 'sum', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (292, 139, '订单支付', '支付订单的实付金额', 'order_actual_amount', 'peopleAvg', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (293, 152, 'RegisterResult', '注册结果', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (294, 152, 'visit_xn', '全站流量', '$is_first_day', 'count', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (297, 153, 'visit_xn', '全站流量', '$is_first_day', 'count', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (298, 153, 'visit_xn', '全站流量1', '$is_first_day', 'count', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (299, 155, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (300, 155, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (303, 156, '$pageview', 'Web浏览页面', '$is_first_day', 'count', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (306, 157, '订单支付', '支付订单', 'order_actual_amount', 'avg', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (307, 157, '提交订单', '提交订单', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (308, 130, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (309, 158, '提交订单', '提交订单', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (310, 159, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (311, 159, 'visit_xn', '全站流量-2', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (314, 160, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (318, 162, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (319, 163, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (320, 164, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (323, 165, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (325, 168, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (326, 166, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (327, 170, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (330, 187, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (331, 172, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (335, 191, '订单支付', '支付订单', 'order_actual_amount', 'sum', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (336, 190, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (340, 192, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (341, 75, '登录', '登录', NULL, 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (343, 80, '放弃支付', '放弃支付', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (344, 142, '$MPHide', '小程序进入后台的停留时长', '$event_duration', 'peopleAvg', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (351, 194, 'visit_xn', '全站流量', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (352, 195, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (353, 198, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (355, 204, '放弃支付', '放弃支付', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (356, 204, '继续支付', '继续支付', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (359, 206, '订单支付', '支付订单', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (360, 206, '提交订单', '提交订单', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (361, 205, 'visit_xn', '全站流量', '', 'total', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (362, 207, 'visit_xn', '全站流量', '', 'users', 'A');
INSERT INTO `t_analysis_model_select` (`id`, `model_id`, `event_en`, `name`, `property_en`, `calc_type`, `sort`) VALUES (363, 88, '订单支付', '支付订单的实付金额', 'order_actual_amount', 'sum', 'A');
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_model_where
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_model_where`;
CREATE TABLE `t_analysis_model_where` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '存在筛选条件的业务uuid',
  `property_en` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事件属性英文名',
  `conditions` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '判断条件',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '判断值',
  `sort` int DEFAULT NULL COMMENT '序号',
  `max_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '判断值最大值区间使用',
  `source` int DEFAULT NULL COMMENT '哪里的where条件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_analysis_model_where
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (1, '537d0494568c68e80c8979646f2de33', '$is_first_day', 'true', NULL, 0, NULL, NULL);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (2, '4f835cf95e6f169732335a6b93f0afea', '$is_first_day', 'true', NULL, 0, NULL, NULL);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (7, 'c959ac68f90843ea9778f8fda2424d6a', 'platform_type', 'like', 'H', NULL, NULL, NULL);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (10, '2c68465040ad4d4da73631ab4187eed6', 'platform_type', 'like', 'H', NULL, NULL, NULL);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (12, 'd9c9c1ee33aa4314b96d205df6850dd8', 'platform_type', 'like', 'H', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (13, 'c407a6cf032546508a3ceff2aa781987', 'is_success', 'false', '', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (22, '943c013e08cd48f592321cdf338e0327', '$event_duration', 'range', '12', NULL, '32', 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (23, '63cd721537fb40be9dae997285be004b', '$event_duration', 'range', '', NULL, '10', 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (25, '461dbcee96f448f2854865ff50cafefd', '$is_first_day', 'true', '', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (27, '76ed567614ec45359386d0edd8ea6f9a', '$province', '!=', '东海', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (28, '0586bd0dd21449868ea1aa826e42a31f', 'channel', 'like', '342！@#kj', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (30, '81e39dcf9bf34221974355a780d40d4a', '$event_duration', '!=', '5', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (31, 'd751dc89c29a4199b200237ef5b07bc4', '$is_first_day', 'true', '', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (32, '916829b0b1e34659a681e07f6d71790a', 'channel', '=', '60003', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (35, 'ce4cef6c889c4d41b365589debc647dd', 'channel', '=', '50001', NULL, NULL, 1);
INSERT INTO `t_analysis_model_where` (`id`, `uuid`, `property_en`, `conditions`, `value`, `sort`, `max_value`, `source`) VALUES (41, 'da436ba3ee1e4be78c312fa0bff3ffcf', 'platform_type', '=', 'H5', NULL, NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_overview
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_overview`;
CREATE TABLE `t_analysis_overview` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '概览名称',
  `status` tinyint(1) DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `overview_type` tinyint(1) DEFAULT NULL COMMENT '0 公共概览 1用户自定义概览',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='概览基础信息表';

-- ----------------------------
-- Records of t_analysis_overview
-- ----------------------------
BEGIN;
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (1, '公共概览', 0, NULL, 1, 0, '2022-06-10 14:17:45', 1, '2022-06-10 14:17:41', 0);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (2, '我的', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (3, '演示概览', 0, NULL, 1, 1, '2022-05-23 14:52:12', 1, '2022-05-31 21:34:45', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (4, 'test_b', 0, NULL, 1, 1, '2022-05-23 15:31:39', 1, '2022-05-23 15:31:39', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (5, 'test_c', 0, NULL, 1, 1, '2022-05-25 10:34:38', 1, '2022-05-25 10:34:38', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (6, '123', 0, NULL, 1, 1, '2022-05-26 14:21:06', 1, '2022-05-26 14:21:06', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (7, '1', 0, NULL, 1, 1, '2022-05-26 16:53:56', 1, '2022-05-26 16:53:56', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (8, 'long-test', 0, NULL, 1, 1, '2022-05-26 16:54:29', 1, '2022-05-26 16:54:29', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (9, 'test_add', 0, NULL, 1, 1, '2022-05-27 11:49:14', 1, '2022-05-27 11:49:14', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (10, 'test_aaa', 0, NULL, 1, 1, '2022-05-27 12:29:45', 1, '2022-05-27 12:29:45', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (11, 'test_abcd', 0, NULL, 1, 1, '2022-05-27 12:36:04', 1, '2022-05-27 12:36:04', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (12, '122222', 0, NULL, 1, 1, '2022-05-27 16:54:43', 1, '2022-05-27 16:54:43', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (13, '11', 0, NULL, 1, 1, '2022-05-27 18:22:03', 1, '2022-05-27 18:22:03', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (14, '88', 0, NULL, 1, 1, '2022-05-27 18:22:23', 1, '2022-05-27 18:22:23', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (15, '12345', 0, NULL, 1, 1, '2022-05-30 10:24:44', 1, '2022-05-30 10:24:44', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (16, '111', 0, NULL, 1, 1, '2022-05-30 10:24:45', 1, '2022-05-30 10:24:52', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (17, '测试添加事件分析', 0, NULL, 1, 1, '2022-05-30 10:44:13', 1, '2022-05-30 10:44:13', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (18, '概览测试-long', 0, NULL, 1, 1, '2022-06-01 16:01:31', 1, '2022-06-01 16:01:31', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (19, '演示概览', 0, NULL, 1, 1, '2022-06-08 15:01:45', 1, '2022-06-15 15:59:16', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (20, '测试概览组件删除用', 0, NULL, 1, 1, '2022-06-09 10:25:16', 1, '2022-06-15 11:39:56', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (21, '99999', 0, NULL, 1, 1, '2022-06-09 10:29:54', 1, '2022-06-09 10:29:54', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (22, '123', 0, NULL, 1, 1, '2022-06-10 18:30:05', 1, '2022-06-10 18:30:05', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (23, 'XXX分析', 0, NULL, 1, 1, '2022-06-13 10:59:01', 1, '2022-06-13 10:59:01', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (24, '22', 0, NULL, 1, 1, '2022-06-13 15:51:51', 1, '2022-06-13 15:51:51', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (25, '333', 0, NULL, 1, 1, '2022-06-13 15:52:07', 1, '2022-06-13 15:52:07', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (26, '33', 0, NULL, 1, 1, '2022-06-13 15:52:49', 1, '2022-06-13 15:52:49', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (27, 'ppppp', 0, NULL, 1, 1, '2022-06-13 15:53:15', 1, '2022-06-13 15:53:15', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (28, 'mmmm', 0, NULL, 1, 1, '2022-06-13 15:53:39', 1, '2022-06-13 15:53:39', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (29, 'lsw', 0, NULL, 1, 1, '2022-06-15 13:33:48', 1, '2022-06-15 13:33:48', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (30, 'cs1', 0, NULL, 1, 1, '2022-06-15 17:42:25', 1, '2022-06-16 10:00:00', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (31, '与人！@#kjhd113iudshdfoikdxn\'', 0, NULL, 1, 1, '2022-06-16 10:16:39', 1, '2022-06-16 10:16:39', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (32, '概览名称-概览名称-概览名称-概览名称-', 0, NULL, 1, 1, '2022-06-16 14:30:02', 1, '2022-06-16 14:30:02', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (33, '1', 0, NULL, 1, 1, '2022-06-17 11:01:18', 1, '2022-06-17 11:01:18', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (34, '王企鹅', 0, NULL, 1, 1, '2022-06-17 11:19:20', 1, '2022-06-17 11:19:20', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (35, 'A渠道概览', 0, NULL, 1, 0, '2022-06-24 16:08:54', 1, '2022-06-24 17:10:43', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (36, '11111', 0, NULL, 1, 1, '2022-06-28 16:17:36', 1, '2022-06-28 16:17:36', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (37, 'cs1111111111111111111111111111111111111111', 0, NULL, 1, 1, '2022-06-28 16:18:48', 1, '2022-07-06 13:24:17', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (38, 'sadasd', 0, NULL, 1, 1, '2022-06-29 13:34:49', 1, '2022-06-29 13:34:49', 1);
INSERT INTO `t_analysis_overview` (`id`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`, `overview_type`) VALUES (39, 'xuqian概览', 0, NULL, 1, 0, '2022-07-06 17:13:02', 1, '2022-07-06 17:13:02', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_analysis_overview_model_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_overview_model_rel`;
CREATE TABLE `t_analysis_overview_model_rel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `overview_id` bigint DEFAULT NULL COMMENT '概览ID',
  `model_id` bigint DEFAULT NULL COMMENT '分析模型ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_analysis_overview_model_rel
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cdp_passback_config
-- ----------------------------
DROP TABLE IF EXISTS `t_cdp_passback_config`;
CREATE TABLE `t_cdp_passback_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `platform_code` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '平台code',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '平台名称',
  `analysis_event_en` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '埋点CDP事件英文名',
  `platform_event_en` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '对应媒体侧事件英文名',
  `passback_type` tinyint(1) DEFAULT NULL COMMENT '回传方式 0实时 1定时',
  `passback_frequency` tinyint(1) DEFAULT NULL COMMENT '回传频率 0每次 1仅首次',
  `passback_window_time` tinyint(1) DEFAULT NULL COMMENT '回传窗口期',
  `task_rate` double(8,2) DEFAULT NULL COMMENT '定时回传的百分比,50%，时间周期内数据只回传50%的数据',
  `task_execute_cycle` int DEFAULT NULL COMMENT '定时的执行周期，天数',
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除0:否 1:是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='回传配置';

-- ----------------------------
-- Records of t_cdp_passback_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cdp_passback_config_property
-- ----------------------------
DROP TABLE IF EXISTS `t_cdp_passback_config_property`;
CREATE TABLE `t_cdp_passback_config_property` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `analysis_property_en` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '埋点CDP属性英文名',
  `platform_property_en` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '媒体侧属性英文名',
  `config_id` bigint DEFAULT NULL COMMENT '回传配置ID',
  `strategy_id` bigint DEFAULT NULL COMMENT '策略ID',
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除0:否 1:是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='回传配置附加属性';

-- ----------------------------
-- Records of t_cdp_passback_config_property
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cdp_passback_in_ad
-- ----------------------------
DROP TABLE IF EXISTS `t_cdp_passback_in_ad`;
CREATE TABLE `t_cdp_passback_in_ad` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `clickid` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '媒体侧给的广告ID',
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='广告进入记录';

-- ----------------------------
-- Records of t_cdp_passback_in_ad
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cdp_passback_platform
-- ----------------------------
DROP TABLE IF EXISTS `t_cdp_passback_platform`;
CREATE TABLE `t_cdp_passback_platform` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `code` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '平台code',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '平台名称',
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除0:否 1:是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='平台信息';

-- ----------------------------
-- Records of t_cdp_passback_platform
-- ----------------------------
BEGIN;
INSERT INTO `t_cdp_passback_platform` (`id`, `code`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (1, '0001', '巨量引擎', 0, NULL, NULL, 0, '2022-07-05 13:53:57', NULL, '2022-07-05 13:54:02');
INSERT INTO `t_cdp_passback_platform` (`id`, `code`, `name`, `status`, `remarks`, `create_user_id`, `del_flag`, `create_time`, `update_user_id`, `update_time`) VALUES (2, '0002', '百度营销', 0, NULL, NULL, 0, '2022-07-05 13:54:25', NULL, '2022-07-05 13:54:28');
COMMIT;

-- ----------------------------
-- Table structure for t_cdp_passback_record
-- ----------------------------
DROP TABLE IF EXISTS `t_cdp_passback_record`;
CREATE TABLE `t_cdp_passback_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `clickid` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '媒体侧给的广告ID',
  `push_data` longtext COLLATE utf8mb4_general_ci COMMENT '需要回传的数据',
  `old_data` longtext COLLATE utf8mb4_general_ci COMMENT '原始数据',
  `passback_type` tinyint DEFAULT NULL COMMENT '回传方式 0实时 1定时',
  `config_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '回传配置名称',
  `config_id` bigint DEFAULT NULL COMMENT '匹配到的回传配置ID',
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='回传记录';

-- ----------------------------
-- Records of t_cdp_passback_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cdp_passback_strategy
-- ----------------------------
DROP TABLE IF EXISTS `t_cdp_passback_strategy`;
CREATE TABLE `t_cdp_passback_strategy` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '策略名称',
  `field_type` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '回传字段类型string、bool、number、enum',
  `where_uuid` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据筛选,对应数据筛选ID',
  `rule` tinyint DEFAULT NULL COMMENT '回传规则',
  `value` double(8,2) DEFAULT NULL COMMENT '计算值rule为0时，为回传真实数据的百分之{value}',
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除0:否 1:是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='回传策略';

-- ----------------------------
-- Records of t_cdp_passback_strategy
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cdp_passback_task_data
-- ----------------------------
DROP TABLE IF EXISTS `t_cdp_passback_task_data`;
CREATE TABLE `t_cdp_passback_task_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `clickid` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '媒体侧给的广告ID',
  `push_data` longtext COLLATE utf8mb4_general_ci COMMENT '需要回传的数据',
  `old_data` longtext COLLATE utf8mb4_general_ci COMMENT '原始数据',
  `config_id` bigint DEFAULT NULL COMMENT '匹配到的回传配置ID',
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='定时跑批数据暂存';

-- ----------------------------
-- Records of t_cdp_passback_task_data
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
