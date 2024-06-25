package com.seong.chess;

import static com.seong.chess.utils.StringUtils.appendNewLine;

import com.seong.chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BLACK_PIECE_LINE = 0;
    private static final int BLACK_PAWN_LINE = 1;
    private static final int WHITE_PAWN_LINE = 6;
    private static final int WHITE_PIECE_LINE = 7;
    private static final int BOARD_LENGTH = 8;

    private final List<Rank> ranks = new ArrayList<>();

    public void initialize() {
        ranks.clear();
        initializeBlackPieces();
        initializeBlank();
        initializeWhitePieces();
    }

    private void initializeBlackPieces() {
        Rank blackPieceLine = new Rank(BLACK_PIECE_LINE);
        Rank blackPawnLine = new Rank(BLACK_PAWN_LINE);
        ranks.add(blackPieceLine);
        ranks.add(blackPawnLine);

        blackPieceLine.add(Piece.createBlackRook());
        blackPieceLine.add(Piece.createBlackKnight());
        blackPieceLine.add(Piece.createBlackBishop());
        blackPieceLine.add(Piece.createBlackQueen());
        blackPieceLine.add(Piece.createBlackKing());
        blackPieceLine.add(Piece.createBlackBishop());
        blackPieceLine.add(Piece.createBlackKnight());
        blackPieceLine.add(Piece.createBlackRook());
        for (int i = 0; i < BOARD_LENGTH; i++) {
            blackPawnLine.add(Piece.createBlackPawn());
        }
    }

    private void initializeBlank() {
        for (int i = 2; i < 6; i++) {
            Rank blankRank = new Rank(i);
            ranks.add(blankRank);
            for (int j = 0; j < BOARD_LENGTH; j++) {
                blankRank.add(Piece.createBlank());
            }
        }
    }

    private void initializeWhitePieces() {
        Rank whitePawnLine = new Rank(WHITE_PAWN_LINE);
        Rank whitePieceLine = new Rank(WHITE_PIECE_LINE);
        ranks.add(whitePawnLine);
        ranks.add(whitePieceLine);

        for (int i = 0; i < BOARD_LENGTH; i++) {
            whitePawnLine.add(Piece.createWhitePawn());
        }
        whitePieceLine.add(Piece.createWhiteRook());
        whitePieceLine.add(Piece.createWhiteKnight());
        whitePieceLine.add(Piece.createWhiteBishop());
        whitePieceLine.add(Piece.createWhiteQueen());
        whitePieceLine.add(Piece.createWhiteKing());
        whitePieceLine.add(Piece.createWhiteBishop());
        whitePieceLine.add(Piece.createWhiteKnight());
        whitePieceLine.add(Piece.createWhiteRook());
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
}
