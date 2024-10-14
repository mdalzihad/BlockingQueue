package net.spring.tasks;

import net.spring.services.TaskQueueProcessService;

/**
 * @author zihad
 * @since 11/10/24
 */
public class DemoTask implements Runnable {
    private final String taskName;
    private final TaskQueueProcessService taskQueueProcessService;

    public DemoTask(String taskName, TaskQueueProcessService taskQueueProcessService) {
        this.taskName = taskName;
        this.taskQueueProcessService = taskQueueProcessService;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public void run() {
        try {
            taskQueueProcessService.processTask(this);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
