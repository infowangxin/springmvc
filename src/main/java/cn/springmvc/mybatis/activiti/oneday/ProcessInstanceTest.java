package cn.springmvc.mybatis.activiti.oneday;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessInstanceTest {

    private static final Logger log = LoggerFactory.getLogger(ProcessInstanceTest.class);

    // 工作流引擎对象
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();// 这行代码默认加载activiti.cfg.xml

    // 部署流程定义
    @Test
    public void deploymentProcessDefinition_zip() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/helloworld.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);

        // 主要是ACT_RE打头的表
        RepositoryService repositoryService = processEngine.getRepositoryService();// 与流程定义部署对象相关的service

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();// 创建一个部署对象
        deploymentBuilder.name("流程定义");// 添加部署的名称
        deploymentBuilder.addZipInputStream(zipInputStream);// 指定zip格式的文件完成部署

        Deployment deployment = deploymentBuilder.deploy();// 部署完成

        log.debug("#部署ID:{}", deployment.getId());// 7501
        log.debug("#部署名称:{}", deployment.getName());// 流程定义
    }

}
