package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.pieces.Piece;

public class Rank {

	private final List<Piece> pieces = new ArrayList<>();

	public int getTotalCountNotNoPiece() {
		return (int)pieces.stream().filter(p -> p.getType() != Piece.Type.NO_PIECE).count();
	}

	public String showRank() {
		return pieces.stream()
			.map(p -> String.valueOf(p.getRepresentation().getSymbol()))
			.collect(Collectors.joining());
	}

	public double calculatePointKnightToQueen(Piece.Color color) {
		return pieces.stream()
			.mapToDouble(p -> p.getType() != Piece.Type.PAWN && p.getColor() == color ? p.getType().getPoint() : 0.0)
			.sum();
	}

	public enum Type {WHITE_PAWN, BLACK_PAWN, WHITE_ROOK_TO_KING, BLACK_ROOK_TO_KING, NO_PIECE;}

	public Piece findPiece(int col) {
		return pieces.get(col);
	}

	public void setPiece(int col, Piece piece) {
		pieces.set(col, piece);
	}

	public Rank(Type type) {
		if (type == Type.WHITE_PAWN) {
			for (int i = 0; i < 8; i++) {
				pieces.add(Piece.createWhitePawn());
			}
		} else if (type == Type.BLACK_PAWN) {
			for (int i = 0; i < 8; i++) {
				pieces.add(Piece.createBlackPawn());
			}
		} else if (type == Type.WHITE_ROOK_TO_KING) {
			pieces.add(Piece.createWhiteRook());
			pieces.add(Piece.createWhiteKnight());
			pieces.add(Piece.createWhiteBishop());
			pieces.add(Piece.createWhiteQueen());
			pieces.add(Piece.createWhiteKing());
			pieces.add(Piece.createWhiteBishop());
			pieces.add(Piece.createWhiteKnight());
			pieces.add(Piece.createWhiteRook());
		} else if (type == Type.BLACK_ROOK_TO_KING) {
			pieces.add(Piece.createBlackRook());
			pieces.add(Piece.createBlackKnight());
			pieces.add(Piece.createBlackBishop());
			pieces.add(Piece.createBlackQueen());
			pieces.add(Piece.createBlackKing());
			pieces.add(Piece.createBlackBishop());
			pieces.add(Piece.createBlackKnight());
			pieces.add(Piece.createBlackRook());
		} else if (type == Type.NO_PIECE) {
			for (int i = 0; i < 8; i++) {
				pieces.add(Piece.createBlank());
			}
		}
	}
}

