# 📚 스프링 기술 스터디

## 요구 사항

- JDK 17
- Gradle 8.5
- Docker, Docker Compose

## 🔍 설치 및 실행

1. 이 저장소를 클론합니다:

```bash
git clone git@github.com:lucycato-backend/lucycato-e-commerce.git
```

2. 프로젝트 디렉토리로 이동합니다:

```bash
cd lucycato-e-commerce
```

3. Docker plug-in, Docker Compose를 사용하여 프로젝트를 빌드 및 이미지를 생성, 실행합니다.

```bash
./gradlew docker &&
 docker compose -f docker-compose-db.yml up -d &&
 docker compose -f docker-compose-kafka.yml up -d &&
 docker compose -f docker-compose-infra.yml up -d &&
 docker compose up -d
```

4. Postman을 사용하여 API를 테스트합니다.

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://god.gw.postman.com/run-collection/11284346-749022bb-df2b-4deb-bfb7-3c0c2cd247a8?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D11284346-749022bb-df2b-4deb-bfb7-3c0c2cd247a8%26entityType%3Dcollection%26workspaceId%3Dfd9e4cc7-7ad5-4b10-a541-37c9f79e5c66)

## 🍎 Team Lucycato 소개
안녕하세요 Team Lucycato 리더 도학태입니다.
Team Lucycato는 스타트업, 금융업에 종사하는 다양한 백엔드 개발자로
이루어진 스터디 모임입니다. 우리는 매주 정기적으로 온라인, 오프라인 모임을 통해
Spring Framework 기반으로 최신 기술 스택 학습과, 본질적인 실력향상을 위해
좋은 코드를 연구하고 분석하는 스터디를 진행하고 있습니다.

진정으로 실력 있는 개발자가 되기 위해 어떻게 해야 할까요?
더 나아가 개발자로서 탁월한 기준과 안목을 가지고 더 좋은 코드를 짜기 위해 우리는 무엇을 해야 할까요?
그렇다면 도대체 왜 탁월한 기준과 안목을 가지고 더 좋은 코드를 작성해야 할까요?
 
개발자로서 갖춰야 할 여러 능력이 있지만 결국 우리는 시스템을 설계하고, 만들고, 유지 할 수 있어야 합니다
또한 우리가 만든 시스템은 세상에 나아가 비전과 가치를 전달하고 영향력을 발휘할 수 있어야 합니다.

하지만 아쉽게도 시스템이 견고하게 세상에 영향력을 발휘하는 것은 쉬운 일이 아닙니다. 정확하게 누구나 다 그렇게 할 수 없습니다.
빈약한 시스템을 만드는 그저 그런 코드는 누구나 다 작성할 수 있지만 견고한 시스템을 만드는 명확한 체계(즉, 설계)와
기준을 가진 코드는 누구나 다 작성할 수 없기 때문입니다.

'개발은 공예와 같다.' 클린코드의 저자는 말했습니다.
개발자는 끊임없이 다듬고 연구해야 한다는 중요한 교훈이 담긴 말입니다.
결국 우리는 실력 있는 개발자가 되기 위해 끊임없이 연구하고 분석해야 합니다.
더 나아가 탁월한 기준, 안목, 식견을 잘 정립해야 합니다. 이것이 진정으로
훌륭한 개발자가 되는 유일한 길일 것입니다.

그래서 저는 진정으로 훌륭한 개발자가 되기 위한 계획을 준비했습니다.
우리 Team Lucycato는 아래의 계획을 기반으로 학습을 진행하고 있습니다.

## Team Lucycato DNA
 - 열정, 희생, 헌신
 - 세상에 나아가자

## Team Lucycato Vision
- 탁월한 기준에 근거한 코드를 작성하는 본질적인 실력향상
- 탁월한 기준과 안목의 초석이 되는 CS 학습
- PR (블로그 글작성 및 영상촬영)

## 계획
### * 1부: 세상에 나아가기 전에 진정으로 훌륭한 코드를 작성해보자. (Local Environment Development)
1. MSA 
	- 어떻게 경계를 나눌 것인가: Business, Domain
2. Hexagonal Architecture 
	- 좋은 아키텍처는 코드를 보는 좋은 눈을 길러준다.
	- Hexagonal Architecture vs Layered Architecture
3. WebFlux: 
	- 코드를 작성하는 훌륭한 패러다임은 훌륭한 코드를 작성하는 눈을 길러준다.
	- 명령형 프로그래밍 vs 반응형 프로그래밍
	- CPU Bounds, I / O Bounds
	- Reactor
	- Tomcat vs Netty
4. Kafka
	- 비동기 통신을 통해 여러 서버를 효율적으로 활용하자.
	- 안정적이고 효율적인 Kafka Server 구성 전략 확립
5. MySQL / JPA / QueryDSL
    - RDB의 내부원리와 특징을 이해하여 성능 확보 전략을 구축해보자.
    - RDB를 잘 활용하기 위한 여러 도구들을 학습하자.
6. Redis
    - 강력한 성능 확보 전략인 Memory Cache 전략을 구축하자.
    - Session Storage, Rank, Geo 등 활용 사례 및 전략을 구축하자. 
7. Spring Batch
	- Batch의 철학과 원리를 이해하고 다양한 활용 전략을 구축하자.  
8. MSA Transaction
	- 분리를 통해 생긴 문제 어떻게 해결할 것인가
	- 2 Phase Commit, 보상트랜젝션, Saga
9. MongoDB / Elasticsearch
    - 여러 데이터베이스의 동작 원리를 이해하자
    - 여러 데이터베이스의 특징 및 장단점을 이해하여 언제 사용하는것이 적합한지 전략을 구축해보자
10. Kotlin
    - vs Java
    - null-safety
    - coroutine
11. 통합테스트, 단위 테스트, 기능 테스트
    - 안정적인 시스템, 리팩토링을 할 수 있는 시스템을 개발해보자.
12. Spring Security, Cookie, Session, JWT, Oauth2  with Gateway, Custom Auth Server
	- MSA 환경에서의 인증 / 인가를 이해하자
	- 자원 서버를 이해하자.
	- 시스템의 보안을 높이는 방법에 대해 연구하자.
13. 결국엔 기초
    - RESTFul 설계, OOP, SOLID, Clean Architecture
    - Class, Function Elastic Naming

### * 2부: 세상에 나아가자. (Infra)
1. Docker, Docker Compose
	- 서버를 분리해보자.
2. Mono Repository
	- 각 팀의 상황에 적합한 프로젝트 구성 전략
	- Multi Module 전략 with Gradle
3. Spring Gateway, Eureka
	- 가장 강력한 자원 활용 방법인 Load Balancing을 이해하자.
4. Monitoring
	- Grafana, Prometheus, Loki, Tempo
	- 메트릭 정보를 수집해서 경향을 파악하자
	- Log 정보를 통해 시스템의 문제 및 취약점을 추적하자
5. Kubernetes, Helm (Proxy Server, Load Balancer)
6. AWS 다양한 서비스
7. CI / CD (Jenkins, Argo CD) : 자동화 파이프라인을 구축하자
8. Graceful Shutdown, Zero Downtime Deployment 이해하고 자주 테스트하고 배포하자.

### * 3부: 진정으로 탁월한 기준을 가지고 싶거든 탁월한 기준을 찾아 나서라. (Finding a Mentor)
    - 기획중
### * Domain: e-commerce
	1. Common 구축
	2. Gateway, Auth
		- 사용 기술: Java, Hexagonal, WebFlux, MySQL, R2DBC, JWT, Passport
		- 집중: JWT, Passport
	3. 회원
		- 사용 기술: Java, Hexagonal, MVC, MySQL, JPA / QueryDSL, Redis Cash
		- 집중: Hexagonal, JPA / QueryDSL, Redis Cash
	4. 상품 (선생님, 강의, 교재, 리뷰)
		- 사용 기술: Java, Hexagonal, WebFlux, MySQL, R2DBC
		- 집중: WebFlux, R2DBC
	5. 나의 강의실(강의 수강, 리뷰)
		- 사용 기술: Java, Hexagonal, WebFlux, R2DBC, Redis, Kafka
		- 집중: Redis, kafka
	6. 알림
		- 사용 기술: Java, WebFlux, R2DBC, Kafka, Batch
		- 집중: Batch
		- 부가적: SMS, FCM, DynamicLink 
	7. 주문 / 결재
		- 사용 기술: Kotlin, WebFlux, MongoDB, Transaction
		- 집중: Kotlin, MongoDB, Transaction
	8. 검색
		- 집중: Elastic Search

## 일정
- 토요일 09:00 ~ 18:00 (오프라인) / 장소: 홍대입구역
- 화요일 21:00 ~ 00:00 (온라인) / 디스코드
- 루시카토 운영 WBS:
  - https://docs.google.com/spreadsheets/d/1_1T-mYBjX9wD2YPES2RJ84CgiRahHYf481Lmy7PeZpM/edit#gid=0
- 루시카토 프로젝트 영상촬영 WBS:
  - https://docs.google.com/spreadsheets/d/1Rid6wIcEXjY4oSuBLqBiuCgG3K82HYU9FAUyZLhkwK4/edit

## 체계
✅ 토요일 루틴:
- 9:00 ~ 9:20
  - Lucycato WBS, Vision Check
- 9:20 ~ 10:00
  - CS O / X 문제 풀이 및 해설
- 10:00 ~ 13:00
  - 블로그 발표 + 질의응답
- 13:00 ~ 14:00 
  - 점심시간
- 14:00 ~ 17:30 
  - 주요  
  - 구성권 과제 기반 코드리뷰
  - 각자 작성한 코드에 대한 리뷰 진행
- 17:30 ~ 18:00
  - 다음주 CS O / X 문제 과제 할당
  - 다음주 블로그 글 작성 Topic 산정
  - 다음주 Topic 기초개념 과제 할당
  - 다음주 개발 과제 할당
   
✅ 화요일 루틴:
- 필요에 의한 전략 회의 최대 00:00 까지 시간 할애

✅ 스터디 과제
- 규칙: 토요일 스터디 전까지 각자 준비해온다.
- 과제 목록
  1. O/X 문제 준비 핵심적인 CS 3문제 + 해설 준비해오기
  2. 각자 맡은 키워드를 주제로 블로그 작성해오기
  3. 영상 보기 & 이커머스 프로젝트 코드 리뷰해오기
     - learnGrowD: 프로젝트 소스코드 영상 제공 및 기본 코드 작성
     - aihoshistar: 인프라 영상 제공
  4. 각 주차별 토픽에 대한 기초지식 각자 예습해오기
  5. 각자 맡은 API 개발해오기
  6. 3 party 자료 조사해오기
     - ex) 구글 소셜로그인, MongoDB etc..

## 🧑‍💻 스터디 참여자
<table>

<tr>
  <td align=center>
    <a href="https://github.com/learnGrowD">
      <img src="https://avatars.githubusercontent.com/u/104897960?v=4" width="160px"  />
      <br/>
      back-end 🖥
      <br/>
      learnGrowD
    </a>
  </td>
  <td align=center>
    <a href="https://github.com/aihoshistar">
      <img src="https://avatars.githubusercontent.com/u/45850400?v=4" width="160px"  />
      <br/>
      back-end 🖥
      <br/>
      aihoshistar
    </a>
  </td>
  <td align=center>
    <a href="https://github.com/Jjinyshin">
      <img src="https://avatars.githubusercontent.com/u/87403267?v=4" width="160px"  />
      <br/>
      back-end 🖥
      <br/>
      Nyeong
    </a>
  </td>
</tr>

<tr>
  <td align=center>
    <a href="https://github.com/Java-kokyu">
      <img src="https://avatars.githubusercontent.com/u/85613861?v=4" width="160px"  />
      <br/>
      back-end 🖥
      <br/>
      Java-kokyu
    </a>
  </td>
  <td align=center>
    <a href="https://github.com/witwint">
      <img src="https://avatars.githubusercontent.com/u/108222981?v=4" width="160px" />
      <br/>
      back-end 🖥
      <br/>
      SEOKBEOM MOON
    </a>
  </td>

  </td>
    <td align=center>
    <a href="https://github.com/lmw313">
      <img src="https://avatars.githubusercontent.com/u/20276615?v=4" width="160px" />
      <br/>
      back-end 🖥
      <br/>
      이민우
    </a>
  </td>
</tr>
</table>

## 블로그 링크
- Lucycato: https://github.com/lucycato-backend/blog/wiki
- learnGrowD: https://velog.io/@will_d/posts
- aihoshistar: https://todo.so.tl/
- Nyeong: https://jinyshin.tistory.com/
- Java-kokyu: https://mindybughunter.com/
- SEOKBEOM MOON: https://plum-quilt-296.notion.site/bc5d97b915224b33b210543286a060a2
- 이민우: https://investment-challenge-developer.tistory.com/

## 스터디 조인 방법
기술 스택과 계획을 봤을 때 만만치 않습니다. 헌신과 희생 없이는 우리의 계획과 목표를 이룰 수 없습니다. 그러나 희생하고 헌신한다면 불가능은 없습니다.
또한 혼자서 위대한 일을 할 수 없지만, 팀이면 위대한 일이 가능하다는 말처럼 팀(규율이 있는)이라면 가능합니다.

우리의 비전에 공감이 가고 진정으로 개발을 잘하고 싶다. 탁월한 개발자가 되고 싶다는 야망을 가진 개발자,
우리의 계획에 희생하고 헌신할 수 있는 개발자라면 과감히 우리 Team Lucycato에 합류해 주세요. 감사합니다.

** 지금 현재 실력이 탁월하진 않지만 노력해서 나는 팀에 도움이 될 수 있다.
나는 결국엔 노력해서 잘할 수 있다는 분이라면 과감히 지원해주세요.
현재 부족한 분들은 제가 기본 개념에 대해서 강의를 해드리고 있습니다. (앞으로 합류할 인원들을 위해 강의 촬영도 기획 중입니다.)
그러니 열정이 있고 포기하지 않는 근성을 가진 분이라면 과감히 연락주세요.

- Team Lucycato 리더 도학태 전화번호: 010-8705-1693
- Team Lucycato github: https://github.com/lucycato-backend
