package cn.springmvc.mybatis.service.activiti.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.mybatis.entity.activiti.LeaveBill;
import cn.springmvc.mybatis.entity.activiti.WorkflowBean;
import cn.springmvc.mybatis.entity.auth.User;
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
    public LeaveBill findLeaveBillById(Long leaveBillId) {
        return leaveBillMapper.findById(leaveBillId);
    }

    @Override
    public void saveLeaveBill(LeaveBill leaveBill) {
        if(leaveBill!=null && null!=leaveBill.getId()){
            leaveBill.setLeaveDate(Calendar.getInstance().getTime());
            leaveBillMapper.update(leaveBill);
        }else{
//            leaveBill.setId("1");
            leaveBillMapper.insert(leaveBill);
        }
    }

    @Override
    public void deleteLeaveBillById(Long leaveBillId) {
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
    public void saveStartProcess(WorkflowBean workflowBean,User user) {
      //1：获取请假单ID，使用请假单ID，查询请假单的对象LeaveBill
        Long id = workflowBean.getId();
        LeaveBill leaveBill = leaveBillMapper.findById(id);
        //2：更新请假单的请假状态从0变成1（初始录入-->审核中）
        leaveBill.setState(1);
        leaveBillMapper.update(leaveBill);
        
        //3：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
        String key = leaveBill.getClass().getSimpleName();
        /**
         * 4：从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人
                * inputUser是流程变量的名称，
                * 获取的办理人是流程变量的值
         */
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("inputUser", user.getName());//表示惟一用户
        /**
         * 5：   (1)使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
                (2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
         */
        //格式：LeaveBill.id的形式（使用流程变量）
        String objId = key+"."+id;
        variables.put("objId", objId);
        //6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
        runtimeService.startProcessInstanceByKey(key,objId,variables);
    }

    @Override
    public List<Task> findTaskListByName(String name) {
        List<Task> list = taskService.createTaskQuery()//
                .taskAssignee(name)// 指定个人任务查询
                .orderByTaskCreateTime().asc()//
                .list();
            return list;
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
    public List<Comment> findCommentByLeaveBillId(Long leaveBillId) {
        // TODO Auto-generated method stub
        return null;
    }

}
