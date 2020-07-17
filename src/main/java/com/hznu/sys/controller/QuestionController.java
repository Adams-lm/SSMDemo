package com.hznu.sys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.hznu.sys.entity.Question;
import com.hznu.sys.service.QuestionService;
import com.hznu.sys.utils.LanguageMap;
import com.hznu.sys.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static com.hznu.sys.utils.SendRequest.sendJudgePost;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lin
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/judge")
    public Resp<JSONObject> judge(@RequestBody JSONObject json) {
        try {
            JSONObject jsonObject = new JSONObject();
            String code = json.getString("code");//获取代码
            String language = json.getString("language");//获取所选语言
            JSONObject language_config = JSON.parseObject(LanguageMap.languageMap.get(language));

            //数据库未成形，题目CPU时间,内存大小,样例路径
            int maxCpuTime = 1;
            int maxMemory = 32;
            String testCase = "8b3aaf798403d787c82c1d4fc1aa2e9e";

            //写入Json
            jsonObject.put("src", code);
            jsonObject.put("language_config", language_config);
            jsonObject.put("max_cpu_time", maxCpuTime * 1000);
            jsonObject.put("max_memory", maxMemory * 1024 * 1024);
            jsonObject.put("test_case_id", testCase);
            jsonObject.put("output", true);
            for (String str : jsonObject.keySet()) {
                System.out.println(str + ":" + jsonObject.get(str));
            }
            JSONObject result = sendJudgePost(jsonObject);
            for (String s : result.keySet()) {
                System.out.println(s + ":" + result.get(s));
            }
            if(null!=result.get("error")){
                return Resp.err("error",result);//.put("code",result).put("msg","error")
            }else{
                return Resp.ok(result);
            }

        } catch (Exception e) {
            System.out.println("系统异常"+ e);
            return Resp.err("系统异常");
        }
    }

    @PostMapping("/add")
    public Resp<String> add(@RequestBody Question question) {

        questionService.save(question);

        return Resp.ok("新增题目成功");

    }

}
