package com.example.springaaaaaaaa.pojo;

/**
 * ClassName: SMSUtils
 * Package: com.example.springaaaaaaaa.pojo
 */

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 短信发送工具类
 */
public class SMSUtils {

    /**
     * 发送短信
     * @param signName 签名
     * @param templateCode 模板
     * @param phoneNumbers 手机号
     * @param param 参数
     */
    public static void sendMessage(String signName, String templateCode,String phoneNumbers,String param){
        DefaultProfile profile = DefaultProfile.getProfile("lhp", " LTAI5tQabB7PyhtBdVm3vTaf", "vbYjRCRN7epA25lSlp3P4VFwHmxt3Z");

        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();

        request.setSysRegionId("cn-hangzhou");
//	    要发送给那个人的电话号码
        request.setPhoneNumbers(phoneNumbers);
//      我们在阿里云设置的签名
        request.setSignName(signName);
//	    我们在阿里云设置的模板
        request.setTemplateCode(templateCode);
//	    在设置模板的时候有一个占位符
        request.setTemplateParam("{\"code\":\""+param+"\"}");

//		request.setPhoneNumbers("1368846****");//接收短信的手机号码
//		request.setSignName("阿里云");//短信签名名称
//		request.setTemplateCode("SMS_20933****");//短信模板CODE
//		request.setTemplateParam("张三");//短信模板变量对应的实际值

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println("短信发送成功");
        }catch (ClientException e) {
            e.printStackTrace();
        }
    }

}