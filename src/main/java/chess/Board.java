package chess;

import chess.pieces.Piece;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private List<Piece> pieces;
    private List<List<Piece>> boardMap;
    private int pieceCount = 0;

    public Board() {
        this.pieces = new ArrayList<>();
    }


    public void initialize() {
        boardMap = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                List<Piece> p = new ArrayList<>();
                p.add(Piece.createWhite(Piece.Type.ROOK));
                p.add(Piece.createWhite(Piece.Type.KNIGHT));
                p.add(Piece.createWhite(Piece.Type.BISHOP));
                p.add(Piece.createWhite(Piece.Type.QUEEN));
                p.add(Piece.createWhite(Piece.Type.KING));
                p.add(Piece.createWhite(Piece.Type.BISHOP));
                p.add(Piece.createWhite(Piece.Type.KNIGHT));
                p.add(Piece.createWhite(Piece.Type.ROOK));
                boardMap.add(p);
            } else if (i == 7) {
                List<Piece> p = new ArrayList<>();
                p.add(Piece.createBlack(Piece.Type.ROOK));
                p.add(Piece.createBlack(Piece.Type.KNIGHT));
                p.add(Piece.createBlack(Piece.Type.BISHOP));
                p.add(Piece.createBlack(Piece.Type.QUEEN));
                p.add(Piece.createBlack(Piece.Type.KING));
                p.add(Piece.createBlack(Piece.Type.BISHOP));
                p.add(Piece.createBlack(Piece.Type.KNIGHT));
                p.add(Piece.createBlack(Piece.Type.ROOK));
                boardMap.add(p);
            } else if(i == 1) {
                List<Piece> p = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    p.add(Piece.createWhite(Piece.Type.PAWN));
                }
                boardMap.add(p);
            } else if(i == 6){
                List<Piece> p = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    p.add(Piece.createBlack(Piece.Type.PAWN));
                }
                boardMap.add(p);
            } else {
                List<Piece> p = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    p.add(Piece.createBlank());
                }
                boardMap.add(p);
            }
        }

        pieceCount = 32;
    }

    public void add(Piece piece) {
        pieces.add(piece);
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

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (List<Piece> row : boardMap) {
            for (Piece c : row) {
                sb.append(c.getType());
            }
            sb.append("\n");
        }

        return StringUtils.replaceNewLine(sb.toString());
    }

    public int getPieceCount() {
        return pieceCount;
    }
}
