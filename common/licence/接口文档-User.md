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

```