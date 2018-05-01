package com.mocktest.demo.classes;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Elimane on Feb, 2018, at 03:15
 */
public class Person {

    private String name;
    private String ID;
    private int age;
    private  Departments departments;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {

        //Class which allow to generate some
        //random string data
        String key = RandomStringUtils.randomAlphanumeric(6);

        return  getDepartments()+key;


    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }
}
