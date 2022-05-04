package com.company;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Gson gson = new Gson();
    static File fileData = new File("src\\com\\company\\tasks.json");
    static FileReader fileReader;
    static {
            try {
            fileReader = new FileReader(fileData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static TaskList tasks = gson.fromJson(fileReader, TaskList.class);
    
    public static void main(String[] args) {
        boolean run = true;
        String menu = """
         Please choose an option:
         [1] Add a task.
         [2] Remove a task.
         [3] Update a task.
         [4] List all tasks.
         [5] List tasks by priority.
         [6] Sort tasks.
         [0] Exit and Save.
         Enter:""";
        /*TaskList<TD_item> tasks = new TaskList<>();
        TD_item task = new TD_item("Sleep","sleeping is good","4");
        tasks.add(task);
        task = new TD_item("Eat","food is good","2");
        tasks.add(task);
        task = new TD_item("Eat","food is good","3");
        tasks.add(task);
        task = new TD_item("Run","running is good","2");
        tasks.add(task);
        task = new TD_item("Walk","walking is good","3");
        tasks.add(task);*/
        String num;
        String task_remove;
        String task_upNum;
        String task_upd;
        String s = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        while(run){
            System.out.println(menu);
            num = input.nextLine();
            switch (num) {
                case "1":{
                    update(tasks, false);
                    break;
                }
                case "2":{
                    System.out.println("\nTasks:");
                    show_list(tasks);
                    System.out.println("What task would you like to remove? \n Enter the tasks number: (1 - " + (tasks.size()) + ")");
                    task_remove = input.nextLine();
                    if(isNum(task_remove)) {
                        if(Integer.parseInt(task_remove) <= tasks.size() & Integer.parseInt(task_remove) > 0) {
                            tasks.remove(Integer.parseInt(task_remove) - 1);
                            System.out.println(s + "Updated tasks list:\n");
                            show_list(tasks);
                        }
                        else{
                            System.out.println(s + "Number is out of range, enter a valid number.");
                        }
                    }
                    else{
                        System.out.println(s + "Error - Enter a valid number.");
                    }
                    break;
                }
                case "3":{
                    System.out.println(s + "Tasks:");
                    show_list(tasks);
                    System.out.println("What task would you like to update? \nEnter the tasks number: (1 - " + (tasks.size()) + ")");
                    task_upNum = input.nextLine();
                    if(isNum(task_upNum)) {
                        if(Integer.parseInt(task_upNum) <= tasks.size() & Integer.parseInt(task_upNum) > 0) {
                            System.out.println("Updating task " + task_upNum + ":");
                            TD_item new_task = update(tasks, true);
                            tasks.set((Integer.parseInt(task_upNum) - 1), new_task);
                            System.out.println(s + "Updated tasks list:\n");
                            show_list(tasks);
                        }
                        else{
                            System.out.println(s + "Number is out of range, enter a valid number.");
                        }
                    }
                    else{
                        System.out.println(s + "Error - Enter a valid number.");
                    }
                    break;
                }
                case "4":{
                    System.out.println(s + "\nAll Tasks are listed below:");
                    show_list(tasks);
                    break;
                }
                case "5":{
                    System.out.println("Enter a priority level to list by:");
                    num = input.nextLine();
                    show_by_pri(tasks, num);
                    break;
                }
                case "6":{
                    System.out.println(s + "Sorted all the tasks:");
                    Collections.sort(tasks);
                    show_list(tasks);
                    break;
                }
                case "0":{
                    System.out.println("Exiting...");
                    saveTasks(tasks);
                    run = false;
                    break;
                }
                default: System.out.println(s + "Invalid Input, enter numbers 0-5");
            }
        }
    }
    public static class TaskList extends ArrayList<TD_item>{
    }
    public static TD_item update(TaskList tasks, boolean return_back){
        String s = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        String task_title;
        String task_des;
        String task_level;
        TD_item task;
        System.out.println("What is the title of the task you want to add?");
        task_title = input.nextLine();
        System.out.println("What is the description of the task '"+ task_title +"'?");
        task_des = input.nextLine();
        System.out.println("What is the priority of the task '"+ task_title +"' (0-5)?");
        task_level = input.nextLine();
        boolean run2 = true;
        boolean out_range = false;
        int value;
        while(run2){
            //check if task_level is a number
            while(!isNum(task_level) || out_range) {
                System.out.println("Not a number or out of range(0-5)");
                System.out.println("What is the priority of the task '" + task_title + "'?");
                task_level = input.nextLine();
                out_range = false;
            }
            //check if task_level is out of range
            value = Integer.parseInt(task_level);
            if(value == 0 || value == 1 || value == 2 || value == 3 || value == 4 || value == 5){
                run2 = false;
            }else{
                out_range = true;
            }
        }
        task_title = task_title.replaceAll(" ", "_");
        if(return_back){
            task = new TD_item(task_title,task_des,task_level);
            return task;
        }else{
            task = new TD_item(task_title,task_des,task_level);
            tasks.add(task);
            System.out.println(s + "Updated tasks list:\n");
            show_list(tasks);
        }
        return null;
    }
    public static void show_list(TaskList tasks) {
        String tasks_list = "";
        /*for(TD_item a: tasks){
            System.out.println(a);
        }*/
        for (int i = 0; i < tasks.size(); i++) {
            TD_item task_get = (TD_item) tasks.get(i);
            tasks_list = tasks_list + ("(" + (i+1) + ")" + task_get.display()) + "\n";
        }
        System.out.println(tasks_list);
    }
    public static void show_by_pri(TaskList tasks, String pri){
        if(pri.equals("0") || pri.equals("1") || pri.equals("2") || pri.equals("3") || pri.equals("4") || pri.equals("5")){
            String tasks_list = "";
            int j = 0;
            for (int i = 0; i < tasks.size(); i++) {
                TD_item task_get = (TD_item) tasks.get(i);
                if (task_get.getPriority().equals(pri)) {
                    tasks_list = tasks_list + ("(" + (j + 1) + ")" + task_get.display()) + "\n";
                    j++;
                }
            }
            if(tasks_list.equals("")){
                System.out.println("There are no tasks with that priority level");
            }else{
                System.out.println(tasks_list);
            }
        }else{
            System.out.println("Enter a valid priority level (0-5)");
        }
    }
    public static void saveTasks(TaskList tasks){
        Gson gson = new Gson();
        //String json = gson.toJson(person);
        String json = gson.toJson(tasks);
        try {

            FileWriter writer = new FileWriter("src\\com\\company\\tasks.json");
            gson.toJson(tasks, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isNum(String string) {
        int value;
        if(string == null || string.equals("")) {
            return false;
        }
        try {
            value = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
