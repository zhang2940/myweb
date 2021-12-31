package com.example.myweb.controller;

import com.example.myweb.pojo.User;
import com.example.myweb.result.ResultStatus;
import com.example.myweb.result.ResultVO;
import com.example.myweb.service.LoginService;
import com.example.myweb.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private RedisUtil redisUtil;


    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public ResultVO login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try{
            subject.login(usernamePasswordToken);
            return ResultVO.success("login");
        }catch (UnknownAccountException unknownAccountException){
            return ResultVO.error(ResultStatus.ERROR_CODE,"用户不存在");
        }catch (IncorrectCredentialsException e){
            return ResultVO.error(ResultStatus.ERROR_CODE,"密码错误");
        }
    }
    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/logins")
    public ResultVO updatePwd(@RequestBody User user){
        Integer count = loginService.updatePwd(user);

        if (count!=0||count!=null){
            return ResultVO.success();
        }
       return ResultVO.error("修改失败");
    }


    @RequestMapping("/findAll")
    public ResultVO Test(@RequestBody User user){
        System.out.println("==========");
        return ResultVO.error("修改失败");
    }


}