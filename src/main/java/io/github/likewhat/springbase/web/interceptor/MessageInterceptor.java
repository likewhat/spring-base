package io.github.likewhat.springbase.web.interceptor;

import io.github.likewhat.springbase.model.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MessageInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MessageInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();

        Object candidate = session.getAttribute(Constants.SYSTEM_MESSAGE);
        if (candidate != null) {
            logger.info("Removing message in session after request handled.");
            
            request.setAttribute(Constants.MESSAGE, candidate);
            session.removeAttribute(Constants.SYSTEM_MESSAGE);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
