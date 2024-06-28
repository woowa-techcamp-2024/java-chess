# be-chess

2024 우아한 테크캠프 7기 체스 프로젝트

## 주의할 점

- Lucas 컨텐츠의 내용을 공개된 저장소 또는 웹에 올리는 것은 엄격하게 금지합니다.
- 커밋메시지는 한글로 두 줄 이상 작성합니다.
- MVC 패턴은 가급적 지양합니다.

## 구현

- 사용 자바 버전: 17
- 실행 방법: Gradle 빌드 후 `java -jar ./java-chess/build/libs/java-chess-1.0-SNAPSHOT.jar` 실행!

### 📸 구현 사진

- 시작 상태

<img width="456" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/5f5b6904-5abb-45a6-a8ac-5fc353217620">

- 기물 선택 및 이동 (드래그)

<img width="228" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/53c18be3-d129-49fa-9959-813f11d1da56">
<img width="228" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/80ef7fcb-d55d-4f94-a183-f5180b08e797">

- 자신의 턴이 아닌 경우 예외가 발생합니다!

<img width="456" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/f5fc9bb5-d874-499b-bdd1-d2eb6febe9a6">

- 체크 메이트시 게임이 종료됩니다.

<img width="456" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/7d405786-0370-42a3-a1af-440e67fb5e97">

### 📢 구현과 관련된 재잘재잘

- 😭아쉽게 구현하지 못한 것들

    - 앙파상, 캐슬링, 프로모션 등의 특수한 규칙들


- `javax.swing` 을 학부 이후로 처음 사용해봤습니다..
- 도메인 기반 지식을 좀더 학습하고 프로젝트에 임했다면 하는 포인트를 늦게나마 알게되서 조금 아쉽습니다.

    - 체스 말 움직임을 로그로 저장해두면 `앙파상`이나 되돌리기 등을 쉽게 구현이 가능하다.
    - 말이 움직일 때마다 `체크` 상태에 대한 확인이 필요하다.
    - 등등..

- 너무 구현에 대해 고민하는 것보다, 일단 질러놓고 리팩토링 하는 방식이 효율적일 때도 있구나! 를 느꼈습니다.

    - 물론 구현에 대한 고민도 중요하다고 생각해요!
