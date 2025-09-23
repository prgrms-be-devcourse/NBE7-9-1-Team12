# ☕ Grids & Circles - Online Coffee Ordering System

> 작은 로컬 카페 **“Grids & Circles”** 의 온라인 주문 관리 시스템  
> 고객은 웹사이트를 통해 커피 원두 패키지를 주문하고, 매일 **오후 2시 기준**으로 주문이 취합되어 배송됩니다.  

---

## ✨ 프로젝트 개요

- **프로젝트명:** Grids & Circles Online Coffee Ordering  
- **목표:** 소규모 카페의 온라인 주문 시스템을 구축하여, 고객 편의성과 주문 효율성을 높임  
- **주요 특징:**
  - 별도의 회원가입 없이 **이메일 기반 주문**
  - 하루 주문을 **오후 2시 기준으로 취합**
  - 동일 고객이 하루에 여러 번 주문하더라도, **하나의 배송 단위로 합산**
  - 기본 제공 상품 4종 관리 (CRUD)  

---

## 📖 시나리오

- 고객은 웹사이트에 접속하여 커피 원두 상품을 장바구니에 담아 주문  
- 주문 시 이메일을 입력하여 고객을 구분  
- **주문 처리 규칙:**
  - **전날 오후 2시 ~ 당일 오후 2시 주문** → 당일 배송 시작  
  - **오후 2시 이후 주문** → 다음 날 배송 시작  
- 안내 문구:  
  > "당일 오후 2시 이후의 주문 건은 다음 날 배송이 시작됩니다."  

---

## 🛠️ 기술 스택

### Backend
- **Java 21** : 최신 LTS 이후 버전 사용, 성능 및 언어 기능 향상
- **Spring Boot 3.5.6** : 애플리케이션 프레임워크
- **Spring Data JPA (Hibernate)** : ORM 기반 데이터 액세스 계층

### Database
- **H2 Database** : 개발 및 테스트 환경용 인메모리/파일형 DB
- **MySQL** : 운영 환경용 RDBMS
- **MySQL Connector/J** : MySQL 연결 드라이버

### Build & Dependency Management
- **Gradle (Kotlin DSL)** : 프로젝트 빌드 및 의존성 관리
- **Spring Dependency Management Plugin (1.1.7)** : BOM 관리

### Development Tools
- **IntelliJ IDEA** : 주요 개발 환경 (IDE)
- **Spring Boot DevTools** : 개발 편의 기능 (자동 리스타트 등)
- **Lombok** : 반복 코드 제거 (Getter, Setter, Builder 자동 생성)

### Testing
- **JUnit 5 (JUnit Platform)** : 단위 및 통합 테스트
- **Spring Boot Starter Test** : Spring 테스트 유틸리티


---

## 📂 프로젝트 구조

```bash
src/main/java/com/mysite/cuffee
 ├── cart
 │   ├── controller
 │   ├── dto
 │   ├── entity
 │   ├── repository
 │   └── service
 ├── order
 │   ├── controller
 │   ├── dto
 │   ├── entity
 │   ├── repository
 │   └── service
 └── products
     ├── controller
     ├── dto
     ├── entity
     ├── repository
     └── service
