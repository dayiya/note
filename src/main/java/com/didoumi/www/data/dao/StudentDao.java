package com.didoumi.www.data.dao;

import com.didoumi.www.data.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectStudentList(Student student);
}
