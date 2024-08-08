![Blue and White Modern Management Thesis Defense Presentation (2)](https://github.com/user-attachments/assets/4fda19ea-38c1-4446-9754-527e79d9d73a)

<br><br>

# 💻 내배캠 수강생 관리 프로그램

### 캠프 수강생을 관리하는 프로젝트로 캠프 매니저가 학생의 수강과목과 점수를 관리하는 프로그램입니다.

<br><br><br>



# 📍 프로그램 디렉토리 구조
<pre>
camp
├── controller
│   ├── ScoreController.java
│   ├── StudentController.java
├── manager
│   ├── ScoreManager.java
│   ├── StudentManager.java
├── model
│   ├── Score.java
│   ├── Student.java
│   ├── Subject.java
├── repository
│   ├── ScoreRepository.java
│   ├── StudentRepository.java
│   ├── SubjectRepository.java
├── view
│   ├── ScoreView.java
│   ├── StudentView.java
└── CampManagementApplication.java
</pre>


<br><br>

## ⌨ 프로그램 구성

### 수강 과목

| 필수 과목 목록 | 선택 과목 목록 |
| --- | --- |
| Java | 디자인 패턴 |
| 객체지향 | Spring Security |
| Spring | Redis |
| JPA | MongoDB |
| MySQL |

<br>

### 등급 산정 기준

| 과목 유형 | A | B | C | D | F | N |
| --- | --- | --- | --- | --- | --- | --- |
| 필수 과목 | 95 ~ 100 | 90 ~ 94 | 80 ~ 89 | 70 ~ 79 | 60 ~ 69 | 60점 미만 |
| 선택 과목 | 90 ~ 100 | 80 ~ 89 | 70 ~ 79 | 60 ~ 69 | 50 ~ 59 | 50점 미만 |


<br><br>

## ⌨ 기능 명세서

### 수강생 관리

| 기능 | 상세 내용 |
| --- | --- |
| **수강생 정보 등록** | 고유 번호, 이름, 과목 목록 |
| **수강생 목록 조회** | 고유 번호, 이름 |
| **수강생 상태 관리** | Green, Red, Yellow |
| **수강생 정보 조회** | 고유 번호, 이름, 상태, 선택한 과목명 |
| **수강생 정보 수정** | 이름, 상태 |
| **상태별 수강생 목록 조회** | 고유 번호, 이름 |
| **수강생 삭제** |  |

<br>

### 점수 관리

| 기능 | 상세 내용 |
| --- | --- |
| **과목별 평균 등급 조회** | 과목명, 평균 등급 |
| **특정 상태 수강생 필수 과목 평균 등급 조회** | 수강생 이름, 필수 과목 평균 등급 |
| **수강생 점수 조회** | 회차, 등급 |

<br><br>

## 프로젝트 구조

![title](https://teamsparta.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F83c75a39-3aba-4ba4-a792-7aefe4b07895%2F21a1b188-1ace-4090-bdc4-36a137632bcd%2FUntitled_diagram-2024-08-07-230712.png?table=block&id=b4c60ccd-dc30-4daa-968b-13bdab2e27df&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=&cache=v2)

<br> 

## 시퀀스 다이어그램

![411f8687-ad8b-426a-b0c8-f4550c25cbf2](https://github.com/user-attachments/assets/6dce5104-79e3-4404-b52c-83138c40156e)
