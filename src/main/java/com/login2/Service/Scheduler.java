package com.login2.Service;

import com.login2.Model.Task;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Scheduler{
  private ScheduledExecutorService scheduledThreadPool;
  ScheduledFuture scheduledFuture;

  public void createpool(int poolsize){
    this.scheduledThreadPool= Executors.newScheduledThreadPool(poolsize);
  }

  public void scheduledtasks(List<Task> taskList){
    for(Task task:taskList)
      scheduledFuture=scheduledThreadPool.schedule(task, 2, TimeUnit.SECONDS);
  }

  public void runtask(){
    scheduledThreadPool.shutdown();
    while(!scheduledThreadPool.isTerminated()){
    }
    return;
  }

  public void executeadhoc(Task task){
    task.start();
  }
}