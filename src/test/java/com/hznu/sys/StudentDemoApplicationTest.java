package com.hznu.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hznu.sys.entity.Student;
import com.hznu.sys.mapper.StudentMapper;
import com.hznu.sys.service.IStudentService;
import com.hznu.sys.vo.StudentScVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class StudentDemoApplicationTest {

    @Autowired
    private IStudentService iStudentService;

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void selectStudentByName() {
        List<StudentScVo> studentList = studentMapper.selectStudent();
        for (StudentScVo studentScVo : studentList) {
            System.out.println(studentScVo);
        }
    }

    @Test
    public void JsonToMapTest() {
        String params = "{\"version\":\"1.0\",\"charset\":\"UTF-8\",\"sign\":\"f94b7eb96d529bfe5b28d57288dd5b4ffb576c2ff98b2fa4ff2be6f70ab8b6e7\",\"signType\":\"SHA-256\",\"reqData\":{\"nextKeyValue\":\"\",\"dateTime\":\"20160825113210\",\"operatorNo\":\"9999\",\"merchantNo\":\"000054\",\"date\":\"20160116\",\"branchNo\":\"0755\"}}";

        HashMap<String, Object> hashMap = JSON.parseObject(params, HashMap.class);
        System.out.println("hashmap=" + hashMap);

        Map<String, Object> map = JSON.parseObject(params, Map.class);
        System.out.println("map=" + map);
    }


    @Test
    public void MapToJson() {
        String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}";
        //第二种方式
        Map mapTypes = JSON.parseObject(str);
        System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");
        for (Object obj : mapTypes.keySet()) {
            System.out.println("key为：" + obj + "值为：" + mapTypes.get(obj));
        }
    }

    @Test
        public void pageaa(){
            Page<Student> page = new Page<>(1, 4);
//        QueryWrapper<Student> wrapper = new QueryWrapper<>();
//        wrapper.eq("sno",95001);
        IPage<Student> iPage = studentMapper.selectPage(page, null);
        System.out.println(JSON.toJSON(iPage));
    }

}
