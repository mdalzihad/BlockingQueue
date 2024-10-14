package net.spring.services;

import net.spring.tasks.DemoTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

/**
 * @author zihad
 * @since 11/10/24
 */
@Service
public class TaskIntakeService {

    @Autowired
    private TaskThreadPoolExecutor taskThreadPoolExecutor;

    public void addTask(DemoTask task) {
        BlockingQueue<Runnable> blockingQueue = taskThreadPoolExecutor.getQueue();

        boolean status = blockingQueue.offer(task);

        if (!status) {
            //changing console output color to Red
            System.out.print("\033[0;31m");
        }

        System.out.printf("[TASK-INTAKE] taskName:%s status:%s blockingQueueRemainingCapacity:%d, blockingQueueSize:%d\n" + "\033[0m",
                task.getTaskName(), status, blockingQueue.remainingCapacity(), blockingQueue.size());

    }
}
