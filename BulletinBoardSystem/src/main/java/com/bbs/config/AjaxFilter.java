package com.bbs.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class AjaxFilter extends AdviceFilter {
    /**
     * 前处理,这里用于判断这此请求是不是ajax请求
     *
     */
    @Override
    protected boolean preHandle(ServletRequest req, ServletResponse resp)
            throws Exception {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //允许跨域,不能放在postHandle内
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
        }
        //获取请求头中的信息，ajax请求最大的特点就是请求头中多了 X-Requested-With 参数
        String requestType = request.getHeader("X-Requested-With");
//        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
        System.out.println(request.getMethod());
        if ((request.getMethod()).equals("OPTIONS")){
            System.out.println("是Ajax请求");
            return true;
        }
        if("XMLHttpRequest".equals(requestType)){
            System.out.println("本次请求是AJAX请求!");
            //现在的用途就是判断当前用户是否被认证
            //先获取到由Web容器管理的subject对象
            Subject subject = SecurityUtils.getSubject();
            //判断是否已经认证
            boolean isAuthc = subject.isAuthenticated();
            if(!isAuthc){
                System.out.println("当前账户使用Shiro认证失败!");
                //如果当前账户没有被认证,本次请求被驳回，ajax进入error的function中进行重定向到登录界面。
                return false;
            }
            return true;
        }else{
            //如果请求类型不是ajax，那么此时requestType为null
            System.out.println("非AJAX请求!");
        }
        //默认返回的是true，将本次请求继续执行下去
        return super.preHandle(request, response);

    }

    /**
     * 后处理，类似于AOP中的后置返回增强
     * 在拦截器链执行完成后执行
     * 一般用于记录时间。
     */
    @Override
    protected void postHandle(ServletRequest request, ServletResponse response)
            throws Exception {
        super.postHandle(request, response);
    }

    /**
     * 最终处理，一定会执行的，一般用于释放资源。
     * 先留着
     */
    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
            throws Exception {
        super.afterCompletion(request, response, exception);
    }
}
