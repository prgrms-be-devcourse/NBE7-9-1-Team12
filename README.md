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

| 분야 | 기술 |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot 3.x |
| Database | H2 (dev), MySQL (prod) |
| ORM | Spring Data JPA (Hibernate) |
| Build Tool | Kotlin |
| Version Control | GitHub |

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
