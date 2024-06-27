package chess.chess;

import chess.chess.piece.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardUtils {

    public static BoardContext createBoardContext(Board board, Position position) {
        return new BoardContextImpl((offset) -> {
            Position targtPosition = position.add(offset);
            if (!targtPosition.isValid()) return null;
            return board.get(targtPosition);
        });
    }

    /**
     * 8*8 크기의 문자열로부터 체스판을 생성합니다.
     * 대문자는 BLACK, 소문자는 WHITE를 나타냅니다.
     * (체스 규칙 상 위쪽이 BLACK)
     * <ul>
     *     <li>K, k: King</li>
     *     <li>Q, q: Queen</li>
     *     <li>R, r: Rook</li>
     *     <li>B, b: Bishop</li>
     *     <li>N, n: Knight</li>
     *     <li>P, p: Pawn</li>
     *     <li>.: (empty)</li>
     * </ul>
     * <pre>
     * 8 ........
     * 7 ........
     * 6 ........
     * 5 ........
     * 4 ........
     * 3 ........
     * 2 ........
     * 1 ........
     *   abcdefgh
     * </pre>
     */
    public static Board createBoard(String state) {
        assertThat(state).hasLineCount(Board.LENGTH);
        assertThat(state.lines()).allSatisfy(line -> assertThat(line).hasSize(Board.LENGTH));

        String[] lines = state.lines().toArray(String[]::new);
        Board board = new Board();
        for (int r = 0; r < Board.LENGTH; r++) {
            for (int c = 0; c < Board.LENGTH; c++) {
                char repr = lines[Board.LENGTH - 1 - r].charAt(c);
                Piece piece = getPieceFromRepr(repr);
                board.set(Position.of(r, c), piece);
            }
        }
        return board;
    }

    private static Piece getPieceFromRepr(char repr) {
        return switch (repr) {
            case 'P' -> Pawn.createBlack();
            case 'p' -> Pawn.createWhite();
            case 'N' -> Knight.createBlack();
            case 'n' -> Knight.createWhite();
            case 'B' -> Bishop.createBlack();
            case 'b' -> Bishop.createWhite();
            case 'R' -> Rook.createBlack();
            case 'r' -> Rook.createWhite();
            case 'Q' -> Queen.createBlack();
            case 'q' -> Queen.createWhite();
            case 'K' -> King.createBlack();
            case 'k' -> King.createWhite();
            default -> null;
        };
    }
}
