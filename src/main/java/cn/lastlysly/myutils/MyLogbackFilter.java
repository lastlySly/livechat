package cn.lastlysly.myutils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-26 16:13
 *
 * 尚未使用
 **/
public class MyLogbackFilter  extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {

        if(iLoggingEvent.getLoggerName().equals("ceshi")){
            return FilterReply.ACCEPT;
        }else {
            return FilterReply.DENY;
        }
    }
}
