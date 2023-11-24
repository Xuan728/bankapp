package com.example.app.pojo;


public class 用户 {

    public 用户() {
    }

    public String 姓名;

    public String 身份证号;

    public String 密码;

    public String 联系方式;

    public 银行卡 银行卡;

    public String 交易情况;

    public String 学生信息;

    public String 理财情况;

    public String 贷款情况;

    public void 选择业务类型() {
        // TODO implement here
    }

    public void 修改个人信息() {
        // TODO implement here
    }

    public void 注册() {
        // TODO implement here
    }

    public void 登录() {
        // TODO implement here
    }

    public void 增加交易情况() {
        // TODO implement here
    }

    public void 查看交易情况() {
        // TODO implement here
    }

}

class 贷款 extends 用户 {

    public 贷款() {
    }

    public String 贷款产品总项;

    public int 贷款产品数量;

    public void 绑定账户() {
        // TODO implement here
    }

    public void 贷款() {
        // TODO implement here
    }

    public void 还款() {
        // TODO implement here
    }

    public void 额度查询() {
        // TODO implement here
    }

}

class 用户支付 extends 用户 {


    public 用户支付() {
    }

    public void 银行卡绑定() {
        // TODO implement here
    }

    public void 余额查询() {
        // TODO implement here
    }

    public void 银行卡支付() {
        // TODO implement here
    }

    public void Operation1() {
        // TODO implement here
    }

    public void Operation2() {
        // TODO implement here
    }

    public void 修改交易情况() {
        // TODO implement here
    }

    public void 银行卡变动查询() {
        // TODO implement here
    }

}

class 理财 extends 用户 {


    public 理财() {
    }

    public int 理财产品数量;


    public String 理财产品总项;

    public void 绑定账户() {
        // TODO implement here
    }

    public void 购买理财() {
        // TODO implement here
    }
    public void 提现() {
        // TODO implement here
    }

}

class 用户登录验证 extends 用户 {

    public 用户登录验证() {
    }

    public void 登录验证() {
        // TODO implement here
    }

    public void 注册记录() {
        // TODO implement here
    }

    public void 加密保护() {
        // TODO implement here
    }

}

class 转账 extends 用户 {

    public 转账() {
    }

    public 用户 转账用户;

    public 用户 被转账对象;

    public double 转账金额;

    public void 绑定账户() {
        // TODO implement here
    }
    public void 选择银行卡() {
        // TODO implement here
    }

}