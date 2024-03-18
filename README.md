# 📚 스프링 기술 스터디

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

** Team Lucycato DNA
 - 열정
 - 희생, 헌신

** Team Lucycato Vision
- 탁월한 기준에 근거한 코드를 작성하는 본질적인 실력향상
- 탁월한 기준과 안목의 초석이 되는 CS 학습
- PR (문서화 블로그 글 작성) 

** 계획과 전략
   - 토요일 09:00 ~ 19:00(오프라인) / 장소 : 합정역
   - 화요일 21:00 ~ 00:00 (온라인)  / 디스코드

1부: 세상에 나아가기 전에 진정으로 훌륭한 코드를 작성해보자.
** Local Environment Development
1. MSA 
	- 어떻게 경계를 나눌 것인가: Business, Domain
2. Mono Repository
	- 각 팀의 상황에 적합한 프로젝트 구성 전략
	- Multi Module 전략 with Gradle
3. Docker, Docker Compose
	- 서버를 분리해보자.
4. Hexagonal Architecture 
	- 좋은 아키텍처는 코드를 보는 좋은 눈을 길러준다.
	- Hexagonal Architecture vs Layered Architecture
5. Webflux: 
	- 코드를 작성하는 훌륭한 패러다임은 훌륭한 코드를 작성하는 눈을 길러준다.
	- 명령형 프로그래밍 vs 반응형 프로그래밍
	- CPU Bounds, I / O Bounds
	- Reactor
	- Tomcat vs Netty
6. Kafka
	- 비동기 통신을 통해 여러 서버를 효율적으로 활용하자.
	- 안정적이고 효율적인 Kafka Server 구성 전략 확립
7. Redis
    - 강력한 성능 확보 전략인 Memory Caching을 활용하자.
    - Session Storage, Rank, Geo 등 다양한 활용 전략을 구축하자.
8. CQRS Pattern
	- 결국 물리적 분해를 통한 성능 확보 어떻게 정합성을 맞출 것인가
9. MSA Transaction
	- 분리를 통해 생긴 문제 어떻게 해결할 것인가
	- 2 Phase Commit, 보상트랜젝션, Saga
10. Spring Batch
	- Batch의 철학과 원리를 이해하고 다양한 활용 전략을 구축하자.
11. Spring Security, Cookie, Session, JWT, Oauth2  with Gateway, Custom Auth Server
	- MSA 환경에서의 인증 / 인가를 이해하자
	- 자원 서버를 이해하자.
	- 시스템의 보안을 높이는 방법에 대해 연구하자.
12. Spring Gateway, Eureka
	- 가장 강력한 자원 활용 방법인 Load Balancing을 이해하자.
13. 통합테스트, 단위 테스트, 기능 테스트
	- 안정적인 시스템, 리팩토링을 할 수 있는 시스템을 개발해보자.
14. Monitoring
	 - ELK
	 - 메트릭 정보를 수집해서 경향을 파악하자
	 - Log 정보를 통해 시스템의 문제 및 취약점을 추적하자
15. 결국엔 기초
	- class, function naming 작명 규칙, 전략
	- OOP, SOLID, Clean Architecture, Refactoring 기준 학습
	- Endpoint 설계 원칙 
	- git branch 분리 전략, git commit, pull request 전략

2부: 세상에 나아가자.
** infra
1. Kubernetes, Helm (Proxy Server, Load Balancer)
2. AWS 다양한 서비스
3. CI / CD (Jenkins, Argo CD) : 자동화 파이프라인을 구축하자
4. Graceful Shutdown, Zero Downtime Deployment 이해하고 자주 테스트하고 배포하자.

3부: 진정으로 탁월한 기준을 가지고 싶거든 탁월한 기준을 찾아 나서라.
** Finding a Mentor

## 🧑‍💻 스터디 참여자

<table>

<tr>
  <td align=center>
    <a href="https://github.com/learnGrowD">
      <img src="https://avatars.githubusercontent.com/u/104897960?v=4" width="100px"  />
      <br/>
      Back-end 🖥
      <br/>
      learnGrowD
    </a>
  </td>
  <td align=center>
    <a href="https://github.com/aihoshistar">
      <img src="https://avatars.githubusercontent.com/u/45850400?v=4" width="100px"  />
      <br/>
      Back-end 🖥
      <br/>
      aihoshistar
    </a>
  </td>
  <td align=center>
    <a href="https://github.com/Jjinyshin">
      <img src="https://avatars.githubusercontent.com/u/87403267?v=4" width="100px"  />
      <br/>
      Back-end 🖥
      <br/>
      Nyeong
    </a>
  </td>
</tr>

<tr>
  <td align=center>
    <a href="https://github.com/Java-kokyu">
      <img src="https://avatars.githubusercontent.com/u/85613861?v=4" width="100px"  />
      <br/>
      Back-end 🖥
      <br/>
      Java-kokyu
    </a>
  </td>
  <td align=center>
    <a href="https://github.com/witwint">
      <img src="https://avatars.githubusercontent.com/u/108222981?v=4" width="100px" />
      <br/>
      Back-end 🖥
      <br/>
      SEOKBEOM MOON
    </a>
  </td>

  </td>
    <td align=center>
    <a href="https://github.com/lmw313">
      <img src="https://avatars.githubusercontent.com/u/20276615?v=4" width="100px" />
      <br/>
      Back-end 🖥
      <br/>
      이민우
    </a>
  </td>
</tr>
</table>

# 🔍 프로젝트 설치 및 실행 방법

## 요구 사항

- Java Development Kit (JDK) 17
- Gradle 8.5
- Docker 및 Docker Compose (필수 사항)

## 설치 및 실행

1. 이 저장소를 클론합니다:

```bash
git clone git@github.com:lucycato-backend/big-tech.git
```

2. 프로젝트 디렉토리로 이동합니다:

```bash
cd big-tech
```

3. Docker Plug in을 사용하여 프로젝트를 빌드 및 이미지를 생성합니다:

```bash
./gradlew docker
docker-compose up -d
```

4. Postman을 사용하여 API를 테스트합니다.

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://god.gw.postman.com/run-collection/11284346-749022bb-df2b-4deb-bfb7-3c0c2cd247a8?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D11284346-749022bb-df2b-4deb-bfb7-3c0c2cd247a8%26entityType%3Dcollection%26workspaceId%3Dfd9e4cc7-7ad5-4b10-a541-37c9f79e5c66)

## 주의 사항

- Docker를 사용하여 서비스를 실행하는 경우 Docker Desktop 또는 Docker Engine이 로컬에 설치되어 있어야 합니다.
- Docker Compose를 사용하여 관련 서비스를 실행하려면 Docker Compose가 로컬에 설치되어 있어야 합니다.

## 스터디 조인 방법

기술 스택과 계획을 봤을 때 만만치 않습니다. 헌신과 희생 없이는 우리의 계획과 목표를 이룰 수 없습니다. 그러나 희생하고 헌신한다면 불가능은 없습니다.
또한 혼자서 위대한 일을 할 수 없지만, 팀이면 위대한 일이 가능하다는 말처럼 팀(규율이 있는)이라면 가능합니다.

우리의 비전에 공감이 가고 진정으로 개발을 잘하고 싶다. 탁월한 개발자가 되고 싶다는 야망을 가진 개발자,
우리의 계획에 희생하고 헌신할 수 있는 개발자라면 과감히 우리 Team Lucycato에 합류해 주세요. 감사합니다.

** 지금 현재 실력이 탁월하진 않지만 노력해서 나는 팀에 도움이 될 수 있다.
나는 결국엔 노력해서 잘할 수 있다는 분이라면 과감히 지원해주세요.
현재 부족한 분들은 제가 기본 개념에 대해서 강의를 해드리고 있습니다. (앞으로 합류할 인원들을 위해 강의 촬영도 기획 중입니다.)
그러니 열정이 있고 포기하지 않는 근성을 가진 분이라면 과감히 연락주세요.

- Team Lucycato 리더 도학태 전화번호 : 010-8705-1693
- Team Lucycato github: https://github.com/lucycato-backend
