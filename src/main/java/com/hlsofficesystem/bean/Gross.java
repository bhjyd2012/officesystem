package com.hlsofficesystem.bean;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
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
@TableName("gross")
public class Gross implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 经理
     */
    private String manager;
    /**
     * 督导
     */
    private String supervisor;
    /**
     * 餐厅编号
     */
    @TableField("restaurantNum")
    private String restaurantNum;
    /**
     * 餐厅名称
     */
    @TableField("restaurantName")
    private String restaurantName;
    /**
     * 日期
     */
    private Date date;
    /**
     * 收入类型
     */
    @TableField("incomeType")
    private String incomeType;
    /**
     * TC
     */
    private BigDecimal tc;
    /**
     * 营业额
     */
    private BigDecimal turnover;
    /**
     * 物料成本
     */
    @TableField("materialCost")
    private BigDecimal materialCost;
    /**
     * 毛利
     */
    @TableField("grossProfit")
    private BigDecimal grossProfit;
    /**
     * 毛利率
     */
    @TableField("grossRate")
    private BigDecimal grossRate;
    /**
     * 上周同比营业额
     */
    private BigDecimal lwturnover;
    /**
     * 上周同比毛利
     */
    @TableField("lwgrossProfit")
    private BigDecimal lwgrossProfit;
    /**
     * 上周同比毛利率
     */
    @TableField("lwgrossRate")
    private BigDecimal lwgrossRate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getRestaurantNum() {
        return restaurantNum;
    }

    public void setRestaurantNum(String restaurantNum) {
        this.restaurantNum = restaurantNum;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getTc() {
        return tc;
    }

    public void setTc(BigDecimal tc) {
        this.tc = tc;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    public BigDecimal getGrossRate() {
        return grossRate;
    }

    public void setGrossRate(BigDecimal grossRate) {
        this.grossRate = grossRate;
    }

    public BigDecimal getLwturnover() {
        return lwturnover;
    }

    public void setLwturnover(BigDecimal lwturnover) {
        this.lwturnover = lwturnover;
    }

    public BigDecimal getLwgrossProfit() {
        return lwgrossProfit;
    }

    public void setLwgrossProfit(BigDecimal lwgrossProfit) {
        this.lwgrossProfit = lwgrossProfit;
    }

    public BigDecimal getLwgrossRate() {
        return lwgrossRate;
    }

    public void setLwgrossRate(BigDecimal lwgrossRate) {
        this.lwgrossRate = lwgrossRate;
    }

    @Override
    public String toString() {
        return "Gross{" +
        ", id=" + id +
        ", manager=" + manager +
        ", supervisor=" + supervisor +
        ", restaurantNum=" + restaurantNum +
        ", restaurantName=" + restaurantName +
        ", date=" + date +
        ", incomeType=" + incomeType +
        ", tc=" + tc +
        ", turnover=" + turnover +
        ", materialCost=" + materialCost +
        ", grossProfit=" + grossProfit +
        ", grossRate=" + grossRate +
        ", lwturnover=" + lwturnover +
        ", lwgrossProfit=" + lwgrossProfit +
        ", lwgrossRate=" + lwgrossRate +
        "}";
    }
}
