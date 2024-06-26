package org.example.chess;

import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.pieces.Piece;
import org.example.utils.StringUtils;

public class Board {

    private class OneColumn {

        // 자신의 col과 row길이를 생성시 받는다.
        private final char colIndex;
        private final int rowLen;
        private final List<Piece> pieces;

        public OneColumn(char colIndex, int rowLen) {
            this.colIndex = colIndex;
            this.rowLen = rowLen;
            this.pieces = createSizeList(rowLen);
        }

        private List<Piece> createSizeList(int boardSize) {
            List<Piece> ret = new ArrayList<>();
            for (int i = 0; i < boardSize; i++) {
                ret.add(Piece.createNoColorPiece());
            }
            return ret;
        }

        public void modifyPiece(Piece piece, int index) {
            pieces.set(index, piece);
        }

        public Piece getPiece(int index) {
            // 1 을 빼줘야한다.
            return pieces.get(index - 1);
        }
    }


    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();
    private final List<OneColumn> oneColumns;

    private final int BOARD_SIZE = 8;
    private final char startChar = 'a';

    public Board() {
        oneColumns = createSizeList(BOARD_SIZE);
        initialize();
    }

    private List<OneColumn> createSizeList(int boardSize) {
        List<OneColumn> ret = new ArrayList<>();
        for (char i = startChar; i < startChar + boardSize; i++) {
            ret.add(new OneColumn(i, boardSize));
        }
        return ret;
    }

    private void initialize() {
        //initPawn();
    }

    private void initPawn() {
        placeBlackPiece();
        placeWhitePiece();
    }

    private void placeWhitePiece() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            whitePieces.add(Piece.createWhitePawn());
        }

        whitePieces.add(Piece.createWhiteRook());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteQueen());
        whitePieces.add(Piece.createWhiteKing());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteRook());
    }

    private void placeBlackPiece() {
        blackPieces.add(Piece.createBlackRook());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackQueen());
        blackPieces.add(Piece.createBlackKing());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackRook());

        for (int i = 0; i < BOARD_SIZE; i++) {
            blackPieces.add(Piece.createBlackPawn());
        }
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : whitePieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : blackPieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }


    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        // row를 구하면 각 col마다 해당 row의 piece를 가져와서 출력한다.
        for (int i = BOARD_SIZE; i > 0; i--) {
            sb.append(appendNewLine(getRow(i)));
        }
        return sb.toString();
    }

    private String getRow(int row) {
        StringBuilder sb = new StringBuilder();
        for (OneColumn oneColumn : oneColumns) {
            sb.append(oneColumn.getPiece(row).getRepresentation());
        }
        return sb.toString();
    }

    public Piece findPawn(int id) {
        // index를 넘어가는 경우 에러를 발생시키는 코드 추가하기
        return whitePieces.get(id);
    }

    public int pieceCount() {
        return whitePieces.size() + blackPieces.size();
    }
}
