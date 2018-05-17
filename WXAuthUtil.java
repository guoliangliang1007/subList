package com.zhongcaidita.test.utils;
/*@authour guoliangliang
 *
 *@date 2018/5/17 11:20
 * 微信授权登陆 网络请求
 */

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AuthUtil {

    public static  final String APPID = "wxb1331952259e7370";
    // APPSECRET ee877fcc2ea6d1d1317bae75adf3da0e
    public static  final String APPSECRET = "ee877fcc2ea6d1d1317bae75adf3da0e";
    public static JSONObject doGetJson(String url) throws IOException {


        JSONObject jsonObject = null;
        //初始化httpClient对象
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        //通过get方式提交
        HttpGet httpGet = new HttpGet(url);
        //发送请求
        HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
        //取出结果集
        HttpEntity entity = httpResponse.getEntity();
        if(entity!=null){
            //进行数据类型转换
            String result = EntityUtils.toString(entity, "UTF-8");
            //返回JSON格式的数据
            jsonObject =jsonObject.fromObject(result);

        }
        //关闭资源httpClient  链接
        defaultHttpClient.clearResponseInterceptors();
        return jsonObject;
    }

}
