package com.devtop.studentsorter.service;

import com.devtop.studentsorter.model.Student;

public class StudentFactory {

    public Student createStudent(String inputLine) {
        String[] input = inputLine.split(",");
        //skipping data integrity check due to time constraints
        return new Student(input[0], Double.valueOf(input[1]));
    }

}
