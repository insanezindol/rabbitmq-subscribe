# RabbitMQ Subscribe 애플리케이션

## 프로젝트 개요

이 프로젝트는 Spring Boot와 RabbitMQ를 사용하여 메시지 구독(Subscribe) 기능을 구현한 애플리케이션입니다.
`product` Exchange를 통해 전달되는 스낵과 음료 관련 메시지를 구독하고 처리합니다.

## 기술 스택

-   **Java**: 1.8
-   **Spring Boot**: 2.3.4.RELEASE
-   **Spring AMQP**: RabbitMQ 연동
-   **Lombok**: 코드 간소화
-   **Gradle**: 빌드 도구

## 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── amqp/rabbitmq/subscribe/
│   │       ├── SubscribeApplication.java          # 메인 애플리케이션 클래스
│   │       ├── config/
│   │       │   ├── RabbitDrinkConfig.java         # 음료 큐 설정
│   │       │   └── RabbitSnackConfig.java         # 스낵 큐 설정
│   │       └── listener/
│   │           └── RabbitMQListener.java          # 메시지 리스너
│   └── resources/
│       └── application.yml                        # 애플리케이션 설정
└── test/
    └── java/
        └── amqp/rabbitmq/subscribe/
            └── SubscribeApplicationTests.java      # 테스트 클래스
```

## 주요 기능

### 1. 메시지 리스닝

-   **스낵 메시지**: `snack.queue`에서 `snack.*` 패턴의 메시지 수신
-   **음료 메시지**: `drink.queue`에서 `drink.*` 패턴의 메시지 수신

### 2. Exchange 및 Queue 구성

-   **Exchange**: `product` (Topic Exchange)
-   **Queues**:
    -   `snack.queue`: 스낵 관련 메시지 처리
    -   `drink.queue`: 음료 관련 메시지 처리
-   **Routing Keys**:
    -   스낵: `snack.#`
    -   음료: `drink.#`

## 설정 정보

### RabbitMQ 연결 설정 (application.yml)

```yaml
spring:
    rabbitmq:
        host: localhost
        port: 5672
        username: rabbitmq
        password: 1234
```

### 서버 포트

-   애플리케이션 포트: `8082`

## 사전 요구사항

### 1. RabbitMQ 서버 설치 및 실행

RabbitMQ 서버가 다음 설정으로 실행되어야 합니다:

-   **호스트**: localhost
-   **포트**: 5672
-   **사용자명**: rabbitmq
-   **비밀번호**: 1234

### 2. Java 환경

-   Java 1.8 이상

## 실행 방법

### 1. 프로젝트 클론

```bash
git clone <repository-url>
cd rabbitmq-subscribe
```

### 2. 애플리케이션 빌드 및 실행

```bash
# Gradle을 사용한 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun
```

또는 IDE에서 `SubscribeApplication.java`를 직접 실행할 수 있습니다.

### 3. 실행 확인

애플리케이션이 성공적으로 시작되면 8082 포트에서 실행되며, 다음과 같은 큐들이 자동으로 생성됩니다:

-   `snack.queue`
-   `drink.queue`

## 메시지 처리 예시

### 스낙 메시지 수신

```
routing key: snack.chips
message: {"product": "감자칩", "price": 1500}
```

### 음료 메시지 수신

```
routing key: drink.cola
message: {"product": "콜라", "price": 2000}
```

## 로그 확인

애플리케이션 실행 시 메시지 수신 로그를 통해 처리 상황을 확인할 수 있습니다:

```
receiveSnackMessage : [메시지 내용]
snack.chips : {"product": "감자칩", "price": 1500}

receiveDrinkMessage : [메시지 내용]
drink.cola : {"product": "콜라", "price": 2000}
```

## 개발 및 테스트

### 테스트 실행

```bash
./gradlew test
```

### 코드 스타일

-   Lombok을 사용하여 보일러플레이트 코드 최소화
-   SLF4J를 통한 로깅
-   Spring의 설정 기반 Bean 관리

## 참고사항

-   이 애플리케이션은 메시지 구독(Subscribe) 전용입니다
-   메시지 발행(Publish)은 별도의 애플리케이션에서 수행되어야 합니다
-   RabbitMQ Management UI는 `http://localhost:15672`에서 접근 가능합니다 (기본 설정 시)
