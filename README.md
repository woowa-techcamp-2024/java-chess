# be-chess

2024 우아한 테크캠프 7기 체스 프로젝트

## 주의할 점

- Lucas 컨텐츠의 내용을 공개된 저장소 또는 웹에 올리는 것은 엄격하게 금지합니다.
- 커밋메시지는 한글로 두 줄 이상 작성합니다.
- MVC 패턴은 가급적 지양합니다.

## 구현

### 📸 구현 사진

- 시작 상태

<img width="456" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/d7b5afc9-425f-4501-9522-5d3ab0a21971">

- 기물 선택 및 이동 (드래그)

<img width="228" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/4dbdfe04-f827-43a5-a89f-80285c8d70d4">
<img width="228" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/88d742d9-7a97-46cd-aa92-a20e6e3fdea6">

- 자신의 턴이 아닌 경우 예외가 발생합니다!

<img width="456" alt="image" src="https://github.com/tidavid1/java-chess/assets/85854384/a5ef60c0-3a94-49af-a7b1-87b18ce8855f">

### 📢 구현과 관련된 재잘재잘

- 😭아쉽게 구현하지 못한 것들

    - 체크 상황에 대한 처리
    - 앙파상, 캐슬링, 프로모션 등의 특수한 규칙들


- `javax.swing` 을 학부 이후로 처음 사용해봤습니다..
- 도메인 기반 지식을 좀더 학습하고 프로젝트에 임했다면 하는 포인트를 늦게나마 알게되서 조금 아쉽습니다.

    - 체스 말 움직임을 로그로 저장해두면 `앙파상`이나 되돌리기 등을 쉽게 구현이 가능하다.
    - 말이 움직일 때마다 `체크` 상태에 대한 확인이 필요하다.
    - 등등..

- 너무 구현에 대해 고민하는 것보다, 일단 질러놓고 리팩토링 하는 방식이 효율적일 때도 있구나! 를 느꼈습니다.

    - 물론 구현에 대한 고민도 중요하다고 생각해요!
