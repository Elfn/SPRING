package com.rp.demo.demo_react_prog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Elimane on May, 2018, at 19:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

      private  String firstname;
      private  String lastname;
      private int age;


    public String studentInfos() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
