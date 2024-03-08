# 📚 스프링 MSA 기술 스터디

> 스프링 MSA와 관련 기술에 논의하고 실습하는 프로젝트입니다.

## 📒 진행 방식

- **매주 3회** 스터디를 진행한다.
- **매주 2회**회는 비 정규적으로 각자 개발한 부분을 라이브로 리뷰하며 공유합니다.
- **매주 토요일** 오프라인으로 모여 스터디를 진행합니다.
    - 각자 학습한 내용을 발표하고 피드백을 받습니다.
    - 스터디를 진행하면서 발생한 이슈를 해결하고 공유합니다.

## 📅 스터디 일정

- 약 6개월간 진행합니다.

## 😀 스터디 참여자

<table>

<tr>
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
  <a href="https://github.com/Java-kokyu">
  <img src="https://avatars.githubusercontent.com/u/85613861?v=4" width="100px"  />
  <br/>
  Back-end 🖥
  <br/>
  Java-kokyu
  </a>
  </td>

  <td align=center>
  <a href="https://github.com/learnGrowD">
  <img src="https://avatars.githubusercontent.com/u/104897960?v=4" width="100px"  />
  <br/>
  Back-end 🖥
  <br/>
  learnGrowD
  </a>
  </td>
</tr>
</table>

# 🔍 프로젝트 설치 및 실행 방법

## 요구 사항

- Java Development Kit (JDK) 17
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

3. Maven을 사용하여 프로젝트를 빌드합니다:

```bash
./gradlew docker
docker-compose up -d
```

4. Postman을 사용하여 API를 테스트합니다.

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://god.gw.postman.com/run-collection/11284346-749022bb-df2b-4deb-bfb7-3c0c2cd247a8?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D11284346-749022bb-df2b-4deb-bfb7-3c0c2cd247a8%26entityType%3Dcollection%26workspaceId%3Dfd9e4cc7-7ad5-4b10-a541-37c9f79e5c66)

## 주의 사항

- 프로젝트를 실행하기 전에 `src/main/resources/application.properties` 파일에서 필요한 데이터베이스 연결 정보 등을 설정해야 합니다.
- Docker를 사용하여 서비스를 실행하는 경우 Docker Desktop 또는 Docker Engine이 로컬에 설치되어 있어야 합니다.
- Docker Compose를 사용하여 관련 서비스를 실행하려면 Docker Compose가 로컬에 설치되어 있어야 합니다.

## 스터디 조인 방법

- 이 저장소 어딘가에 흔적을 남겨주시면 저희가 연락 드리겠습니다.
