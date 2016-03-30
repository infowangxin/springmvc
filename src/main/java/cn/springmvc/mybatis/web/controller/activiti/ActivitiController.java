package cn.springmvc.mybatis.web.controller.activiti;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.springmvc.mybatis.entity.activiti.LeaveBill;
import cn.springmvc.mybatis.entity.activiti.WorkflowBean;
import cn.springmvc.mybatis.service.activiti.ActivitiService;
import cn.springmvc.mybatis.web.util.WebUtil;

@Controller
@RequestMapping(value = "activiti")
public class ActivitiController {

    // private static final Logger log = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private ActivitiService activitiService;

    // @Autowired
    // private AuthService authService;

    /**
     * 请假管理首页显示
     * 
     * @return
     */
    @RequestMapping(value = "home")
    public String home(Model model) {
        // 1：查询所有的请假信息（对应a_leavebill），返回List<LeaveBill>
        List<LeaveBill> list = activitiService.findLeaveBillList();
        // 放置到上下文对象中
        model.addAttribute("list", list);
        return "activiti/home";
    }

    /**
     * 添加请假申请
     * 
     * @return
     */
    @RequestMapping(value = "input")
    public String input(Model model) {
        // 1：获取请假单ID
        String leaveBillId = null;
        // 修改
        if (StringUtils.isNoneBlank(leaveBillId)) {

            // 2：使用请假单ID，查询请假单信息，
            LeaveBill bill = activitiService.findLeaveBillById(leaveBillId);
            // 3：将请假单信息放置到栈顶，页面使用struts2的标签，支持表单回显
            // ValueContext.putValueStack(bill);
        }
        // 新增
        return "activiti/input";
    }

    /**
     * 保存/更新，请假申请
     * 
     */
    @RequestMapping(value = "save")
    public String save(@ModelAttribute("leaveBill") LeaveBill leaveBill) {
        // 执行保存
        activitiService.saveLeaveBill(leaveBill);
        return "activiti/save";
    }

    /**
     * 删除，请假申请
     * 
     */
    @RequestMapping(value = "delete")
    public String delete(@RequestParam("leaveBillId") String leaveBillId, Model model) {
        // 1：获取请假单ID
        // 执行删除
        activitiService.deleteLeaveBillById(leaveBillId);
        return "activiti/save";
    }

    // ==================以下方法为与工作流相关方法=========================================

    /**
     * 部署管理首页显示
     * 
     * @return
     */
    @RequestMapping(value = "deployHome")
    public String deployHome(Model model) {
        // 1:查询部署对象信息，对应表（act_re_deployment）
        List<Deployment> depList = activitiService.findDeploymentList();
        // 2:查询流程定义的信息，对应表（act_re_procdef）
        List<ProcessDefinition> pdList = activitiService.findProcessDefinitionList();
        // 放置到上下文对象中
        model.addAttribute("depList", depList);
        model.addAttribute("pdList", pdList);
        return "activiti/deployHome";
    }

    /**
     * 发布流程
     * 
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "newdeploy", method = RequestMethod.POST)
    public String newdeploy(@RequestParam("bpmnZip") MultipartFile bpmnZip, @RequestParam("processDefinitionName") String processDefinitionName, Model model) throws IOException {

        // 获取页面传递的值
        // 1：获取页面上传递的zip格式的文件，格式是File类型
        byte bytes[] = bpmnZip.getBytes();
        // 文件名称
        // String filename = bpmnZip.getName();
        // 完成部署
        activitiService.saveNewDeploye(bytes, processDefinitionName);
        return "redirect:/activiti/deployHome";
    }

    /**
     * 删除部署信息
     */
    @RequestMapping(value = "delDeployment")
    public String delDeployment(@RequestParam("deploymentId") String deploymentId) {
        // 1：获取部署对象ID
        // String deploymentId = activitiService.getDeploymentId();
        // 2：使用部署对象ID，删除流程定义
        activitiService.deleteProcessDefinitionByDeploymentId(deploymentId);
        return "list";
    }

    /**
     * 查看流程图
     * 
     * @throws Exception
     */
    @RequestMapping(value = "viewImage")
    public String viewImage(@RequestParam("imageName") String imageName, HttpServletResponse response) throws Exception {
        // 1：获取页面传递的部署对象ID和资源图片名称
        // 部署对象ID
        String deploymentId = activitiService.getDeploymentId();
        // 资源图片名称
        // String imageName = workflowBean.getImageName();
        // 2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
        InputStream in = activitiService.findImageInputStream(deploymentId, imageName);
        // 3：从response对象获取输出流
        OutputStream out = response.getOutputStream();
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
    @RequestMapping(value = "startProcess")
    public String startProcess(@ModelAttribute("workflowBean") WorkflowBean workflowBean) {
        // 更新请假状态，启动流程实例，让启动的流程实例关联业务
        activitiService.saveStartProcess(workflowBean);
        return "listTask";
    }

    /**
     * 任务管理首页显示
     * 
     * @return
     */
    @RequestMapping(value = "listTask")
    public String listTask(Model model) {
        // 1：从Session中获取当前用户名
        String name = WebUtil.getUser().getName();
        // 2：使用当前用户名查询正在执行的任务表，获取当前任务的集合List<Task>
        List<Task> list = activitiService.findTaskListByName(name);
        model.addAttribute("list", list);
        return "task";
    }

    /**
     * 打开任务表单
     */
    @RequestMapping(value = "viewTaskForm")
    public String viewTaskForm(@RequestParam("taskId") String taskId, Model model) {
        // 任务ID
        // String taskId = workflowBean.getTaskId();
        // 获取任务表单中任务节点的url连接
        String url = activitiService.findTaskFormKeyByTaskId(taskId);
        url += "?taskId=" + taskId;
        model.addAttribute("url", url);
        return "viewTaskForm";
    }

    // 准备表单数据
    @RequestMapping(value = "audit")
    public String audit(@RequestParam("taskId") String taskId, Model model) {
        // 获取任务ID
        // String taskId = workflowBean.getTaskId();
        /** 一：使用任务ID，查找请假单ID，从而获取请假单信息 */
        LeaveBill leaveBill = activitiService.findLeaveBillByTaskId(taskId);
        // ValueContext.putValueStack(leaveBill);
        /** 二：已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中 */
        List<String> outcomeList = activitiService.findOutComeListByTaskId(taskId);
        model.addAttribute("outcomeList", outcomeList);
        /** 三：查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment> */
        List<Comment> commentList = activitiService.findCommentByTaskId(taskId);
        model.addAttribute("commentList", commentList);
        return "taskForm";
    }

    /**
     * 提交任务
     */
    @RequestMapping(value = "submitTask")
    public String submitTask(@ModelAttribute("workflowBean") WorkflowBean workflowBean) {
        activitiService.saveSubmitTask(workflowBean);
        return "listTask";
    }

    /**
     * 查看当前流程图（查看当前活动节点，并使用红色的框标注）
     */
    @RequestMapping(value = "viewCurrentImage")
    public String viewCurrentImage(@RequestParam("taskId") String taskId, Model model) {
        // 任务ID
        // String taskId = workflowBean.getTaskId();
        /** 一：查看流程图 */
        // 1：获取任务ID，获取任务对象，使用任务对象获取流程定义ID，查询流程定义对象
        ProcessDefinition pd = activitiService.findProcessDefinitionByTaskId(taskId);
        // workflowAction_viewImage?deploymentId=<s:property value='#deploymentId'/>&imageName=<s:property value='#imageName'/>
        model.addAttribute("deploymentId", pd.getDeploymentId());
        model.addAttribute("imageName", pd.getDiagramResourceName());
        /** 二：查看当前活动，获取当期活动对应的坐标x,y,width,height，将4个值存放到Map<String,Object>中 */
        Map<String, Object> map = activitiService.findCoordingByTask(taskId);
        model.addAttribute("acs", map);
        return "image";
    }

    // 查看历史的批注信息
    @RequestMapping(value = "viewHisComment")
    public String viewHisComment(@RequestParam("leaveBillId") String leaveBillId, Model model) {
        // 获取清单ID
        // Long id = workflowBean.getId();
        // 1：使用请假单ID，查询请假单对象，将对象放置到栈顶，支持表单回显
        LeaveBill leaveBill = activitiService.findLeaveBillById(leaveBillId);
        // ValueContext.putValueStack(leaveBill);
        // 2：使用请假单ID，查询历史的批注信息
        List<Comment> commentList = activitiService.findCommentByLeaveBillId(leaveBillId);
        model.addAttribute("commentList", commentList);
        return "viewHisComment";
    }

}
