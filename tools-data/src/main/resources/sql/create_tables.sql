-- ----------------------------
-- 创建相关数据表
-- ----------------------------
create table if not exists pt_node
(
    id          bigint unsigned                    not null comment '主键 id'
        primary key,
    name        varchar(256)                       not null comment '站点名称',
    alias       varchar(128)                       null comment '站点别名',
    code        varchar(128)                       not null comment '站点编码',
    description varchar(1024)                      null comment '站点描述',
    url         varchar(128)                       not null comment '站点 URL',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted  tinyint  default 0                 not null comment '是否删除',
    constraint uk_code_is_deleted
        unique (code, is_deleted)
) comment 'PT 站点信息';

create table if not exists pt_user_info
(
    id            bigint unsigned                    not null comment '主键 id'
        primary key,
    user_id       varchar(256)                       not null comment '用户 id',
    passkey       varchar(128)                       not null comment '密钥',
    register_date datetime                           null comment '注册日期',
    email         varchar(256)                       null comment '邮箱',
    cookies       json                               null comment '用户 Cookie',
    create_time   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted    tinyint  default 0                 not null comment '是否删除',
    constraint uk_code_is_deleted
        unique (user_id, is_deleted)
) comment 'PT 用户信息';

