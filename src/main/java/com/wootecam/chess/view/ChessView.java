package com.wootecam.chess.view;

import static com.wootecam.chess.utils.AnsiCode.BLUE;
import static com.wootecam.chess.utils.AnsiCode.BOLD;
import static com.wootecam.chess.utils.AnsiCode.RED;
import static com.wootecam.chess.utils.AnsiCode.RESET;
import static com.wootecam.chess.utils.StringUtils.BLANK;

import com.wootecam.chess.game.BoardState;
import com.wootecam.chess.game.ChessResult;
import com.wootecam.chess.io.Reader;
import com.wootecam.chess.io.Writer;
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
    private static final String PROMPT = ">>> ";
    private static final String FILE_IDENTIFIERS = "  a b c d e f g h";
    private static final String RESULT_MESSAGE = """
            흰색 기물 점수: %f
            검은색 기물 점수: %f
            승자: %s
            """;

    private final Reader reader;
    private final Writer writer;

    public ChessView(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void printGreetMessage() {
        writer.printLine(GREET_MESSAGE);
    }

    public String readInput() {
        writer.print(PROMPT);
        return reader.readLine();
    }

    public void printChessBoard(BoardState boardState) {
        List<List<Piece>> curState = boardState.boardState();

        StringBuilder message = new StringBuilder();

        message.append(BOLD).append(RED).append(FILE_IDENTIFIERS).append(RESET)
                .append(StringUtils.NEW_LINE);

        for (int i = 0; i < curState.size(); i++) {
            int rowNumber = 8 - i;

            message.append(BOLD).append(BLUE).append(rowNumber).append(RESET).append(BLANK);

            String rowString = curState.get(i).stream()
                    .map(Piece::getRepresentation)
                    .map(rp -> rp.value)
                    .collect(Collectors.joining(BLANK));

            message.append(rowString).append(BLANK);
            message.append(BOLD).append(BLUE).append(rowNumber).append(RESET).append(StringUtils.NEW_LINE);
        }

        message.append(BOLD).append(RED).append(FILE_IDENTIFIERS).append(RESET);

        writer.printLine(message);
    }

    public void printChessResult(ChessResult result) {
        String message = String.format(RESULT_MESSAGE, result.whiteScore(), result.blackScore(), result.winner());

        writer.printLine(message);
    }

    public void printErrorMessage(String errorMessage) {
        writer.printLine(errorMessage);
    }
}
