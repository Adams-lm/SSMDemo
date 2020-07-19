package com.hznu.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hznu.sys.entity.Student;
import com.hznu.sys.mapper.StudentMapper;
import com.hznu.sys.service.IStudentService;
import com.hznu.sys.utils.LanguageMap;
import com.hznu.sys.vo.StudentScVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hznu.sys.utils.SendRequest.sendJudgePost;

//@RunWith(SpringRunner.class)
//@SpringBootTest()
public class StudentDemoApplicationTest {

    @Test
    public void File() {
        try {
            String content = "This is the content to write into file";
            String filePath = "/OnlineJudge/data/backend/test_case/" + "1/";
            File fileName=new File(filePath);
            if(!fileName.exists()){//如果文件夹不存在
                fileName.mkdir();//创建文件夹
            }
            File file = new File(filePath+"/info");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void OnlineJudge() throws Exception {

        //用户代码和语言
        String code = "#include<iostream>\nusing namespace std;\nint main(){\n\tint a,b;\n  cin>>a>>b;\n  cout<<a+b;\n}";
        String language = "cpp";
        JSONObject language_config = JSON.parseObject(LanguageMap.languageMap.get(language));

        //数据库未成形，题目CPU时间,内存大小,样例路径
        int maxCpuTime = 1;
        int maxMemory = 32;
        String testCase = "a0365c31427450ef08e23de97984355d";

        //写入Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("src", code);
        jsonObject.put("language_config", language_config);
        jsonObject.put("max_cpu_time", maxCpuTime * 1000);
        jsonObject.put("max_memory", maxMemory * 1024 * 1024);
        jsonObject.put("test_case_id", testCase);
        jsonObject.put("output", true);


        System.out.println("=================下面是传入的Json====================");
        for (String str : jsonObject.keySet()) {
            System.out.println(str + ":" + jsonObject.get(str));
        }
        //post
        JSONObject result = sendJudgePost(jsonObject);
        System.out.println("=================下面是返回的Json====================");
        for (String s : result.keySet()) {
            System.out.println(s + ":" + result.get(s));
        }

        System.out.println("=================下面处理完的结果====================");
        String err = (String) result.get("err");
        //若编译没有报错
        if (null == err) {
            JSONArray dataList = (JSONArray) result.get("data");
            for (int i = 0; i < dataList.size(); i++) {
                JSONObject jsonObject1 = dataList.getJSONObject(i);
                Integer result1 = (Integer) jsonObject1.get("result");
                if (result1 == -1) {
                    System.out.println("样例" + (i + 1) + "错误！");
                    return;
                }
            }
            System.out.println("代码正确！");
        } else {
            //编译报错，输出类型和具体原因
            System.out.println("编译错误:" + err);
            System.out.println("具体原因:" + result.get("data"));
        }
    }


}
