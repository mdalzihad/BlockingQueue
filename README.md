# Blocking Queue usage in Spring

## Topics Covered

* ThreadPoolExecutor with Bounded Blocking Queue
* Add Task in the queue if there is space for new task

## How to run
This is a gradle project. Can be run with following command:
> gradle clean build run

## Code Example

- Defining a custom ThreadPool Executor

```java
    public TaskThreadPoolExecutor() {
        super(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                WAIT_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(BLOCKING_QUEUE_CAPACITY)
        );
    }
```

- Declare the Custom ThreadPool as a bean
```java
    @Component
    public class TaskThreadPoolExecutor extends ThreadPoolExecutor implements InitializingBean {
```

- Start the ThreadPool Executor
```java
    int threadCount = prestartAllCoreThreads();
```

- Add task(s) to the ThreadPool's blocking queue
```java
    BlockingQueue<Runnable> blockingQueue = taskThreadPoolExecutor.getQueue();

    boolean status = blockingQueue.offer(task);
```

- Add an implementation of Runnable Task
```java
    public class DemoTask implements Runnable {
```

- Init the demo task with TaskProcessor. It can be a bean or any object.
```java
    public DemoTask(String taskName, TaskQueueProcessService taskQueueProcessService) {
        this.taskName = taskName;
        this.taskQueueProcessService = taskQueueProcessService;
    }
```

- Initiate task using runnable
```java
    @Override
    public void run() {
        try {
            taskQueueProcessService.processTask(this);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
```

### Happy Coding!!