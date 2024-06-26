package chess;

import static chess.DirectionType.*;
import static java.lang.Math.*;
import static pieces.Color.*;
import static pieces.PieceType.BLANK;
import static pieces.PieceType.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pieces.Color;
import pieces.Piece;
import pieces.PieceType;
import utils.MathUtils;
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

	@Override
	public String toString() {
		return ranks.getRanks().stream()
			.map(rank -> rank.pieces()
				.stream()
				.map(piece -> String.valueOf(piece.getRepresentation()))
				.collect(Collectors.joining()))
			.collect(Collectors.joining(StringUtils.appendNewLine()));
	}

	public void move(Position sourcePosition, Position destinationPosition) {
		Piece sourcePiece = getPieceByPosition(sourcePosition);
		Piece destinationPiece = getPieceByPosition(destinationPosition);

		DirectionType directionType = getDirectionType(sourcePiece, sourcePosition, destinationPosition);
		validateDirectionType(sourcePiece, destinationPiece);

		addDirectionToDestination(sourcePosition, directionType, destinationPosition);
		updatePiece(sourcePosition, Piece.of(Color.BLANK, BLANK));
		updatePiece(destinationPosition, sourcePiece);
	}

	private void addDirectionToDestination(Position sourcePosition, DirectionType directionType,
		Position destinationPosition) {
		Position temp = Position.of(sourcePosition.getRow(), sourcePosition.getColumn());
		temp.addDirection(directionType);
		while (!temp.equals(destinationPosition)) {
			if (getPieceByPosition(temp).isSamePieceType(BLANK)) {
				throw new IllegalArgumentException();
			}
			temp.addDirection(directionType);
		}
	}

	private void validateDirectionType(Piece sourcePiece, Piece destinationPiece) {
		if (isInvalidPoint(sourcePiece, destinationPiece)) {
			throw new IllegalArgumentException("invalid move");
		}
	}

	private boolean isInvalidPoint(Piece sourcePiece, Piece destinationPiece) {
		return sourcePiece.getPieceType() == BLANK || destinationPiece.isSameColor(sourcePiece.getColor());
	}

	private DirectionType getDirectionType(Piece sourcePiece, Position sourcePosition, Position destinationPosition) {
		DirectionType finalDirectionType;
		List<DirectionType> directionTypes = findByPiece(sourcePiece);
		// piece 별로 갈 수 있는 모든 방향 정하는거
		if (sourcePiece.getPieceType() == PAWN) {
			if (sourcePiece.isSameColor(BLACK) && getPieceByPosition(sourcePosition.getRow() + 1, sourcePosition.getColumn()).isSameColor(WHITE)) {
				directionTypes.clear();
			}
			if (sourcePiece.isSameColor(WHITE) && getPieceByPosition(sourcePosition.getRow() - 1, sourcePosition.getColumn()).isSameColor(BLACK)) {
				directionTypes.clear();
			}
			if (sourcePiece.isSameColor(BLACK) && sourcePosition.getRow() == 1) {
				directionTypes.add(SS);
			} else if (sourcePiece.isSameColor(WHITE) && sourcePosition.getRow() == 6) {
				directionTypes.add(NN);
			}
			if (sourcePiece.isSameColor(WHITE)) {
				if (getPieceByPosition(sourcePosition.getRow() - 1, sourcePosition.getColumn() - 1).isSameColor(
					BLACK)) {
					directionTypes.add(NORTHWEST);
				}
				if (getPieceByPosition(sourcePosition.getRow() - 1, sourcePosition.getColumn() + 1).isSameColor(
					BLACK)) {
					directionTypes.add(NORTHEAST);
				}
			} else if (sourcePiece.isSameColor(BLACK)) {
				if (getPieceByPosition(sourcePosition.getRow() + 1, sourcePosition.getColumn() - 1).isSameColor(
					WHITE)) {
					directionTypes.add(SOUTHWEST);
				}
				if (getPieceByPosition(sourcePosition.getRow() + 1, sourcePosition.getColumn() + 1).isSameColor(
					WHITE)) {
					directionTypes.add(SOUTHEAST);
				}
			}
		}
		// 방향 딱 하나 정하는거
		if (sourcePiece.getPieceType() == BISHOP || sourcePiece.getPieceType() == ROOK
			|| sourcePiece.getPieceType() == QUEEN) {
			finalDirectionType = directionTypes.stream()
				.filter(directionType -> {
					int sourceDiff = destinationPosition.getRow() - sourcePosition.getRow();
					int destinationDiff = destinationPosition.getColumn() - sourcePosition.getColumn();
					int gcd = MathUtils.gcd(abs(sourceDiff), abs(destinationDiff));
					if (gcd != 0) {
						sourceDiff /= gcd;
						destinationDiff /= gcd;
					}
					return directionType.getX() == sourceDiff && directionType.getY() == destinationDiff;
				})
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
		} else {
			finalDirectionType = directionTypes.stream()
				.filter(directionType -> {
					int sourceDiff = destinationPosition.getRow() - sourcePosition.getRow();
					int destinationDiff = destinationPosition.getColumn() - sourcePosition.getColumn();
					return directionType.getX() == sourceDiff && directionType.getY() == destinationDiff;
				})
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
		}
		return finalDirectionType;
	}

	private void updatePiece(Position position, Piece piece) {
		int row = position.getRow();
		int column = position.getColumn();
		ranks.getRanks().get(row).pieces().set(column, piece);
	}
}
