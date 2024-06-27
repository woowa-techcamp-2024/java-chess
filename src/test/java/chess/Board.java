package chess;

import chess.pieces.Piece;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Rank> map = new ArrayList<>();


    public Board() {}
    public void initializeEmpty() {
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
    }
    public void initialize() {
        map.add(new Rank(Rank.Type.BLACK_ROOK_TO_KING));
        map.add(new Rank(Rank.Type.BLACK_PAWN));
        for (int i = 0; i < 4; i++) {
            map.add(new Rank(Rank.Type.NO_PIECE));
        }
        map.add(new Rank(Rank.Type.WHITE_PAWN));
        map.add(new Rank(Rank.Type.WHITE_ROOK_TO_KING));
    }

    public void move(String sourcePosition, String targetPosition) {
        Position sourcePos = new Position(sourcePosition);
        Position targetPos = new Position(targetPosition);
        Piece movePiece = map.get(sourcePos.getRow()).getPieces().get(sourcePos.getCol());
        map.get(sourcePos.getRow()).getPieces().set(sourcePos.getCol(), Piece.createBlank());
        map.get(targetPos.getRow()).getPieces().set(targetPos.getCol(), movePiece);
    }

    public void addPiece(String position, Piece piece) {
        Position sourcePos = new Position(position);
        map.get(sourcePos.getRow()).getPieces().set(sourcePos.getCol(), piece);
    }

    public double calculatePoint(Piece.Color color) {
        double sum = 0;
        for (Rank rank : map) {
            for (Piece piece : rank.getPieces()) {
                if (piece.getColor() == color) {
                    if (piece.getType() == Piece.Type.QUEEN) sum += 9;
                    if (piece.getType() == Piece.Type.ROOK) sum += 5;
                    if (piece.getType() == Piece.Type.BISHOP) sum += 3;
                    if (piece.getType() == Piece.Type.KNIGHT) sum += 2.5;
                }
            }
        }
        for (int i=0; i<8; i++){
            int pawnCount = 0;
            for (Rank rank : map) {
                Piece piece = rank.getPieces().get(i);
                if (piece.getColor() == color && piece.getType() == Piece.Type.PAWN) {
                    pawnCount++;
                }
            }
            if (pawnCount >= 2) sum += pawnCount * 0.5;
            else sum += pawnCount;
        }
        return sum;
    }

    public Piece findPiece(String position){
        Position pos = new Position(position);
        return map.get(pos.getRow()).getPieces().get(pos.getCol());
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {
        int count = 0;
        for (Rank rank : map) {
            for (Piece piece : rank.getPieces()) {
                if (piece.getColor() == color && piece.getType() == type) count++;
            }
        }
        return count;
    }

    public int pieceCount() {
        int count = 0;
        for (Rank rank : map) {
            for (Piece piece : rank.getPieces()) {
                if (piece.getType() != Piece.Type.NO_PIECE) count++;
            }
        }
        return count;
    }

    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (Rank rank : map) {
            for (Piece piece : rank.getPieces()) {
                if (piece.getType() == Piece.Type.NO_PIECE) builder.append('.');
                else builder.append(piece.getRepresentation().getSymbol());
            }
            builder.append(StringUtils.appendNewLine(""));
        }
        return builder.toString();
    }
}
