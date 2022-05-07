# User
### 添加用户
#### 请求方式
> POST
#### 请求路径
> /user
#### 请求报文
```json
{
    "userName":"patrick2",
    "userPhone":"15966245914"
}
```
#### 返回报文
```json
{
    "code": 0,
    "message": "成功",
    "data": {
        "id": 7,
        "userGroup": 2,
        "userName": "patrick3",
        "userPhone": "15966245915"
    },
    "status": 100000,
    "page": 0,
    "size": 0,
    "total": 0
}
```
### 删除用户
#### 请求方式
> DELETE
#### 请求路径
> /user/7
#### 请求报文
```json

```
#### 返回报文
```json
{
    "code": 0,
    "message": "成功",
    "data": {
        "id": 6,
        "userGroup": 2,
        "userName": "patrick2",
        "userPhone": "15966245914"
    },
    "status": 100000,
    "page": 0,
    "size": 0,
    "total": 0
}
```
### 修改用户
#### 请求方式
> PATCH
#### 请求路径
> /user
#### 请求报文
```json
{
    "id": 9,
    "userPhone":"15866245917"
}
```
#### 返回报文
```json
{
  "code": 0,
  "message": "成功",
  "data": {
    "id": 9,
    "userGroup": 2,
    "userName": "patrick5",
    "userPhone": "15866245917"
  },
  "status": 100000,
  "page": 0,
  "size": 0,
  "total": 0
}
```
### 根据id查询用户
#### 请求方式
> GET
#### 请求路径
> /user/1
#### 请求报文
```json

```
#### 返回报文
```json
{
    "code": 0,
    "message": "成功",
    "data": {
        "id": 1,
        "userGroup": 0,
        "userName": "Dylan",
        "userPhone": "15966245906"
    },
    "status": 100000,
    "page": 0,
    "size": 0,
    "total": 0
}
```
### 分页查询用户
#### 请求方式
> GET
#### 请求路径
> /user?page=1&limit=10
#### 请求报文
```json

```
#### 返回报文
```json
{
  "code": 0,
  "message": "成功",
  "data": [
    {
      "id": 1,
      "userGroup": 0,
      "userName": "Dylan",
      "userPhone": "15966245906"
    },
    {
      "id": 2,
      "userGroup": 0,
      "userName": "lucifer",
      "userPhone": "12345678910"
    },
    {
      "id": 3,
      "userGroup": 0,
      "userName": "duke",
      "userPhone": "12345678911"
    }
  ],
  "status": 100000,
  "page": 1,
  "size": 3,
  "total": 8
}
```
### 登录
#### 请求方式
> POST
#### 请求路径
> /act/login
#### 请求报文
```json
{
    "userName": "Dylan",
    "userPassword": "19970413"
}
```
#### 返回报文
```json
{
    "code": 0,
    "message": "登陆成功",
    "data": {
        "id": 1,
        "userGroup": 0,
        "userName": "Dylan",
        "userPassword": "CBACCCEDFC9DD12051CFAC29A06015EF",
        "userPhone": "15966245906"
    },
    "status": 100000,
    "page": 0,
    "size": 0,
    "total": 0
}
```
### 登出
#### 请求方式
> GET
#### 请求路径
> /act/logout
#### 请求报文
```json

```
#### 返回报文
```json
{
    "code": 0,
    "message": "再见",
    "data": {
        "id": 1,
        "userGroup": 0,
        "userName": "Dylan",
        "userPhone": "15966245906"
    },
    "status": 100000,
    "page": 0,
    "size": 0,
    "total": 0
}
```
### 获取当前用户
#### 请求方式
> GET
#### 请求路径
> /act/who
#### 请求报文
```json

```
#### 返回报文
```json
{
    "code": 0,
    "message": "成功",
    "data": {
        "id": 1,
        "userGroup": 0,
        "userName": "Dylan",
        "userPhone": "15966245906"
    },
    "status": 100000,
    "page": 0,
    "size": 0,
    "total": 0
}
```