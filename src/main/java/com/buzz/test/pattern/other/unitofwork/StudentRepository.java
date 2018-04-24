package com.buzz.test.pattern.other.unitofwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentRepository implements IUnitOfWork<Student> {

    private Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    private Map<String, List<Student>> context;

    private StudentDatabase studentDatabase;

    public StudentRepository(Map<String, List<Student>> context, StudentDatabase studentDatabase) {
        this.context = context;
        this.studentDatabase = studentDatabase;
    }

    private void register(Student student, String operation) {
        logger.info("Registering {} for delete in context.", student.getName());
        List<Student> studentToOperation = context.get(operation);
        if (studentToOperation == null) {
            studentToOperation = new ArrayList<>();
        }

        studentToOperation.add(student);
        context.put(operation, studentToOperation);
    }

    @Override
    public void registerNew(Student entity) {
        register(entity, IUnitOfWork.INSERT);
    }


    @Override
    public void registerModify(Student entity) {
        register(entity, IUnitOfWork.MODIFY);
    }

    @Override
    public void registerDelete(Student entity) {
        register(entity, IUnitOfWork.DELETE);
    }

    @Override
    public void commit() {
        if (context == null || context.size() == 0) {
            return;
        }

        if (context.containsKey(IUnitOfWork.INSERT)) {
            commitInsert();
        }
    }

    public void commitInsert() {
        List<Student> studentToBeInsert = context.get(IUnitOfWork.INSERT);
        for (Student student : studentToBeInsert) {
            studentDatabase.insert(student);
        }
    }

    public void commitModify() {

        List<Student> studentToBeModified = context.get(IUnitOfWork.MODIFY);
        for (Student student : studentToBeModified) {
            studentDatabase.modify(student);
        }
    }

    public void commitDelete() {

        List<Student> studentToBeDeleted = context.get(IUnitOfWork.DELETE);

        for (Student student : studentToBeDeleted) {
            studentDatabase.delete(student);
        }
    }
}
