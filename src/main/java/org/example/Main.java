package org.example;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        List<Student> students = new ArrayList<>();


        allFilesRead(students);
        students.get(0).setName("Angela");
        students.get(1).setName("Angela");
        students.get(2).setName("Angela");
        students.get(0).persist();
        students.get(1).persist();
        students.get(2).persist();


    }
    private static void allFilesRead(List<Student> students) throws IOException {
        List<String> paths = new ArrayList<>();
        String path1 = "C:\\Users\\u25761\\IdeaProjects\\IO\\obj\\0001";
        String path2 = "C:\\Users\\u25761\\IdeaProjects\\IO\\obj\\0002";
        String path3 = "C:\\Users\\u25761\\IdeaProjects\\IO\\obj\\0003";
        paths.add(path1);
        paths.add(path2);
        paths.add(path3);

        for (String path : paths) {
            File directory = new File(path);
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                assert files != null;
                String currentString = Files.readString(Path.of(path,"Current.stu"));
              //  String result = currentState(path);


                for (File file : files) {
                    if (file.isFile() && file.getName().equals(currentString)) {
                        RedundantFilePersistenceStorage redundantFilePersistenceStorage = new RedundantFilePersistenceStorage(path,currentString);
                        Student student = new Student(directory.getName());
                        student.setPersistenStorage(redundantFilePersistenceStorage);
                        student.restore(redundantFilePersistenceStorage.getLastState());
                        students.add(student);
                        System.out.println(student);

                    }
                }

            }
        else {
                System.out.println("Ungültiges Verzeichnis.");
            }

        }

    }

}










