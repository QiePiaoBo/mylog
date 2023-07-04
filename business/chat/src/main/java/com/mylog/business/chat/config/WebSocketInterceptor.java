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
        // 使用WS的子协议传入连接参数 结构示意: userName,talkWith
        String authorization = serverHttpRequest.getServletRequest().getHeader("Sec-WebSocket-Protocol");
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
            Integer sessionId = sessionService.getOrCreateSession(split[0].trim(), split[1].trim());
            if (Objects.isNull(sessionId)){
                logger.error("<beforeHandshake> error, error getting session of {} and {}", split[0], split[1]);
                return false;
            }
            map.put(WebsocketConstant.WS_PROPERTIES_USERNAME, split[0].trim());
            map.put(WebsocketConstant.WS_PROPERTIES_TALKWITH, split[1].trim());
            map.put(WebsocketConstant.WS_PROPERTIES_SESSIONID, sessionId + "");
            // 如果传入了两个子协议 必须返回其中一个给客户端表示服务端选择了其中一个 如果将两个子协议原样返回 会导致连接失败
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
