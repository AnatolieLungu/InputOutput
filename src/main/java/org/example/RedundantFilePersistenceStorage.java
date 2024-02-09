package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class RedundantFilePersistenceStorage implements PersistenceStorage{
    String path;
   String fileName;

    public RedundantFilePersistenceStorage(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    @Override
    public String getLastState() {

        String stringLastState = "";

        try {
            stringLastState = Files.readString(Path.of(path, fileName));

        }catch (IOException e) {
            System.out.println("ERROR File not exist");;
        }

        return stringLastState;
    }

    @Override
    public void store(String state) {

        String fileLastState = "Current.stu";

        try {

            String currentString = Files.readString(Path.of(path,fileLastState));

            if (currentString.equals("A.stu")){
                Files.writeString(Path.of(path,"B.stu"), state, StandardCharsets.UTF_8);
                Files.writeString(Path.of(path,fileLastState), "B.stu", StandardCharsets.UTF_8);
            }else {
                Files.writeString(Path.of(path,"A.stu"), state, StandardCharsets.UTF_8);
                Files.writeString(Path.of(path,fileLastState), "A.stu", StandardCharsets.UTF_8);
            }


        } catch (IOException ex) {
            System.out.println("Invalid Path");
        }

    }
}
