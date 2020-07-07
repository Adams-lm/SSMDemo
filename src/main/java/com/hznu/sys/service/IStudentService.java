package com.hznu.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hznu.sys.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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
    IPage<Student> selectStudentByName(Page<Student> page);
}
