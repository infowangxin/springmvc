create table `a_employee` (
  `id` bigint(20) primary key not null auto_increment,
  `name` varchar(255) default null,
  `password` varchar(255) default null,
  `email` varchar(255) default null,
  `role` varchar(255) default null,
  `manager_id` bigint(20) default null
) engine=innodb default charset=utf8;


create table `a_leavebill` (
  `id` bigint(20) primary key not null auto_increment,
  `days` int(11) default null,
  `content` varchar(255) default null,
  `remark` varchar(255) default null,
  `leavedate` datetime default null,
  `state` int(11) default null,
  `user_id` bigint(20) default null
) engine=innodb default charset=utf8;

insert a_employee(id,name,password,email,role,manager_id) values( 1,'王中军','123','wangzhongjun@163.com','boss',NULL );
insert a_employee(id,name,password,email,role,manager_id) values( 2,'冯小刚经纪人','123','fengxiaogangManager@163.com','manager',1 );
insert a_employee(id,name,password,email,role,manager_id) values( 3,'范冰冰经纪人','123','fanbingbingManager@163.com','manager',1);
insert a_employee(id,name,password,email,role,manager_id) values( 4,'冯小刚','123','fengxiaogang@163.com','user',2 );
insert a_employee(id,name,password,email,role,manager_id) values( 5,'范冰冰','123','fanbingbing@163.com','user',3);
