package com.buzz.test.pattern.other.unitofwork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitOfWorkApp {
    public static void main(String[] args) {
        Student ram = new Student(1L, "ram", "13489096754");
        Student shyam = new Student(1L, "shyam", "13489096755");
        Student gopi = new Student(1L, "ram", "13489096756");

        Map<String, List<Student>> context = new HashMap<>();
        StudentDatabase studentDatabase = new StudentDatabase();
        StudentRepository studentRepository = new StudentRepository(context, studentDatabase);

        studentRepository.registerNew(ram);
        studentRepository.registerModify(shyam);
        studentRepository.registerDelete(gopi);

        studentRepository.commit();

    }
}
