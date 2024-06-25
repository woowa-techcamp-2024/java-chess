package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static chess.utils.StringUtils.appendNewLine;

public class Board {

    private final List<List<Piece>> pieces;
    private int pieceCount;

    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;
    private static final int WHITE_PAWN_ROW = 1;
    private static final int BLACK_PAWN_ROW = 6;
    private static final int INITIAL_PIECE_COUNT = 32;
    private static final int BLACK_FIRST_ROW = 0;
    private static final int WHITE_FIRST_ROW = 7;

    private static final String EMPTY_ROW = "........";


    public void initialize() {
        pieceCount = INITIAL_PIECE_COUNT;
        initializeWhiteFirstRow();
        initializeBlackFirstRow();
        initializeWhitePawns();
        initializeBlackPawns();
    }

    private void initializeBlackFirstRow() {
        List<Piece> blackFirstRow = pieces.get(BLACK_FIRST_ROW);
        blackFirstRow.add(Piece.createBlackRook());
        blackFirstRow.add(Piece.createBlackKnight());
        blackFirstRow.add(Piece.createBlackBishop());
        blackFirstRow.add(Piece.createBlackQueen());
        blackFirstRow.add(Piece.createBlackKing());
        blackFirstRow.add(Piece.createBlackBishop());
        blackFirstRow.add(Piece.createBlackKnight());
        blackFirstRow.add(Piece.createBlackRook());
    }

    private void initializeWhiteFirstRow() {
        List<Piece> whiteFirstRow = pieces.get(WHITE_FIRST_ROW);
        whiteFirstRow.add(Piece.createWhiteRook());
        whiteFirstRow.add(Piece.createWhiteKnight());
        whiteFirstRow.add(Piece.createWhiteBishop());
        whiteFirstRow.add(Piece.createWhiteQueen());
        whiteFirstRow.add(Piece.createWhiteKing());
        whiteFirstRow.add(Piece.createWhiteBishop());
        whiteFirstRow.add(Piece.createWhiteKnight());
        whiteFirstRow.add(Piece.createWhiteRook());
    }

    // TODO 현재 빈 줄을 EMPTY_ROW로 출력하지만, 이건 나중에 변경 필요
    public String print() {
        StringBuilder sb = new StringBuilder();

        for (List<Piece> piece : pieces) {
            if(piece.isEmpty()) {
                sb.append(EMPTY_ROW);
                appendNewLine(sb);
                continue;
            }
            piece.stream()
                    .map(Piece::getRepresentation)
                    .forEach(sb::append);
            appendNewLine(sb);
        }
        return sb.toString();
    }

    public int pieceCount() {
        return pieceCount;
    }

    public String showBoard() {
        return print();
    }

    protected Board() {
        pieces = new ArrayList<>();
        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i -> pieces.add(new ArrayList<>()));
    }

    private void initializeWhitePawns() {
        ArrayList<Piece> initializedPieces = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> Piece.createWhitePawn())
                .collect(Collectors.toCollection(ArrayList::new));
        this.pieces.get(WHITE_PAWN_ROW).addAll(initializedPieces);
    }

    private void initializeBlackPawns() {
        ArrayList<Piece> initializedPieces = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> Piece.createBlackPawn())
                .collect(Collectors.toCollection(ArrayList::new));
        this.pieces.get(BLACK_PAWN_ROW).addAll(initializedPieces);
    }
}
