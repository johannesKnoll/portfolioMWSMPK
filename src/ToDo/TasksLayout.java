package ToDo;

import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Flow;

public class TasksLayout extends JPanel {

    ReadJSON readJSON = new ReadJSON("tasks.json");
    LineBorder roundedLineBorder = new LineBorder(Color.black, 2, true);
    TitledBorder roundedTitledBorder = new TitledBorder(roundedLineBorder, "Task");

    JPanel tasksPanel = new JPanel();

    ActionListener actionListenerDone = (ActionEvent actionEvent) ->{
        for(int i = 0; i < readJSON.getArraySize(); i++){
            if(actionEvent.getActionCommand().equals("" + i)){
                readJSON.removeTaskByIndex(i);
                Layout topFrame = (Layout) SwingUtilities.getWindowAncestor(this);
                topFrame.setTasksLayout(this);
            }else if(actionEvent.getActionCommand().equals("" + i + i)){
                readJSON.removeTaskByIndex(i);
                Layout topFrame = (Layout) SwingUtilities.getWindowAncestor(this);
                topFrame.setTasksLayout(this);
            }
        }
    };

    public JLabel getJLabelWithTask(int index){
        JLabel label = new JLabel();
        label.setText(readJSON.getTaskByIndex(index));
        label.setBorder(new EmptyBorder(0, 10, 0, 0));

        return label;
    }

    public JLabel getJLabelWithDescription(int index){
        JLabel label = new JLabel();
        label.setText(readJSON.getDescriptionByIndex(index));
        label.setBorder(new EmptyBorder(0, 10, 0, 0));

        return label;
    }

    public JLabel getJLabelWithDueDate(int index){
        JLabel label = new JLabel();
        label.setText(readJSON.getDueDateByIndex(index));
        label.setBorder(new EmptyBorder(0, 10, 0, 0));

        return label;
    }

    public JLabel getJLabel(String text){
        JLabel jLabel = new JLabel("<HTML><U>" + text + "</U></HTML>");
        jLabel.setFont(new Font("Godfrey Thin", Font.BOLD, 18));
        jLabel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return jLabel;
    }

    public JButton getDoneButton(String text){
        JButton done = new JButton(text);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
        RoundedBorder roundedBorder = new RoundedBorder(10);
        CompoundBorder compoundBorder = new CompoundBorder(emptyBorder, roundedBorder);
        done.setBorder(roundedBorder);
        done.addActionListener(actionListenerDone);
        done.setForeground(Color.white);
        done.setBackground(Color.black);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                done.setForeground(Color.black);
                done.setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                done.setForeground(Color.white);
                done.setBackground(Color.black);
            }
        };
        done.addMouseListener(mouseAdapter);

        return done;
    }

    public JButton getDeleteButton(String text){
        JButton delete = new JButton(text);
        Border emptyBorder = new EmptyBorder(15, 5, 5, 5);
        RoundedBorder roundedBorder = new RoundedBorder(10);
        CompoundBorder compoundBorder = new CompoundBorder(emptyBorder, roundedBorder);
        delete.setBorder(roundedBorder);
        delete.addActionListener(actionListenerDone);
        delete.setForeground(Color.white);
        delete.setBackground(Color.black);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                delete.setForeground(Color.black);
                delete.setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delete.setForeground(Color.white);
                delete.setBackground(Color.black);
            }
        };
        delete.addMouseListener(mouseAdapter);

        return delete;
    }

    public void printAllTasks(){
        for (int i = 0; i < readJSON.getArraySize(); i++){
            JPanel task = new JPanel();
            task.setLayout(new BoxLayout(task, BoxLayout.Y_AXIS));

            task.add(getJLabel("Task:"));
            task.add(getJLabelWithTask(i));

            task.add(getJLabel("Description:"));
            task.add(getJLabelWithDescription(i));

            task.add(getJLabel("Due-Date:"));
            task.add(getJLabelWithDueDate(i));

            JButton done = getDoneButton("Erledigt");
            done.setActionCommand("" + i);
            task.add(done);

            JButton delete = getDeleteButton("Löschen");
            delete.setActionCommand("" + i + i);
            task.add(delete);

            task.setBorder(roundedTitledBorder);
            tasksPanel.add(task);
        }
    }

    public TasksLayout(){
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.Y_AXIS));
        tasksPanel.setSize(new Dimension(500, 380));
        tasksPanel.setMaximumSize(new Dimension(200, 380));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JScrollPane jScrollPane = new JScrollPane(tasksPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //printAllTasks();

        if(readJSON.isEmptyList()){
            add(new JLabel("Aktuell keine Aufgaben vorhanden"));
        }else{
            setLayout(new BorderLayout());
            add(jScrollPane, BorderLayout.CENTER);
            setVisible(true);
        }
    }
}