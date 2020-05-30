package ToDo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Date;
import java.util.Iterator;

public class ReadJSON {

    private JSONParser jsonParser;
    private Object object;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public JSONObject getJsonObject(){
        return this.jsonObject;
    }

    public ReadJSON(String file){
        this.jsonParser = new JSONParser();
        try {
            this.object = this.jsonParser.parse(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.jsonObject = (JSONObject) object;
        this.jsonArray = (JSONArray) jsonObject.get("Tasks");
    }

    public void flushJSON(){
        try {
            FileWriter fileWriter = new FileWriter("tasks.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initialize(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        jsonObject.put("Tasks", jsonArray);

        try {
            FileWriter fileWriter = new FileWriter("tasks.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getTaskByIndex(int index){
        try{
            Object object = jsonParser.parse(new FileReader("tasks.json"));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray jsonArray = (JSONArray) jsonObject.get("Tasks");

            JSONObject tasks = (JSONObject) jsonArray.get(index);
            String task = (String) tasks.get("Task");

            return task;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDescriptionByIndex(int index){
        try{
            Object object = jsonParser.parse(new FileReader("tasks.json"));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray jsonArray = (JSONArray) jsonObject.get("Tasks");

            JSONObject tasks = (JSONObject) jsonArray.get(index);
            String description = (String) tasks.get("Description");

            return description;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDueDateByIndex(int index){
        try{
            Object object = jsonParser.parse(new FileReader("tasks.json"));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray jsonArray = (JSONArray) jsonObject.get("Tasks");

            JSONObject tasks = (JSONObject) jsonArray.get(index);
            String description = (String) tasks.get("Due-Date");

            return description;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean isEmptyList(){
        if(this.jsonArray.size() == 0){
            return true;
        }
        return false;
    }

    public int getArraySize(){
        return jsonArray.size();
    }

    public void printAllTasks(){
        try{
            Object object = jsonParser.parse(new FileReader("tasks.json"));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray jsonArray = (JSONArray) jsonObject.get("Tasks");

            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject tasks = (JSONObject) jsonArray.get(i);
                String task = (String) tasks.get("Task");
                System.out.println("Aufgabe: " + task);
                String description = (String) tasks.get("Description");
                System.out.println("Beschreibung: " + description);
                String dueDate = (String) tasks.get("Due-Date");
                System.out.println("Due-Date: " + dueDate);
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTaskByIndex(int index){
        try{
            Object object = jsonParser.parse(new FileReader("tasks.json"));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray jsonArray = (JSONArray) jsonObject.get("Tasks");

            jsonArray.remove(index);

            FileWriter fileWriter = new FileWriter("tasks.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String task, String description, String dueDate){
        try {
            JSONObject addedTask = new JSONObject();
            addedTask.put("Task", task);
            addedTask.put("Description", description);
            addedTask.put("Due-Date", dueDate);
            jsonArray.add(addedTask);

            FileWriter fileWriter = new FileWriter("tasks.json");
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ReadJSON readJSON = new ReadJSON("tasks.json");
        readJSON.initialize();
        readJSON.addTask("Hallo", "Hallo", "Hallo");
    }
}