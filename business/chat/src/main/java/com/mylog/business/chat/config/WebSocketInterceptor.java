package com.mylog.business.chat.config;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.chat.service.SessionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Classname WebSocketInterceptor
 * @Description WebSocketInterceptor
 * @Date 7/6/2022 3:52 PM
 */
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {

    private static final MyLogger logger = MyLoggerFactory.getLogger(WebSocketInterceptor.class);

    @Resource
    private SessionService sessionService;

    /**
     * 握手前
     * @param request
     * @param response
     * @param webSocketHandler
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
        ServletServerHttpResponse serverHttpResponse = (ServletServerHttpResponse) response;
        // 参数传入userId和pwd方式
        // String userId = serverHttpRequest.getServletRequest().getParameter("userId");
        // String pwd = serverHttpRequest.getServletRequest().getParameter("pwd");
        // header中
        String authorization = serverHttpRequest.getServletRequest().getHeader("Sec-WebSocket-Protocol");
        // todo 根据获取到的authorization的值进行鉴权
        if (StringUtils.isBlank(authorization)){
            return false;
        }
        HttpSession httpSession = serverHttpRequest.getServletRequest().getSession(true);
        if (Objects.nonNull(httpSession)){
            String userName = (String) httpSession.getAttribute("SESSION_USERNAME");
            if (StringUtils.isBlank(userName)){
                userName = authorization;
            }
        }
        if (authorization.contains(",")){
            String[] split = authorization.split(",");
            Integer sessionId = sessionService.getOrCreateSession(split[0], split[1]);
            map.put("USERNAME", split[0].trim());
            map.put("TALKWITH", split[1].trim());
            serverHttpResponse.getServletResponse().setHeader("Sec-WebSocket-Protocol", split[0]);
        }else {
            serverHttpResponse.getServletResponse().setHeader("Sec-WebSocket-Protocol", authorization);
        }
        logger.info("start shaking hands >>>>>>");

        return true;
    }

    /**
     * 握手后
     * @param request
     * @param response
     * @param webSocketHandler
     * @param e
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Exception e) {
        logger.info("handshake success.");
    }


}
