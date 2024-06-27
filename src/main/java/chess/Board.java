package chess;

import static chess.Direction.NN;
import static chess.Direction.SS;
import static chess.Direction.findDirectionsByPiece;
import static chess.Direction.getForwardDiagonalDirections;
import static pieces.Color.BLACK;
import static pieces.Color.WHITE;
import static pieces.PieceType.BLANK;
import static pieces.PieceType.PAWN;

import java.util.List;
import java.util.stream.Collectors;
import pieces.Color;
import pieces.Piece;
import utils.StringUtils;

public record Board(Ranks ranks) {

	public static final int BOARD_SIZE = 8;

	public int size() {
		return BOARD_SIZE;
	}

	public Piece getPieceByPosition(int x, int y) {
		return ranks.getRanks().get(x).getPiece(y);
	}

	public Piece getPieceByPosition(Position position) {
		return ranks.getRanks().get(position.getRow()).getPiece(position.getColumn());
	}

	/**
	 * sourcePosition에서 targetPosition으로 이동
	 */
	public void move(Position source, Position target) {
		Piece sourcePiece = getPieceByPosition(source);
		Piece targetPiece = getPieceByPosition(target);

		Direction direction = getDirection(sourcePiece, source, target);
		validateDirection(sourcePiece, targetPiece);
		validateRoute(direction, source, target);

		updateRanks(source, Piece.of(Color.BLANK, BLANK));
		updateRanks(target, sourcePiece);
	}

	/**
	 * 이동하는 경로 상에 어떠한 기물이 있는지 확인
	 */
	private void validateRoute(Direction direction, Position source, Position target) {
		Position temp = Position.of(source.getRow(), source.getColumn());
		temp.addDirection(direction);
		while (!temp.equals(target)) {
			if (getPieceByPosition(temp).isSamePieceType(BLANK)) {
				throw new IllegalArgumentException();
			}
			temp.addDirection(direction);
		}
	}

	private void validateDirection(Piece source, Piece target) {
		if (isInvalidPoint(source, target)) {
			throw new IllegalArgumentException("invalid move");
		}
	}

	/**
	 * source가 비어있거나, target이 내 기물인 경우
	 */
	private boolean isInvalidPoint(Piece source, Piece target) {
		return source.pieceType() == BLANK || target.isSameColor(source.color());
	}

	/**
	 * source에서 target으로 가는데, 어떤 Direction인지 정함
	 */
	private Direction getDirection(Piece sourcePiece, Position source, Position target) {
		List<Direction> directions = findDirectionsByPiece(sourcePiece);

		addSpecificDirections(sourcePiece, source, directions);
		return sourcePiece.getFinalDirection(directions, source, target, sourcePiece.pieceType());
	}

	private void addSpecificDirections(Piece sourcePiece, Position sourcePosition, List<Direction> directions) {
		if (sourcePiece.pieceType() == PAWN) {
			if (cantPawnMoveForward(sourcePiece, sourcePosition.getRow(), sourcePosition.getColumn())) {
				directions.clear();
			}
			if (isFirstMove(sourcePiece, sourcePosition)) {
				addDoubleForward(directions, sourcePiece);
			}
			addEnemyDirections(sourcePiece, sourcePosition, directions);
		}
	}

	private void addEnemyDirections(Piece sourcePiece, Position sourcePosition, List<Direction> directions) {
		List<Direction> forwardDiagonalDirections = getForwardDiagonalDirections(sourcePiece.color());
		for (Direction direction : forwardDiagonalDirections) {
			int nx = sourcePosition.getRow() + direction.getX();
			int ny = sourcePosition.getColumn() + direction.getY();
			Piece enemyPiece = getPieceByPosition(nx, ny);
			if (!enemyPiece.isSameColor(sourcePiece.color())) {
				directions.add(direction);
			}
		}
	}

	private static void addDoubleForward(List<Direction> directions, Piece sourcePiece) {
		if (sourcePiece.isSameColor(WHITE)) {
			directions.add(NN);
			return;
		}
		directions.add(SS);
	}

	private static boolean isFirstMove(Piece sourcePiece, Position sourcePosition) {
		return sourcePiece.isSameColor(BLACK) && sourcePosition.getRow() == 1
			|| sourcePiece.isSameColor(WHITE) && sourcePosition.getRow() == 6;
	}

	private boolean cantPawnMoveForward(Piece sourcePiece, int sourceRow, int sourceColumn) {
		return sourcePiece.isSameColor(WHITE) && getPieceByPosition(sourceRow - 1, sourceColumn).isSameColor(BLACK)
			|| sourcePiece.isSameColor(BLACK) && getPieceByPosition(sourceRow + 1, sourceColumn).isSameColor(WHITE);
	}

	/**
	 * position의 piece를 @Param piece 로 변경
	 */
	private void updateRanks(Position position, Piece piece) {
		int row = position.getRow();
		int column = position.getColumn();
		ranks.getRanks().get(row).pieces().set(column, piece);
	}

	@Override
	public String toString() {
		return ranks.getRanks().stream()
			.map(rank -> rank.pieces()
				.stream()
				.map(piece -> String.valueOf(piece.getRepresentation()))
				.collect(Collectors.joining()))
			.collect(Collectors.joining(StringUtils.appendNewLine()));
	}
}
