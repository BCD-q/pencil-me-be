# pencilme-spring
2023 Capstone Design Project - Pencil me back-end Repository

---

## API Response Format

### Success ✅

#### Header

| HTTP Status Code |
|------------------|

#### Body

| Response Code | Response Message | DTO |
|---------------|------------------|-----|

#### Example
| HTTP Status Code | Response Code | Response Message | DTO (Optional)     |
|------------------|---------------|------------------|--------------------|
| 200 (OK)         | MEMBER_FOUND  | 사용자 조회 완료        | MemeberResponseDto |

### Error 🚫

#### Header

| HTTP Status Code |
|------------------|

#### Body

| Response Code | Response Message |
|---------------|------------------|

#### Example
| HTTP Status Code | Response Code    | Response Message  |
|------------------|------------------|-------------------|
| 404 (Not Found)  | MEMBER_NOT_FOUND | 사용자 정보가 존재하지 않습니다 |

---

## CI/CD Pipeline

---

## Repository Link
- [Pencil me - front-end Repository](https://github.com/BCD-q/pencil-me-fe)
- [Pencil me - AI Repository]()