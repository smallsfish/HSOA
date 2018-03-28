package com.hassdata.hserp.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description
 * @author  作者 WTF
 * @E-mail 13856890104@SINA.cn
 * @date 创建时间：2017年11月25日 下午3:49:02
 * @version 1.0
 * @since
 */

@Component("httpClientUtil")
public class HttpClientUtil {

    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;

    /**
     * post请求
     * @param url
     * @param obj
     * @return
     * @throws Exception
     */
    public String httpPostResponse(String url, Object obj) throws Exception{
        HttpPost post = new HttpPost(url);
        post.setConfig(this.requestConfig);
        ObjectMapper objMapper = new ObjectMapper();
        //将对象转为JSON格式(待测)(请求参数)
        String json = objMapper.writeValueAsString(obj);
        if (json != null) {
            //设置参数及格式
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            //参数entity放入post请求
            post.setEntity(entity);
        }
        //获取从接口响应的数据
        CloseableHttpResponse response = this.httpClient.execute(post);
        //response.getEntity()获取响应的实体
        String responseRes = EntityUtils.toString(response.getEntity());
        //关闭响应
        response.close();
        return responseRes;
    }

    /**
     * get请求
     * @param url
     * @return
     * @throws Exception
     */
    public String httpGetResponse(String url) throws Exception{
        HttpGet get = new HttpGet(url);
        get.setConfig(this.requestConfig);
        //将对象转为JSON格式(待测)
        //获取从接口响应的数据
        CloseableHttpResponse response = this.httpClient.execute(get);
        //
        String responseRes = EntityUtils.toString(response.getEntity(), "UTF-8");
        //关闭响应
        response.close();
        return responseRes;
    }

}
