package com.example.demo.event;

import com.example.demo.context.Board;

/**
 * 특수 규칙에 의해서 무조건 이동해야하는 상황애서 사용합니다.
 * @param from 이동할 대상의 위치
 * @param to 이동할 위치
 */
public record MoveActionEvent(
        Board.Location from,
        Board.Location to
) implements Event{
}
