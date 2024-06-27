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
    private int pieceNumber;

    public Board() {
        board = new ArrayList<>();
    }

    public int pieceCount() {
        return pieceNumber;
    }

    public void initialize() {
        pieceNumber = 32;
        initializeEmpty();
        initializeBlackPiece();
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
        board.set(0, new Rank(pieces));
        pieces.clear();

        for(int i = 0; i < BOARD_SIZE; i++){
            pieces.add(Piece.createBlackPawn());
        }
        board.set(1, new Rank(pieces));
    }

    public void initializeEmpty() {
        for(int i = 0; i < BOARD_SIZE; i++) {
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
        board.set(6, new Rank(pieces));
        pieces.clear();

        pieces.add(Piece.createWhiteRook());
        pieces.add(Piece.createWhiteKnight());
        pieces.add(Piece.createWhiteBishop());
        pieces.add(Piece.createWhiteQueen());
        pieces.add(Piece.createWhiteKing());
        pieces.add(Piece.createWhiteBishop());
        pieces.add(Piece.createWhiteKnight());
        pieces.add(Piece.createWhiteRook());
        board.set(7, new Rank(pieces));
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

    public void move(String stringPosition, Piece piece) {
        Position position = new Position(stringPosition);
        board.get(position.getRank()).setPiece(position.getFile(), piece);
    }

}