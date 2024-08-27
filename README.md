E-commerce Backend System
=

Tech Stack
-
- Spring boot
- Vue3.js
- MySQL
- GCP
- Redis

API
-
### 1. User API
#### 1.1 Register
##### 1.1.1 Information

> Router: /users/register  
> Method: POST  
> Description: 註冊使用者帳號

- No require authentication

##### 1.1.2 Request
Parameter form: application/json


| Key name | Type   | Required | Remark             |
| -------- |:------ |:--------:|:------------------ |
| username | string |   true   | 5-16 nonempty char |
| password | string |   true   | 5-16 nonempty char |

Example:
```json
{
  "username": "user123",
  "password": "password123"
}
```

##### 1.1.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
| -------- |:------:|:--------:|:------ |
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```
---
#### 1.2 Login
##### 1.2.1 Information
> Router: /users/login  
> Method: POST  
> Description: 使用者登入  

- No require authentication

##### 1.2.2 Request
Parameter form: application/json


| Key name | Type   | Required | Remark             |
| -------- |:------ |:--------:|:------------------ |
| username | string |   true   | 5-16 nonempty char |
| password | string |   true   | 5-16 nonempty char |

Example:
```json
{
  "username": "user123",
  "password": "password123"
}
```

##### 1.2.3 Response

Data form: application/json

| Key name |  Type  | Required | Remark           |
| -------- |:------:|:--------:|:---------------- |
| status   | number |   true   |                  |
| message  | string |  false   |                  |
| data     | string |   true   | return JWT token |

Example:
```json!
{
  "status": 200,
  "message": "success",
  "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```
---
#### 1.3 Get User By Id
##### 1.3.1 Information
> Router: /users/{id}  
> Method: GET  
> Description: 取得該id的使用者資訊 

##### 1.3.2 Request
Parameter form: path variable


| Key name | Type   | Required | Remark |
| -------- |:------ |:--------:|:------ |
| id       | number |   true   |        |

Example:
```
/users/1
```
##### 1.3.3 Response
Data form: application/json

| Key name       |  Type  | Required | Remark             |
|:-------------- |:------:|:--------:|:------------------ |
| status         | number |   true   |                    |
| message        | string |  false   |                    |
| data           | object |   true   | return user object |
| \|-id          | number |  false   |                    |
| \|-username    | string |  false   |                    |
| \|-nickname    | string |  false   |                    |
| \|-email       | string |  false   |                    |
| \|-userImg     | string |  false   |                    |
| \|-createdTime | string |  false   |                    |
| \|-updatedTime | string |  false   |                    |

Example:
```json!
{
  "status": 200,
  "message": "success",
  "data": 
    "id": 1,
    "username": "user123",
    "nickname": "",
    "email": "",
    "userImg": "",
    "createdTime": "2024-08-18 22:21:31",
    "updatedTime": "2024-08-18 22:21:31"
}
```
---
#### 1.4 Update User Information
##### 1.4.1 Information
> Router: /users/{id}  
> Method: PUT  
> Description: 更新該id的使用者資訊(除頭像和密碼)

##### 1.4.2 Request
Parameter form: application/json


| Key name | Type   | Required | Remark             |
|:-------- |:------ |:--------:|:------------------ |
| id       | number |   true   |                    |
| username | string |  false   | 5-16 nonempty char |
| nickname | string |   true   | 1-10 nonempty char |
| email    | string |   true   | email format       |

Example:
```
/users/1
```
```json!
{
    "id": 1,
    "username": "user123",
    "nickname": "user",
    "email": "user123@gmail.com",
}
```

##### 1.4.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
| -------- |:------:|:--------:|:------ |
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```

---
#### 1.5 Update User Image
##### 1.5.1 Information
> Router: /users/{id}/image  
> Method: PATCH  
> Description: 更新該id的使用者頭像

##### 1.5.2 Request
Parameter form: query parameter


| Key name | Type   | Required | Remark    |
|:-------- |:------ |:--------:|:--------- |
| url      | string |   true   | image url |

Example:
```
/users/1/image?url=https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/Spring_Boot.svg/1024px-Spring_Boot.svg.png
```

##### 1.5.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
| -------- |:------:|:--------:|:------ |
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```

---
#### 1.6 Update User Password
##### 1.6.1 Information
> Router: /users/{id}/pwd  
> Method: PATCH  
> Description: 更新該id的使用者密碼

##### 1.6.2 Request
Parameter form: application/json

| Key name |  Type  | Required | Remark                |
| -------- |:------:|:--------:|:--------------------- |
| old_pwd  | string |   true   | origin password       |
| new_pwd  | string |   true   | new passwrod          |
| re_pwd   | string |   true   | repeated new password |

Example:
```json
{
  "old_pwd": "123456",
  "new_pwd": "234567",
  "re_pwd": "234567"
}
```

##### 1.6.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
| -------- |:------:|:--------:|:------ |
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```
---
### 2. Category API
#### 2.1 Create Product Category
##### 2.1.1 Information
> Router: /categories  
> Method: POST  
> Description: 新增產品分類

##### 2.1.2 Request
Parameter form: application/json

| Key name     |  Type  | Required | Remark |
| ------------ |:------:|:--------:|:------ |
| categoryName | string |   true   |        |


Example:
```json
{
  "categoryName": "衣服"
}
```

##### 2.1.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
| -------- |:------:|:--------:|:------ |
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```

---

#### 2.2 Get Product Category List
##### 2.2.1 Information
> Router: /categories  
> Method: GET  
> Description: 取得產品分類列表

##### 2.2.2 Request

None

##### 2.2.3 Response
Data form: application/json

| Key name        |  Type  | Required | Remark |
|:--------------- |:------:|:--------:|:------ |
| status          | number |   true   |        |
| message         | string |  false   |        |
| data            | array  |   true   |        |
| \|-id           | number |  false   |        |
| \|-categoryName | string |  false   |        |
| \|-createdBy    | number |  false   |        |
| \|-createdTime  | string |  false   |        |
| \|-createdBy    | number |  false   |        |
| \|-updatedTime  | string |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": [
        {
            "id": 3,
            "categoryName": "衣服",
            "createdBy": 2,
            "createdTime": "2024-08-20 12:06:59",
            "updatedBy": 2,
            "updatedTime": "2024-08-20 12:06:59"
        },
        {
            "id": 4,
            "categoryName": "褲子",
            "createdBy": 1,
            "createdTime": "2024-08-20 12:06:59",
            "updatedBy": 2,
            "updatedTime": "2024-08-20 12:06:59"
        },
        {
            "id": 5,
            "categoryName": "配件",
            "createdBy": 2,
            "createdTime": "2024-08-20 12:06:59",
            "updatedBy": 1,
            "updatedTime": "2024-08-20 12:06:59"
        }
    ]
}
```

---

#### 2.3 Get Product Category
##### 2.3.1 Information
> Router: /categories/{id}  
> Method: GET  
> Description: 取得該id的產品分類資訊

##### 2.3.2 Request
Parameter form: path variable

| Key name |  Type  | Required | Remark |
|:-------- |:------:|:--------:|:------ |
| id       | number |   true   |        |

Example:
```
/categories/3
```

##### 2.3.3 Response
Data form: application/json

| Key name        |  Type  | Required | Remark |
|:--------------- |:------:|:--------:|:------ |
| status          | number |   true   |        |
| message         | string |  false   |        |
| data            | array  |   true   |        |
| \|-id           | number |  false   |        |
| \|-categoryName | string |  false   |        |
| \|-createdBy    | number |  false   |        |
| \|-createdTime  | string |  false   |        |
| \|-createdBy    | number |  false   |        |
| \|-updatedTime  | string |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": {
        "id": 3,
        "categoryName": "衣服",
        "createdBy": 2,
        "createdTime": "2024-08-20 12:06:59",
        "updatedBy": 2,
        "updatedTime": "2024-08-20 12:06:59"
  }
}
```

---

#### 2.4 Update Product Category
##### 2.4.1 Information
> Router: /categories/{id}  
> Method: PUT  
> Description: 更新該id的產品分類資訊

##### 2.4.2 Request
Parameter form: application/json

| Key name     |  Type  | Required | Remark |
|:------------ |:------:|:--------:|:------ |
| id           | number |   true   |        |
| categoryName | string |   true   |        |

Example:
```
/categories/3
```

```jsonld!
{
    "id": 3,
    "categoryName": "衣服"
}
```

##### 2.4.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
|:-------- |:------:|:--------:|:------ |
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```

---

#### 2.5 Delete Product Category
##### 2.5.1 Information
> Router: /categories/{id}  
> Method: DELETE  
> Description: 刪除該id的產品分類資訊

##### 2.5.2 Request
Parameter form: path variable

| Key name     |  Type  | Required | Remark |
|:------------ |:------:|:--------:|:------ |
| id           | number |   true   |        |

Example:
```
/categories/3
```

##### 2.5.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
|:-------- |:------:|:--------:|:------ |
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```

---

### 3. Product API
#### 3.1 Create Product
##### 3.1.1 Information
> Router: /products  
> Method: POST  
> Description: 新增產品

##### 3.1.2 Request
Parameter form: application/json

| Key name      |  Type  | Required | Remark             |
|:------------- |:------:|:--------:|:------------------ |
| productName   | string |   true   | 1~10 nonempty char |
| description   | string |  false   |                    |
| price         | number |   true   |                    |
| stockQuantity | number |   true   |                    |
| categoryId    | number |   true   |                    |
| productImg    | string |   true   | url                |
| remark        | string |  false   |                    |

Example:
```jsonld!
{
    "productName": "長袖T恤",
    "description": "產品敘述",
    "price": 450,
    "stockQuantity": 30,
    "categoryId": 1,
    "productImg": "https://i.imgur.com/bCclExz.jpg",
    "remark": "備註事項"
}
```

##### 3.1.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
|:-------- |:------:|:--------:|:------ |
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```

---

#### 3.2 Get Product List By Page 
##### 3.2.1 Information
> Router: /products  
> Method: GET  
> Description: 根據頁數取得產品資訊列表

##### 3.2.2 Request
Parameter form: query parameter

| Key name   |  Type  | Required | Remark      |
|:---------- |:------:|:--------:|:----------- |
| page       | number |   true   | page number |
| size       | number |   true   | page size   |
| categoryId | number |  false   |             |


Example:
```
/products?page=2&size=10&categoryId=2
```

##### 3.2.3 Response
Data form: application/json

| Key name         |  Type  | Required |      Remark       |
|:---------------- |:------:|:--------:|:-----------------:|
| status           | number |   true   |                   |
| message          | string |  false   |                   |
| data             | object |   true   |                   |
| \|-total         | number |   true   | total data number |
| \|-items         | array  |   true   |     data list     |
| \|-id            | number |  false   |                   |
| \|-productName   | string |  false   |                   |
| \|-description   | string |  false   |                   |
| \|-price         | number |  false   |                   |
| \|-stockQuantity | number |  false   |                   |
| \|-categoryId    | number |  false   |                   |
| \|-productImg    | string |  false   |                   |
| \|-remark        | string |  false   |                   |
| \|-createdBy     | number |  false   |                   |
| \|-createdTime   | string |  false   |                   |
| \|-updatedBy     | number |  false   |                   |
| \|-updatedTime   | string |  false   |                   |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": {
    "total": 2,
    "items": [
      {
        "id": 2,
        "productName": "長袖T恤",
        "description": "產品敘述",
        "price": 450,
        "stockQuantity": 30,
        "categoryId": 1,
        "productImg": "https://i.imgur.com/bCclExz.jpg",
        "remark": "備註事項",
        "createdBy": 2,
        "createdTime": "2024-08-20 06:31:25",
        "updatedBy": 2,
        "updatedTime": "2024-08-20 06:31:25"
      },
      {
        "id": 3,
        "productName": "短袖T恤",
        "description": "產品敘述",
        "price": 350,
        "stockQuantity": 40,
        "categoryId": 1,
        "productImg": "https://i.imgur.com/bCclExz.jpg",
        "remark": "備註事項",
        "createdBy": 2,
        "createdTime": "2024-08-20 06:31:25",
        "updatedBy": 2,
        "updatedTime": "2024-08-20 06:31:25"
      }
    ]
  }
}
```

---

#### 3.3 Get Product By ID
##### 3.3.1 Information
> Router: /products/{id}  
> Method: GET  
> Description: 取得該id的產品資訊

##### 3.3.2 Request
Parameter form: path variable

| Key name |  Type  | Required | Remark |
|:-------- |:------:|:--------:|:------ |
| id       | number |   true   |        |


Example:
```
/products/3
```

##### 3.3.3 Response
Data form: application/json

| Key name         |  Type  | Required |      Remark       |
|:---------------- |:------:|:--------:|:-----------------:|
| status           | number |   true   |                   |
| message          | string |  false   |                   |
| data             | object |   true   |                   |
| \|-id            | number |  false   |                   |
| \|-productName   | string |  false   |                   |
| \|-description   | string |  false   |                   |
| \|-price         | number |  false   |                   |
| \|-stockQuantity | number |  false   |                   |
| \|-categoryId    | number |  false   |                   |
| \|-productImg    | string |  false   |                   |
| \|-remark        | string |  false   |                   |
| \|-createdBy     | number |  false   |                   |
| \|-createdTime   | string |  false   |                   |
| \|-updatedBy     | number |  false   |                   |
| \|-updatedTime   | string |  false   |                   |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": 
    {
        "id": 3,
        "productName": "長袖T恤",
        "description": "產品敘述",
        "price": 450,
        "stockQuantity": 30,
        "categoryId": 1,
        "productImg": "https://i.imgur.com/bCclExz.jpg",
        "remark": "備註事項",
        "createdBy": 2,
        "createdTime": "2024-08-20 06:31:25",
        "updatedBy": 2,
        "updatedTime": "2024-08-20 06:31:25"
    }
}
```

---

#### 3.4 Update Product
##### 3.4.1 Information
> Router: /products/{id}  
> Method: PUT  
> Description: 更新該id的產品資訊

##### 3.4.2 Request
Parameter form: application/json

| Key name      |  Type  | Required | Remark             |
|:------------- |:------:|:--------:|:------------------ |
| id            | number |   true   |                    |
| productName   | string |   true   | 1~10 nonempty char |
| description   | string |  false   |                    |
| price         | number |   true   |                    |
| stockQuantity | number |   true   |                    |
| categoryId    | number |   true   |                    |
| productImg    | string |   true   | url                |
| remark        | string |  false   |                    |


Example:
```json
{
    "id": 3,
    "productName": "長袖T恤",
    "description": "產品敘述",
    "price": 450,
    "stockQuantity": 30,
    "categoryId": 1,
    "productImg": "https://i.imgur.com/bCclExz.jpg",
    "remark": "備註事項"
}
```

##### 3.4.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
|:-------- |:------:|:--------:|:------:|
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```

---

#### 3.5 Delete Product
##### 3.5.1 Information
> Router: /products/{id}  
> Method: DELETE  
> Description: 刪除該id的產品資訊

##### 3.5.2 Request
Parameter form: path variable

| Key name |  Type  | Required | Remark |
|:-------- |:------:|:--------:|:------ |
| id       | number |   true   |        |


Example:
```
/products/3
```

##### 3.5.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark |
|:-------- |:------:|:--------:|:------:|
| status   | number |   true   |        |
| message  | string |  false   |        |
| data     | object |  false   |        |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": null
}
```

---

### 4. File API
#### 4.1 File Upload
##### 4.1.1 Information
> Router: /files  
> Method: POST  
> Description: 上傳檔案

##### 4.1.2 Request
Parameter form: multipart/form-data

| Key name | Type | Required | Remark |
|:-------- |:----:|:--------:|:------ |
| file     | file |   true   |        |

##### 4.1.3 Response
Data form: application/json

| Key name |  Type  | Required | Remark                       |
|:-------- |:------:|:--------:|:---------------------------- |
| status   | number |   true   |                              |
| message  | string |  false   |                              |
| data     | string |   true   | URL of image stored in cloud |

Example:
```json
{
  "status": 200,
  "message": "success",
  "data": "https://i.imgur.com/bCclExz.jpg"
}
```

---

Database
-

- user

|   Key Name   |  Data Type   | Attribute         |
|:------------:|:------------:|:----------------- |
|      id      |     int      | PK auto_increment |
|   username   | varchar(20)  | not null unique   |
|   password   | varchar(128) | not null          |
|   nickname   | varchar(10)  | default ' '       |
|    email     | varchar(128) | default ' '       |
|   user_img   | varchar(128) | default ' '       |
|  created_by  |     int      | not null          |
| created_time |  timestamp   | not null          |
|  updated_by  |     int      | not null          |
| updated_time |  timestamp   | not null          |

- product

|    Key Name    |  Data Type   | Attribute              |
|:--------------:|:------------:|:---------------------- |
|       id       |     int      | PK auto_increment      |
|  product_name  | varchar(64)  | not null               |
|  description   |     text     |                        |
|     price      |     int      | not null               |
| stock_quantity |     int      | not null  default 0    |
|  category_id   |     int      | FK{catedgory(id)}      |
|  product_img   | varchar(128) | default ' '            |
|     remark     |     text     |                        |
|   created_by   |     int      | not null  FK{user(id)} |
|  created_time  |  timestamp   | not null               |
|   updated_by   |     int      | not null  FK{user(id)} |
|  updated_time  |  timestamp   | not null               |

- category

|   Key Name    |  Data Type  | Attribute              |
|:-------------:|:-----------:|:---------------------- |
|      id       |     int     | PK auto_increment      |
| category_name | varchar(32) | not null               |
|  created_by   |     int     | not null  FK{user(id)} |
| created_time  |  timestamp  | not null               |
|  updated_by   |     int     | not null  FK{user(id)} |
| updated_time  |  timestamp  | not null               |

GCP
-

Project ID: ecommercebackendsystem

Service Account: ecommercebackendsystem@ecommercebackendsystem.iam.gserviceaccount.com

---

### 1. Cloud Storage

Bucket Name: ecommerce_backend_system

---

### 2. Secret Manager

> Manage credential json created by service account

Sercet ID: service-account-key

#### 2.1 Set up ADC(Application Default Credentials) on server machine when deploy project

1. Install the Google Cloud CLI on server machine
2. Initialize it by running following command:

```
gcloud init
```

3. Create local authentication credebtials for your user account(login Google account):

```
gcloud auth application-default login
```
A sign-in screen appears. After you sign in, your credentials are stored in the local credential file used by ADC.

Docker
-

> Deploy on docker container

Container: 
1. Spring boot backend
   * port: 8080
2. Redis
   * port: 6379 

Add containers of project to same docker network: 
```
docker network create <network-name>
docker network connect <network-name> <redis-container-id>
docker network connect <network-name> <spring-boot-container-id>
```

```
docker network inspect <network-name> // check network info
```
