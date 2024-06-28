package lass9436.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lass9436.chess.pieces.Piece;
import lass9436.utils.StringUtils;

public class Board {

	private final List<Rank> map = new ArrayList<>();

	public Board() {
	}

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

	private Position createPosition(String position) {
		return new Position(position);
	}

	private Piece findPieceWithPosition(Position position) {
		return map.get(position.getRow()).findPiece(position.getCol());
	}

	private List<Position> findPossibleMoves(Piece piece, Position sourcePosition, Position destinationPosition) {
		return piece.getPossibleMoves(sourcePosition).stream()
			.filter(list -> list.contains(destinationPosition))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("impossible move"));
	}

	private boolean pawnCheck(Piece sourcePiece, Piece destinationPiece, Position sourcePosition,
		Position destinationPosition) {
		if (sourcePiece.getType() == Piece.Type.PAWN) {
			if (sourcePosition.getCol() != destinationPosition.getCol()
				&& destinationPiece.getType() == Piece.Type.NO_PIECE) {
				return true;
			}
			if (sourcePosition.getCol() == destinationPosition.getCol()
				&& destinationPiece.getType() != Piece.Type.NO_PIECE) {
				return true;
			}
		}
		return false;
	}

	private boolean colorCheck(Piece sourcePiece, Piece destinationPiece) {
		return sourcePiece.getColor() == destinationPiece.getColor();
	}

	private boolean isPositionEquals(Position currentPosition, Position destinationPosition) {
		return currentPosition.equals(destinationPosition);
	}

	private Piece findObstaclePiece(Position currentPosition) {
		return map.get(currentPosition.getRow()).findPiece(currentPosition.getCol());
	}

	private boolean isObstacle(Position currentPosition) {
		Piece obstaclePiece = findObstaclePiece(currentPosition);
		return obstaclePiece.getType() != Piece.Type.NO_PIECE;
	}

	private void movePossibleCheck(List<Position> moves, Position sourcePosition, Position destinationPosition,
		Piece sourcePiece, Piece destinationPiece) {
		boolean isPossible = false;
		for (Position move : moves) {
			if (isPositionEquals(move, destinationPosition)) {
				if (pawnCheck(sourcePiece, destinationPiece, sourcePosition, destinationPosition)) {
					break;
				}
				if (colorCheck(sourcePiece, destinationPiece)) {
					break;
				}
				isPossible = true;
				break;
			}
			if (isObstacle(move)) {
				break;
			}
		}
		if (!isPossible)
			throw new IllegalArgumentException("impossible move");
	}

	private void movePiece(Piece piece, Position sourcePosition, Position destinationPosition) {
		map.get(sourcePosition.getRow()).setPiece(sourcePosition.getCol(), Piece.createBlank());
		map.get(destinationPosition.getRow()).setPiece(destinationPosition.getCol(), piece);
	}

	public void move(String sourcePosition, String targetPosition) {
		Position sourcePos = createPosition(sourcePosition);
		Position targetPos = createPosition(targetPosition);

		Piece movePiece = findPieceWithPosition(sourcePos);
		Piece targetPiece = findPieceWithPosition(targetPos);

		List<Position> moves = findPossibleMoves(movePiece, sourcePos, targetPos);

		movePossibleCheck(moves, sourcePos, targetPos, movePiece, targetPiece);

		movePiece(movePiece, sourcePos, targetPos);
	}

	public void addPiece(String position, Piece piece) {
		Position sourcePos = new Position(position);
		map.get(sourcePos.getRow()).setPiece(sourcePos.getCol(), piece);
	}

	private double calculatePointKnightToQueen(Piece.Color color) {
		return map.stream()
			.mapToDouble(rank -> rank.calculatePointKnightToQueen(color))
			.sum();
	}

	private double calculatePointPawn(Piece.Color color) {
		return IntStream.range(0, 8)
			.mapToDouble(column -> {
				long pawnCount = map.stream()
					.filter(rank -> {
						Piece piece = rank.findPiece(column);
						return piece.getColor() == color && piece.getType() == Piece.Type.PAWN;
					})
					.count();
				return pawnCount >= 2 ? pawnCount * 0.5 : pawnCount;
			})
			.sum();
	}

	public double calculatePoint(Piece.Color color) {
		return calculatePointKnightToQueen(color) + calculatePointPawn(color);
	}

	public Piece findPiece(String position) {
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
