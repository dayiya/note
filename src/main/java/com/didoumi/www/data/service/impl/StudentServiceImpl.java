package com.didoumi.www.data.service.impl;

import com.didoumi.www.data.service.StudentService;
import com.didoumi.www.data.dao.StudentDao;
import com.didoumi.www.data.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public List<Student> selectStudentList(Student student) {
        List<Student> list = studentDao.selectStudentList(student);
        return list;
    }
}
