package org.example.chess.board;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

import static org.example.chess.pieces.Piece.createBlank;

public class Board {

    List<List<Piece>> board2 = new ArrayList<>();
    List<Piece> pieceList = new ArrayList<>();
    List<Piece> whitePieceList = new ArrayList<>();
    List<Piece> blackPieceList = new ArrayList<>();

    public void add(Piece piece) {
        if (piece.isWhite()) {
            System.out.println("화이트추가");
            whitePieceList.add(piece);
        }
        if (piece.isBlack()) {
            System.out.println("블랙추가");
            blackPieceList.add(piece);
        }
        pieceList.add(piece);
    }

    public int pieceCount() {
        return pieceList.size();
    }


    public void initializeV2() {
        for (int i = 0; i < 8; i++) {
            List<Piece> row = new ArrayList<>();
            if (i != 0 && i != 7) {
                for (int j = 0; j < 8; j++) {
                    row.add(createBlank()); // 빈 칸은 Blank 입력
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    row.add(null); // 말이 들어갈 곳은 Null 입력
                }
            }
            board2.add(row);
        }

        // 체스말 인스턴스 추가
        board2.get(0).set(0, Piece.createWhiteRook());
        board2.get(0).set(1, Piece.createWhiteKnight());
        board2.get(0).set(2, Piece.createWhiteBishop());
        board2.get(0).set(3, Piece.createWhiteQueen());
        board2.get(0).set(4, Piece.createWhiteKing());
        board2.get(0).set(5, Piece.createWhiteBishop());
        board2.get(0).set(6, Piece.createWhiteKnight());
        board2.get(0).set(7, Piece.createWhiteRook());
        for (int i = 0; i < 8; i++) {
            board2.get(1).set(i, Piece.createWhitePawn());
        }

        board2.get(7).set(0, Piece.createBlackRook());
        board2.get(7).set(1, Piece.createBlackKnight());
        board2.get(7).set(2, Piece.createBlackBishop());
        board2.get(7).set(3, Piece.createBlackQueen());
        board2.get(7).set(4, Piece.createBlackKing());
        board2.get(7).set(5, Piece.createBlackBishop());
        board2.get(7).set(6, Piece.createBlackKnight());
        board2.get(7).set(7, Piece.createBlackRook());
        for (int i = 0; i < 8; i++) {
            board2.get(6).set(i, Piece.createBlackPawn());
        }

        printBoard();

    }

    private void printBoard() {
        System.out.println(showBoard());
    }


    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (List<Piece> row : board2) {
            StringBuilder rowSb = new StringBuilder();
            for (Piece piece : row) {
                rowSb.append(piece.getRepresentation());
            }
            sb.append(rowSb).append("\n");
        }
        return sb.toString();
    }


    public String getWhitePawnsResult() {
        return getPawnResult(whitePieceList);
    }

    public String getBlackPawnsResult() {
        return getPawnResult(blackPieceList);
    }

    private static String getPawnResult(List<Piece> pieceList) {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : pieceList) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public Piece findPiece(String position) {
        //todo : position 유효값 검사
        Position pos = Position.fromStr(position);

        return board2.get(pos.getRow()).get(pos.getColumn());
    }

    public void move(String position, Piece piece) {
        Piece locatedPiece = findPiece(position);
        Position pos = Position.fromStr(position);

        // todo : 현재는 임의로 말이 있는 경우 놓지 못하게 하였다.
        if (locatedPiece.getPieceType() != PieceType.NO_PIECE) return;

        board2.get(pos.getRow()).set(pos.getColumn(), piece);
    }
}
