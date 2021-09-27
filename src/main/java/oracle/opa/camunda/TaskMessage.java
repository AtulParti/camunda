package oracle.opa.camunda;
import java.util.*;
public class TaskMessage {
    private final String assignee;
    private final Map<String, Object> payload;
    private final String emailId;

    public TaskMessage(String assignee, Map<String, Object> payload, String emailId) {
        this.assignee = assignee;
        this.payload = payload;
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAssignee() {
        return assignee;
    }
    public Map<String, Object> getPayload() {
        return payload;
    }


    @Override
    public String toString() {
        StringBuilder payloadStr = new StringBuilder();
        payload.entrySet().forEach(entry -> {
            payloadStr.append(entry.getKey() + "::" + entry.getValue() + ", ");
        });
        return "TaskMessage{" +
                "assignee='" + assignee + '\'' +
                ", payload={" + payloadStr +
                "} , emailId='" + emailId + '\'' +
                '}';
    }
}
