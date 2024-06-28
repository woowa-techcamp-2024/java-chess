package java_chess.chess;

import static java_chess.chess.pieces.enums.Type.PAWN;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java_chess.chess.pieces.Piece;
import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Type;
import java_chess.chess.pieces.values.Location;
import java_chess.chess.pieces.values.Move;

public class Board {

    public static final int[] ROWS = {1, 2, 3, 4, 5, 6, 7, 8};
    public static final char[] COLS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    private final Map<Location, Piece> table;

    public Board() {
        table = new HashMap<>();
    }

    /**
     * 보드를 초기 설정 상태로 만듭니다. 게임 시작 위치에 말들을 배치합니다.
     */
    public void initialize() {
        table.clear();
        setupPieces(1, Color.WHITE, Type.defaultOrder());
        setupPieces(2, Color.WHITE);
        setupPieces(7, Color.BLACK);
        setupPieces(8, Color.BLACK, Type.defaultOrder());
    }

    /**
     * 보드에 말을 추가합니다. 이미 해당 위치에 말이 있는 경우, 덮어씁니다.
     *
     * @param location 위치
     * @param piece    말
     */
    public void addPiece(Location location, Piece piece) {
        table.put(location, piece);
    }

    /**
     * 보드에서 말을 제거합니다.
     *
     * @param location 위치
     */
    public void removePiece(Location location) {
        var result = table.remove(location);
        if (result == null) {
            throw new IllegalArgumentException("location: " + location + " does not have a piece.");
        }
    }

    /**
     * 위치에 존재하는 보드의 말을 갖고 옵니다. 말이 존재하지 않을 시 Blank를 반환합니다.
     *
     * @param location 위치
     * @return 말
     */
    public Piece getPiece(Location location) {
        return table.getOrDefault(location, Piece.getBlank());
    }

    /**
     * 해당 행에 존재하는 말들을 갖고 옵니다.
     *
     * @param row 행
     * @return 해당 행에 존재하는 말들
     */
    public List<Piece> getPiecesWithRow(int row) {
        List<Piece> result = new ArrayList<>();
        for (char col : COLS) {
            result.add(getPiece(Location.of(row, col)));
        }
        return result;
    }

    /**
     * 보드에서 색이 일치하는 말들을 점수에 기반해 정렬해 갖고옵니다.
     *
     * @param color 색
     * @return 정렬된 말들
     */
    public List<Piece> getSortedPiecesByColor(Color color) {
        return getSortedPiecesByColor(color, false);
    }

    /**
     * 보드에서 색이 일치하는 말들을 점수에 기반해 정렬해 갖고옵니다.
     *
     * @param color   색
     * @param reverse 역순 여부
     * @return 정렬된 말들
     */
    public List<Piece> getSortedPiecesByColor(Color color, boolean reverse) {
        var comparator = reverse ? Comparator.comparingDouble(Piece::getScore)
            : Comparator.comparingDouble(Piece::getScore).reversed();
        return table.values().stream()
            .filter(piece -> piece.verifySameColor(color))
            .sorted(comparator)
            .toList();
    }

    /**
     * 보드에서 색이 일치하는 말들을 갖고 옵니다.
     *
     * @param color 색
     * @return 말들
     */
    public List<Piece> getPiecesByColor(Color color) {
        return table.values().stream().filter(piece -> piece.verifySameColor(color)).toList();
    }

    /**
     * 해당 위치에 존재하는 말의 이동 가능 위치들을 갖고 옵니다.
     *
     * @param location 위치
     * @return 이동 가능 위치
     * @throws IllegalArgumentException 위치에 말이 없을 경우
     */
    public List<Location> getLocationsThatPieceCanMoveByLocation(Location location) {
        var piece = getPiece(location);
        if (piece.isBlank()) {
            return List.of();
        }
        var color = piece.getColor();
        if (PAWN.isInstance(piece)) {
            return getLocationsThatPawnCanMoveByLocation(location);
        }
        var result = new ArrayList<Location>();
        var directions = piece.getDirections();
        for (Direction direction : directions) {
            verifyMoveable(location, direction, color, result, piece.canMoveMultipleTimes());
        }
        return result;
    }

    /**
     * 보드에서 색이 일치하는 폰들의 위치를 갖고 옵니다.
     *
     * @param color 색
     * @return 위치
     */
    public List<Location> getPawnsLocationsByColor(Color color) {
        return table.entrySet().stream()
            .filter(entry -> entry.getValue().verifySameColor(color))
            .filter(entry -> PAWN.isInstance(entry.getValue()))
            .map(Entry::getKey)
            .toList();
    }

    /**
     * 보드에서 색과 타입이 일치하는 말들의 개수를 갖고 옵니다.
     *
     * @param color 색
     * @param type  타입
     * @return 말의 개수
     */
    public int countPiecesByTypeAndColor(Type type, Color color) {
        return (int) table.values().stream()
            .filter(type::isInstance)
            .filter(piece -> piece.verifySameColor(color))
            .count();
    }

    /**
     * 보드에 존재하는 모든 말의 갯수를 리턴합니다.
     *
     * @return 말의 갯수
     */
    public long size() {
        return table.values().size();
    }


    /**
     * 해당 색상의 킹이 체크 상태인지 확인합니다.
     *
     * @param color 색상
     * @return 체크 상태 여부
     * @throws IllegalArgumentException 킹을 찾을 수 없을 경우
     */
    public boolean verifyKingInCheck(Color color) {
        var kingLocation = getKingLocation(color);
        var oppositeColor = color.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;

        return table.entrySet().stream()
            .filter(entry -> entry.getValue().verifySameColor(oppositeColor))
            .anyMatch(entry -> getLocationsThatPieceCanMoveByLocation(entry.getKey()).contains(
                kingLocation));

    }

    private boolean verifyKingInCheck(Map<Location, Piece> map, Color color) {
        var kingLocation = getKingLocation(color);
        var oppositeColor = color.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;

        return map.entrySet().stream()
            .filter(entry -> entry.getValue().verifySameColor(oppositeColor))
            .anyMatch(entry -> getLocationsThatPieceCanMoveByLocation(entry.getKey()).contains(
                kingLocation));

    }

    private void setupPieces(int row, Color color) {
        for (char col : COLS) {
            var location = Location.of(row, col);
            addPiece(location, Piece.generatePiece(PAWN, color));
        }
    }

    private void setupPieces(int row, Color color, Type[] types) {
        for (int i = 0; i < COLS.length; i++) {
            var location = Location.of(row, COLS[i]);
            addPiece(location, Piece.generatePiece(types[i], color));
        }
    }

    private List<Location> getLocationsThatPawnCanMoveByLocation(Location location) {
        var result = new ArrayList<Location>();
        // Verify diagonal and single move possible
        var piece = table.get(location);
        for (Direction direction : piece.getDirections()) {
            if (verifyPawnMoveable(location, direction, piece.getColor())) {
                result.add(direction.nextLocation(location));
            }
        }
        return result;
    }

    private void verifyMoveable(Location location, Direction direction, Color color,
        List<Location> result, boolean flag) {
        if (!direction.canMove(location)) {
            return;
        }
        var nextLocation = direction.nextLocation(location);
        var nextPiece = getPiece(nextLocation);
        if (nextPiece.verifySameColor(color)) {
            return;
        }
        result.add(nextLocation);
        if (flag && nextPiece.isBlank()) {
            verifyMoveable(nextLocation, direction, color, result, true);
        }
    }

    private boolean verifyPawnMoveable(Location location, Direction direction, Color color) {
        if (!direction.canMove(location)) {
            return false;
        }
        var nextLocation = direction.nextLocation(location);
        var nextPiece = getPiece(nextLocation);
        return switch (direction) {
            case UP, DOWN -> nextPiece.isBlank();
            case PAWN_BLACK_DOUBLE_DOWN -> {
                var isMoved = location.getX() == 7;
                yield isMoved && nextPiece.isBlank() && getPiece(
                    Direction.DOWN.nextLocation(location)).isBlank();
            }
            case PAWN_WHITE_DOUBLE_UP -> {
                var isMoved = location.getX() == 2;
                yield isMoved && nextPiece.isBlank() && getPiece(
                    Direction.UP.nextLocation(location)).isBlank();
            }
            default -> !nextPiece.isBlank() && !nextPiece.verifySameColor(color);
        };

    }

    private Location getKingLocation(Color color) {
        return table.entrySet().stream()
            .filter(entry -> Type.KING.isInstance(entry.getValue()))
            .filter(entry -> entry.getValue().verifySameColor(color))
            .map(Entry::getKey)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("King not found"));
    }

    public boolean verifyCheckMate(Color color) {
        if (!verifyKingInCheck(color)) {
            return false;
        }

        return table.entrySet().stream()
            .filter(entry -> entry.getValue().verifySameColor(color))
            .flatMap(entry -> {
                Location from = entry.getKey();
                Piece piece = entry.getValue();
                return getLocationsThatPieceCanMoveByLocation(from).stream()
                    .map(to -> new Move(from, to, piece));
            }).filter(this::canPreventCheck)
            .findAny().isEmpty();
    }

    private boolean canPreventCheck(Move move) {
        // 현재 보드의 복사본을 생성합니다.
        var testMap = new HashMap<>(table);
        var from = move.getFrom();
        var to = move.getTo();
        var piece = move.getPiece();

        // 가상의 움직임을 통한 메이트 판별
        testMap.put(to, piece);
        testMap.remove(from);
        return !verifyKingInCheck(testMap, piece.getColor());

    }

}
