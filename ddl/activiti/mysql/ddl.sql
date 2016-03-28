-- drop table a_employee;
create table `a_employee`(
	`id` varchar(32) primary key not null,
	`name` varchar(100) ,
  `password` varchar(100) , 
  `email` varchar(100) ,
  `role` varchar(100) , 
  `manager_id` varchar(32)
);

-- drop table a_leavebill;
create table `a_leavebill`(
	`id` varchar(32) primary key not null, 
	`days` integer ,
  `content` varchar(500) , 
  `leaveDate` timestamp,
  `state` integer
);

insert a_employee(id,name,password,email,role,manager_id) values( 1,'王中军','123','wangzhongjun@163.com','boss',NULL );
insert a_employee(id,name,password,email,role,manager_id) values( 2,'冯小刚经纪人','123','fengxiaogangManager@163.com','manager',1 );
insert a_employee(id,name,password,email,role,manager_id) values( 3,'范冰冰经纪人','123','fanbingbingManager@163.com','manager',1);
insert a_employee(id,name,password,email,role,manager_id) values( 4,'冯小刚','123','fengxiaogang@163.com','user',2 );
insert a_employee(id,name,password,email,role,manager_id) values( 5,'范冰冰','123','fanbingbing@163.com','user',3);
