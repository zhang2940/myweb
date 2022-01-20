package com.example.myweb.controller;

import com.example.myweb.pojo.User;
import com.example.myweb.result.ResultStatus;
import com.example.myweb.result.ResultVO;
import com.example.myweb.service.LoginService;
import com.example.myweb.util.JwtUtil;
import com.example.myweb.util.RabbitMQUtil;
import com.example.myweb.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RabbitMQUtil rabbitMQUtil;


    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultVO login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(new Md5Hash(user.getUsername()).toString(),new Md5Hash(user.getPassword()).toString());
        try{
            subject.login(usernamePasswordToken);
            HashMap<String, String> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("username",usernamePasswordToken.getUsername());
//            设置token过期时间为30分钟
            String token = JwtUtil.getInstance().create("token", objectObjectHashMap, "用户信息", new Date(System.currentTimeMillis() + (1800 * 1000)), new Date());
            Cookie cookie=new Cookie("token",token);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return ResultVO.success();
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
    @RequestMapping(value = "/upd_pwd",method = RequestMethod.POST)
    public ResultVO updatePwd(@RequestBody User user){
        Integer count = loginService.updatePwd(user);

        if (count!=0||count!=null){
            return ResultVO.success();
        }
       return ResultVO.error("修改失败");
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/region",method = RequestMethod.POST)
    public ResultVO region(@RequestBody User user){
        Integer region = loginService.region(user);
        if (region==0||region==null){
            return ResultVO.error("注册失败");
        }
        return ResultVO.success("注册成功");
    }
    /**
     * 注册用户
     * @param
     * @return
     */
    @RequestMapping(value = "/sendmsg",method = RequestMethod.POST)
    public ResultVO sendmsg(){
        System.out.println(rabbitMQUtil.sendMsg());

        return ResultVO.success();
    }

}