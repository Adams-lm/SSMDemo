package com.hznu.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hznu.sys.entity.Student;
import com.hznu.sys.vo.StudentScVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinMing
 * @since 2020-07-03
 */
public interface StudentMapper extends BaseMapper<Student> {

    @Select("SELECT S.*,Cno,Grade FROM student S,sc where S.Sno=sc.Sno")
    List<StudentScVo> selectStudent();

    Student selectStudentById(Integer Sno);

    @Select("SELECT S.*,Cno,Grade FROM student S,sc where S.Sno=sc.Sno")
    List<StudentScVo> selectStudentListPage(Page<StudentScVo> page);
}
