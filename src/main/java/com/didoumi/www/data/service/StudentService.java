package com.didoumi.www.data.service;

import com.didoumi.www.data.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> selectStudentList(Student student);
}
