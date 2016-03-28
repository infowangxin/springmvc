package cn.springmvc.mybatis.mapper.activiti;

import java.util.List;

import cn.springmvc.mybatis.entity.activiti.LeaveBill;
import cn.springmvc.mybatis.mapper.BaseMapper;

public interface LeaveBillMapper extends BaseMapper<String, LeaveBill> {

    List<LeaveBill> findLeaveBillList();

    void saveLeaveBill(LeaveBill leaveBill);

    void updateLeaveBill(LeaveBill leaveBill);

    LeaveBill findLeaveBillById(String id);

}
