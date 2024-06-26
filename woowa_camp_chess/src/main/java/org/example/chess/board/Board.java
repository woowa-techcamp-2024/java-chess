package org.example.chess.board;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

import static org.example.chess.pieces.Piece.createBlank;

public class Board {

    static final int SIZE = 8;
    static final int EMPTY_INDEX = -1;
    static char EMPTY_CHAR = '.';
//    int[][] board = new int[SIZE][SIZE];
    List<List<Piece>> board2 = new ArrayList<>();

    List<Piece> pieceList = new ArrayList<>();
    List<Piece> whitePieceList = new ArrayList<>();
    List<Piece> blackPieceList = new ArrayList<>();

    public void add(Piece piece) {
        if(piece.isWhite()){
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

    public Piece findPawn(int i) {
        return pieceList.get(i);
    }

    public void initializeV2() {
        for (int i = 0; i < 8; i++) {
            List<Piece> row = new ArrayList<>();
            if(i!=0&&i!=7) {
                for (int j = 0; j < 8; j++) {
                    row.add(createBlank()); // 빈 칸은 Blank 입력
                }
            }else{
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


//    public void initialize() {
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                board[i][j] = EMPTY_INDEX;
//            }
//        }
//
//        // Pawn 기물 초기화
//        for (int i = 0; i < SIZE; i++) {
//            Piece whitePawn = createWhitePawn();
////            whitePieceList.add(whitePawn);
//            add(whitePawn);
//            board[6][i] = pieceList.size() - 1;
//
//            Piece blackPawn = createBlackPawn();
////            blackPieceList.add(blackPawn);
//            add(blackPawn);
//            board[1][i] = pieceList.size() - 1;
//        }
//
//        // Knight 기물 초기화
//        Piece whiteKnight1 = createWhiteKnight();
//        Piece whiteKnight2 = createWhiteKnight();
//        Piece blackKnight1 = createBlackKnight();
//        Piece blackKnight2 = createBlackKnight();
//        add(whiteKnight1);
//        add(whiteKnight2);
//        add(blackKnight1);
//        add(blackKnight2);
//        board[0][1] = pieceList.size() - 1;
//        board[0][6] = pieceList.size() - 2;
//        board[7][1] = pieceList.size() - 3;
//        board[7][6] = pieceList.size() - 4;
//
//        // Bishop 기물 초기화
//        Piece whiteBishop1 = createWhiteBishop();
//        Piece whiteBishop2 = createWhiteBishop();
//        Piece blackBishop1 = createBlackBishop();
//        Piece blackBishop2 = createBlackBishop();
//        add(whiteBishop1);
//        add(whiteBishop2);
//        add(blackBishop1);
//        add(blackBishop2);
//        board[0][2] = pieceList.size() - 1;
//        board[0][5] = pieceList.size() - 2;
//        board[7][2] = pieceList.size() - 3;
//        board[7][5] = pieceList.size() - 4;
//
//        // Rook 기물 초기화
//        Piece whiteRook1 = createWhiteRook();
//        Piece whiteRook2 = createWhiteRook();
//        Piece blackRook1 = createBlackRook();
//        Piece blackRook2 = createBlackRook();
//        add(whiteRook1);
//        add(whiteRook2);
//        add(blackRook1);
//        add(blackRook2);
//        board[0][0] = pieceList.size() - 1;
//        board[0][7] = pieceList.size() - 2;
//        board[7][0] = pieceList.size() - 3;
//        board[7][7] = pieceList.size() - 4;
//
//        // Queen 기물 초기화
//        Piece whiteQueen = createWhiteQueen();
//        Piece blackQueen = createBlackQueen();
//        add(whiteQueen);
//        add(blackQueen);
//        board[0][3] = pieceList.size() - 1;
//        board[7][3] = pieceList.size() - 2;
//
//        // King 기물 초기화
//        Piece whiteKing = createWhiteKing();
//        Piece blackKing = createBlackKing();
//        add(whiteKing);
//        add(blackKing);
//        board[0][4] = pieceList.size() - 1;
//        board[7][4] = pieceList.size() - 2;
//    }
    
//    public String showBoard() {
//        StringBuilder print = new StringBuilder();
//
//        for (int i = 0; i < SIZE; i++) {
//            StringBuilder row = new StringBuilder();
//            for (int j = 0; j < SIZE; j++) {
//                int index = board[i][j];
//                row.append(index != -1 ? findPawn(index).getRepresentation() : EMPTY_CHAR);
//            }
//            print.append(appendNewLine(row.toString()));
//        }
//
//        return print.toString();
//    }

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
        if(locatedPiece.getPieceType()!= PieceType.NO_PIECE) return;

        board2.get(pos.getRow()).set(pos.getColumn(),piece);
    }
}
