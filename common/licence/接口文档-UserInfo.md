# UserInfo
### 1. 根据userId查询用户信息
#### 请求方式
> GET
#### 请求路径
> /userinfo/get-by-userid?userId=1
#### 请求报文
```json

```
#### 返回报文
```json
{
    "code": 0,
    "message": "成功",
    "data": {
        "userId": 1,
        "realName": "段其伦1",
        "gender": 0,
        "wechatCode": "duan1637",
        "mail": "15966245906@163.com"
    },
    "status": 100000,
    "page": 0,
    "size": 0,
    "total": 0
}
```

### 2. 分页获取用户信息
#### 请求方式
> GET
#### 请求路径
> /userinfo?page=1&limit=3
#### 请求报文
```json
{
    "code": 0,
    "message": "成功",
    "data": [
        {
          "id": 1,
          "userId": 1,
          "realName": "段其伦2",
          "gender": 0,
          "wechatCode": "duan1637",
          "mail": "15966245906@163.com"
        }
    ],
    "status": 100000,
    "page": 1,
    "size": 1,
    "total": 7
}
```
#### 返回报文
```json

```

### 3. 根据id修改用户信息
#### 请求方式
> PATCH
#### 请求路径
> /userinfo
#### 请求报文
```json
{
    "id": 1,
    "realName": "段其伦2"
}
```
#### 返回报文
```json
{
    "code": 0,
    "message": "成功",
    "data": {
        "userId": 1,
        "realName": "段其伦2",
        "gender": 0,
        "wechatCode": "duan1637",
        "mail": "15966245906@163.com"
    },
    "status": 100000,
    "page": 0,
    "size": 0,
    "total": 0
}
```