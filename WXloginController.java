package com.zhongcaidita.test.controller;
/*@authour guoliangliang
 *
 *@date 2018/5/17 14:26
 */

import com.zhongcaidita.test.utils.AuthUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("wx")
public class WXloginController {

   //入口文件
    @RequestMapping("/login.action")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String backUrl = "http://公网必须可以访问";
        //用户同意授权，获取code
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid="+AuthUtil.APPID +
                //回调地址(必须公网可以访问),请使用 urlEncode 对链接进行处理
                "&redirect_uri="+URLEncoder.encode(backUrl)+
                "&response_type=code" +
                //snsapi_base(不弹出授权界面)  snsapi_userinfo(弹出授权界面)
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        //重定向
        response.sendRedirect(backUrl);
    }

   //获取code
    @RequestMapping("http://公网必须可以访问.action")
    public String getCode(Model model , HttpServletRequest request, HttpServletResponse response) throws IOException {
        //code
        String  code  = request.getParameter("code");
        String  url ="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+AuthUtil.APPID +
                "&secret="+AuthUtil.APPSECRET +
                "&code="+code +
                "&grant_type=authorization_code";
        JSONObject doGetJson = AuthUtil.doGetJson(url);
        //获取 access_token openid
        String openid = doGetJson.getString("openid");
        String access_token = doGetJson.getString("access_token");
        //拉取用户信息(需scope为 snsapi_userinfo)
        String userInfo = " https://api.weixin.qq.com/sns/userinfo?" +
                "access_token="+access_token +
                "&openid="+openid +
                "&lang=zh_CN";
        //进行网络请求
        JSONObject jsonObject = AuthUtil.doGetJson(userInfo);
        //可以获取到用户的账号、头像、所在地等
        System.out.println("拉取到的基本信息："+jsonObject);
        model.addAttribute("info",jsonObject);
        return "去信息展示界面"; //去信息展示界面
    }
}
