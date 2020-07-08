package com.hznu.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hznu.sys.entity.Student;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LinMing
 * @since 2020-07-03
 */
public interface IStudentService extends IService<Student> {
    Student selectStudentById(Integer Sno);
}
