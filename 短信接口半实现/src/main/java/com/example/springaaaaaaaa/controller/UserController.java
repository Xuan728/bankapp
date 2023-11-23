package com.example.springaaaaaaaa.controller;

import com.aliyuncs.utils.StringUtils;
import com.example.springaaaaaaaa.pojo.Result;
import com.example.springaaaaaaaa.pojo.SMSUtils;
import com.example.springaaaaaaaa.pojo.User;
import com.example.springaaaaaaaa.pojo.ValidateCodeUtils;
import com.example.springaaaaaaaa.service.UserService;
import com.example.springaaaaaaaa.utils.JwtUtil;
import com.example.springaaaaaaaa.utils.Md5Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: UserController
 * Package: com.example.springaaaaaaaa.controller
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        User u = userService.findByUserName(username);
        if (u == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }

    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password)
    {
        User loginUser=userService.findByUserName(username);
        if(loginUser==null){
            return Result.error("用户名错误");

        }
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword()))
        {
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token= JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");


    }
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user, HttpSession session){
//      1.获取手机号
        String phone = user.getEmail();

        if(StringUtils.isEmpty(phone)){
            return  Result.error("短信发送失败");
        }
//      2.随机生成四位验证码
        String code = ValidateCodeUtils.generateValidateCode(4).toString();

//      3.调用阿里云提供的短信服务
        SMSUtils.sendMessage("张靖奇","",phone,code);

//      4.需要将生成的验证码保存到session中
        session.setAttribute(phone,code);

        return  Result.success("验证码短信发送成功");
    }
}
