-- ----------------------------
-- 1、用户表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id           varchar(64)      not null     comment '用户ID',
  org_id           varchar(64)      default null               comment '部门ID',
  user_name         varchar(30)     not null                   comment '用户账号',
  user_mobile       varchar(11)     default ''                 comment '手机号码',
  sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
  avatar            varchar(100)    default ''                 comment '头像地址',
  password          varchar(100)    default ''                 comment '密码',
  status        char(1)         default '0'                comment '状态（0正常 1停用）',
  flag          char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime     DEFAULT CURRENT_TIMESTAMP      comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP      comment '更新时间',
  primary key (user_id)
) engine=innodb comment = '用户表';
-- ----------------------------
-- 2、用户组表
-- ----------------------------
drop table if exists sys_user_group;
create table sys_user_group (
  group_id          varchar(64)      not null     comment '用户组ID',
  group_name           varchar(64)      default null               comment '用户组名称',
  status        char(1)         default '0'                comment '状态（0正常 1停用）',
  flag          char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime     DEFAULT CURRENT_TIMESTAMP      comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP      comment '更新时间',
  primary key (group_id)
) engine=innodb comment = '用户组表';
-- ----------------------------
-- 3、角色表
-- ----------------------------
drop table if exists role;
create table role (
  role_id              varchar(64)      not null     comment '角色ID',
  role_name            varchar(30)     not null                   comment '角色名称',
  order            int(4)          not null                   comment '显示顺序',
  data_scope           char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  status               char(1)         not null                   comment '状态（0正常 1停用）',
  del_flag             char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by            varchar(64)     default ''                 comment '创建者',
  create_time          datetime                                   comment '创建时间',
  update_by            varchar(64)     default ''                 comment '更新者',
  update_time          datetime                                   comment '更新时间',
  remark               varchar(500)    default null               comment '备注',
  primary key (role_id)
) engine=innodb comment = '角色表';
-- ----------------------------
-- 4、权限表
-- ----------------------------
drop table if exists permission;
create table permission (
  permission_id              varchar(64)      not null     comment '权限ID',
  permission_name            varchar(30)     not null                   comment '权限名称',
  status               char(1)         not null                   comment '状态（0正常 1停用）',
  del_flag             char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by            varchar(64)     default ''                 comment '创建者',
  create_time          datetime                                   comment '创建时间',
  update_by            varchar(64)     default ''                 comment '更新者',
  update_time          datetime                                   comment '更新时间',
  remark               varchar(500)    default null               comment '备注',
  primary key (role_id)
) engine=innodb comment = '角色表';
-- ----------------------------
-- 5、部门表
-- ----------------------------
drop table if exists org;
create table org (
  org_id            varchar(64)      not null     comment '部门id',
  p_id              varchar(64)     default 'root'                  comment '父部门id',
  org_name         varchar(30)     default ''                 comment '部门名称',
  order         int(4)          default 0                  comment '显示顺序',
  status        char(1)         default '0'                comment '部门状态（0正常 1停用）',
  flag          char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime     DEFAULT CURRENT_TIMESTAMP      comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP      comment '更新时间',
  primary key (org_id)
) engine=innodb  comment = '部门表';
-- ----------------------------
-- 6、菜单表
-- ----------------------------
drop table if exists menu;
create table menu (
  menu_id           varchar(64)      not null    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  p_id         varchar(64)      default 'root'                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  component         varchar(255)    default null               comment '组件路径',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  icon              varchar(100)    default '#'                comment '菜单图标',
  perms             varchar(100)    default null               comment '权限标识',
  status        char(1)         default '0'                comment '状态（0正常 1停用）',
  flag          char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime     DEFAULT CURRENT_TIMESTAMP      comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP      comment '更新时间',
  primary key (menu_id)
) engine=innodb comment = '菜单权限表';
-- ----------------------------
-- 7、用户和角色关联表  用户N-M角色
-- ----------------------------
drop table if exists user_role_rln;
create table user_role_rln (
  id   varchar(64) not null comment '关联id',
  user_id   varchar(64) not null comment '用户ID',
  role_id   varchar(64) not null comment '角色ID',
  primary key(id)
) engine=innodb comment = '用户和角色关联表';
-- ----------------------------
-- 8、用户和用户组关联表  用户N-M角色
-- ----------------------------
drop table if exists user_role_rln;
create table user_group_rln (
  id   varchar(64) not null comment '关联id',
  user_id   varchar(64) not null comment '用户ID',
  group_id   varchar(64) not null comment '用户组ID',
  primary key(id)
) engine=innodb comment = '用户和用户组关联表';
-- ----------------------------
-- 9、用户组和角色关联表  用户N-M角色
-- ----------------------------
drop table if exists user_group_rln;
create table user_group_rln (
  id   varchar(64) not null comment '关联id',
  role   varchar(64) not null comment '角色ID',
  group_id   varchar(64) not null comment '用户组ID',
  primary key(id)
) engine=innodb comment = '用户和用户组关联表';
-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  id   varchar(64) not null comment '关联id',
  role_id   bigint(20) not null comment '角色ID',
  permission_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';
-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  id   varchar(64) not null comment '关联id',
  permission_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';








