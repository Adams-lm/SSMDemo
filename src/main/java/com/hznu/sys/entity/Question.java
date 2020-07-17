package com.hznu.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lin
 * @since 2020-07-17
 */
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private Integer limitTime;

    private Integer limitMemory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }
    public Integer getLimitMemory() {
        return limitMemory;
    }

    public void setLimitMemory(Integer limitMemory) {
        this.limitMemory = limitMemory;
    }

    @Override
    public String toString() {
        return "Question{" +
        "id=" + id +
        ", title=" + title +
        ", limitTime=" + limitTime +
        ", limitMemory=" + limitMemory +
        "}";
    }
}
