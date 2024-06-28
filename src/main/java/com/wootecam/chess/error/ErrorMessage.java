package com.wootecam.chess.error;

public enum ErrorMessage {

    UNSUPPORTED_COMMAND("지원하지 않는 명령입니다."),
    NO_GAME_STARTED("게임을 먼저 시작해주세요."),
    INVALID_POSITION("유효하지 않은 위치입니다."),
    PIECE_CANNOT_MOVE("이동할 수 없는 위치입니다."),
    PIECE_CANNOT_FOUND("해당 위치에 기물이 존재하지 않습니다."),
    INVALID_TURN("현재 턴이 아닙니다."),
    ;

    public final String value;

    ErrorMessage(String value) {
        this.value = value;
    }
}
