package com.zhuima.stage2.handler;

import ch.qos.logback.classic.Logger;
import com.zhuima.stage2.controller.BookController;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;



@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = (Logger) LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 异常处理,
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ModelAndView handelException(HttpServletRequest request, Exception exception) throws Exception {
        logger.error("Request URL: {}, Exception: {}", request.getRequestURI(), exception.getMessage());

        // 跳过指定状态吗的异常
        if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null) {
            throw exception;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error/error");
        return mav;

    }

}
