package org.example.chess;

import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.pieces.Piece;

public class Board {

    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();

    private final int BOARD_SIZE = 8;

    public void initialize() {
        addPawn();
    }

    private void addPawn() {
        placeBlackPiece();
        placeWhitePiece();
    }

    private void placeWhitePiece() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            whitePieces.add(Piece.createWhitePawn());
        }

        whitePieces.add(Piece.createWhiteRook());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteQueen());
        whitePieces.add(Piece.createWhiteKing());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteRook());
    }

    private void placeBlackPiece() {
        blackPieces.add(Piece.createBlackRook());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackQueen());
        blackPieces.add(Piece.createBlackKing());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackRook());

        for (int i = 0; i < BOARD_SIZE; i++) {
            blackPieces.add(Piece.createBlackPawn());
        }
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : whitePieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : blackPieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }


    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        String defaultLine = ".".repeat(BOARD_SIZE);
        String defaultLineWithNewLine = appendNewLine(defaultLine);

        sb.append(appendNewLine(getBlackPawnsResult().substring(0, BOARD_SIZE)));
        sb.append(appendNewLine(getBlackPawnsResult().substring(BOARD_SIZE, BOARD_SIZE*2)));

        sb.append(defaultLineWithNewLine);
        sb.append(defaultLineWithNewLine);
        sb.append(defaultLineWithNewLine);
        sb.append(defaultLineWithNewLine);

        sb.append(appendNewLine(getWhitePawnsResult().substring(0, BOARD_SIZE)));
        sb.append(appendNewLine(getWhitePawnsResult().substring(BOARD_SIZE, BOARD_SIZE*2)));

        return sb.toString();
    }

    public Piece findPawn(int id) {
        // index를 넘어가는 경우 에러를 발생시키는 코드 추가하기
        return whitePieces.get(id);
    }

    public int pieceCount() {
        return whitePieces.size() + blackPieces.size();
    }
}
