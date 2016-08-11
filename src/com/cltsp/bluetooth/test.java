package com.cltsp.bluetooth;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leacher on 16-8-9.
 */
public class test {
    public static void main(String[] args) {
        ArrayList<student> students=new ArrayList<>();
        HashMap<String,String> maptest=new HashMap<>();
        student s1;
        student s2;
        students.add(new student(18));
        students.add(new student(19));
        s1=students.get(0);
        s2=students.get(1);
        System.out.println(s1);
        System.out.println(s1.age);
        s1=s2;
        System.out.println(s1);
        System.out.println(s1.age);
        System.out.println(students.get(0));
        System.out.println(students.get(0).age);
        teacher t1=new teacher(s1);
        s1=students.get(0);
        s2=students.get(0);
        System.out.println(t1.s);
    }
}

class student{
    public int age;
    public student(int age) {
        this.age = age;
    }
}

class teacher{
    public student s;
    public teacher(student s) {
        this.s = s;
        System.out.println("this.s:"+this.s);
        System.out.println("s:"+s);
    }


}

