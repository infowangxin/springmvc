package cn.springmvc.mybatis.activiti.service;

import java.util.List;

import cn.springmvc.mybatis.entity.activiti.LeaveBill;

public interface ILeaveBillService {

    List<LeaveBill> findLeaveBillList();

    void saveLeaveBill(LeaveBill leaveBill);

    LeaveBill findLeaveBillById(Long id);

    void deleteLeaveBillById(Long id);

}
