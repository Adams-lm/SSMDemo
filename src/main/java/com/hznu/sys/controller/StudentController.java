package com.hznu.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hznu.sys.entity.Student;
import com.hznu.sys.mapper.StudentMapper;
import com.hznu.sys.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LinMing
 * @since 2020-07-03
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @Resource
    private StudentMapper studentMapper;

    @PostMapping("/add")
    public String addStudent(Student student){
        studentMapper.insert(student);
        return "OK";
    }

    @GetMapping("/test/{Sno}")
    public Student ServiceTest(@PathVariable Integer Sno){
        Student student = iStudentService.selectStudentById(Sno);
        return student;
    }

    @PostMapping("/a")
    public IPage<Student> selectStudentByName(Map<String,Object> params){
            Page<Student> page = new Page<>((Integer)params.get("current"), (Integer)params.get("size"));
            return iStudentService.selectStudentByName(page);
        }
    }
