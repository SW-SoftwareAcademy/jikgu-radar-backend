# Jikgu Radar Backend

국가기술표준원과 공정거래위원회의 리콜 데이터를 수집·통합하고, 사용자가 등록한 제품과 비교해 관련 리콜 정보를 알려주는 서비스의 백엔드입니다.

AI는 제품 사진에서 제품명·제조사·모델번호·인증번호를 추출하는 역할만 담당하며, 제품의 안전 여부나 리콜 대상 여부를 최종 판단하지 않습니다.

## 현재 초기 설정

- Java 21, Spring Boot 3.4.5
- Spring Web, Data JPA, Validation
- Spring Security 기본 설정
- Spring Batch 의존성
- PostgreSQL 드라이버와 개발·운영 프로필
- 공통 API 응답과 예외 처리
- JPA 생성·수정 시간 자동 기록
- CORS와 Swagger/OpenAPI
- 로컬 PostgreSQL `compose.yml`
- PR 빌드 확인용 GitHub Actions

현재는 프로젝트 기반만 구성되어 있으며 카카오 로그인, JWT, 공공데이터 수집, AI 분석, 제품 매칭, 알림은 구현하지 않았습니다.

## 현재 패키지

```text
com.jikguradar
├─ JikguRadarApplication.java
├─ global
│  ├─ common
│  ├─ config
│  └─ exception
└─ security
```

비즈니스 도메인은 실제 기능을 시작할 때 생성합니다.

```text
domain/
├─ auth
├─ product
├─ recall
├─ ingestion
├─ analysis
├─ matching
├─ notification
└─ admin
```

## 로컬 실행

Java 21과 Docker가 필요합니다.

```powershell
docker compose up -d postgres
./gradlew.bat bootRun
```

기본 개발용 PostgreSQL 설정:

```text
URL      jdbc:postgresql://localhost:5432/jikgu_radar
Database jikgu_radar
Username jikgu_radar
Password jikgu_radar
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

환경변수를 사용하려면 `.env.example`에 정리된 항목을 IDE 또는 실행 환경에 등록합니다. `.env` 파일은 Git에 포함하지 않습니다.

## 이후 기능별로 추가할 항목

| 기능 | 구현 시 추가할 내용 |
| --- | --- |
| 카카오 로그인 | JWT 라이브러리, 카카오 API 설정, `auth`·`user` 도메인 |
| 공공데이터 적재 | Batch Job·Step, Scheduler, WireMock 테스트 |
| 제품 매칭 | Flyway, `pg_trgm` 마이그레이션, PostgreSQL 통합 테스트 |
| 이미지 분석 | S3 SDK, Vision LLM 클라이언트, 비동기 작업 상태 |
| 푸시 알림 | Firebase Admin SDK, Outbox와 재시도 처리 |
| 배포 | Dockerfile, Actuator, EC2 배포 Workflow |

최종 기술 스택을 한 번에 추가하지 않고 실제 기능 구현 시점에 필요한 설정과 테스트를 함께 추가합니다.

