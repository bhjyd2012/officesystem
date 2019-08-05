package com.hlsofficesystem.bean;

import java.math.BigDecimal;
import java.util.Date;
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
 * @since 2019-07-25
 */
@TableName("regionalprofit")
public class Regionalprofit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 门店编号
     */
    @TableField("StoreNumber")
    private String StoreNumber;
    /**
     * 门店名称
     */
    @TableField("StoreName")
    private String StoreName;
    /**
     * 门店科目
     */
    @TableField("StoreSubjects")
    private String StoreSubjects;
    /**
     * 去年统计
     */
    @TableField("StoreLastYearStatistics")
    private BigDecimal StoreLastYearStatistics;
    /**
     * 当年统计
     */
    @TableField("StoreTheYearStatistics")
    private BigDecimal StoreTheYearStatistics;
    /**
     * 统计日期
     */
    @TableField("StoreTheYearStatisticsDate")
    private Date StoreTheYearStatisticsDate;
    
    @TableField(exist=false)
    private Subject subject;//科目对象
    
    
    public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreNumber() {
        return StoreNumber;
    }

    public void setStoreNumber(String StoreNumber) {
        this.StoreNumber = StoreNumber;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }

    public String getStoreSubjects() {
        return StoreSubjects;
    }

    public void setStoreSubjects(String StoreSubjects) {
        this.StoreSubjects = StoreSubjects;
    }

    public BigDecimal getStoreLastYearStatistics() {
        return StoreLastYearStatistics;
    }

    public void setStoreLastYearStatistics(BigDecimal StoreLastYearStatistics) {
        this.StoreLastYearStatistics = StoreLastYearStatistics;
    }

    public BigDecimal getStoreTheYearStatistics() {
        return StoreTheYearStatistics;
    }

    public void setStoreTheYearStatistics(BigDecimal StoreTheYearStatistics) {
        this.StoreTheYearStatistics = StoreTheYearStatistics;
    }

    public Date getStoreTheYearStatisticsDate() {
        return StoreTheYearStatisticsDate;
    }

    public void setStoreTheYearStatisticsDate(Date StoreTheYearStatisticsDate) {
        this.StoreTheYearStatisticsDate = StoreTheYearStatisticsDate;
    }

    @Override
	public String toString() {
		return "Regionalprofit [id=" + id + ", StoreNumber=" + StoreNumber + ", StoreName=" + StoreName
				+ ", StoreSubjects=" + StoreSubjects + ", StoreLastYearStatistics=" + StoreLastYearStatistics
				+ ", StoreTheYearStatistics=" + StoreTheYearStatistics + ", StoreTheYearStatisticsDate="
				+ StoreTheYearStatisticsDate + ", subject=" + subject + "]";
	}
    
}
