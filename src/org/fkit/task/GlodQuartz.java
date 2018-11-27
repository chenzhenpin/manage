package org.fkit.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class GlodQuartz {
	//该种定时任务不易实现动态定时任务
    /**
     * 用户自动加金币
     * 每天凌晨一点执行一次
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void addUserGold() {
        System.out.println("凌晨一点了，你睡了么？");
    }

    /**
     * 每隔5秒定时清理缓存
     */
//    @Scheduled(cron = "*/5 * * * * ? ")
//    public void cacheClear() {
//        System.out.println("时间又过去5秒了，真令人伤感...");
//    }
}
