package cn.springmvc.mybatis.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vincent.wang
 *
 */
@RequestMapping("im")
@Controller
public class IMController {
    
    private static final Logger log = LoggerFactory.getLogger(IMController.class);
    
    @RequestMapping("/openfire")
    public String openfire(){
        log.info("# 进入IM聊天首页");
        return "im/openfire";
    }
    
    

}
