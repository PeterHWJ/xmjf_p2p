package com.hwj.xm.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hwj.xm.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	/**
	 * 添加单条记录
	 * @param entity
	 * @return
	 */
	public Integer insert(T entity) {
		return 	baseDao.insert(entity);
	}


	/**
	 * 批量添加
	 * @param entities
	 * @return
	 */
	public Integer insertBatch(List<T> entities){
		return baseDao.insertBatch(entities);
	}

	/**
	 * 根据id 查询实体
	 * @param id
	 * @return
	 */
	public T queryById(Integer id) {
		return baseDao.queryById(id);
	}


	/**
	 *   total
	 *   rows
	 * 多条件查询
	 * @param baseQuery
	 * @return
	 */
	public Map<String, Object> queryForPage(BaseQuery baseQuery) {
		PageHelper.startPage(baseQuery.getPage(),baseQuery.getRows());
		PageInfo<T>  pageInfo=new PageInfo<T>(baseDao.queryForPage(baseQuery));
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}

	/**
	 * 更新实体记录
	 * @param entity
	 * @return
	 */
	public Integer update(T entity) {
		return baseDao.update(entity);
	}

	/**
	 * 批量更新
	 * @param entities
	 * @return
	 */
	public Integer updateBatch(List<T> entities){
		return baseDao.updateBatch(entities);
	}


	/**
	 * 删除单条记录
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id){
		AssertUtil.isTrue(id==null||null==queryById(id), "待删除记录不存在!");
		return baseDao.delete(id);
	}

	/**
	 * 批量删除多条件记录
	 * @param ids
	 * @return
	 */
	public Integer deleteBatch(Integer[] ids) {
		return baseDao.deleteBatch(ids);
	}





}
