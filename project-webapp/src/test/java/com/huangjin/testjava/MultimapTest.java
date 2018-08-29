package com.huangjin.testjava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2018-4-12.
 */
public class MultimapTest {
    class Student {
        String name;
        int age;
    }

    private static final String CLASS_NAME_1 = "一年级";
    private static final String CLASS_NAME_2 = "二年级";

    Map<String, List<Student>> StudentsMap = new HashMap<>();

    public void testStudent() {

        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.name = "Tom" + i;
            student.age = 6;
            addStudent(CLASS_NAME_1, student);
        }
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.name = "Jary" + i;
            student.age = 7;
            addStudent(CLASS_NAME_2, student);
        }
        List<Student> class1StudentList = StudentsMap.get(CLASS_NAME_1);

        for (Student stu : class1StudentList) {
            System.out.println("一年级学生 name:" + stu.name + " age:" + stu.age);
        }
    }

    public void addStudent(String className, Student student) {
        List<Student> students = StudentsMap.get(className);
        if (students == null) {
            students = new ArrayList<>();
            StudentsMap.put(className, students);
        }
        students.add(student);
    }

    public static void main(String[] args) {
        MultimapTest multimapTest = new MultimapTest();
        multimapTest.testStudent();

    }
}
