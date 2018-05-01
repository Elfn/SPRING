package com.mocktest.demo.bootstrap;

import com.mocktest.demo.classes.Departments;
import com.mocktest.demo.classes.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elimane on Feb, 2018, at 03:15
 */
@Component
public class PersonBootstrap {

    private static PersonBootstrap instance = new PersonBootstrap();
    private  final static List<Person> persons = new ArrayList<>();

    private PersonBootstrap() {
    }


    public static PersonBootstrap getInstance()
    {
        return instance;
    }

    public Person createPerson(String name, Departments personDepartment, int age)
    {
        Person person = new Person();

        person.setName(name);
        person.setDepartments(personDepartment);
        person.setID(person.getID());
        person.setAge(age);

//        return person.getID()+" "+person.getName()+" from department "+personDepartment;
        return person;
    }

    public void  addPerson(Person person)
    {
        persons.add(person);
    }

    public  List<Person> getAllPersons() {
        return persons;
    }

    public List<Person> getVeterans()
    {
       final List<Person> veterans = new ArrayList<>();

        persons.stream()
                .filter(veteran-> veteran.getAge() >= 30)
                 .forEach(person -> {veterans.add(person);});

            return veterans;
    }

    public List<Person> getYoungs()
    {
        List<Person> youngs = new ArrayList<>();

        persons.stream()
                .filter(young-> young.getAge() <= 30)
                .forEach(person -> {youngs.add(person);});

        return youngs;
    }
}
