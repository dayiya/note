package com.didoumi.www.data.controller;

import com.didoumi.www.data.entity.User;
import com.didoumi.www.data.service.ShiroService;
import com.didoumi.www.data.service.StudentService;
import com.didoumi.www.data.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> studentList() {
        Student student = new Student();
        List<Student> students = studentService.selectStudentList(student);
        /*students.stream().map(m -> {
            return m.getName();
        }).collect(Collectors.toList());
        students.stream().forEach(m ->
                System.out.println(m.getName()));*/
        return students;
    }

    @RequestMapping(value = "/{oid}", method = RequestMethod.GET)
    @ResponseBody
    public Student student(@PathVariable("oid") String oid) {
        Student student = new Student();
        student.setOid(oid);
        List<Student> students = studentService.selectStudentList(student);
        Optional<Student> opt = students.stream().findFirst();
        if (opt.isPresent()) {
            return opt.get();
        }
        student.setOid(null);
        return student;
    }
    @Resource
    private ShiroService shiroService;

    @RequestMapping(value = "/shiro/{username}", method = RequestMethod.GET)
    @ResponseBody
    public User user(@PathVariable("username") String username) {
        return shiroService.findByUsername(username);
    }

}
