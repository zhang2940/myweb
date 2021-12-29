package com.example.myweb.controller;

import com.example.myweb.pojo.User;
import com.example.myweb.result.ResultVO;
import com.example.myweb.service.LoginService;
import com.example.myweb.util.RedisUtil;
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
        User login = loginService.login(user);
        return ResultVO.success(login);

    }
    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public ResultVO updatePwd(@RequestBody User user){
        Integer count = loginService.updatePwd(user);
        if (count!=0||count!=null){
            return ResultVO.success();
        }
       return ResultVO.error("修改失败");
    }


}