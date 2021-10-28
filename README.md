# 알서포트(공지사항)

# 주제
공지사항 관리 REST API 구현

## 기능 요구사항
- 공지사항 등록, 수정, 삭제, 조회 API를 구현한다.
- 공지사항 등록시 입력 항목은 다음과 같다.
  - 제목, 내용, 공지 시작일시, 공지 종료일시, 첨부파일(여러개)
- 공지사항 조회시 응답은 다음과 같다
  - 제목, 내용, 등록일시, 조회수, 작성자

## 비기능 요구사항 및 평가 항목
- REST API로 구현
- 개발 언어는 Java, Kotlin 중 익숙한 개발 언어로 한다.
- 웹 프레임 워크는 Spring Boot 을 사용한다.
- Persistence 프레임 워크는 Hibernate 사용시 가산점
- 데이터 베이스는 제약 없음
- 기능 및 제약사항에 대한 단위/통합테스트 작성
- 대용량 트래픽을 고려하여 구현할 것
- 핵심 문제해결 전략 및 실행 방법등을 README 파일에 명시

### 기술 스택
- Java
- Spring Boot
- JPA
- MySQL
- Mustache (FrontEnd)

### 화면 구성
- 공지사항 등록
![image](https://user-images.githubusercontent.com/37436822/139311210-8ecd116a-7883-43e9-95e3-1564d07d9f59.png)

- 공지사항 전체 조회 화면
![image](https://user-images.githubusercontent.com/37436822/139310763-aafe0c64-748a-4580-8f1f-3a39592e1db6.png)

- 공지사항 상세 조회 화면
![image](https://user-images.githubusercontent.com/37436822/139311016-acd6b55c-98a5-4ea5-acbf-f7f581061e38.png)

### 첨부파일
- 단일 또는 다중 첨부파일 선택
![image](https://user-images.githubusercontent.com/37436822/139311549-c191c1b4-cbc7-42c9-9553-93b3a81048fa.png)

- 다중 파일 DB저장
![image](https://user-images.githubusercontent.com/37436822/139311779-b800d1ee-eaf4-4dd1-92d2-ac11a7e9e2e8.png)

- 실제 저장

![스크린샷 2021-10-29 오전 3 12 29](https://user-images.githubusercontent.com/37436822/139311999-1feaa111-869c-48b9-aa8a-8b5275fccedb.png)

### SQL
- 공지사항 생성 테이블
```SQL
create table notice (
    notice_id bigint not null auto_increment primary key,
    created_date datetime(6),
    modified_date datetime(6),
    start_date datetime(6),
    end_date datetime(6),
    content TEXT not null,
    hit int,
    title varchar(500) not null
);
```

- 파일 저장 테이블
```SQL
create table file_load(
    file_id bigint not null auto_increment primary key,
    notice_id bigint not null,
    real_filename varchar(150),
    filename varchar(150),
    file_path varchar(300)
);
```




