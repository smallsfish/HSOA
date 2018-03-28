package com.hassdata.hserp.base;

import org.apache.ibatis.session.SqlSessionFactory;
import javax.annotation.Resource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Resource
    protected SqlSessionFactory sqlSessionFactory;

    public abstract BaseDao<T> getMapper();

    @Override
    public T getById(Serializable id) {
        return this.getMapper().getById(id);
    }

    @Override
    public T getOne(T t) {
        return this.getMapper().getOne(t);
    }

    @Override
    public List<T> list(T t,String orderBy) {
        Map<String, Object> map = buildParams(t, orderBy, null, null);
        return this.getMapper().list(map);
    }

    @Override
    public List<T> listPage(T t,String orderBy, Integer fromIndex, Integer pageSize) {
        Map<String, Object> map = buildParams(t, orderBy, fromIndex, pageSize);
        return this.getMapper().listPage(map);
    }


    @Override
    public List<T> listLikePage(T t,String orderBy, Integer fromIndex, Integer pageSize) {
        Map<String, Object> map = buildParams(t, orderBy, fromIndex, pageSize);
        return this.getMapper().listLikePage(map);
    }

    @Override
    public void save(T t) {
        this.getMapper().save(t);
    }

    @Override
    public int batchSave(List<T> list) {
        return this.getMapper().batchSave(list);
    }

    @Override
    public int update(T t) {
        return this.getMapper().update(t);
    }

    @Override
    public int batchUpdate(List<T> list) {
        return this.getMapper().batchUpdate(list);
    }

    @Override
    public int delById(Serializable id) {
        return this.getMapper().delById(id);
    }

    @Override
    public int delList(List<T> list) {
        return this.getMapper().delList(list);
    }

    @Override
    public int delArray(int[] ids) {
        return this.getMapper().delArray(ids);
    }

    @Override
    public Long count(T t) {
        return this.getMapper().count(t);
    }

    @Override
    public Long countLike(T t) {
        return this.getMapper().countLike(t);
    }



    protected Map<String, Object> buildDefaultParamsMap(T params) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(params == null) {
            return map;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(params.getClass(), Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor pd : pds) {
                map.put(pd.getName(), pd.getReadMethod().invoke(params));
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Map<String, Object> buildParams(T params, String orderBy, Integer fromIndex, Integer pageSize) {
        Map<String, Object> map = buildDefaultParamsMap(params);
        if(orderBy != null) {
            map.put("orderBy", orderBy);
        }
        if(fromIndex != null && pageSize != null) {
            map.put("fromIndex", (fromIndex-1)*pageSize);
            map.put("pageSize", pageSize);
            map.put("lastIndex", fromIndex + pageSize);
        }
        return map;
    }
}
