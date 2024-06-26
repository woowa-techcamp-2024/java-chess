package chess;

import static pieces.PieceType.BLANK;
import static pieces.PieceType.KNIGHT;
import static pieces.PieceType.PAWN;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import pieces.Color;
import pieces.Piece;
import pieces.PieceType;

public record Board(
    List<Rank> ranks
) {

    public static final int BOARD_SIZE = 8;

    public int size() {
        return ranks.size();
    }

    public int totalPieceCount() {
        return ranks.stream()
            .map(Rank::totalPieceCount)
            .reduce(0, Integer::sum);
    }

    public int getPieceCountByPieceType(PieceType pieceType) {
        return ranks.stream()
            .map(rank -> rank.getPieceCountByPieceType(pieceType))
            .reduce(0, Integer::sum);
    }

    public Piece getPieceByPosition(String pos) {
        Position position = Position.of(pos);
        return ranks.get(position.getRow()).getPiece(position.getColumn());
    }

    public void move(String sourcePosition, String destinationPosition) {
        Piece sourcePiece = getPieceByPosition(sourcePosition);
        Piece destinationPiece = getPieceByPosition(destinationPosition);
        sourcePiece.move(destinationPosition);

        int sourceRow = sourcePiece.getPosition().getRow();
        int sourceColumn = sourcePiece.getPosition().getColumn();
        int destinationRow = destinationPiece.getPosition().getRow();
        int destinationColumn = destinationPiece.getPosition().getColumn();

        Piece tempPiece = ranks.get(sourceRow).pieces().get(sourceColumn);
        ranks.get(sourceRow).pieces().set(sourceColumn, destinationPiece);
        ranks.get(destinationRow).pieces().set(destinationColumn, tempPiece);

        sourcePiece.updatePosition(Position.of(destinationPosition));
        destinationPiece.updatePosition(Position.of(sourcePosition));
    }

    public double calculatePoint(Color color) {
        double result = 0;
        for (int j = 0; j < BOARD_SIZE; j++) {
            int pawnCount = 0;
            for (int i = 0; i < BOARD_SIZE; i++) {
                Piece piece = ranks.get(i).pieces().get(j);
                if (piece.isSameColor(color)) {
                    result += piece.getPieceType().getDefaultPoint();
                }
                if (piece.isSamePieceType(PAWN)) {
                    pawnCount++;
                }
            }
            result -= pawnCount > 1 ? pawnCount * 0.5 : 0;
        }
        return result;
    }

    public List<Piece> sortPieces(Color color, Comparator<Piece> comparator) {
        List<Piece> pieces = new ArrayList<>(ranks.stream()
            .map(rank -> rank.pieces().stream()
                .filter(piece -> piece.getColor() == color)
                .toList())
            .flatMap(List::stream)
            .toList());
        pieces.sort(comparator);
        return pieces;
    }

    public Piece getPieceByPosition(int x, int y) {
        return ranks.get(x).getPiece(y);
    }

    public Piece getPieceByPosition(Position position) {
        return ranks.get(position.getRow()).getPiece(position.getColumn());
    }

    public boolean isExistMyPiece(Position source, Position destination, DirectionType directionType) {
        int sx = source.getRow();
        int sy = source.getColumn();
        int ex = destination.getRow();
        int ey = destination.getColumn();

        while (sx != ex || sy != ey) {
            sx += directionType.getX();
            sy += directionType.getY();
            if (!getPieceByPosition(sx, sy).isSamePieceType(BLANK)) {
                return false;
            }
        }
        return true;
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    private void validateMove(Piece piece, String position) {
        Position source = piece.getPosition();
        Position target = Position.of(position);
        DirectionType directionType = DirectionType.findByPath(source, target, piece.getPieceType());
        // 나랑 색이 같은 경우
        if (piece.isSameColor(getPieceByPosition(target).getColor())) {
            throw new IllegalArgumentException("목적지에 아군이 있습니다.");
        }
        // 나이트는 뛰어넘을 수 있지만, 나머지는 뛰어넘을 수 없다.
        if (!piece.isSamePieceType(KNIGHT)) {
            while (!source.equals(target)) {
                source.addDirection(directionType);

            }
        }
    }
}
