# Schedule Project

## 📖 목차
1. [프로젝트 소개](#프로젝트-소개)
2. [프로젝트 계기](#프로젝트-계기)
3. [주요기능](#주요기능)
4. [개발기간](#개발기간)
5. [기술스택](#기술스택)
6. [Trouble Shooting](#trouble-shooting)
7. [ERD](#ERD)
8. [API 명세](#API명세)

## 👨‍🏫 프로젝트 소개
일정 관리 앱 심화 프로젝트

## 소개
개인 프로젝트 / 내일배움캠프 과제

## 💜 주요기능
1. 일정 생성
2. 일정 조회
3. 일정 수정
4. 일정 삭제


## ⏲️ 개발기간
- 2026.04.16(목) ~ 2026.04.21(화)

## 📚️ 기술스택

### ✔️ Language
java Spring

### ✔️ IDE
IntelliJ

## 트러블슈팅
벨로그 참고

## 🗂️ ERD
<details>
<summary>🗂️ <b>ERD 열기</b> </summary>

![ERD](./docs/ERD.png)

</details>

## API 명세

<details>
<summary>📌 <b>일정 생성 API</b> </summary>

### 일정 생성 API
- Method: POST
- URL: /schedules
- Request Body:
```json
{
  "title": "회의",
  "content": "팀 프로젝트 회의",
  "userId": 1
}
```
- Request 필드

| 필드명     | 타입     | 필수 여부 | 설명    |
|---------|--------| ----- |-------|
| title   | String | O     | 일정 제목 |
| content | String | O     | 일정 내용 |
| userId  | Long   | O     | 유저 id |
- Response
```json
{
  "id": 1,
  "title": "회의",
  "content": "팀 프로젝트 회의",
  "userId": 1,
  "userName": "홍길동",
  "createdAt": "2026-04-10T14:30:00",
  "updatedAt": "2026-04-10T14:30:00"
}
```
- Response 필드

  | 필드명       | 타입            | 설명    |
    |-----------|---------------|-------|
  | id        | Long          | 일정 ID |
  | title     | String        | 일정 제목 |
  | content   | String        | 일정 내용 |
  | userId    | Long          | 유저 id |
  | userName  | String        | 유저 이름 |
  | createdAt | LocalDateTime | 생성일   |
  | updatedAt | LocalDateTime | 수정일   |

- 상태 코드

| 상태 코드 | 설명                |
| ----- | ----------------- |
| 201   | 생성 성공             |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |
</details>
<details>
<summary>📌 <b>일정 조회 API</b> </summary>

### 전체 일정 조회 API
- Method: GET
- URL: /schedules
- Request Body:
```json
{
  "authorName": "홍길동"
}
```
- Request 필드

| 필드명        | 타입     | 필수 여부 | 설명             |
| ---------- | ------ |-------| -------------- |
| authorName | String | x     | 작성자 이름         |


- Response
```json
{
  "id": 1,
  "title": "회의",
  "content": "팀 프로젝트 회의",
  "authorName": "홍길동",
  "createdAt": "2026-04-10T14:30:00",
  "updatedAt": "2026-04-10T14:30:00"
}
```
- Response 필드

  | 필드명        | 타입            | 설명     |
      | ---------- | ------------- | ------ |
  | id         | Long          | 일정 ID  |
  | title      | String        | 일정 제목  |
  | content    | String        | 일정 내용  |
  | authorName | String        | 작성자 이름 |
  | createdAt  | LocalDateTime | 생성일    |
  | updatedAt  | LocalDateTime | 수정일    |

- 상태 코드

| 상태 코드 | 설명                |
|-------|-------------------|
| 200   | 성공                |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |

### 선택 일정 조회 API
- Method: GET
- URL: /schedules/{id}
- Request Body:
```json
{
  "id": 1
}
```
- Request 필드

| 필드명     | 타입    | 필수 여부 | 설명       |
| ------- | ----- |-------| -------- |
| id      | Long   | O     | 일정 ID   |


- Response
```json
{
  "id": 1,
  "title": "회의",
  "content": "팀 프로젝트 회의",
  "authorName": "홍길동",
  "createdAt": "2026-04-10T14:30:00",
  "updatedAt": "2026-04-10T14:30:00"
}
```
- Response 필드

  | 필드명            | 타입            | 설명        |
        |----------------| ------------- |-----------|
  | id             | Long          | 일정 ID     |
  | title          | String        | 일정 제목     |
  | content        | String        | 일정 내용     |
  | authorName     | String        | 작성자 이름    |
  | createdAt      | LocalDateTime | 생성일       |
  | updatedAt      | LocalDateTime | 수정일       |


- 상태 코드

| 상태 코드 | 설명                |
|-------|-------------------|
| 200   | 성공                |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |
</details>
<details>
<summary>📌 <b>일정 수정 API</b> </summary>

### 일정 수정 API
- Method: PATCH
- URL: /schedules/{id}
- Request Body:
```json
{
  "title": "회의",
  "content": "회의 내용"
}
```
- Request 필드

| 필드명     | 타입     | 필수 여부 | 설명        |
|---------| ------ | ----- |-----------|
| title   | String | O     | 수정할 일정 제목 |
| content | String | O     | 수정할 회의 내용 |

- Response
```json
{
  "id": 1,
  "title": "회의",
  "authorName": "홍길동",
  "createdAt": "2026-04-10T14:30:00",
  "updatedAt": "2026-04-10T14:30:00"
}
```
- Response 필드

  | 필드명        | 타입            | 설명         |
      | ---------- | ------------- |------------|
  | id         | Long          | 일정 ID      |
  | title      | String        | 수정된 일정 제목  |
  | authorName | String        | 수정된 작성자 이름 |
  | createdAt  | LocalDateTime | 생성일        |
  | updatedAt  | LocalDateTime | 수정일        |

- 상태 코드

| 상태 코드 | 설명                |
|-------| ----------------- |
| 200   | 성공             |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |
</details>

<details>
<summary>📌 <b>일정 삭제 API</b> </summary>

### 일정 삭제 API
- Method: DELETE
- URL: /schedules/{id}
- Request Body: 없음
- Request 필드 : 없음

- 상태 코드

| 상태 코드 | 설명                |
|-------| ----------------- |
| 204   | 성공             |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |
</details>

<details>
<summary>📌 <b>유저 생성 API</b> </summary>

### 유저 생성 API
- Method: POST
- URL: /users
- Request Body:
```json
{
  "userName": "홍길동",
  "email": "hong@example.com"
}
````

* Request 필드

| 필드명      | 타입     | 필수 여부 | 설명  |
| -------- | ------ | ----- | --- |
| userName | String | O     | 유저명 |
| email    | String | O     | 이메일 |

* Response

```json
{
  "id": 1,
  "userName": "홍길동",
  "email": "hong@example.com",
  "createdAt": "2026-04-10T14:30:00",
  "updatedAt": "2026-04-10T14:30:00"
}
```

* Response 필드

| 필드명       | 타입            | 설명    |
| --------- | ------------- | ----- |
| id        | Long          | 유저 ID |
| userName  | String        | 유저명   |
| email     | String        | 이메일   |
| createdAt | LocalDateTime | 작성일   |
| updatedAt | LocalDateTime | 수정일   |

* 상태 코드

| 상태 코드 | 설명                |
| ----- | ----------------- |
| 201   | 생성 성공             |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |

</details>

<details>
<summary>📌 <b>유저 조회 API</b> </summary>

### 전체 유저 조회 API

* Method: GET

* URL: /users

* Request Body: 없음

* Request 필드 : 없음

* Response

```json
{
  "id": 1,
  "userName": "홍길동",
  "email": "hong@example.com",
  "createdAt": "2026-04-10T14:30:00",
  "updatedAt": "2026-04-10T14:30:00"
}
```

* Response 필드

| 필드명       | 타입            | 설명    |
| --------- | ------------- | ----- |
| id        | Long          | 유저 ID |
| userName  | String        | 유저명   |
| email     | String        | 이메일   |
| createdAt | LocalDateTime | 작성일   |
| updatedAt | LocalDateTime | 수정일   |

* 상태 코드

| 상태 코드 | 설명                |
| ----- | ----------------- |
| 200   | 성공                |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |

### 선택 유저 조회 API

* Method: GET

* URL: /users/{id}

* Request Body: 없음

* Request 필드 : 없음

* Response

```json
{
  "id": 1,
  "userName": "홍길동",
  "email": "hong@example.com",
  "createdAt": "2026-04-10T14:30:00",
  "updatedAt": "2026-04-10T14:30:00"
}
```

* Response 필드

| 필드명       | 타입            | 설명    |
| --------- | ------------- | ----- |
| id        | Long          | 유저 ID |
| userName  | String        | 유저명   |
| email     | String        | 이메일   |
| createdAt | LocalDateTime | 작성일   |
| updatedAt | LocalDateTime | 수정일   |

* 상태 코드

| 상태 코드 | 설명                |
| ----- | ----------------- |
| 200   | 성공                |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |

</details>

<details>
<summary>📌 <b>유저 수정 API</b> </summary>

### 유저 수정 API

* Method: PATCH
* URL: /users/{id}
* Request Body:

```json
{
  "userName": "홍길동",
  "email": "hong@example.com"
}
```

* Request 필드

| 필드명      | 타입     | 필수 여부 | 설명      |
| -------- | ------ | ----- | ------- |
| userName | String | O     | 수정할 유저명 |
| email    | String | O     | 수정할 이메일 |

* Response

```json
{
  "id": 1,
  "userName": "홍길동",
  "email": "hong@example.com",
  "createdAt": "2026-04-10T14:30:00",
  "updatedAt": "2026-04-10T14:30:00"
}
```

* Response 필드

| 필드명       | 타입            | 설명      |
| --------- | ------------- | ------- |
| id        | Long          | 유저 ID   |
| userName  | String        | 수정된 유저명 |
| email     | String        | 수정된 이메일 |
| createdAt | LocalDateTime | 작성일     |
| updatedAt | LocalDateTime | 수정일     |

* 상태 코드

| 상태 코드 | 설명                |
| ----- | ----------------- |
| 200   | 성공                |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |

</details>

<details>
<summary>📌 <b>유저 삭제 API</b> </summary>

### 유저 삭제 API

* Method: DELETE

* URL: /users/{id}

* Request Body: 없음

* Request 필드 : 없음

* 상태 코드

| 상태 코드 | 설명                |
| ----- | ----------------- |
| 204   | 성공                |
| 400   | 잘못된 요청 (필수값 누락 등) |
| 500   | 서버 오류             |

</details>


#
