package org.mall.filter;


import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//@WebFilter(urlPatterns = "/*", filterName = "logFilter2")
//如果使用上面这个注解的话，需要在application中添加filter扫描包 不用直接的话 就在config注解类中实现
public class MyFilter01 implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/err", "/user/login", "", "/no_authority")));

    //    下面通过过滤器来实现记录请求执行时间的功能
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String id = null;
        String password = null;

//        先筛选是不是不用过滤的
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);

        if (allowedPath == false) {
            if (path.contains("/static/")) {
                allowedPath = true;
            }
        }

        if (allowedPath) {
//            这是不用过滤的
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
//                如果没有cookie
                response.sendRedirect("/no_authority");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
//                有cookie
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userId")) {
                        id = cookie.getValue();
                    }
                    if (cookie.getName().equals("userPassword")) {
                        password = cookie.getValue();
                    }
                }
                if (id != null && password != null) {
                    if (id.equals("1") && password.equals("123")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        response.sendRedirect("/no_authority");
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                } else {
                    response.sendRedirect("/no_authority");
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }

//        测试全部可以的
//        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("请求执行时间=" + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void destroy() {

    }
}