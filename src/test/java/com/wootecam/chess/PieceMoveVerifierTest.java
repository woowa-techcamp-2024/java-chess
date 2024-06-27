package com.wootecam.chess;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Rank;
import com.wootecam.chess.pieces.Bishop;
import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.King;
import com.wootecam.chess.pieces.Knight;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Position;
import com.wootecam.chess.pieces.Queen;
import com.wootecam.chess.pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceMoveVerifierTest {

    private PieceMoveVerifier verifier;

    private Board board;

    @BeforeEach
    void setUp() {
        verifier = new PieceMoveVerifier();
        board = initialize();
    }

    private Board initialize() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.createBlackOtherPieces());
        ranks.add(Rank.createPawns(Color.BLACK));
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createPawns(Color.WHITE));
        ranks.add(Rank.createWhiteOtherPieces());

        return new Board(ranks);
    }

    private Board initializeEmpty() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());

        return new Board(ranks);
    }

    @Nested
    class verifyMove_메소드는 {

        private CoordinatesExtractor extractor;

        @BeforeEach
        void setUp() {
            extractor = new CoordinatesExtractor();
        }

        @Nested
        class 폰을_이동하려_할때 {

            @ParameterizedTest
            @CsvSource(textBlock = """
                    b2, a3
                    b2, b5
                    b2, b1
                    """)
            void 잘못된_이동이라면_예외가_발생한다(String start, String target) {
                // given
                Position startPosition = extractor.extractPosition(start);
                Position targetPosition = extractor.extractPosition(target);
                Piece startPiece = board.findPiece(startPosition);
                Piece targetPiece = board.findPiece(targetPosition);

                // expect
                assertThatThrownBy(() -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                        board::findPiece))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @ParameterizedTest
            @CsvSource(textBlock = """
                    b7, b6
                    b7, b5
                    """)
            void 정상적인_이동이라면_이동한다(String start, String target) {
                // given
                Position startPosition = extractor.extractPosition(start);
                Position targetPosition = extractor.extractPosition(target);
                Piece startPiece = board.findPiece(startPosition);
                Piece targetPiece = board.findPiece(targetPosition);

                // expect
                assertThatNoException().isThrownBy(
                        () -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                                board::findPiece));
            }
        }

        @Nested
        class 킹을_이동하려_할때 {

            @ParameterizedTest
            @CsvSource(textBlock = """
                    e1, d1
                    e1, d2
                    e1, e2
                    e1, f2
                    e1, f1
                    """)
            void 잘못된_이동이라면_예외가_발생한다(String start, String target) {
                // given
                Position startPosition = extractor.extractPosition(start);
                Position targetPosition = extractor.extractPosition(target);
                Piece startPiece = board.findPiece(startPosition);
                Piece targetPiece = board.findPiece(targetPosition);

                // expect
                assertThatThrownBy(() -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                        board::findPiece))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @Nested
            class 정상적인_이동이라면 {

                @BeforeEach
                void setUp() {
                    board = initializeEmpty();
                    board.updatePiece(new Position(4, 3), new King(Color.BLACK));
                }

                @ParameterizedTest
                @CsvSource(textBlock = """
                        d4, d5
                        d4, e4
                        d4, d3
                        d4, c4
                        d4, e5
                        d4, e3
                        d4, c3
                        d4, c5
                        """)
                void 이동할_수_있다(String start, String target) {
                    // given
                    Position startPosition = extractor.extractPosition(start);
                    Position targetPosition = extractor.extractPosition(target);
                    Piece startPiece = board.findPiece(startPosition);
                    Piece targetPiece = board.findPiece(targetPosition);

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                                    board::findPiece));
                }
            }
        }

        @Nested
        class 퀸을_이동하려_할때 {

            @ParameterizedTest
            @CsvSource(textBlock = """
                    d1, e1
                    d1, d2
                    d1, c1
                    d1, c2
                    d1, e2
                    """)
            void 잘못된_이동이라면_예외가_발생한다(String start, String target) {
                // given
                Position startPosition = extractor.extractPosition(start);
                Position targetPosition = extractor.extractPosition(target);
                Piece startPiece = board.findPiece(startPosition);
                Piece targetPiece = board.findPiece(targetPosition);

                // expect
                assertThatThrownBy(() -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                        board::findPiece))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @Nested
            class 정상적인_이동이라면 {

                @BeforeEach
                void setUp() {
                    board = initializeEmpty();
                    board.updatePiece(new Position(4, 3), new Queen(Color.BLACK));
                }

                @ParameterizedTest
                @CsvSource(textBlock = """
                        d4, d1
                        d4, h4
                        d4, d8
                        d4, a4
                        d4, a1
                        d4, g1
                        d4, a7
                        d4, h8
                        """)
                void 이동할_수_있다(String start, String target) {
                    // given
                    Position startPosition = extractor.extractPosition(start);
                    Position targetPosition = extractor.extractPosition(target);
                    Piece startPiece = board.findPiece(startPosition);
                    Piece targetPiece = board.findPiece(targetPosition);

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                                    board::findPiece));
                }
            }
        }

        @Nested
        class 비숍을_이동하려_할때 {

            @ParameterizedTest
            @CsvSource(textBlock = """
                    c1, d2
                    c1, c2
                    """)
            void 잘못된_이동이라면_예외가_발생한다(String start, String target) {
                // given
                Position startPosition = extractor.extractPosition(start);
                Position targetPosition = extractor.extractPosition(target);
                Piece startPiece = board.findPiece(startPosition);
                Piece targetPiece = board.findPiece(targetPosition);

                // expect
                assertThatThrownBy(() -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                        board::findPiece))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @Nested
            class 정상적인_이동이라면 {

                @BeforeEach
                void setUp() {
                    board = initializeEmpty();
                    board.updatePiece(new Position(4, 3), new Bishop(Color.BLACK));
                }

                @ParameterizedTest
                @CsvSource(textBlock = """
                        d4, a1
                        d4, g1
                        d4, a7
                        d4, h8
                        """)
                void 이동할_수_있다(String start, String target) {
                    // given
                    Position startPosition = extractor.extractPosition(start);
                    Position targetPosition = extractor.extractPosition(target);
                    Piece startPiece = board.findPiece(startPosition);
                    Piece targetPiece = board.findPiece(targetPosition);

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                                    board::findPiece));
                }
            }
        }

        @Nested
        class 룩을_이동하려_할때 {

            @ParameterizedTest
            @CsvSource(textBlock = """
                    a1, a2
                    a1, b1
                    a1, a5
                    """)
            void 잘못된_이동이라면_예외가_발생한다(String start, String target) {
                // given
                Position startPosition = extractor.extractPosition(start);
                Position targetPosition = extractor.extractPosition(target);
                Piece startPiece = board.findPiece(startPosition);
                Piece targetPiece = board.findPiece(targetPosition);

                // expect
                assertThatThrownBy(() -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                        board::findPiece))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @Nested
            class 정상적인_이동이라면 {

                @BeforeEach
                void setUp() {
                    board = initializeEmpty();
                    board.updatePiece(new Position(4, 3), new Rook(Color.BLACK));
                }

                @ParameterizedTest
                @CsvSource(textBlock = """
                        d4, d1
                        d4, h4
                        d4, d8
                        d4, a4
                        """)
                void 이동할_수_있다(String start, String target) {
                    // given
                    Position startPosition = extractor.extractPosition(start);
                    Position targetPosition = extractor.extractPosition(target);
                    Piece startPiece = board.findPiece(startPosition);
                    Piece targetPiece = board.findPiece(targetPosition);

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                                    board::findPiece));
                }
            }
        }

        @Nested
        class 나이트를_이동하려_할때 {

            @ParameterizedTest
            @CsvSource(textBlock = """
                    b1, d2
                    g1, e2
                    """)
            void 잘못된_이동이라면_예외가_발생한다(String start, String target) {
                // given
                Position startPosition = extractor.extractPosition(start);
                Position targetPosition = extractor.extractPosition(target);
                Piece startPiece = board.findPiece(startPosition);
                Piece targetPiece = board.findPiece(targetPosition);
                // expect
                assertThatThrownBy(() -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                        board::findPiece))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @Nested
            class 정상적인_이동이라면 {

                @BeforeEach
                void setUp() {
                    board = initializeEmpty();
                    board.updatePiece(new Position(4, 3), new Knight(Color.BLACK));
                    board.updatePiece(new Position(4, 4), new Queen(Color.BLACK));
                    board.updatePiece(new Position(4, 2), new Queen(Color.BLACK));
                    board.updatePiece(new Position(3, 3), new Queen(Color.BLACK));
                    board.updatePiece(new Position(5, 4), new Queen(Color.BLACK));
                }

                @ParameterizedTest
                @CsvSource(textBlock = """
                        d4, e6
                        d4, c6
                        d4, e2
                        d4, c2
                        d4, f5
                        d4, f3
                        d4, b5
                        d4, b3
                        """)
                void 이동할_수_있다(String start, String target) {
                    // given
                    Position startPosition = extractor.extractPosition(start);
                    Position targetPosition = extractor.extractPosition(target);
                    Piece startPiece = board.findPiece(startPosition);
                    Piece targetPiece = board.findPiece(targetPosition);

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> verifier.verifyMove(startPiece, targetPiece, startPosition, targetPosition,
                                    board::findPiece));
                }
            }
        }
    }
}
