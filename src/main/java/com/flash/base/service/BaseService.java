package com.flash.base.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flash.commons.json.JsonHelper;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseService<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
	/**
	 * 抽象的获取mapper方法，必须实现
	 * @return
	 */
	protected abstract Mapper<T> getMapper();
	
	/**
	 * 保存方法
	 * @param data
	 * @return
	 */
	protected T save(T data) {
		LOGGER.debug("插入一条数据开始,数据序列化为{}", JsonHelper.transObjToJsonString(data));
		
		long start = System.currentTimeMillis();
		int selective = this.getMapper().insertSelective(data);
		long end = System.currentTimeMillis();
		if (selective < 1) {
			LOGGER.debug("插入数据失败,原因未知,耗时{}ms", end - start);
		} else {
			LOGGER.debug("插入数据到数据库成功,耗时{}ms", end - start);
		}
		return data;
	}
	
	/**
	 * 删除一条数据
	 * @param pk
	 * @return
	 */
	protected int deleteByPk(Object pk) {
		LOGGER.debug("删除一条数据开始，删除数据id为{}",pk);
		long start = System.currentTimeMillis();
		int delete = this.getMapper().deleteByPrimaryKey(pk);
		long end = System.currentTimeMillis();
		if (delete < 1) {
			LOGGER.debug("删除数据失败,原因未知,耗时{}ms", end - start);
		} else {
			LOGGER.debug("删除数据到成功,耗时{}ms", end - start);
		}
		return delete;
	}
	
	/**
	 * 修改一条数据
	 * @param data
	 * @return
	 */
	public T update(T data){
		LOGGER.debug("更新一条数据开始,数据序列化为{}", JsonHelper.transObjToJsonString(data));
		long start = System.currentTimeMillis();
		int update = this.getMapper().updateByPrimaryKey(data);
		long end = System.currentTimeMillis();
		if (update < 1) {
			LOGGER.debug("更新数据失败,原因未知,耗时{}ms", end - start);
		} else {
			LOGGER.debug("更新数据到数据库成功,耗时{}ms", end - start);
		}
		return data;
	}
	
	/**
	 * 根据主键查询数据
	 * @param pk
	 * @return
	 */
	public T selectByPk(Object pk){
		LOGGER.debug("查询主键为{}的信息开始", pk);
		long start = System.currentTimeMillis();
		T data = this.getMapper().selectByPrimaryKey(pk);
		long end = System.currentTimeMillis();
		if (null == data) {
			LOGGER.debug("查询pk为{}的数据成功,该数据不存在,耗时{}ms", pk, end - start);
		} else {
			LOGGER.debug("查询pk为{}的数据成功,耗时{}ms", pk, end - start);
		}
		return data;
	}
	
	
	
	
	
	
	
	
	
}
