package chess.board;

import chess.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chess.board.BoardFactory.*;

public class Board {

    private final Map<Position, Piece> board = new HashMap<>();

    public Board() {
    }

    public void initialize() {
        createPawn(this);
        createBishop(this);
        createQueen(this);
        createKing(this);
        createKnight(this);
        createRook(this);
    }

    public void add(final Position position, final Piece piece) {
        this.board.put(position, piece);
    }

    public void move(final Position target, final Piece piece) {
        if (piece.getType().equals(Type.PAWN)) {
            ((Pawn) piece).completeFirstMove();
        }

        this.board.put(target, piece);
    }

    public void move(final Position source, final Position target) {
        Piece piece = validatePieceInPosition(source);

        move(target, piece);

        this.board.remove(source);
    }

    public int pieceCount() {
        return this.board.size();
    }

    public Piece findPiece(final Position position) {
        return Optional.ofNullable(this.board.get(position))
                .orElseGet(Blank::create);
    }

    public Piece findPiece(final String input) {
        Position position = Position.of(input);

        return Optional.ofNullable(this.board.get(position))
                .orElseGet(Blank::create);
    }

    public List<Piece> findPiece(final PieceColor color) {
        return this.board.values().stream()
                .filter(piece -> piece.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public List<Position> findPosition(final PieceColor color) {
        return this.board.entrySet().stream()
                .filter(entry -> entry.getValue().getColor().equals(color))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public long getPieceResult(final PieceColor color, final Type type) {
        return board.values().stream()
                .filter(pawn -> pawn.getColor().equals(color))
                .filter(pawn -> pawn.getType().equals(type))
                .count();
    }

    public double calculatePoint(final PieceColor color) {
        List<Piece> sameFilePawn = findSameFilePawn(color);
        int sameFilePawnCnt = sameFilePawn.size();

        List<Piece> piece = findPiece(color);

        double sum = piece.stream()
                .map(p -> p.getType().getDefaultScore())
                .reduce(0.0, Double::sum);

        sum -= 0.5 * sameFilePawnCnt;

        return sum;
    }


    public List<Piece> orderPieceWithScore(final PieceColor color) {
        return this.board.values().stream()
                .filter(piece -> piece.getColor().equals(color))
                .sorted((p1, p2) -> Double.compare(p2.getType().getDefaultScore(), p1.getType().getDefaultScore()))
                .toList();
    }

    public char getPieceInPosition(final Position position) {
        Piece piece = board.getOrDefault(position, Blank.create());

        return piece.getType().getRepresentation(piece.getColor());
    }

    private List<Piece> findSameFilePawn(final PieceColor color) {
        return this.board.keySet().stream()
                .collect(Collectors.groupingBy(Position::file))
                .values().stream()
                .flatMap(positions -> {
                    List<Piece> pawns = positions.stream()
                            .map(this.board::get)
                            .filter(piece -> piece.getColor().equals(color))
                            .filter(piece -> piece.getType().equals(Type.PAWN))
                            .toList();
                    return pawns.size() >= 2 ? pawns.stream() : Stream.empty();
                })
                .toList();
    }

    private Piece validatePieceInPosition(final Position sourcePosition) {
        Piece piece = Optional.ofNullable(this.board.get(sourcePosition))
                .orElseGet(Blank::create);

        if (piece.getType().equals(Type.NO_PIECE)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        return piece;
    }

    public Position findKingPosition(final PieceColor turn) {
        return this.board.entrySet().stream()
                .filter(entry -> entry.getValue().getType().equals(Type.KING))
                .filter(entry -> entry.getValue().getColor().equals(turn))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }
}
