package com.example.app.controller;

import com.example.app.pojo.Result;
import com.example.app.pojo.User;
import com.example.app.service.UserService;
import com.example.app.utils.JwtUtil;
import com.example.app.utils.Md5Util;
import com.example.app.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired  //注解，常用于一个类在另一个类中使用时，自动装配
    private UserService userService; //定义用户服务接口实例

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @CrossOrigin(origins = "http://localhost:8080")  // 允许特定源的跨域请求
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){

        User u = userService.findByUserName(username);
            if(u == null){
                //是新用户，执行注册
                userService.register(username,password);
                return Result.success();
            }else{
                //用户已存在
                return Result.error("用户名已被占用");
            }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){

        User loginuser = userService.findByUserName(username);
        if(loginuser == null){
            return Result.error("用户名错误");
        }

        if(Md5Util.getMD5String(password).equals(loginuser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginuser.getId());
            claims.put("username",loginuser.getUsername());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);

            return Result.success(token);
        }
        return Result.error("密码错误");

    }

    @GetMapping ("/userInfo")
    public Result<User> userInfo(/*@RequestHeader("Authorization") String token*/){
        //根据用户名查询用户
//        Map<String,Object> map = JwtUtil.parseToken(token);
//        String username = (String)map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){

        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvater(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        //校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User loginUser = userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码不正确");
        }
        if(!newPwd.equals(rePwd))
        {
            return Result.error("两次填写的新密码不一样");
        }
        userService.updatePwd(newPwd);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }
}
