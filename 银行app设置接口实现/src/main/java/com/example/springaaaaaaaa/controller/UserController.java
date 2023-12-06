package com.example.springaaaaaaaa.controller;

import com.aliyuncs.utils.StringUtils;
import com.example.springaaaaaaaa.pojo.Result;
import com.example.springaaaaaaaa.pojo.SMSUtils;
import com.example.springaaaaaaaa.pojo.User;
import com.example.springaaaaaaaa.pojo.ValidateCodeUtils;
import com.example.springaaaaaaaa.service.UserService;
import com.example.springaaaaaaaa.utils.JwtUtil;
import com.example.springaaaaaaaa.utils.Md5Util;
import com.example.springaaaaaaaa.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        /*Map<String,Object> map = JwtUtil.parseToken(token);
        String username= (String) map.get("username");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username= (String) map.get("username");
        User user= userService.findByUserName(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    public  Result updata(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();

    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params)
    {
        String oldPwd= params.get("old_pwd");
        String newPwd=params.get("new_pwd");
        String rePwd=params.get("re_pwd");
        if(StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(rePwd))
        {
            return Result.error("缺少必要的参数");
        }
        Map<String,Object> map= ThreadLocalUtil.get();
        String username=(String) map.get("username");
        User loginUser= userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd)))
        {
            return Result.error("原密码填写不正确");
        }
        if(!rePwd.equals(newPwd))
            return Result.error("两次天写的密码填写不一样");
        userService.updatePwd(newPwd);
        return  Result.success();

    }

}
