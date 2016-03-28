package cn.springmvc.mybatis.entity.activiti;

import java.util.Date;

import cn.springmvc.mybatis.entity.BaseEntity;

/**
 * 请假单
 */
public class LeaveBill implements BaseEntity<String> {

    private static final long serialVersionUID = -5400999970408251440L;
    private String id;
    private Integer days;// 请假天数
    private String content;// 请假内容
    private Date leaveDate = new Date();// 请假时间
    private String remark;// 备注
    private String userId;// 请假人

    private Integer state = 0;// 请假单状态 0初始录入,1.开始审批,2为审批完成

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
