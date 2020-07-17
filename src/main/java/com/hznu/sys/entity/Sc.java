package com.hznu.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LinMing
 * @since 2020-07-03
 */
public class Sc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Sno")
    private Integer Sno;

    @TableField("Cno")
    private Integer Cno;

    @TableField("Grade")
    private Integer Grade;

    public Integer getSno() {
        return Sno;
    }

    public void setSno(Integer Sno) {
        this.Sno = Sno;
    }
    public Integer getCno() {
        return Cno;
    }

    public void setCno(Integer Cno) {
        this.Cno = Cno;
    }
    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer Grade) {
        this.Grade = Grade;
    }

    @Override
    public String toString() {
        return "Sc{" +
        "Sno=" + Sno +
        ", Cno=" + Cno +
        ", Grade=" + Grade +
        "}";
    }
}
