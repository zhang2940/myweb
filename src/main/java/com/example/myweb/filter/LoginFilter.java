package com.example.myweb.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.myweb.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.file.Files;

@Component
public class LoginFilter implements HandlerInterceptor {

    private Logger logger= LoggerFactory.getLogger(this.getClass());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("拦截请求，验证token");
        Cookie[] cookies = request.getCookies();

        String token=null;
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if ("token".equals(cookie.getName())){
                    token=cookie.getValue();
                    break;
                }
            }
        }
        if (token==null){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",HttpServletResponse.SC_SEE_OTHER);
            jsonObject.put("msg","请登录");
            PrintWriter out = response.getWriter();
            out.write(jsonObject.toString());
            out.flush();
            out.close();
            return false;
        }
        boolean verify = JwtUtil.getInstance().verify(token);
        if (verify){
            return true;
        }else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",HttpServletResponse.SC_GATEWAY_TIMEOUT);
            jsonObject.put("msg","登录异常，请重新登录");
            PrintWriter out = response.getWriter();
            out.write(jsonObject.toString());
            out.flush();
            out.close();
            return false;
        }


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    //    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.info("拦截器启动");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        logger.info("拦截器拦截到"+servletRequest.getLocalAddr());
//        filterChain.doFilter(servletRequest,servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        logger.info("拦截器拦销毁");
//    }
}
