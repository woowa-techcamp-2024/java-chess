package org.example.chess.board;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceFactory;
import org.example.chess.pieces.PieceType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.example.chess.pieces.PieceFactory.createBlank;


public class Board {
    static final int SIZE = 8;
    protected List<List<Piece>> board = new ArrayList<>();
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
        int count = 0;
        for(List<Piece> pieceList : board) {
            for(Piece piece : pieceList) {
                if(!piece.getPieceType().equals(PieceType.NO_PIECE)) count++;
            }
        }
        return count;
    }


    public void initialize() {
        initializeEmpty();

        // 체스말 인스턴스 추가
        board.get(7).set(0, PieceFactory.createWhiteRook());
        board.get(7).set(1, PieceFactory.createWhiteKnight());
        board.get(7).set(2, PieceFactory.createWhiteBishop());
        board.get(7).set(3, PieceFactory.createWhiteQueen());
        board.get(7).set(4, PieceFactory.createWhiteKing());
        board.get(7).set(5, PieceFactory.createWhiteBishop());
        board.get(7).set(6, PieceFactory.createWhiteKnight());
        board.get(7).set(7, PieceFactory.createWhiteRook());
        for (int i = 0; i < 8; i++) {
            board.get(6).set(i, PieceFactory.createWhitePawn());
        }

        board.get(0).set(0, PieceFactory.createBlackRook());
        board.get(0).set(1, PieceFactory.createBlackKnight());
        board.get(0).set(2, PieceFactory.createBlackBishop());
        board.get(0).set(3, PieceFactory.createBlackQueen());
        board.get(0).set(4, PieceFactory.createBlackKing());
        board.get(0).set(5, PieceFactory.createBlackBishop());
        board.get(0).set(6, PieceFactory.createBlackKnight());
        board.get(0).set(7, PieceFactory.createBlackRook());
        for (int i = 0; i < 8; i++) {
            board.get(1).set(i, PieceFactory.createBlackPawn());
        }

        printBoard();
    }

    private void printBoard() {
        System.out.println(showBoard());
    }


    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (List<Piece> row : board) {
            StringBuilder rowSb = new StringBuilder();
            for (Piece piece : row) {
                rowSb.append(piece.getRepresentation());
            }
            sb.append(rowSb).append("\n");
        }
        return sb.toString();
    }


    public Piece findPiece(String position) {
        //todo : position 유효값 검사
        Position pos = Position.fromStr(position);
        return board.get(pos.getRow()).get(pos.getColumn());
    }

    public Piece findPiece(Position pos) {
        //todo : position 유효값 검사
        return board.get(pos.getRow()).get(pos.getColumn());
    }

    public void move(String position, Piece piece) {
        Piece locatedPiece = findPiece(position);
        Position pos = Position.fromStr(position);

        // todo : 현재는 임의로 말이 있는 경우 놓지 못하게 하였다.
        if (locatedPiece.getPieceType() != PieceType.NO_PIECE) return;

        board.get(pos.getRow()).set(pos.getColumn(), piece);
    }

    public void initializeEmpty() {
        board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<Piece> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(createBlank()); // 빈 칸은 Blank 입력
            }
            board.add(row);
        }
    }

    public double calculatePoint(Piece.Color color) {
        double sum = 0;

        for (int i = 0; i < SIZE; i++) {
            List<Piece> row = board.get(i);
            for (int j = 0; j < SIZE; j++) {
                Piece piece = row.get(j);
                if(!piece.getColor().equals(color)) continue;
                double defaultPoint = piece.getPieceType().getDefaultPoint();

                //todo : 현재는 같은 행에 Pawn이 있는지 for 문을 돌며 확인 -> Piece별 점수 계산을 담당하는 부분을 구현해야 할지 ?
                if (piece.getPieceType().equals(PieceType.PAWN)) {
                    for (int k = 0; k < SIZE; k++) {
                        Piece pieceTmp = board.get(k).get(j);
                        if(i==k) continue;
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

    public void move(String sourcePosition, String targetPosition) {
        Position srcPosition = Position.fromStr(sourcePosition);
        Position tarPosition = Position.fromStr(targetPosition);

        Piece srcPiece = board.get(srcPosition.getRow()).get(srcPosition.getColumn());
        board.get(srcPosition.getRow()).set(srcPosition.getColumn(), PieceFactory.createBlank());
        board.get(tarPosition.getRow()).set(tarPosition.getColumn(), srcPiece);
    }
}
