package chess;

import chess.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chess.BoardFactory.*;
import static chess.utils.StringUtils.appendNewLine;

public class Board {

    private static final int BOARD_SIZE = 8;

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

    public void move(final String target, final Piece piece) {
        Position position = getPosition(target);

        this.board.put(position, piece);
    }

    public int pieceCount() {
        return this.board.size();
    }

    public Piece findPiece(final Position position) {
        return this.board.get(position);
    }

    public Piece findPiece(final String input) {
        Position position = getPosition(input);

        return this.board.get(position);
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

    public String showBoard() {
        StringBuilder chessBoard = new StringBuilder();

        for (int row = BOARD_SIZE; row >= 1; row--) {
            for (int col = 1; col <= BOARD_SIZE; col++) {
                Position position = Position.of(File.of(col), Rank.of(row));
                char representation = getPieceInPosition(position);

                chessBoard.append(representation);
            }
            chessBoard.append(appendNewLine(String.valueOf(Rank.of(row).getIndex())));
        }

        chessBoard.append(appendNewLine("abcdefgh"));

        return chessBoard.toString();
    }

    public void print() {
        System.out.println(showBoard());
    }

    private static Position getPosition(final String input) {
        File file = File.of(input.charAt(0));
        Rank rank = Rank.of(Character.getNumericValue(input.charAt(1)));
        return Position.of(file, rank);
    }

    private char getPieceInPosition(final Position position) {
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
                .collect(Collectors.toList());
    }


    private List<Piece> findPiece(final PieceColor color) {
        return this.board.values().stream()
                .filter(piece -> piece.getColor().equals(color))
                .collect(Collectors.toList());
    }
}