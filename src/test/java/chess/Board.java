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
        Piece movePiece = map.get(sourcePos.getRow()).findPiece(sourcePos.getCol());
        Piece targetPiece = map.get(targetPos.getRow()).findPiece(targetPos.getCol());
        List<List<Position>> allDirectionMoves = movePiece.getPossibleMoves(sourcePos);
        List<Position> moves = allDirectionMoves.stream()
                .filter(list -> list.contains(targetPos))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("impossible move"));
        boolean isPossible = false;
        for (Position move : moves) {
            if (move.equals(targetPos)) {
                if (movePiece.getType() == Piece.Type.PAWN) {
                    if (sourcePos.getCol() != targetPos.getCol() && targetPiece.getType() == Piece.Type.NO_PIECE) {
                        break;
                    }
                    if (sourcePos.getCol() == targetPos.getCol() && targetPiece.getType() != Piece.Type.NO_PIECE) {
                        break;
                    }
                }
                isPossible = true;
                break;
            }
            Piece piece = map.get(move.getRow()).findPiece(move.getCol());
            if (piece.getType() != Piece.Type.NO_PIECE) break;
        }
        if (!isPossible) throw new IllegalArgumentException("impossible move");
        map.get(sourcePos.getRow()).setPiece(sourcePos.getCol(), Piece.createBlank());
        map.get(targetPos.getRow()).setPiece(targetPos.getCol(), movePiece);
    }

    public void addPiece(String position, Piece piece) {
        Position sourcePos = new Position(position);
        map.get(sourcePos.getRow()).setPiece(sourcePos.getCol(), piece);
    }

    public double calculatePoint(Piece.Color color) {
        double sum = 0;
        for (Rank rank : map) {
            sum += rank.calculatePointKnightToQueen(color);
        }
        for (int i=0; i<8; i++){
            int pawnCount = 0;
            for (Rank rank : map) {
                Piece piece = rank.findPiece(i);
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
        return map.get(pos.getRow()).findPiece(pos.getCol());
    }

    public int pieceCount() {
        int count = 0;
        for (Rank rank : map) {
            count += rank.getTotalCountNotNoPiece();
        }
        return count;
    }

    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (Rank rank : map) {
            builder.append(StringUtils.appendNewLine(rank.showRank()));
        }
        return builder.toString();
    }
}
