package chess;

import chess.pieces.Piece;

import static utils.StringUtils.appendNewLine;

public class Board {

    private Piece[][] map;

    public Board() {}

    public void initialize() {
        map = new Piece[8][8];
        map[0][0] = Piece.createBlackRook();
        map[0][1] = Piece.createBlackKnight();
        map[0][2] = Piece.createBlackBishop();
        map[0][3] = Piece.createBlackQueen();
        map[0][4] = Piece.createBlackKing();
        map[0][5] = Piece.createBlackBishop();
        map[0][6] = Piece.createBlackKnight();
        map[0][7] = Piece.createBlackRook();
        for (int i = 0; i < 8; i++) {
            map[1][i] = Piece.createBlackPawn();
            map[6][i] = Piece.createWhitePawn();
        }
        map[7][0] = Piece.createWhiteRook();
        map[7][1] = Piece.createWhiteKnight();
        map[7][2] = Piece.createWhiteBishop();
        map[7][3] = Piece.createWhiteQueen();
        map[7][4] = Piece.createWhiteKing();
        map[7][5] = Piece.createWhiteBishop();
        map[7][6] = Piece.createWhiteKnight();
        map[7][7] = Piece.createWhiteRook();
    }

    public int pieceCount() {
        int count = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (map[row][col] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (map[row][col] != null) {
                    builder.append(map[row][col].getRepresentation().getSymbol());
                } else builder.append('.');
            }
            builder.append(appendNewLine(""));
        }
        return builder.toString();
    }
}
