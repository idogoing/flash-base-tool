package com.flash.base.tool.query;

import java.util.ArrayList;
import java.util.List;

import com.flash.commons.date.DateUtils;

/**
 * 基本查询,此类封装了进行查询的时候，页面传入的当前页码和每页显示数量
 * 
 */
public abstract class BaseQuery {

	public static final Integer NULL_PAGE = 0;
	public static final Integer PAGE_NUM_DEFAULT_START = 1;
	public static final Integer PAGE_SIZE_DEFAULT = 20;

	private Integer pageStart;
	private Integer pageNum;
	private Integer pageSize;
	private Long createTimeStart; //记录创建开始时间 通过prepareTimeBlock()方法
	private Long createTimeEnd; //创建创建结束时间 通过prepareTimeBlock()方法
	private boolean pagination = false;// 是否分页

	private List<String> orderByDESC = new ArrayList<String>();
	private List<String> orderByASC = new ArrayList<String>();

	public void addDesc(String field) {
		this.orderByDESC.add(field);
	}

	public void addAsc(String field) {
		this.orderByASC.add(field);
	}

	public List<String> getOrderByDESC() {
		return orderByDESC;
	}

	public List<String> getOrderByASC() {
		return orderByASC;
	}

	/**
	 * 准备分页参数，此方法会准备pageNum pageSize pageStart
	 * @author lonaking
	 */
	public void preparePageInfo() {
		if (this.pagination) {
			if (this.pageNum == null || this.pageNum == 0) {
				this.pageNum = PAGE_NUM_DEFAULT_START;
			}
			if (this.pageSize == null && this.pageSize != NULL_PAGE) {
				this.pageSize = PAGE_SIZE_DEFAULT;
			}
			//启示页
			this.pageStart = Math.max((this.pageNum - 1) * pageSize, 1);
		}else{
			//不分页的
			this.pageSize = NULL_PAGE;
			this.pageNum = PAGE_NUM_DEFAULT_START;
		}
	}

	/**
	 * 某天到几天后
	 * @author lonaking
	 * @param createTime
	 * @param dayBlcok
	 */
	public void prepareTimeBlock(Long createTime, Integer dayBlcok) {
		this.createTimeStart = DateUtils.getStart(createTime);
		this.createTimeEnd = DateUtils.getTodayAfterDays(dayBlcok).getTime();
	}

	/**
	 * 某天到某天
	 * @author lonaking
	 * @param createTimeStart
	 * @param createTimeEnd
	 */
	public void prepareTimeBlock(Long createTimeStart, Long createTimeEnd) {
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
	}

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public Long getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Long createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Long getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public void setOrderByDESC(List<String> orderByDESC) {
		this.orderByDESC = orderByDESC;
	}

	public void setOrderByASC(List<String> orderByASC) {
		this.orderByASC = orderByASC;
	}

}
