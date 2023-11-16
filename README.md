# bankapp
## 后端：

使用springboot框架实现后端接口，app文件夹包含了此项目的后端代码。

目前后端实现了注册账号，用户登录，获取用户数据接口

###### 注册接口：

可以将用户名及密码存储到数据库，密码用md5方式加密。若用户名已存在则返回响应数据

###### 登录接口：

用户能够输入账号密码进行登录，用户名未查询到会响应"用户名错误"，若用户存在，密码错误会响应"密码错误"，密码正确则登陆成功，返回jwt令牌码（此令牌码后续每次访问接口都需在请求头中携带，否则无权限访问除登录和注册外的其他页面）

获取用户数据接口：

###### 获取用户数据接口：

请求头中需携带jwt令牌码

#### 前端请求参数说明：

##### 注册接口：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| username | 用户名 | string | 是       | 5~16位非空字符 |
| password | 密码   | string | 是       | 5~16位非空字符 |

请求数据样例：

```shell
username=zhangsan&password=123456
```

| 名称    | 类型   | 是否必须 |      | 备注                  | 其他信息 |
| ------- | ------ | -------- | ---- | --------------------- | -------- |
| code    | number | 必须     |      | 响应码, 0-成功,1-失败 |          |
| message | string | 非必须   |      | 提示信息              |          |
| data    | object | 非必须   |      | 返回的数据            |          |

响应数据样例：

```json
{
    "code": 0,
    "message": "操作成功",
    "data": null
}
```

##### 登录接口

##### 1.2.1 基本信息

> 请求路径：/user/login
>
> 请求方式：POST
>
> 接口描述：该接口用于登录

##### 1.2.2 请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| username | 用户名 | string | 是       | 5~16位非空字符 |
| password | 密码   | string | 是       | 5~16位非空字符 |

请求数据样例：

```shell
username=zhangsan&password=123456
```

##### 1.2.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------- | ------ | -------- | ------ | --------------------- | -------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |          |
| message | string | 非必须   |        | 提示信息              |          |
| data    | string | 必须     |        | 返回的数据,jwt令牌    |          |

响应数据样例：

```json
{
    "code": 0,
    "message": "操作成功",
    "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjUsInVzZXJuYW1lIjoid2FuZ2JhIn0sImV4cCI6MTY5MzcxNTk3OH0.pE_RATcoF7Nm9KEp9eC3CzcBbKWAFOL0IsuMNjnZ95M"
}
```

