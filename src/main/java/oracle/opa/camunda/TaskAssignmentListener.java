package oracle.opa.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;

import java.util.Set;
import java.util.*;


@Component
class TaskAssignmentListener {

    @EventListener
    public void onTaskEvent(DelegateTask taskDelegate) {

        if (taskDelegate.getEventName().equalsIgnoreCase("assignment")) {
//            System.out.println("onTaskEvent(1).." + taskDelegate.getEventName());
            Set<String> variables = taskDelegate.getVariableNames();
            Map<String, Object> payload = new HashMap<String, Object>();

        for (String variable:variables) {
            payload.put(variable , taskDelegate.getVariable(variable));
        }

            // handle mutable task event



            String assignee = taskDelegate.getAssignee();
            String taskId = taskDelegate.getId();
            String email = null;
            if (assignee != null) {

                // Get User Profile from User Management
                IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
                User user = identityService.createUserQuery().userId(assignee).singleResult();

                if (user != null) {

                    // Get Email Address from User Profile
                    email = user.getEmail();


                }

            }
            TaskMessage taskMessage = new TaskMessage(assignee, payload, email);

            System.out.println("Task Message :: " + taskMessage);

        }
    }

//    @EventListener
//    public void onTaskEvent(org.camunda.bpm.spring.boot.starter.event
//                                        .TaskEvent taskEvent) {
////        System.out.println("onTaskEvent");
//        System.out.println("onTaskEvent(2)" + taskEvent.getEventName());
//        // handle immutable task event
//    }
//
//    @EventListener
//    public void onExecutionEvent(DelegateExecution executionDelegate) {
//        System.out.println("onExecutionEvent(1)::"  + executionDelegate.getEventName());
//
//        // handle mutable execution event
//    }
//
//    @EventListener
//    public void onExecutionEvent(org.camunda.bpm.spring.boot.starter.event
//                                             .ExecutionEvent executionEvent) {
//        System.out.println("onExecutionEvent(2)::" + executionEvent.getEventName());
//
//        // handle immutable execution event
//    }



}
