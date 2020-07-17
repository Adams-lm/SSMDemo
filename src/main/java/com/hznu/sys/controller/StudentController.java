package com.hznu.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hznu.sys.entity.Student;
import com.hznu.sys.mapper.StudentMapper;
import com.hznu.sys.service.IStudentService;
import com.hznu.sys.vo.StudentScVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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
    public String addStudent(Student student) {
        studentMapper.insert(student);
        return "OK";
    }

    @GetMapping("/test/{Sno}")
    public Student ServiceTest(@PathVariable Integer Sno) {
        Student student = iStudentService.selectStudentById(Sno);
        return student;
    }


    @PostMapping("/pageTest")
    public Object R(@RequestBody Map<String,Object> map) {
        Integer current = (Integer) map.get("current");
        Integer size = (Integer) map.get("size");
        String keyword = (String) map.get("keyword");

        Page<Student> page = new Page<>(current, size);
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("sno",keyword);

        IPage<Student> iPage = studentMapper.selectPage(page, wrapper);
        return page;
    }

    @PostMapping("/pageConnect")
    public Object selectStudentListPage(@RequestBody Map<String,Object> map){
        Integer current = (Integer) map.get("current");
        Integer size = (Integer) map.get("size");
//        String keyword = (String) map.get("keyword");
//
//        QueryWrapper<Student> wrapper = new QueryWrapper<>();
//        wrapper.eq("Sname",keyword);
        Page<StudentScVo> page = new Page<>(current, size);

        page.setRecords(iStudentService.selectStudentListPage(page));
        return page;
    }

}
