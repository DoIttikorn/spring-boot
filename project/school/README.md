# School

## Requirement

### story: EXP01

```text
ในฐานะอาจารย์ ฉันต้องการบันทึกข้อมูลอาจารย์ได้
```

Technical Details:

- POST /api/teacher/v1
- Request Body

```json
{
  "first name": "John",
  "last name": "which",
  "email": "dodo@gmail.com",
  "age": 25
}
```

- Response Body

```json
{
  "id": 1,
  "first name": "Sudarud",
  "last name": "Kidrak",
  "email": "dodo@gmail.com",
  "status": "Y",
  "age": 25
}
```

### story: EXP02

```text
ในฐานะอาจารย์ ฉันต้องการค้นหาข้อมูลอาจารย์ได้โดยใช้ไอดีอาจารย์
```

Technical Details:

- GET /api/teacher/v1/{id}
- Response Body

```json
{
  "id": 1,
  "first name": "Sudarud",
  "last name": "Kidrak",
  "email": "e@gmil.com",
  "status": "Y",
  "age": 25,
  "created date": "2020-01-01 00:00:00",
  "updated date": "2020-01-01 00:00:00"
}
```

### story: EXP03

```text
ในฐานะอาจารย์ ฉันต้องการแก้ไขข้อมูลอาจารย์ได้
```

Technical Details:

- PUT /api/teacher/v1
- Request Body

```json
{
  "id": 1,
  "first name": "John",
  "last name": "which",
  "email": "e@gmail.com",
  "age": 25
}
```

- Response Body

```json
{
  "id": 1,
  "first name": "John",
  "last name": "which",
  "email": "e@gmail.com",
  "status": "Y",
  "age": 25,
  "created date": "2020-01-01 00:00:00",
  "updated date": "2020-01-01 00:00:00"
}
```

### story: EXP04

```text
ในฐานะอาจารย์ ฉันต้องการลบข้อมูลอาจารย์ได้
```

Technical Details:

- DELETE /api/teacher/v1/:id
- Response Return Status Code 200

---

### story: EXP05

```text
ในฐานะอาจารย์ ฉันต้องการเห็นรายชื่อนักเรียนที่รับผิดชอบ เพื่อติดตามผลการเรียน
```

Technical Details:

- GET /api/teacher/v1/{teacherId}/student
- Response Body

```json
[
  {
    "id": 1,
    "first name": "Ittikorn",
    "last name": "Chawkamud",
    "age": 23
  },
  {
    "id": 2,
    "first name": "test",
    "last name": "last test",
    "age": 22
  }
]
```

### story: EXP06

```text
ในฐานะอาจารย์ ฉันต้องการบันทึกนักเรียนที่ฉันรับผิดชอบใหม่เข้าไปได้
```

Technical Details:

- POST /api/teacher/v1/{teacherId}/student
- Request Body

```json
{
  "first name": "John",
  "last name": "which",
  "age": 25
}
```

- Response Body

```json
{
  "id": 3,
  "first name": "John",
  "last name": "which",
  "age": 25
}
```

### story: EXP07

```text
ในฐานะอาจารย์ ฉันต้องการอัพเดรตข้อมูลนักเรียนได้
```

Technical Details:

- PUT /api/teacher/{teacherId}/student/{teacherId}
- Request Body

```json
{
  "first name": "John",
  "last name": "which",
  "age": 21
}
```

- Response Body

```json
{
  "id": 3,
  "first name": "John",
  "last name": "which",
  "age": 21
}
```


### story: EXP08

```text
ในฐานะอาจารย์ ฉันต้องการลบรายชื่อนักเรียนออกจากรายชื่อที่อาจารย์ดูแลได้
```

Technical Details:

- DELETE /api/teacher/v1/{teacherId}/student/{studentId}
- Return Status Code 200

## DATABASE DIGARM

[dbdigarm.io](https://dbdiagram.io/d/63bbdf596afaa541e5d132bb)