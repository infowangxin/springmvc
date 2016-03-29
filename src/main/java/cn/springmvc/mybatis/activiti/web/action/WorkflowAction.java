package cn.springmvc.mybatis.activiti.web.action;

//import java.io.File;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.List;
//import java.util.Map;

//import org.activiti.engine.repository.Deployment;
//import org.activiti.engine.repository.ProcessDefinition;
//import org.activiti.engine.task.Comment;
//import org.activiti.engine.task.Task;

//@SuppressWarnings("serial")
public class WorkflowAction /*extends ActionSupport implements ModelDriven<WorkflowBean>*/ {

    /*private WorkflowBean workflowBean = new WorkflowBean();

    @Override
    public WorkflowBean getModel() {
        return workflowBean;
    }

    private WorkflowService workflowService;

    private LeaveBillService leaveBillService;

    public void setLeaveBillService(LeaveBillService leaveBillService) {
        this.leaveBillService = leaveBillService;
    }

    public void setWorkflowService(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    *//**
     * 部署管理首页显示
     * 
     * @return
     *//*
    public String deployHome() {
        // 1:查询部署对象信息，对应表（act_re_deployment）
        List<Deployment> depList = workflowService.findDeploymentList();
        // 2:查询流程定义的信息，对应表（act_re_procdef）
        List<ProcessDefinition> pdList = workflowService.findProcessDefinitionList();
        // 放置到上下文对象中
        ValueContext.putValueContext("depList", depList);
        ValueContext.putValueContext("pdList", pdList);
        return "deployHome";
    }

    *//**
     * 发布流程
     * 
     * @return
     *//*
    public String newdeploy() {
        // 获取页面传递的值
        // 1：获取页面上传递的zip格式的文件，格式是File类型
        File file = workflowBean.getFile();
        // 文件名称
        String filename = workflowBean.getFilename();
        // 完成部署
        workflowService.saveNewDeploye(file, filename);
        return "list";
    }

    *//**
     * 删除部署信息
     *//*
    public String delDeployment() {
        // 1：获取部署对象ID
        String deploymentId = workflowBean.getDeploymentId();
        // 2：使用部署对象ID，删除流程定义
        workflowService.deleteProcessDefinitionByDeploymentId(deploymentId);
        return "list";
    }

    *//**
     * 查看流程图
     * 
     * @throws Exception
     *//*
    public String viewImage() throws Exception {
        // 1：获取页面传递的部署对象ID和资源图片名称
        // 部署对象ID
        String deploymentId = workflowBean.getDeploymentId();
        // 资源图片名称
        String imageName = workflowBean.getImageName();
        // 2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
        InputStream in = workflowService.findImageInputStream(deploymentId, imageName);
        // 3：从response对象获取输出流
        OutputStream out = ServletActionContext.getResponse().getOutputStream();
        // 4：将输入流中的数据读取出来，写到输出流中
        for (int b = -1; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();
        // 将图写到页面上，用输出流写
        return null;
    }

    // 启动流程
    public String startProcess() {
        // 更新请假状态，启动流程实例，让启动的流程实例关联业务
        workflowService.saveStartProcess(workflowBean);
        return "listTask";
    }

    *//**
     * 任务管理首页显示
     * 
     * @return
     *//*
    public String listTask() {
        // 1：从Session中获取当前用户名
        String name = SessionContext.get().getName();
        // 2：使用当前用户名查询正在执行的任务表，获取当前任务的集合List<Task>
        List<Task> list = workflowService.findTaskListByName(name);
        ValueContext.putValueContext("list", list);
        return "task";
    }

    *//**
     * 打开任务表单
     *//*
    public String viewTaskForm() {
        // 任务ID
        String taskId = workflowBean.getTaskId();
        // 获取任务表单中任务节点的url连接
        String url = workflowService.findTaskFormKeyByTaskId(taskId);
        url += "?taskId=" + taskId;
        ValueContext.putValueContext("url", url);
        return "viewTaskForm";
    }

    // 准备表单数据
    public String audit() {
        // 获取任务ID
        String taskId = workflowBean.getTaskId();
        *//** 一：使用任务ID，查找请假单ID，从而获取请假单信息 *//*
        LeaveBill leaveBill = workflowService.findLeaveBillByTaskId(taskId);
        ValueContext.putValueStack(leaveBill);
        *//** 二：已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中 *//*
        List<String> outcomeList = workflowService.findOutComeListByTaskId(taskId);
        ValueContext.putValueContext("outcomeList", outcomeList);
        *//** 三：查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment> *//*
        List<Comment> commentList = workflowService.findCommentByTaskId(taskId);
        ValueContext.putValueContext("commentList", commentList);
        return "taskForm";
    }

    *//**
     * 提交任务
     *//*
    public String submitTask() {
        workflowService.saveSubmitTask(workflowBean);
        return "listTask";
    }

    *//**
     * 查看当前流程图（查看当前活动节点，并使用红色的框标注）
     *//*
    public String viewCurrentImage() {
        // 任务ID
        String taskId = workflowBean.getTaskId();
        *//** 一：查看流程图 *//*
        // 1：获取任务ID，获取任务对象，使用任务对象获取流程定义ID，查询流程定义对象
        ProcessDefinition pd = workflowService.findProcessDefinitionByTaskId(taskId);
        // workflowAction_viewImage?deploymentId=<s:property value='#deploymentId'/>&imageName=<s:property value='#imageName'/>
        ValueContext.putValueContext("deploymentId", pd.getDeploymentId());
        ValueContext.putValueContext("imageName", pd.getDiagramResourceName());
        *//** 二：查看当前活动，获取当期活动对应的坐标x,y,width,height，将4个值存放到Map<String,Object>中 *//*
        Map<String, Object> map = workflowService.findCoordingByTask(taskId);
        ValueContext.putValueContext("acs", map);
        return "image";
    }

    // 查看历史的批注信息
    public String viewHisComment() {
        // 获取清单ID
        Long id = workflowBean.getId();
        // 1：使用请假单ID，查询请假单对象，将对象放置到栈顶，支持表单回显
        LeaveBill leaveBill = leaveBillService.findLeaveBillById(id);
        ValueContext.putValueStack(leaveBill);
        // 2：使用请假单ID，查询历史的批注信息
        List<Comment> commentList = workflowService.findCommentByLeaveBillId(id);
        ValueContext.putValueContext("commentList", commentList);
        return "viewHisComment";
    }*/
}
