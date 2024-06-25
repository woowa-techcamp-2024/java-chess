package chess;

import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Rank;
import chess.pieces.Type;

import static chess.utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Rank> board = new ArrayList<>();
    public static final int BOARD_SIZE = 8;

    public int pieceCount() {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            count += board.get(i).getCount();
        }
        return count;
    }

    private void initializeNull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<Piece> pieceList = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                pieceList.add(Piece.createBlank());
            }
            board.add(new Rank(pieceList));
        }
    }

    private void initializeWhitePiece() {
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(Piece.createWhiteRook());
        pieceList.add(Piece.createWhiteKnight());
        pieceList.add(Piece.createWhiteBishop());
        pieceList.add(Piece.createWhiteQueen());
        pieceList.add(Piece.createWhiteKing());
        pieceList.add(Piece.createWhiteBishop());
        pieceList.add(Piece.createWhiteKnight());
        pieceList.add(Piece.createWhiteRook());
        board.set(7, new Rank(pieceList));

        List<Piece> pawnList = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece piece = Piece.createWhitePawn();
            pawnList.add(piece);
        }
        board.set(6, new Rank(pawnList));
    }

    private void initializeBlackPiece() {
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(Piece.createBlackRook());
        pieceList.add(Piece.createBlackKnight());
        pieceList.add(Piece.createBlackBishop());
        pieceList.add(Piece.createBlackQueen());
        pieceList.add(Piece.createBlackKing());
        pieceList.add(Piece.createBlackBishop());
        pieceList.add(Piece.createBlackKnight());
        pieceList.add(Piece.createBlackRook());
        board.set(0, new Rank(pieceList));

        List<Piece> pawnList = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece piece = Piece.createBlackPawn();
            pawnList.add(piece);
        }
        board.set(1, new Rank(pawnList));
    }

    public void initialize() {
        initializeNull();
        initializeWhitePiece();
        initializeBlackPiece();
    }

    public String showBoard() {
        StringBuilder printResult = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            String line = appendNewLine(board.get(i).showRank());
            printResult.append(line);
        }
        return printResult.toString();
    }

    public int countPieces(final Color color, final Type type) {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            count += board.get(i).getCount(color, type);
        }
        return count;
    }

    public Piece findPiece(final String position) {
        char x = position.charAt(0);
        int xPos = x - 'a';

        char y = position.charAt(1);
        int yPos = 8 - Character.getNumericValue(y);

        return board.get(yPos).getPiece(xPos);
    }

    // test를 위한 메서드
    public void updateBoard(final List<String> exampleBoard) {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            List<Piece> pieces = new ArrayList<>();
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                pieces.add(Piece.create(exampleBoard.get(i).charAt(j)));
            }
            board.set(i, new Rank(pieces));
        }
    }
}
