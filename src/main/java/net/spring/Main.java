package net.spring;

import net.spring.services.TaskIntakeService;
import net.spring.services.TaskQueueProcessService;
import net.spring.tasks.DemoTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication(scanBasePackages = {"net.spring.services"})
public class Main implements CommandLineRunner {

    @Autowired
    TaskIntakeService taskIntakeService;

    @Autowired
    private TaskQueueProcessService taskQueueProcessService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        System.out.println(":: Process Started ::");

        for (int i = 0; i < 100; i++) {
            taskIntakeService.addTask(new DemoTask("task " + i, taskQueueProcessService));
            Thread.sleep(100);
        }

        Thread.sleep(5000);

        System.out.println("Adding more tasks");

        for (int i = 101; i < 200; i++) {
            taskIntakeService.addTask(new DemoTask("task " + i, taskQueueProcessService));
            Thread.sleep(100);
        }

        System.out.println(":: Process Completed ::");
    }
}