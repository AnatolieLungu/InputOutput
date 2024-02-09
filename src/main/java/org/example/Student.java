package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class Student implements Persistable,Restorable{

    private  String name;
    private  Date dateOfBirth;
    private  String address;
    private final   String studentId ;
    private transient PersistenceStorage persistenceStorage;


    public Student(String name, Date dateOfBirth, String address, String studentId) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.studentId = studentId;
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date parsedDate = dateFormat.parse(dateOfBirth);
            this.dateOfBirth = parsedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String persist()  {


        String result = "";
        try {
            Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
           result= gson.toJson(this);

            persistenceStorage.store(result);

        }catch (Exception e){
            System.out.println("Invalid date format. Please enter date in the format MM/dd/yyyy.");
        }
        return result;
    }

    @Override
    public void restore(String state)  {

        try {
            Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
            Student student = gson.fromJson(state, Student.class);
            this.address = student.getAddress();
            this.name = student.getName();
            this.dateOfBirth = student.getDateOfBirth();


        }catch (Exception e) {
        System.out.println("Invalid date format.");;
    }
    }

    public void setPersistenStorage(PersistenceStorage persistenceStorage) {
        this.persistenceStorage = persistenceStorage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                 ", id ='" + studentId + '\'' +
                '}';
    }
}
