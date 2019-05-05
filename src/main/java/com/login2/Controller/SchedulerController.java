package com.login2.Controller;

import com.login2.Model.Task;
import com.login2.Service.Scheduler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {

  @Autowired
  private Scheduler scheduler;

  List<Task> tasks;

  @RequestMapping(value = "/execute/{templatevariable}", method = RequestMethod.GET)
  public List<Map<String, List<Integer>>> execute(
      @PathVariable("templatevariable") String templatevariable) {
    tasks = new ArrayList<>();
    String[] templatevariables = templatevariable.split(",");
    for (int i = 0; i < templatevariables.length; i++)
      tasks.add(new Task(Integer.parseInt(templatevariables[i]), new ArrayList()));

    scheduler.createpool(1);
    scheduler.scheduledtasks(tasks);
    scheduler.runtask();

    return tasks.stream().map(a -> convertmap(a)).collect(Collectors.toList());

  }

  private Map<String, List<Integer>> convertmap(Task a) {
    return new HashMap() {
      {
        put(a.num, a.primenumbers);
      }
    };
  }

  @RequestMapping(value = "/executeadhoc/{templatevariable}", method = RequestMethod.GET)
  public Map<Integer, List<Integer>> executeadhoc(@PathVariable("templatevariable") String templatevariable) {
    Task task=new Task(Integer.parseInt(templatevariable), new ArrayList());
    scheduler.executeadhoc(task);
    return new HashMap() {
      {
        put(task.num,task.primenumbers);
      }
    };
  }
}

