package cn.springmvc.mybatis.init;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.springmvc.mybatis.common.constants.Constants;
import cn.springmvc.mybatis.entity.Permission;
import cn.springmvc.mybatis.entity.Role;
import cn.springmvc.mybatis.entity.User;
import cn.springmvc.mybatis.service.PermissionService;
import cn.springmvc.mybatis.service.RoleService;
import cn.springmvc.mybatis.service.UserService;
import cn.springmvc.mybatis.web.util.MenuUtil;

/**
 * @author Vincent.wang
 *
 *         production为生产环境，development为测试环境
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/applicationContext-dao.xml", "classpath:/spring/applicationContext-shiro.xml" })
@ActiveProfiles("development")
public class InitServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 创建角色
     */
    private void addRoles() {
        try {
            Role system = new Role();
            system.setName(Constants.SYSTEM_ROLE_NAME);// 系统管理员
            system.setCode(Constants.SYSTEM_ROLE_CODE);
            system.setRemark(Constants.SYSTEM_ROLE_NAME);
            roleService.addRole(system);

            Role common = new Role();
            common.setName(Constants.COMMON_ROLE_NAME);// 普通用户
            common.setCode(Constants.COMMON_ROLE_CODE);
            common.setRemark(Constants.COMMON_ROLE_NAME);
            roleService.addRole(common);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建菜单
     */
    private void addPermission() {
        try {
            List<Permission> permis = MenuUtil.importPermissionData();
            for (Permission permission : permis) {
                permissionService.addPermission(permission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建用户
     */
    private void addUsers() {
        try {
            Role systemRole = roleService.findRoleByCode(Constants.SYSTEM_ROLE_CODE);// 管理员
            // Role commonRole = roleService.findRoleByCode(Constants.COMMON_ROLE_CODE);// 普通用户
            String password = "guest";

            User admin = new User();
            admin.setName("guest");
            admin.setEmail("infowangxin@163.com");
            admin.setTrueName("管理员");
            admin.setPassword(password);
            userService.addUser(admin, systemRole);

            // User wangxin = new User();
            // wangxin.setName("wangxin");
            // wangxin.setTrueName("王鑫");
            // wangxin.setEmail("infowangxin@139.com");
            // wangxin.setPassword(password);
            // userService.addUser(wangxin, commonRole);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 给角色授权
    private void bindRolePermission() {
        try {
            // 系统管理员
            roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.user);
            // roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.useradd);
            // roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.upload);
            // roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.ajaxupload);
            // roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.springupload);
            // roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.download);
            // roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.zipupload);
            // roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.search);
            // roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.jquery_search);
            roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.news);
            roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.newsadd);
            roleService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.news_search);

            // 普通用户
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.upload);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.ajaxupload);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.springupload);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.download);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.zipupload);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.search);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.jquery_search);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.news);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.newsadd);
            // roleService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.news_search);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void init() {
        try {
            addRoles();
            addPermission();
            addUsers();
            bindRolePermission();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
