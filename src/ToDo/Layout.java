package ToDo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Layout extends JFrame {

    private TasksLayout tasksLayout;
    private NewTaskLayout newTaskLayout;
    private ReadJSON readJSON = new ReadJSON("tasks.json");

    public Layout(){
        super("To-Do-Liste");

        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        tasksLayout = new TasksLayout();
        tasksLayout.printAllTasks();
        newTaskLayout = new NewTaskLayout();

        this.add(tasksLayout, BorderLayout.CENTER);
        this.add(newTaskLayout, BorderLayout.EAST);
        this.setSize(new Dimension(580, 380));
    }

    public void setTasksLayout(TasksLayout taskLayout){
        this.setVisible(false);
        new Layout();
    }

    public static void main(String[] args) {
        Layout layout = new Layout();
    }
}
