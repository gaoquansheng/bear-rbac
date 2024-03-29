-- ----------------------------
-- 1、用户表
-- ----------------------------
drop table if exists sys_user;
create table sys_user
(
    user_id     bigint      not null auto_increment comment '用户ID',
    org_id      bigint comment '部门ID',
    user_name   varchar(64) not null comment '用户账号',
    user_mobile varchar(11) comment '手机号码',
    sex         char(1) comment '用户性别（0男 1女 2未知）',
    avatar      varchar(100) comment '头像地址',
    password    varchar(100) comment '密码',
    status      char(1)  default '0' comment '状态（0正常 1停用）',
    del_flag    char(1)  default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   bigint comment '创建者',
    create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by   bigint comment '更新者',
    update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    primary key (user_id)
) engine = innodb comment = '用户表';
-- ----------------------------
-- 2、用户组表
-- ----------------------------
drop table if exists sys_user_group;
create table sys_user_group
(
    user_group_id   bigint not null auto_increment comment '用户组ID',
    user_group_name varchar(64) comment '用户组名称',
    status          char(1)  default '0' comment '状态（0正常 1停用）',
    del_flag        char(1)  default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by       bigint comment '创建者',
    create_time     datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by       bigint comment '更新者',
    update_time     datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    primary key (user_group_id)
) engine = innodb comment = '用户组表';
-- ----------------------------
-- 3、角色表
-- ----------------------------
drop table if exists sys_role;
create table sys_role
(
    role_id     bigint not null auto_increment comment '角色ID',
    role_name   varchar(64) comment '角色名称',
    order_num   int comment '显示顺序',
    data_scope  char(1)  default '1' comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    status      char(1)  default '0' comment '状态（0正常 1停用）',
    del_flag    char(1)  default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   bigint comment '创建者',
    create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by   bigint comment '更新者',
    update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    primary key (role_id)
) engine = innodb comment = '角色表';
-- ----------------------------
-- 4、权限表
-- ----------------------------
drop table if exists sys_permission;
create table sys_permission
(
    permission_id   bigint not null auto_increment comment '权限ID',
    permission_name varchar(64) comment '权限名称',
    status          char(1)  default '0' comment '状态（0正常 1停用）',
    del_flag        char(1)  default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by       bigint comment '创建者',
    create_time     datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by       bigint comment '更新者',
    update_time     datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    primary key (permission_id)
) engine = innodb comment = '权限表';
-- ----------------------------
-- 5、部门表
-- ----------------------------
drop table if exists sys_org;
create table sys_org
(
    org_id      bigint not null auto_increment comment '部门id',
    pid         bigint comment '父部门id',
    org_name    varchar(64) comment '部门名称',
    order_num   int      default 0 comment '显示顺序',
    status      char(1)  default '0' comment '部门状态（0正常 1停用）',
    del_flag    char(1)  default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   bigint comment '创建者',
    create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by   bigint comment '更新者',
    update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    primary key (org_id)
) engine = innodb comment = '部门表';
-- ----------------------------
-- 6、菜单表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu
(
    menu_id     bigint not null auto_increment comment '菜单ID',
    menu_name   varchar(64) comment '菜单名称',
    pid         bigint comment '父菜单ID',
    order_num   int          default 0 comment '显示顺序',
    component   varchar(255) comment '组件路径',
    menu_type   char(1)      default '' comment '菜单类型（M目录 C菜单 F按钮）',
    icon        varchar(100) default '#' comment '菜单图标',
    perms       varchar(100) comment '权限标识',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    del_flag    char(1)      default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   bigint comment '创建者',
    create_time datetime     DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by   bigint comment '更新者',
    update_time datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    primary key (menu_id)
) engine = innodb comment = '菜单表';
-- ----------------------------
-- 7、用户和角色关联表  用户N-M角色
-- ----------------------------
drop table if exists sys_user_role_rln;
create table sys_user_role_rln
(
    rln_id  bigint not null auto_increment comment '关联id',
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    primary key (rln_id)
) engine = innodb comment = '用户和角色关联表';
-- ----------------------------
-- 8、用户和用户组关联表  用户N-M角色
-- ----------------------------
drop table if exists sys_user_group_rln;
create table sys_user_group_rln
(
    rln_id        bigint not null auto_increment comment '关联id',
    user_id       bigint not null comment '用户ID',
    user_group_id bigint not null comment '用户组ID',
    primary key (rln_id)
) engine = innodb comment = '用户和用户组关联表';
-- ----------------------------
-- 9、用户组和角色关联表  用户N-M角色
-- ----------------------------
drop table if exists sys_user_group_role_rln;
create table sys_user_group_role_rln
(
    rln_id        bigint not null auto_increment comment '关联id',
    user_group_id bigint not null comment '用户组ID',
    role_id       bigint not null comment '角色ID',
    primary key (rln_id)
) engine = innodb comment = '用户组和角色关联表';
-- ----------------------------
-- 7、角色和权限关联表  角色M-N权限
-- ----------------------------
drop table if exists sys_role_permission_rln;
create table sys_role_permission_rln
(
    rln_id        bigint not null auto_increment comment '关联id',
    permission_id bigint not null comment '权限ID',
    role_id       bigint not null comment '角色ID',
    primary key (rln_id)
) engine = innodb comment = '角色和权限关联表';

-- ----------------------------
-- 7、权限和菜单关联表  权限M-N菜单
-- ----------------------------
drop table if exists sys_permission_menu_rln;
create table sys_permission_menu_rln
(
    rln_id        bigint not null auto_increment comment '关联id',
    menu_id       bigint not null comment '菜单ID',
    permission_id bigint not null comment '权限id',
    primary key (rln_id)
) engine = innodb comment = '权限和菜单关联表';


-- ----------------------------
-- 8、生成表
-- ----------------------------

CREATE TABLE `gen_table`
(
    `table_id`        bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_name`      varchar(200)  DEFAULT '' COMMENT '表名称',
    `table_comment`   varchar(500)  DEFAULT '' COMMENT '表描述',
    `class_name`      varchar(100)  DEFAULT '' COMMENT '实体类名称',
    `package_name`    varchar(100)  DEFAULT NULL COMMENT '生成包路径',
    `module_name`     varchar(30)   DEFAULT NULL COMMENT '生成模块名',
    `business_name`   varchar(30)   DEFAULT NULL COMMENT '生成业务名',
    `function_name`   varchar(50)   DEFAULT NULL COMMENT '生成功能名',
    `function_author` varchar(50)   DEFAULT NULL COMMENT '生成功能作者',
    `gen_type`        char(1)       DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
    `gen_path`        varchar(200)  DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
    `options`         varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
    `create_by`       varchar(64)   DEFAULT '' COMMENT '创建者',
    `create_time`     datetime      DEFAULT NULL COMMENT '创建时间',
    `update_by`       varchar(64)   DEFAULT '' COMMENT '更新者',
    `update_time`     datetime      DEFAULT NULL COMMENT '更新时间',
    `remark`          varchar(500)  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`table_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='代码生成业务表';

-- ----------------------------
-- 9、生成表字段
-- ----------------------------

CREATE TABLE `gen_table_column`
(
    `column_id`      bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_id`       bigint       DEFAULT NULL COMMENT '归属表编号',
    `column_name`    varchar(200) DEFAULT NULL COMMENT '列名称',
    `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
    `column_type`    varchar(100) DEFAULT NULL COMMENT '列类型',
    `java_type`      varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
    `java_field`     varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
    `is_pk`          char(1)      DEFAULT NULL COMMENT '是否主键（1是）',
    `is_increment`   char(1)      DEFAULT NULL COMMENT '是否自增（1是）',
    `is_required`    char(1)      DEFAULT NULL COMMENT '是否必填（1是）',
    `is_insert`      char(1)      DEFAULT NULL COMMENT '是否为插入字段（1是）',
    `is_edit`        char(1)      DEFAULT NULL COMMENT '是否编辑字段（1是）',
    `is_list`        char(1)      DEFAULT NULL COMMENT '是否列表字段（1是）',
    `is_query`       char(1)      DEFAULT NULL COMMENT '是否查询字段（1是）',
    `query_type`     varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
    `html_type`      varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    `dict_type`      varchar(200) DEFAULT '' COMMENT '字典类型',
    `sort`           int          DEFAULT NULL COMMENT '排序',
    `create_by`      varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`column_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='代码生成业务表字段';








