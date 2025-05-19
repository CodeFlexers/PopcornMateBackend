- 로그인 성공 = 200
- 로그인 실패 = 401
- 회원가입 성공 = 200
- 회원가입 실패 = 400
- 토큰 expire, 이상 = 401

토큰 정보 
- accountCode
- accountId
- accountRole


- 회원가입
- 로그인
- jwt 토큰 발행
- refresh-token 발행, 검증, 새로운 토큰 발행
- jwt 토큰 검증
- 리액트 서버 통신을 위한 CORS 설정
- 웹소켓 통신 구현

### 의존성
- lombok
- mysqlDriver
- mariaDriver
- Spring data JPA
- Spring security
- Spring devtools
- WebSocket
- JWT 0.12.3

### 테스트
- Postman
- React
