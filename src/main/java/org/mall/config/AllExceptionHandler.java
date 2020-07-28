package org.mall.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//表明这是注解类
@ControllerAdvice
public class AllExceptionHandler {
    public static final String ERROR_VIEW = "thymeleaf/error/error";

    @ExceptionHandler(value = Exception.class)
    public Object allErrorHandler(HttpServletRequest request,
                                  HttpServletResponse response, Exception exception) throws Exception {
        exception.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", exception);
        mav.setViewName(ERROR_VIEW);
        return mav;
    }

//    /**
//     * 捕捉shiro的异常
//     * */
//    @ExceptionHandler(ShiroException.class)
//    public ResponseMsg handle401() {
//        return new ResponseMsg("401" , "您没有权限访问！");
//    }
//
//    /**
//     * 捕捉其他所有异常
//     * */
//    @ExceptionHandler(Exception.class)
//    public ResponseMsg globalException(HttpServletRequest request, Throwable ex) {
//        if (ex instanceof NoHandlerFoundException) {
//            return new ResponseMsg("404" , "未找到页面: " + ex.getMessage());
//        }
//        else{
//            return new ResponseMsg("500" , "访问出错，无法访问: " + ex.getMessage());
//        }
//    }
//
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
}
