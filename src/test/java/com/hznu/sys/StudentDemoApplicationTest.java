package com.hznu.sys;

import com.hznu.sys.mapper.StudentMapper;
import com.hznu.sys.service.IStudentService;
import com.hznu.sys.vo.StudentScVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class StudentDemoApplicationTest {

    @Autowired
    private IStudentService iStudentService;

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void selectStudentByName(){
        List<StudentScVo> studentList = studentMapper.selectStudent();
        for (StudentScVo studentScVo : studentList) {
            System.out.println(studentScVo);
        }
    }

    @Test
    public void  print(){
        System.out.println("啦啦啦啦啦啦啦啦啦啦");
    }
}
