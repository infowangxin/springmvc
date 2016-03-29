package cn.springmvc.mybatis.service.activiti.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.mybatis.entity.activiti.LeaveBill;
import cn.springmvc.mybatis.entity.activiti.WorkflowBean;
import cn.springmvc.mybatis.mapper.activiti.LeaveBillMapper;
import cn.springmvc.mybatis.service.activiti.ActivitiService;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private LeaveBillMapper leaveBillMapper;

    @Override
    public List<LeaveBill> findLeaveBillList() {
        Map<String, Object> paramMap = null;
        return leaveBillMapper.findAllByFilter(paramMap);
    }

    @Override
    public LeaveBill findLeaveBillById(String leaveBillId) {
        return leaveBillMapper.findById(leaveBillId);
    }

    @Override
    public void saveLeaveBill(LeaveBill leaveBill) {
        leaveBillMapper.insert(leaveBill);
    }

    @Override
    public void deleteLeaveBillById(String leaveBillId) {
        leaveBillMapper.delete(leaveBillId);
    }

    @Override
    public List<Deployment> findDeploymentList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProcessDefinition> findProcessDefinitionList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveNewDeploye(byte[] bytes, String filename) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getDeploymentId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteProcessDefinitionByDeploymentId(String deploymentId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public InputStream findImageInputStream(String deploymentId, String imageName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveStartProcess(WorkflowBean workflowBean) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Task> findTaskListByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String findTaskFormKeyByTaskId(String taskId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LeaveBill findLeaveBillByTaskId(String taskId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> findOutComeListByTaskId(String taskId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Comment> findCommentByTaskId(String taskId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveSubmitTask(WorkflowBean workflowBean) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> findCoordingByTask(String taskId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Comment> findCommentByLeaveBillId(String leaveBillId) {
        // TODO Auto-generated method stub
        return null;
    }

}
