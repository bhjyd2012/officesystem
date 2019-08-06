package com.hlsofficesystem.bean;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2019-08-06
 */
@TableName("middle")
public class Middle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "middleid", type = IdType.AUTO)
    private Integer middleid;
    private Integer menuid;
    private Integer roleid;


    public Integer getMiddleid() {
        return middleid;
    }

    public void setMiddleid(Integer middleid) {
        this.middleid = middleid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "Middle{" +
        ", middleid=" + middleid +
        ", menuid=" + menuid +
        ", roleid=" + roleid +
        "}";
    }
}
