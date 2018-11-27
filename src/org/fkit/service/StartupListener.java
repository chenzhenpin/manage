package org.fkit.service;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * 启动监听器
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

 

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
           System.out.println("容器初始化完成执行的方法");
        }
    }

  
}