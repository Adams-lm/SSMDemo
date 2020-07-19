package com.hznu.sys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hznu.sys.entity.Question;
import com.hznu.sys.service.QuestionService;
import com.hznu.sys.utils.LanguageMap;
import com.hznu.sys.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import static com.hznu.sys.utils.SendRequest.sendJudgePost;

/**
 * <p>
 * 前端控制器
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
    public Resp<String> judge(@RequestBody JSONObject json) {
        try {
            JSONObject jsonObject = new JSONObject();
            Integer questionId = json.getInteger("id");//获取题号
            String code = json.getString("code");//获取代码
            String language = json.getString("language");//获取所选语言

            JSONObject language_config = JSON.parseObject(LanguageMap.languageMap.get(language));

            //数据库未成形，题目CPU时间,内存大小,样例路径
            Question question = questionService.getById(questionId);
            int maxCpuTime = question.getLimitTime();
            int maxMemory = question.getLimitMemory();
            String testCase = String.valueOf(questionId);

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
            //发送请求，返回result结果
            JSONObject result = sendJudgePost(jsonObject);
            //获取err字段
            String err = (String) result.get("err");
            //若编译没有报错 测试各个样例
            if (null == err) {
                JSONArray dataList = (JSONArray) result.get("data");
                for (int i = 0; i < dataList.size(); i++) {
                    JSONObject jsonObject1 = dataList.getJSONObject(i);
                    Integer result1 = (Integer) jsonObject1.get("result");
                    //若有错误则返回错误
                    if (result1 == -1) {
                        return Resp.err("样例" + (i + 1) + "错误！");
                    }
                }
                //无错误 返回正确
                return Resp.ok("答案正确！");
            } else {
                //编译报错，输出类型和具体原因
                return Resp.err("错误类型：" + err + "，具体原因:" + result.get("data"));
            }
        } catch (Exception e) {
            System.out.println("系统异常" + e);
            return Resp.err("系统异常");
        }
    }

    @PostMapping("/add")
    public Resp<String> add(@RequestBody JSONObject jsonObject) {
        //截取question Json
        JSONObject questionObject = jsonObject.getJSONObject("question");
        //获取字段判断是否为空
        String title = questionObject.getString("title");
        Integer limit_time = questionObject.getInteger("limit_time");
        Integer limit_memory = questionObject.getInteger("limit_memory");
        Assert.notNull(title, "题目不能为空");
        Assert.notNull(limit_time, "时间限制不能为空");
        Assert.notNull(limit_memory, "内存限制不能为空");
        //存入对象写入数据库
        Question question = new Question(title, limit_time, limit_memory);
        questionService.save(question);
        //获取该题目的数据，后利用id作为文件夹名
        Question this_question = questionService.getOne(new QueryWrapper<Question>()
                .orderByDesc("id")
                .last("limit 1"));
        //info中的test_cases Json
        JSONObject testCases = new JSONObject();
        //截取testCast JsonArray并遍历
        JSONArray testCastArray = jsonObject.getJSONArray("test_cast");
        for (int i = 0; i < testCastArray.size(); i++) {
            int caseId = i + 1;
            //获取JsonArray中的JsonObject
            JSONObject jsonObject1 = testCastArray.getJSONObject(i);
            //input、output文件内容和size
            String input = jsonObject1.getString("input");
            String output = jsonObject1.getString("output");
            int inputSize = input.length();
            int outputSize = output.length();
            //文件写入
            try {
                String filePath = "/OnlineJudgeDeploy/data/backend/test_case/" + this_question.getId() + "/";
                //如果文件夹不存在则创建文件夹
                File fileName = new File(filePath);
                if (!fileName.exists()) {
                    fileName.mkdir();
                }
                //创建 in 和 out文件
                File fileIn = new File(filePath + caseId + ".in");
                File fileOut = new File(filePath + caseId + ".out");
                fileIn.createNewFile();
                fileOut.createNewFile();
                //进行读写
                FileWriter fwIn = new FileWriter(fileIn.getAbsoluteFile());
                FileWriter fwOut = new FileWriter(fileOut.getAbsoluteFile());
                BufferedWriter bwIn = new BufferedWriter(fwIn);
                BufferedWriter bwOut = new BufferedWriter(fwOut);
                bwIn.write(input);
                bwOut.write(output);
                //文件关闭
                bwIn.close();
                bwOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //处理out、put末尾空白符 md5加密
            String pattern = "\\S*(\\s+$)";
            while (output.length() != 0 && Pattern.matches(pattern, output)) {
                output = output.substring(0, output.length() - 1);
            }
            String md5 = DigestUtils.md5DigestAsHex(output.getBytes());
            //将改样例数据存为cases
            JSONObject cases = new JSONObject();
            cases.put("stripped_output_md5", md5);
            cases.put("input_size", inputSize);
            cases.put("output_size", outputSize);
            cases.put("input_name", caseId + ".in");
            cases.put("output_name", caseId + ".out");
            //将cases存入testCases
            testCases.put(String.valueOf(caseId), cases);
            //文件写入
        }
        //最终存入infoJson
        JSONObject infoJson = new JSONObject();
        infoJson.put("spj", false);
        infoJson.put("test_cases", testCases);
        //info文件内容 对Json进行格式化
        String info = JSON.toJSONString(infoJson, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        //写入info文件
        try {
            String filePath = "/OnlineJudgeDeploy/data/backend/test_case/" + this_question.getId() + "/";
            File file = new File(filePath + "/info");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(info);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return Resp.ok("新增题目异常");
        }
        return Resp.ok("新增题目成功,题号为:" + this_question.getId());
    }
}
