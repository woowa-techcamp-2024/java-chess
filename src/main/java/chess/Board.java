package chess;

import chess.pieces.Piece;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Piece> pieces;
    private Character[][] boardMap;
    private int pieceCount = 0;

    public Board() {
        this.pieces = new ArrayList<>();
    }


    public void initialize() {
        boardMap = new Character[][]{
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},

        };

        pieceCount = 32;
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    private String getLineAt(int pos) {
        StringBuilder res = new StringBuilder();
        for (Character p : boardMap[pos]) {
            res.append(p);
        }
        return res.toString();
    }

    public String getWhitePawnsResult() {
        return getLineAt(6);
    }

    public String getBlackPawnsResult() {
        return getLineAt(1);
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (Character[] row : boardMap) {
            for (Character c : row) {
                sb.append(c);
            }
            sb.append("\n");
        }

        return StringUtils.replaceNewLine(sb.toString());
    }

    public int getPieceCount() {
        return pieceCount;
    }
}
