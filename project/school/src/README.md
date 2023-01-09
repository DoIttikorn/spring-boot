# School

## Requirement

### story: EXP01

```text
ในฐานะอาจารย์ ฉันต้องการเห็นรายชื่อนักเรียนที่รับผิดชอบ เพื่อติดตาม ผลการเรียน
```

Technical Details:

- GET /api/teacher/1/student
- :id = 1
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

### story: EXP02

```text
ในฐานะอาจารย์ ฉันต้องการบันทึกนักเรียนที่ฉันรับผิดชอบใหม่เข้าไปได้
```

Technical Details:

- POST /api/teacher/1/student
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

### story: EXP03

```text
ในฐานะอาจารย์ ฉันต้องการอัพเดรตข้อมูลนักเรียนได้
```

Technical Details:

- PUT /api/teacher/1/student
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

<div>
<iframe width="560" height="315" src='https://dbdiagram.io/embed/63bbdf596afaa541e5d132bb'> </iframe>
</div>



