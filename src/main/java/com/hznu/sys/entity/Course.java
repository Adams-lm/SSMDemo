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
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Cno", type = IdType.AUTO)
    private Integer Cno;

    @TableField("Cname")
    private String Cname;

    @TableField("Cpno")
    private Integer Cpno;

    @TableField("Ccredit")
    private Integer Ccredit;

    public Integer getCno() {
        return Cno;
    }

    public void setCno(Integer Cno) {
        this.Cno = Cno;
    }
    public String getCname() {
        return Cname;
    }

    public void setCname(String Cname) {
        this.Cname = Cname;
    }
    public Integer getCpno() {
        return Cpno;
    }

    public void setCpno(Integer Cpno) {
        this.Cpno = Cpno;
    }
    public Integer getCcredit() {
        return Ccredit;
    }

    public void setCcredit(Integer Ccredit) {
        this.Ccredit = Ccredit;
    }

    @Override
    public String toString() {
        return "Course{" +
        "Cno=" + Cno +
        ", Cname=" + Cname +
        ", Cpno=" + Cpno +
        ", Ccredit=" + Ccredit +
        "}";
    }
}
