package sample.javafiles;

public class Tasks {
    private String taskId;
    private String task;
    private String taskDate;

    public Tasks(String taskId, String task, String taskDate) {
        this.taskId = taskId;
        this.task = task;
        this.taskDate = taskDate;
    }
    public Tasks(){}

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTask() {
        return task;
    }

    public String getTaskDate() {
        return taskDate;
    }
}
