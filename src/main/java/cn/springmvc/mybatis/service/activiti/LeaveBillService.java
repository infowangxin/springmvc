package cn.springmvc.mybatis.service.activiti;

import java.util.List;

import cn.springmvc.mybatis.entity.activiti.LeaveBill;

public interface LeaveBillService {

    List<LeaveBill> findLeaveBillList();

    void saveLeaveBill(LeaveBill leaveBill);

    LeaveBill findLeaveBillById(String id);

    void deleteLeaveBillById(String id);

}
