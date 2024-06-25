package com.seong.chess;

import static com.seong.chess.utils.StringUtils.appendNewLine;

import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private record Position(int col, int row) {

        private Position {
            validateRow(row);
            validateCol(col);
        }

        private static Position convert(String rawPosition) {
            int col = rawPosition.charAt(0) - 'a';
            int row = 8 - Character.getNumericValue(rawPosition.charAt(1));
            return new Position(col, row);
        }

        private void validateRow(int row) {
            if (row < 0 || row >= 8) {
                throw new IllegalArgumentException("체스 보드 행은 1이상, 8 이하입니다.");
            }
        }

        private void validateCol(int col) {
            if (col < 0 || col >= 8) {
                throw new IllegalArgumentException("체스 보드 열은 a이상, h 이하입니다.");
            }
        }
    }

    private static final int BLACK_PIECE_LINE = 0;
    private static final int BLACK_PAWN_LINE = 1;
    private static final int WHITE_PAWN_LINE = 6;
    private static final int WHITE_PIECE_LINE = 7;
    private static final int BOARD_LENGTH = 8;

    private final List<Rank> ranks = new ArrayList<>();

    public void initialize() {
        ranks.clear();
        initializeBlackPieces();
        initializeBlank();
        initializeWhitePieces();
    }

    public void initializeEmpty() {
        ranks.clear();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            Rank rank = new Rank();
            ranks.add(rank);
            for (int j = 0; j < BOARD_LENGTH; j++) {
                rank.add(Piece.createBlank());
            }
        }
    }

    private void initializeBlackPieces() {
        Rank blackPieceLine = new Rank();
        Rank blackPawnLine = new Rank();
        ranks.add(blackPieceLine);
        ranks.add(blackPawnLine);

        blackPieceLine.add(Piece.createBlackRook());
        blackPieceLine.add(Piece.createBlackKnight());
        blackPieceLine.add(Piece.createBlackBishop());
        blackPieceLine.add(Piece.createBlackQueen());
        blackPieceLine.add(Piece.createBlackKing());
        blackPieceLine.add(Piece.createBlackBishop());
        blackPieceLine.add(Piece.createBlackKnight());
        blackPieceLine.add(Piece.createBlackRook());
        for (int i = 0; i < BOARD_LENGTH; i++) {
            blackPawnLine.add(Piece.createBlackPawn());
        }
    }

    private void initializeBlank() {
        for (int i = 2; i < 6; i++) {
            Rank blankRank = new Rank();
            ranks.add(blankRank);
            for (int j = 0; j < BOARD_LENGTH; j++) {
                blankRank.add(Piece.createBlank());
            }
        }
    }

    private void initializeWhitePieces() {
        Rank whitePawnLine = new Rank();
        Rank whitePieceLine = new Rank();
        ranks.add(whitePawnLine);
        ranks.add(whitePieceLine);

        for (int i = 0; i < BOARD_LENGTH; i++) {
            whitePawnLine.add(Piece.createWhitePawn());
        }
        whitePieceLine.add(Piece.createWhiteRook());
        whitePieceLine.add(Piece.createWhiteKnight());
        whitePieceLine.add(Piece.createWhiteBishop());
        whitePieceLine.add(Piece.createWhiteQueen());
        whitePieceLine.add(Piece.createWhiteKing());
        whitePieceLine.add(Piece.createWhiteBishop());
        whitePieceLine.add(Piece.createWhiteKnight());
        whitePieceLine.add(Piece.createWhiteRook());
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            sb.append(appendNewLine(ranks.get(i).getRepresentation()));
        }
        return sb.toString();
    }

    public int pieceCount() {
        return ranks.stream()
                .map(Rank::pieceCount)
                .reduce(0, Integer::sum);
    }

    public int pieceCount(Type type, Color color) {
        return ranks.stream()
                .map(rank -> rank.pieceCount(type, color))
                .reduce(0, Integer::sum);
    }

    public void move(String rawPosition, Piece piece) {
        Position position = Position.convert(rawPosition);
        ranks.get(position.row).set(position.col, piece);
    }

    public Piece findPiece(String rawPosition) {
        Position position = Position.convert(rawPosition);
        return ranks.get(position.row).get(position.col);
    }
}
