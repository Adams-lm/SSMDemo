package com.hznu.sys.utils;

import java.util.HashMap;

/**
 * @author LIN
 * @date 2020/7/14 18:34
 */
public class LanguageMap {

    public static HashMap<String, String> languageMap = new HashMap<>();

    static {
        String c = "{\"compile\":{\"src_name\":\"main.c\",\"exe_name\":\"main\",\"max_cpu_time\":3000,\"max_real_time\":1000,\"max_memory\":1073741824,\"compile_command\":\"/usr/bin/gcc -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c11 {src_path} -lm -o {exe_path}\"},\"run\":{\"command\":\"{exe_path}\",\"seccomp_rule\":\"c_cpp\",\"env\":[\"LANG=en_US.UTF-8\",\"LANGUAGE=en_US:en\",\"LC_ALL=en_US.UTF-8\"]}}";
        String cpp = "{\"compile\":{\"src_name\":\"main.cpp\",\"exe_name\":\"main\",\"max_cpu_time\":3000,\"max_real_time\":1000,\"max_memory\":1073741824,\"compile_command\":\"/usr/bin/g++ -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c++11 {src_path} -lm -o {exe_path}\"},\"run\":{\"command\":\"{exe_path}\",\"seccomp_rule\":\"c_cpp\",\"env\":[\"LANG=en_US.UTF-8\",\"LANGUAGE=en_US:en\",\"LC_ALL=en_US.UTF-8\"]}}";
        languageMap.put("c", c);
        languageMap.put("cpp", cpp);
    }
}
