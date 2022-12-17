package com.mylog.tools.utils.logconverter;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dylan
 * @Description LogJsonConverter
 * @Date : 2022/12/17 - 22:53
 */
@Configuration
public class LogJsonConverter extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        try {
            // 将日志中的双引号改为 \"
            String message = event.getMessage();
            String replacedVar = message;
            if (message.contains("\"")){
                replacedVar = message.replaceAll("\"", "\\\\\"");
            }
            String res = MessageFormatter.arrayFormat(replacedVar, event.getArgumentArray()).getMessage();
            return res;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
