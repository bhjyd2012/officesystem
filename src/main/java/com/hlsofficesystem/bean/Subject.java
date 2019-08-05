package com.hlsofficesystem.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hucheng
 * @since 2019-07-29
 */
@TableName("subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科目编号
     */
    @TableId(value = "subjectNumber", type = IdType.AUTO)
    private String subjectNumber;
    /**
     * 科目名称
     */
    @TableField("subjectName")
    private String subjectName;


    public String getSubjectNumber() {
        return subjectNumber;
    }

    public void setSubjectNumber(String subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject{" +
        ", subjectNumber=" + subjectNumber +
        ", subjectName=" + subjectName +
        "}";
    }
}
