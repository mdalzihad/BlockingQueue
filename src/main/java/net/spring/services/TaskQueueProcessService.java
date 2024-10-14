package net.spring.services;

import net.spring.tasks.DemoTask;
import org.springframework.stereotype.Component;

/**
 * @author zihad
 * @since 29/8/24
 */
@Component
public class TaskQueueProcessService {

    public void processTask(DemoTask demoTask) throws InterruptedException {

        Thread.sleep(1000);

        System.out.printf("[TASK-PROCESS-SERVICE] status:completed taskName:%s\n", demoTask.getTaskName());
    }
}
