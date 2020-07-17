package com.hznu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hznu.sys.entity.Student;
import com.hznu.sys.mapper.StudentMapper;
import com.hznu.sys.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hznu.sys.vo.StudentScVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LinMing
 * @since 2020-07-03
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Override
    public Student selectStudentById(Integer Sno) {
        Student student = this.baseMapper.selectStudentById(Sno);
        return student;
    }

    @Override
    public List<StudentScVo> selectStudentListPage(Page<StudentScVo> page) {
        return this.baseMapper.selectStudentListPage(page);
    }

}
