package com.hznu.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class StudentScVo {

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

    @TableField("Cno")
    private Integer Cno;

    @TableField("Grade")
    private Integer Grade;

    public Integer getSno() {
        return Sno;
    }

    public void setSno(Integer sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public Integer getSage() {
        return Sage;
    }

    public void setSage(Integer sage) {
        Sage = sage;
    }

    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String sdept) {
        Sdept = sdept;
    }

    public Integer getCno() {
        return Cno;
    }

    public void setCno(Integer cno) {
        Cno = cno;
    }

    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer grade) {
        Grade = grade;
    }

    @Override
    public String toString() {
        return "StudentScVo{" +
                "Sno=" + Sno +
                ", Sname='" + Sname + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", Sage=" + Sage +
                ", Sdept='" + Sdept + '\'' +
                ", Cno=" + Cno +
                ", Grade=" + Grade +
                '}';
    }
}
