package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;

import java.util.*;


public class Board {
    private List<List<Piece>> boardMap;
    private int pieceCount = 0;
    private int rowSize;
    private int colSize;

    public Board(final int rowSize, final int colSize) {
        this.boardMap = new ArrayList<>();

        for (int row = 0; row < rowSize; row++) {
            boardMap = new ArrayList<>();
        }

        this.rowSize = rowSize;
        this.colSize = colSize;
    }


    private String getLineAt(int pos) {
        StringBuilder res = new StringBuilder();
        for (Piece p : boardMap.get(pos)) {
            res.append(p.getType());
        }
        return res.toString();
    }

    public String getWhitePawnsResult() {
        return getLineAt(1);
    }

    public String getBlackPawnsResult() {
        return getLineAt(6);
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public int findPiece(Color color, Type type) {
        int count = 0;
        Character t = type.getRepresentation(color);
        for (List<Piece> row : boardMap) {
            for (Piece c : row) {
                if (Objects.equals(c.getType(), t)) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public Piece findPiece(String cmd) {
        Position pos = CommandChanger.getPosition(cmd);
        return boardMap.get(pos.getRow()).get(pos.getColumn());
    }

    public void add(String position, Piece piece) {
        Position pos = CommandChanger.getPosition(position);
        boardMap.get(pos.getRow()).set(pos.getColumn(), piece);
    }

    public void appendLine(List<Piece> line) {
        boardMap.add(line);
    }

    public Piece getPiece(int i, int j) {
        return boardMap.get(i).get(j);
    }

    public void replacePiece(int row, int column, Piece piece) {
        boardMap.get(row).set(column, piece);
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }
}
