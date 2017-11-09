package com.hwj.xm.base;

import java.util.List;

import com.hwj.xm.base.BaseQuery;
import org.springframework.dao.DataAccessException;

/**
 * 
 * @author peter
 *
 */
public interface BaseDao<T> {
	
	/**
	 * 添加单条记录
	 * @param entity
	 * @return
	 */
	public Integer insert(T entity) throws DataAccessException;
	
	
	/**
	 * 批量添加
	 * @param entities
	 * @return
	 */
	public Integer insertBatch(List<T> entities) throws DataAccessException;
	
	/**
	 * 根据id 查询实体
	 * @param id
	 * @return
	 */
	public T queryById(Integer id) throws DataAccessException;
	
	
	/**
	 * 多条件查询
	 * @param baseQuery
	 * @return
	 */
	public List<T> queryForPage(BaseQuery baseQuery) throws DataAccessException;
	
	/**
	 * 更新实体记录
	 * @param entity
	 * @return
	 */
	public Integer update(T entity) throws DataAccessException;
	
	/**
	 * 批量更新
	 * @param entities
	 * @return
	 */
	public Integer updateBatch(List<T> entities) throws DataAccessException;
	
	
	/**
	 * 删除单条记录
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id) throws DataAccessException;
	
	/**
	 * 批量删除多条件记录
	 * @param ids
	 * @return
	 */
	public Integer deleteBatch(Integer[] ids) throws DataAccessException;
	
	

}
