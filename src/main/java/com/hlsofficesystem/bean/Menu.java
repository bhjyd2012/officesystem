package com.hlsofficesystem.bean;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author hucheng
 * @since 2019-08-06
 */
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menuid", type = IdType.AUTO)
    private Integer menuid;
    private String menuname;
    private Integer upmenuid;
    private String menupath;
    private Integer menustate;
    private String menuremark;
    
    private List<Role> roles;

    private List<Menu> seconds;//二级菜单集合
    
    

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Menu> getSeconds() {
		return seconds;
	}

	public void setSeconds(List<Menu> seconds) {
		this.seconds = seconds;
	}

	public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Integer getUpmenuid() {
        return upmenuid;
    }

    public void setUpmenuid(Integer upmenuid) {
        this.upmenuid = upmenuid;
    }

    public String getMenupath() {
        return menupath;
    }

    public void setMenupath(String menupath) {
        this.menupath = menupath;
    }

    public Integer getMenustate() {
        return menustate;
    }

    public void setMenustate(Integer menustate) {
        this.menustate = menustate;
    }

    public String getMenuremark() {
        return menuremark;
    }

    public void setMenuremark(String menuremark) {
        this.menuremark = menuremark;
    }

    @Override
    public String toString() {
        return "Menu{" +
        ", menuid=" + menuid +
        ", menuname=" + menuname +
        ", upmenuid=" + upmenuid +
        ", menupath=" + menupath +
        ", menustate=" + menustate +
        ", menuremark=" + menuremark +
        "}";
    }
}
