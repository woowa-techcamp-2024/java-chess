package org.example.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {

    @DisplayName("색깔에 맞는 폰이 생성되어야 한다")
    @ParameterizedTest(name = "폰의 색깔은 {0}이고 표현은 {1}이어야 한다")
    @MethodSource("argumentsStream")
    public void create(Piece piece, String color, String representation) {
        verifyPiece(piece, color, representation);
    }

    private void verifyPiece(final Piece piece, final String color, final String representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    private static Stream<Arguments> argumentsStream() {

        // Map을 사용하여 기물 생성자와 예상 속성을 연결
        Map<Supplier<Piece>, String[]> pieceData = new HashMap<>();
        pieceData.put(Piece::createWhitePawn, new String[]{Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION});
        pieceData.put(Piece::createBlackPawn, new String[]{Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION});
        pieceData.put(Piece::createWhiteKnight, new String[]{Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION});
        pieceData.put(Piece::createBlackKnight, new String[]{Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION});
        pieceData.put(Piece::createWhiteRook, new String[]{Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION});
        pieceData.put(Piece::createBlackRook, new String[]{Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION});
        pieceData.put(Piece::createWhiteBishop, new String[]{Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION});
        pieceData.put(Piece::createBlackBishop, new String[]{Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION});
        pieceData.put(Piece::createWhiteQueen, new String[]{Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION});
        pieceData.put(Piece::createBlackQueen, new String[]{Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION});
        pieceData.put(Piece::createWhiteKing, new String[]{Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION});
        pieceData.put(Piece::createBlackKing, new String[]{Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION});

        // Map 데이터를 기반으로 Stream 생성
        return pieceData.entrySet().stream()
            .map(entry -> Arguments.arguments(entry.getKey().get(), entry.getValue()[0], entry.getValue()[1]));
    }

}