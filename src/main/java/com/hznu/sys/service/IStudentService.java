package com.hznu.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hznu.sys.entity.Student;
import com.hznu.sys.vo.StudentScVo;

import java.util.List;

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
    List<StudentScVo> selectStudentListPage(Page<StudentScVo> page);
}
