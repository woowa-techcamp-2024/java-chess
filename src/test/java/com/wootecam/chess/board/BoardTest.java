package com.wootecam.chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wootecam.chess.CoordinatesExtractor;
import com.wootecam.chess.pieces.Bishop;
import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.King;
import com.wootecam.chess.pieces.Knight;
import com.wootecam.chess.pieces.Pawn;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Position;
import com.wootecam.chess.pieces.Queen;
import com.wootecam.chess.pieces.Rook;
import com.wootecam.chess.pieces.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
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
    class countBoardPieces_메소드는 {

        @Nested
        class 보드를_생성하고_호출하면 {

            @Test
            void 현재_보드에_존재하는_기물의_갯수를_반환한다() {
                // when
                int count = board.countBoardPieces();

                // then
                assertThat(count).isEqualTo(32);
            }
        }
    }

    @Nested
    class countSpecificBoardPieces_메소드는 {

        @Test
        void 특정_색상과_타입을_가진_모든_Piece의_개수를_카운팅한다() {
            // when
            int blackPawnCount = board.countSpecificBoardPieces(Color.BLACK, Type.PAWN);
            int whiteBishopCount = board.countSpecificBoardPieces(Color.WHITE, Type.BISHOP);

            // then
            assertAll(
                    () -> assertThat(blackPawnCount).isEqualTo(8),
                    () -> assertThat(whiteBishopCount).isEqualTo(2)
            );
        }
    }

    @Nested
    class findPiece_메소드는 {

        @Test
        void 주어진_위치의_기물을_조회한다() {
            // expect
            assertAll(
                    () -> assertThat(board.findPiece(new Position(0, 0))).isEqualTo(new Rook(Color.BLACK)),
                    () -> assertThat(board.findPiece(new Position(0, 7))).isEqualTo(new Rook(Color.BLACK)),
                    () -> assertThat(board.findPiece(new Position(7, 0))).isEqualTo(new Rook(Color.WHITE)),
                    () -> assertThat(board.findPiece(new Position(7, 7))).isEqualTo(new Rook(Color.WHITE))

            );
        }
    }

    @Nested
    class initializeEmpty_메소드는 {

        @BeforeEach
        void setUp() {
            board = initializeEmpty();
        }

        @Test
        void 아무것도_없는_체스판을_생성한다() {
            // when
            int count = board.countBoardPieces();

            // then
            assertThat(count).isZero();
        }
    }

    @Nested
    class updatePiece_메소드는 {

        @BeforeEach
        void setUp() {
            board = initialize();
        }

        @Test
        void 해당_좌표에_기물을_세운다() {
            // given
            Position position = new Position(0, 0);

            // when
            board.updatePiece(position, new Pawn(Color.WHITE));

            // then
            assertThat(board.findPiece(new Position(0, 0))).isEqualTo(new Pawn(Color.WHITE));
        }
    }

    @Nested
    class findDescOrderedPieces_메소드는 {

        @BeforeEach
        void setUp() {
            List<Rank> ranks = List.of(new Rank(List.of(
                    new Rook(Color.WHITE), new Rook(Color.BLACK), new Queen(Color.WHITE),
                    new Rook(Color.BLACK), new Rook(Color.BLACK), new Rook(Color.BLACK),
                    new Rook(Color.BLACK), new Rook(Color.BLACK)))
            );
            board = new Board(ranks);
        }

        @Test
        void 지정한_색상의_기물을_점수_내림차순으로_조회한다() {
            // when
            List<Piece> descOrderedPieces = board.findDescOrderedPieces(Color.WHITE);

            // then
            assertThat(descOrderedPieces).containsExactly(new Queen(Color.WHITE), new Rook(Color.WHITE));
        }
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

                // expect
                assertThatThrownBy(() -> board.verifyMove(new Pawn(Color.WHITE), startPosition, targetPosition))
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

                // expect
                assertThatNoException().isThrownBy(
                        () -> board.verifyMove(new Pawn(Color.BLACK), startPosition, targetPosition));
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

                // expect
                assertThatThrownBy(() -> board.verifyMove(new King(Color.WHITE), startPosition, targetPosition))
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

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> board.verifyMove(new King(Color.BLACK), startPosition, targetPosition));
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

                // expect
                assertThatThrownBy(() -> board.verifyMove(new Queen(Color.WHITE), startPosition, targetPosition))
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

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> board.verifyMove(new Queen(Color.BLACK), startPosition, targetPosition));
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

                // expect
                assertThatThrownBy(() -> board.verifyMove(new Bishop(Color.WHITE), startPosition, targetPosition))
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

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> board.verifyMove(new Bishop(Color.BLACK), startPosition, targetPosition));
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

                // expect
                assertThatThrownBy(() -> board.verifyMove(new Rook(Color.WHITE), startPosition, targetPosition))
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

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> board.verifyMove(new Rook(Color.BLACK), startPosition, targetPosition));
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

                // expect
                assertThatThrownBy(() -> board.verifyMove(new Knight(Color.WHITE), startPosition, targetPosition))
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

                    // expect
                    assertThatNoException().isThrownBy(
                            () -> board.verifyMove(new Knight(Color.BLACK), startPosition, targetPosition));
                }
            }
        }
    }
}
