package org.example.chess.board;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.example.chess.pieces.Piece.createBlank;

public class Board {
    static final int SIZE = 8;
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

    public void initializeEmpty() {
        board2 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<Piece> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(createBlank()); // 빈 칸은 Blank 입력
            }
            board2.add(row);
        }
    }

    public double calculatePoint(Piece.Color color) {
        double sum = 0;

        for (int i = 0; i < SIZE; i++) {
            List<Piece> row = board2.get(i);
            for (int j = 0; j < SIZE; j++) {
                Piece piece = row.get(j);
                if(!piece.getColor().equals(color)) continue;
                double defaultPoint = piece.getPieceType().getDefaultPoint();

                //todo : 현재는 같은 열에 Pawn이 있는지 for 문을 돌며 확인 -> Piece별 점수 계산을 담당하는 부분을 구현해야 할지 ?
                if (piece.getPieceType().equals(PieceType.PAWN)) {
                    for (int k = 0; k < SIZE; k++) {
                        Piece pieceTmp = board2.get(j).get(k);
                        if(pieceTmp.getPieceType().equals(PieceType.PAWN)&&pieceTmp.getColor().equals(color)) defaultPoint-=0.5;
                    }
                }

                sum += defaultPoint;
            }
        }

        return sum;
    }

    // 기물의 점수순으로 정렬 ( 내림차순 or 오름차순)
    // todo : condition 예외
    public void sortPieceByPoint(String condition) {
        if ("desc".equals(condition)) {
            whitePieceList.sort((a, b) -> -Double.compare(a.getPieceType().getDefaultPoint(), b.getPieceType().getDefaultPoint()));
            blackPieceList.sort((a, b) -> -Double.compare(a.getPieceType().getDefaultPoint(), b.getPieceType().getDefaultPoint()));
            return;
        }
        if ("asc".equals(condition)) {
            whitePieceList.sort(Comparator.comparingDouble(a -> a.getPieceType().getDefaultPoint()));
            blackPieceList.sort(Comparator.comparingDouble(a -> a.getPieceType().getDefaultPoint()));
        }
    }
}
