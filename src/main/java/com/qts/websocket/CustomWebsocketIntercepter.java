package com.qts.websocket;

import com.github.pagehelper.util.StringUtil;
import com.qts.pojo.User;
import com.qts.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @description: websocket拦截器
 * @author: duanyashu
 * @time: 2020-07-03 14:27
 */
public class CustomWebsocketIntercepter extends HttpSessionHandshakeInterceptor {

    /**
     * 在websockethangdler之前拦截
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest req= (ServletServerHttpRequest) request;
            HttpSession session = req.getServletRequest().getSession();
            if (session!=null){
             User user = (User) session.getAttribute(UserServiceImpl.SESSION_LOGIN_KEY);
                if (!StringUtils.isEmpty(user)){
                    attributes.put(SampleWebsocketHandler.USER_KEY,user.getId());
                }
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    /**
     *  在websockethangdler之后拦截
     * @param request
     * @param response
     * @param wsHandler
     * @param ex
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
