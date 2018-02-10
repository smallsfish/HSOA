package com.hassdata.hserp.base;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
    /**
     * 通过ID查询
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 查询单条记录
     * @return
     */
    T getOne(T t);

    /**
     * 查询记录集合
     * @return
     */
    List<?> list(T t, String orderBy);

    /**
     * 分页查询
     * @return
     */
    List<T> listPage(T t, String orderBy, Integer fromIndex, Integer pageSize);

    /**
     * 模糊分页查询
     * @return
     */
    List<T> listLikePage(T t, String orderBy, Integer fromIndex, Integer pageSize);

    /**
     * 通用的保存方法
     */
    void save(T t);

    /**
     * 批量保存
     * @param list
     */
    int batchSave(List<T> list);

    /**
     * 通用的修改方法
     */
    int update(T t);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int batchUpdate(List<T> list);

    /**
     * 删除方法
     * @param id
     */
    int delById(Serializable id);

    /**
     * 批量删除
     * @param list
     * @return
     */
    int delList(List<?> list);

    /**
     * 批量删除方法
     * @param ids
     */
    int delArray(int[] ids);

    /**
     * 统计查询
     * @return 总记录条数
     */
    Long count(T t);

    /**
     * 模糊统计查询
     * @return 总记录条数
     */
    Long countLike(T t);
}
