package ToDo;

public class Tasks {

    private String task;
    private String description;
    private String dueDate;

    public Tasks(String task, String description, String dueDate) {
        this.task = task;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
