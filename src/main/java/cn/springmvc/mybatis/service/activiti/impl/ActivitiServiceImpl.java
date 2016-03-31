package cn.springmvc.mybatis.service.activiti.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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

    @Resource(name = "processEngine")
    private ProcessEngine processEngine;

    @Resource(name = "repositoryService")
    private RepositoryService repositoryService;

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "formService")
    private FormService formService;

    @Resource(name = "historyService")
    private HistoryService historyService;

    @Override
    public List<LeaveBill> findLeaveBillList() {
        return leaveBillMapper.findAll();
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
        List<Deployment> list = repositoryService.createDeploymentQuery()// 创建部署对象查询
            .orderByDeploymenTime().asc()//
            .list();
        return list;
    }

    @Override
    public List<ProcessDefinition> findProcessDefinitionList() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()// 创建流程定义查询
            .orderByProcessDefinitionVersion().asc()//
            .list();
        return list;
    }

    @Override
    public void saveNewDeploye(byte[] bytes, String filename) {
        try {
            // 2：将byte类型的文件转化成ZipInputStream流
            InputStream stream = new ByteArrayInputStream(bytes);
            ZipInputStream zipInputStream = new ZipInputStream(stream);
            repositoryService.createDeployment()// 创建部署对象
                .name(filename)// 添加部署名称
                .addZipInputStream(zipInputStream)//
                .deploy();// 完成部署
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDeploymentId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteProcessDefinitionByDeploymentId(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
    }

    @Override
    public InputStream findImageInputStream(String deploymentId, String imageName) {
        return repositoryService.getResourceAsStream(deploymentId, imageName);
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
