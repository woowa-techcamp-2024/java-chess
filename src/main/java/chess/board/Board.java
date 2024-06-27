package chess.board;

import chess.pieces.Piece;
import chess.pieces.Piece.Type;
import chess.pieces.Piece.Color;

import java.util.ArrayList;
import java.util.List;

import static utils.StringUtils.appendNewLine;

public class Board {

    private final List<Rank> board;
    private final int BOARD_SIZE = 8;
    private final int BLACK_PAWN_RANK = 1;
    private final int WHITE_PAWN_RANK = 6;
    private int pieceNumber;

    public Board() {
        board = new ArrayList<>();
    }

    public int pieceCount() {
        return pieceNumber;
    }

    public void initialize() {
        pieceNumber = 32;
        initializeBlackPiece();
        initializeEmpty();
        initializeWhitePiece();
    }

    public void initializeBlackPiece() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(Piece.createBlackRook());
        pieces.add(Piece.createBlackKnight());
        pieces.add(Piece.createBlackBishop());
        pieces.add(Piece.createBlackQueen());
        pieces.add(Piece.createBlackKing());
        pieces.add(Piece.createBlackBishop());
        pieces.add(Piece.createBlackKnight());
        pieces.add(Piece.createBlackRook());
        board.add(new Rank(pieces));
        pieces.clear();

        for(int i = 0; i < BOARD_SIZE; i++){
            pieces.add(Piece.createBlackPawn());
        }
        board.add(new Rank(pieces));
    }

    public void initializeEmpty() {
        for(int i = BLACK_PAWN_RANK + 1; i < WHITE_PAWN_RANK; i++) {
            List<Piece> pieces = new ArrayList<>();
            for(int j = 0; j < BOARD_SIZE; j++) {
                pieces.add(Piece.createBlank());
            }
            board.add(new Rank(pieces));
        }
    }

    public void initializeWhitePiece() {
        List<Piece> pieces = new ArrayList<>();

        for(int i = 0; i < BOARD_SIZE; i++){
            pieces.add(Piece.createWhitePawn());
        }
        board.add(new Rank(pieces));
        pieces.clear();

        pieces.add(Piece.createWhiteRook());
        pieces.add(Piece.createWhiteKnight());
        pieces.add(Piece.createWhiteBishop());
        pieces.add(Piece.createWhiteQueen());
        pieces.add(Piece.createWhiteKing());
        pieces.add(Piece.createWhiteBishop());
        pieces.add(Piece.createWhiteKnight());
        pieces.add(Piece.createWhiteRook());
        board.add(new Rank(pieces));
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            sb.append(appendNewLine(board.get(i).toString()));
        }
        return sb.toString();
    }

    public int countPiece(Color color, Type type) {
        return board.stream()
                .mapToInt(rank -> rank.countPiece(color, type))
                .sum();
    }

    public Piece findPiece(String stringPosition) {
        Position position = new Position(stringPosition);
        return board.get(position.getRank()).getPiece(position.getFile());
    }

}