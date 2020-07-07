package com.hznu.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Sno", type = IdType.AUTO)
    private Integer Sno;

    @TableField("Sname")
    private String Sname;

    @TableField("Ssex")
    private String Ssex;

    @TableField("Sage")
    private Integer Sage;

    @TableField("Sdept")
    private String Sdept;

    public Integer getSno() {
        return Sno;
    }

    public void setSno(Integer Sno) {
        this.Sno = Sno;
    }
    public String getSname() {
        return Sname;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }
    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String Ssex) {
        this.Ssex = Ssex;
    }
    public Integer getSage() {
        return Sage;
    }

    public void setSage(Integer Sage) {
        this.Sage = Sage;
    }
    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String Sdept) {
        this.Sdept = Sdept;
    }

    @Override
    public String toString() {
        return "Student{" +
        "Sno=" + Sno +
        ", Sname=" + Sname +
        ", Ssex=" + Ssex +
        ", Sage=" + Sage +
        ", Sdept=" + Sdept +
        "}";
    }
}
