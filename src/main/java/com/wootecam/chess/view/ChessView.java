package com.wootecam.chess.view;

import static com.wootecam.chess.utils.AnsiCode.BLUE;
import static com.wootecam.chess.utils.AnsiCode.BOLD;
import static com.wootecam.chess.utils.AnsiCode.RED;
import static com.wootecam.chess.utils.AnsiCode.RESET;
import static com.wootecam.chess.utils.StringUtils.BLANK;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.utils.StringUtils;
import java.util.List;
import java.util.stream.Collectors;

public class ChessView {
    private static final String GREET_MESSAGE = """
            체스 게임을 시작합니다.
            start를 입력하여 게임을 시작할 수 있습니다.
            end를 입력하여 게임을 종료할 수 있습니다.
            move a1 a2와 같이 입력하여 기물을 움직일 수 있습니다.
            """;
    private static final String FILE_IDENTIFIERS = "  a b c d e f g h";

    public void printGreetMessage() {
        System.out.println(GREET_MESSAGE);
    }

    public void printChessBoard(Board board) {
        List<List<Piece>> curState = board.getCurrentState();

        StringBuilder curStateResponse = new StringBuilder();

        curStateResponse.append(BOLD).append(RED).append(FILE_IDENTIFIERS).append(RESET)
                .append(StringUtils.NEW_LINE);

        for (int i = 0; i < curState.size(); i++) {
            int rowNumber = 8 - i;

            curStateResponse.append(BOLD).append(BLUE).append(rowNumber).append(RESET).append(BLANK);

            String rowString = curState.get(i).stream()
                    .map(Piece::getRepresentation)
                    .map(rp -> rp.value)
                    .collect(Collectors.joining(BLANK));

            curStateResponse.append(rowString).append(BLANK);
            curStateResponse.append(BOLD).append(BLUE).append(rowNumber).append(RESET).append(StringUtils.NEW_LINE);
        }

        curStateResponse.append(BOLD).append(RED).append(FILE_IDENTIFIERS).append(RESET);

        System.out.println(curStateResponse);
    }
}
