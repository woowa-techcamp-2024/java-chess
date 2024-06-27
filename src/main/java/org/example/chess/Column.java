package org.example.chess;

import static org.example.pieces.PieceFactory.createNoColorPiece;

import java.util.ArrayList;
import java.util.List;
import org.example.pieces.Piece;

public class Column {

    // 자신의 col과 row길이를 생성시 받는다.
    private final char colIndex;
    private final int rowLen;
    private final List<Piece> pieces;

    public Column(char colIndex, int rowLen) {
        this.colIndex = colIndex;
        this.rowLen = rowLen;
        this.pieces = createSizeList(rowLen);
    }

    private List<Piece> createSizeList(int boardSize) {
        List<Piece> ret = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            ret.add(createNoColorPiece());
        }
        return ret;
    }

    void setPiece(Piece piece, int rowNumber) {
        pieces.set(rowNumber - 1, piece);
    }

    public Piece getPiece(int index) {
        // 1 을 빼줘야한다.
        return pieces.get(index - 1);
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}