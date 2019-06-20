package cn.gyyx.elves.console.system;

import org.apache.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private Logger logger = Logger.getLogger( this.getClass() );

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.debug( "启动成功"  );
    }
}
