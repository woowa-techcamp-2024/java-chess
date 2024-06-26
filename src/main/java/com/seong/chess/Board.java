package com.seong.chess;

import static com.seong.chess.utils.StringUtils.appendNewLine;

import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Board {

    private static final int EMPTY_ROW_BEGIN = 2;
    private static final int EMPTY_ROW_END = 6;
    static final int BOARD_LENGTH = 8;

    private final List<Rank> ranks = new ArrayList<>();

    public void initializeEmpty() {
        ranks.clear();
        initializeBlank(0, BOARD_LENGTH);
    }

    public void initialize() {
        ranks.clear();
        ranks.add(Rank.createBlackPiecesRank());
        ranks.add(Rank.createBlackPawnRank());
        initializeBlank(EMPTY_ROW_BEGIN, EMPTY_ROW_END);
        ranks.add(Rank.createWhitePawnRank());
        ranks.add(Rank.createWhitePiecesRank());
    }

    private void initializeBlank(int beginRow, int endRow) {
        for (int i = beginRow; i < endRow; i++) {
            ranks.add(Rank.createBlackRank());
        }
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            sb.append(appendNewLine(ranks.get(i).getRepresentation()));
        }
        return sb.toString();
    }

    public int pieceCount() {
        return ranks.stream()
                .map(Rank::pieceCount)
                .reduce(0, Integer::sum);
    }

    public int pieceCount(Type type, Color color) {
        return ranks.stream()
                .map(rank -> rank.pieceCount(type, color))
                .reduce(0, Integer::sum);
    }

    public void move(String rawPosition, Piece piece) {
        Position position = Position.convert(rawPosition);
        ranks.get(position.row()).move(position.col(), piece);
    }

    public Piece findPiece(String rawPosition) {
        Position position = Position.convert(rawPosition);
        return ranks.get(position.row()).get(position.col());
    }

    public double getColumnPawnCount(Color color, int row) {
        double pawnCount = 0;
        for (int col = 0; col < BOARD_LENGTH; col++) {
            Piece piece = ranks.get(col).get(row);
            if (piece.isEqual(Type.PAWN, color)) {
                pawnCount++;
            }
        }
        return pawnCount;
    }

    public List<Piece> getPiecesOrderByHighestScore(Color color) {
        return getPiecesOrderBy(color, (piece1, piece2) -> {
            if (piece1.getDefaultPoint() == piece2.getDefaultPoint()) {
                return 0;
            }
            return piece2.getDefaultPoint() > piece1.getDefaultPoint() ? 1 : -1;
        });
    }

    public List<Piece> getPiecesOrderByLowest(Color color) {
        return getPiecesOrderBy(color, (piece1, piece2) -> {
            if (piece1.getDefaultPoint() == piece2.getDefaultPoint()) {
                return 0;
            }
            return piece1.getDefaultPoint() > piece2.getDefaultPoint() ? 1 : -1;
        });
    }

    private List<Piece> getPiecesOrderBy(Color color, Comparator<Piece> order) {
        return ranks.stream()
                .flatMap(rank -> rank.getSameColorPieces(color).stream())
                .sorted(order)
                .toList();
    }
}
