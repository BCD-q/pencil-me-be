## 

<div align="center">
    <img src="https://avatars.githubusercontent.com/u/150117859?s=200&v=4" />
    <h1>Pencil me</h1>
    <h3>개발자를 위한 생산성 도구 겸 할 일 관리 파트너 서비스</h3>
    <h3>AI를 이용한 할 일 등록 | 할 일 기록 기반 컨텐츠 추천 | Chrome Extension 연동</h3>
</div>

---
## Database ERD

## API Response Format

### Success ✅

#### Header

| HTTP Status Code |
|------------------|

#### Body

| Response Code | Response Message | DTO |
|---------------|------------------|-----|

#### Example
| HTTP Status Code | Response Code | Response Message | DTO (Optional)    |
|------------------|---------------|------------------|-------------------|
| 200 (OK)         | MEMBER_FOUND  | 사용자 조회 완료        | MemberResponseDto |

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

## Architecture
![](https://github.com/BCD-q/pencil-me-be/assets/108407945/a760ce6f-f996-42ff-9889-fad35fc97055)

---

## CI/CD Pipeline

- Pull Request (to main)
![](https://github.com/BCD-q/pencil-me-be/assets/108407945/a2c6fcc6-a658-426c-a531-b05264be259e)

- Push (main)
![](https://github.com/BCD-q/pencil-me-be/assets/108407945/0fe5aa87-301b-4b1d-ad0a-bf15e87e630b)

---

## Repository Link
- [Pencil me - front-end Repository](https://github.com/BCD-q/pencil-me-fe)
- [Pencil me - AI Repository]()

---

## Contributors
|김원정|이주원|
|:-:|:-:|
|<a href="https://github.com/NARUBROWN"><img src="https://avatars.githubusercontent.com/u/38902021?v=4" width=120></a>|<a href="https://github.com/Juser0"><img src="https://avatars.githubusercontent.com/u/108407945?v=4" width=120></a>|
|[@NARUBROWN](https://github.com/NARUBROWN)|[@Juser0](https://github.com/Juser0)|