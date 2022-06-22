# 가계부 구현

### 기술
- Java 11
- SpringBoot 2.7.0
- Spring Security
- Spring Data JPA
- JWT

### API
- 회원가입 (GET /signup)
  - body: email, password
- 로그인 (POST /login)
  - body: email, password
- 로그아웃 (POST /logout)
- 가계부 목록 조회 (GET /accountbook)
- 가계부 저장 (POST /accountbook)
  - body: money, memo
- 가계부 상세 조회 (GET /accountbook/{accountBookId})
  - path: accountBookId
- 가계부 수정 (PUT /accountbook/{accountBookId})
  - path: accountBookId
  - body: money, memo
- 가계부 삭제 (DELETE /accountbook/{accountBookId})
  - path: accountBookId
- 가계부 삭제 취소 (PUT /accountbook/{accountBookId}/active)
  - path: accountBookId