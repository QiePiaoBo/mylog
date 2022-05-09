# UserInfo

### 1. 添加角色

#### 请求方式

> POST

#### 请求路径

> /role

#### 请求报文

```json
{
  "roleCode": "r2",
  "roleName": "common",
  "pid": 1,
  "roleDescription": "common user",
  "roleStatus": 1,
  "roleSort": 0
}
```

#### 返回报文

```json
{
  "status": 100000,
  "message": "成功",
  "data": {
    "id": 2,
    "roleCode": "r2",
    "roleName": "common",
    "pid": 1,
    "roleDescription": "common user",
    "roleStatus": 1,
    "roleSort": 0
  }
}
```

### 2. 分页查询角色

#### 请求方式

> GET

#### 请求路径

> /role?page=1&limit=3

#### 请求报文

```json

```

#### 返回报文

```json
{
    "status": 100000,
    "message": "成功",
    "data": [
        {
            "id": 1,
            "roleCode": "r1",
            "roleName": "admin",
            "pid": 0,
            "roleDescription": "role of admin",
            "roleStatus": 1,
            "roleSort": 1
        },
        {
            "id": 2,
            "roleCode": "r2",
            "roleName": "common",
            "pid": 1,
            "roleDescription": "common user",
            "roleStatus": 1,
            "roleSort": 0
        }
    ],
    "page": 1,
    "size": 3,
    "total": 2
}
```

### asd

#### 请求方式

>

#### 请求路径

>

#### 请求报文

```json

```

#### 返回报文

```json

```

### asd

#### 请求方式

>

#### 请求路径

>

#### 请求报文

```json

```

#### 返回报文

```json

```

### asd

#### 请求方式

>

#### 请求路径

>

#### 请求报文

```json

```

#### 返回报文

```json

```

### asd

#### 请求方式

>

#### 请求路径

>

#### 请求报文

```json

```

#### 返回报文

```json

```

### asd

#### 请求方式

>

#### 请求路径

>

#### 请求报文

```json

```

#### 返回报文

```json

```

### asd

#### 请求方式

>

#### 请求路径

>

#### 请求报文

```json

```

#### 返回报文

```json

```

### asd

#### 请求方式

>

#### 请求路径

>

#### 请求报文

```json

```

#### 返回报文

```json

```