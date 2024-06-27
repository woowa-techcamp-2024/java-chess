package chess.util;

import chess.Board;
import chess.piece.*;

import java.util.*;

public enum MoveRule{
    Common {
        public void move(Board board, String source, String target) {
            Piece piece = board.findPiece(source);
            piece.setMoved();
            board.removePieceIfExist(ChessPoint.of(source));
            board.removePieceIfExist(ChessPoint.of(target));
            board.putPiece(target, piece);
        }

        @Override
        public Map<ChessPoint, MoveRule> getMovablePoints(Board board, ChessPoint source, Piece piece) {
            Map<ChessPoint, MoveRule> movablePoints = new HashMap<>();
            for (Direction direction : piece.getMovableDirections()) {
                for (int i = 1; i <= piece.getMoveDistance(); i++) {
                    if (!source.isMoveable(direction, i)) {
                        break;
                    }
                    ChessPoint target = source.move(direction, i);
                    Piece targetPiece = board.findPiece(target);
                    if (targetPiece == null) {
                        movablePoints.put(target, this);
                    } else {
                        if (targetPiece.getColor() != piece.getColor()) {
                            movablePoints.put(target, this);
                        }
                        break;
                    }
                }
            }
            return movablePoints;
        }
    },
    Castling {
        public void move(Board board, String source, String target) {
            Piece king = board.findPiece(source);
            king.setMoved();
            ChessPoint kingPoint = ChessPoint.of(source);
            board.removePieceIfExist(kingPoint);
            board.putPiece(target, king);

            ChessPoint rookPoint = ChessPoint.of(kingPoint.file() < target.charAt(0) ? 'h' : 'a', kingPoint.rank());
            Rook rook = (Rook) board.findPiece(rookPoint);
            rook.setMoved();
            board.removePieceIfExist(rookPoint);
            board.putPiece(ChessPoint.of(target).move(kingPoint.file() < target.charAt(0) ? Direction.WEST : Direction.EAST, 1), rook);
        }

        @Override
        public Map<ChessPoint, MoveRule> getMovablePoints(Board board, ChessPoint kingPoint, Piece piece) {
            Map<ChessPoint, MoveRule> castlingPoints = new HashMap<>();
            if (!(piece instanceof King)) {
                throw new IllegalArgumentException("킹이 아닌 말은 캐슬링할 수 없습니다.");
            }
            King king = (King) piece;
            Piece.Color color = king.getColor();

            List<Map.Entry<ChessPoint, Rook>> rooks = board.findAllPiecesMap(Rook.class, color);

            for (Map.Entry<ChessPoint, Rook> entry : rooks) {
                ChessPoint rookPoint = entry.getKey();
                Rook rook = entry.getValue();

                // 캐슬링 가능 여부를 체크하고, 가능하면 캐슬링 포인트를 추가
                if (canCastle(board, king, rook, kingPoint, rookPoint)) {
                    castlingPoints.put(getKingTargetPoint(kingPoint, rookPoint), this);
                }
            }

            return castlingPoints;
        }

        private boolean canCastle(Board board, King king, Rook rook, ChessPoint kingPoint, ChessPoint rookPoint) {
            Piece.Color color = king.getColor();

            // 캐슬링의 대상이 되는 킹과 룩은 캐슬링하는 시점 전에 움직인 적이 없어야 한다.
            if (king.isMoved() || rook.isMoved() || board.isCheck(color)) {
                return false;
            }

            // 킹과 룩 사이에는 기물이 없어야 한다.
            for (char c = (char) (Math.min(kingPoint.file(), rookPoint.file()) + 1); c < Math.max(kingPoint.file(), rookPoint.file()); c++) {
                ChessPoint betweenPoint = ChessPoint.of(c, kingPoint.rank());
                if (board.findPiece(betweenPoint) != null) {
                    return false;
                }
            }

            // 킹이 캐슬링을 하기 위해 지나가는 칸들 중 상대 기물에 의해서 공격받는 칸이 있어서는 안된다.
            Direction rookDirection = kingPoint.file() < rookPoint.file() ? Direction.EAST : Direction.WEST;
            if (board.isAttackablePointIfKingMove(kingPoint.move(rookDirection, 1), color)
                    || board.isAttackablePointIfKingMove(kingPoint.move(rookDirection, 2), color)) {
                return false;
            }

            return true;
        }


        // 룩의 목표 지점을 계산하는 메서드
        private ChessPoint getKingTargetPoint(ChessPoint kingPoint, ChessPoint rookPoint) {
            Direction rookTargetDirection = kingPoint.file() < rookPoint.file() ? Direction.EAST : Direction.WEST;
            return kingPoint.move(rookTargetDirection, 2);
        }


        @Override
        public boolean isAttackable() {
            return false;
        }

    },
    PawnMove {
        public void move(Board board, String source, String target) {
            Piece piece = board.findPiece(source);
            piece.setMoved();
            board.removePieceIfExist(ChessPoint.of(source));
            board.removePieceIfExist(ChessPoint.of(target));
            board.putPiece(target, piece);
        }

        @Override
        public Map<ChessPoint, MoveRule> getMovablePoints(Board board, ChessPoint source, Piece piece) {
            if (!(piece instanceof Pawn)) {
                throw new IllegalArgumentException("폰이 아닌 말은 이동할 수 없습니다.");
            }
            Pawn pawn = (Pawn) piece;

            Map<ChessPoint, MoveRule> movablePoints = new HashMap<>();

            Direction front = pawn.getColor() == Piece.Color.WHITE ? Direction.NORTH : Direction.SOUTH;
            Direction frontRight = pawn.getColor() == Piece.Color.WHITE ? Direction.NORTHEAST : Direction.SOUTHWEST;
            Direction frontLeft = pawn.getColor() == Piece.Color.WHITE ? Direction.NORTHWEST : Direction.SOUTHEAST;

            // 바로 앞의 칸이 비어 있다면 앞으로 한 칸 전진할 수 있다.(바로 앞에 상대의 기물이 있어도 잡을 수 없다.)
            if (source.isMoveable(front, 1)) {
                ChessPoint frontPoint = source.move(front, 1);
                Piece frontPiece = board.findPiece(frontPoint);
                if (frontPiece == null) {
                    movablePoints.put(frontPoint, isPromotionZone(frontPoint) ? MoveRule.Promotion : MoveRule.PawnMove);
                }
            }

            // 경기중 단 한번도 움직이지 않은 폰은 바로 앞의 두칸이 비어 있을 때 두칸 전진할 수 있다. (상대의 기물이 있을 때는 이동할 수 없다.)
            if (!pawn.isMoved() && source.isMoveable(front, 2)) {
                ChessPoint frontPoint = source.move(front, 2);
                Piece frontPiece = board.findPiece(frontPoint);
                if (frontPiece == null) {
                    movablePoints.put(frontPoint, isPromotionZone(frontPoint) ? MoveRule.Promotion : MoveRule.PawnMove);
                }
            }

            // 대각선으로 한 칸 이동하여 상대 기물을 잡을 수 있다.
            addPawnCaptureMove(source, board, movablePoints, frontRight, pawn.getColor());

            addPawnCaptureMove(source, board, movablePoints, frontLeft, pawn.getColor());

            return movablePoints;
        }

        private void addPawnCaptureMove(ChessPoint source, Board board, Map<ChessPoint, MoveRule> movablePoints, Direction direction, Piece.Color color) {
            if (source.isMoveable(direction, 1)) {
                ChessPoint frontLeftPoint = source.move(direction, 1);
                Piece piece = board.findPiece(frontLeftPoint);
                if (piece != null && piece.getColor() != color) {
                    movablePoints.put(frontLeftPoint, isPromotionZone(frontLeftPoint) ? MoveRule.Promotion : MoveRule.PawnMove);
                }
            }
        }

        private boolean isPromotionZone(ChessPoint target) {
            return target.rank() == 1 || target.rank() == 8;
        }
    },
    Promotion {
        public void move(Board board, String source, String target) {
            Piece piece = board.findPiece(source);
            board.removePieceIfExist(ChessPoint.of(source));
            board.removePieceIfExist(ChessPoint.of(target));
            Piece promrotedPiece = getPromotedPiece(piece.getColor());
            board.putPiece(target, promrotedPiece);
        }

        @Override
        public Map<ChessPoint, MoveRule> getMovablePoints(Board board, ChessPoint source, Piece piece) {
            return MoveRule.PawnMove.getMovablePoints(board, source, piece);
        }
    },
    None {
        public void move(Board board, String source, String target) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없는 위치입니다.");
        }

        @Override
        public Map<ChessPoint, MoveRule> getMovablePoints(Board board, ChessPoint source, Piece piece) {
            return Map.of();
        }
    };

    public void adapt(Map<ChessPoint, MoveRule> map, Board board, ChessPoint source, Piece piece, boolean onlyAttackable) {
        if (onlyAttackable) {
            if (this.isAttackable()) {
                map.putAll(this.getMovablePoints(board, source, piece));
            }
        }
        else {
            map.putAll(this.getMovablePoints(board, source, piece));
        }
    }

    public abstract void move(Board board, String source, String target);

    public abstract Map<ChessPoint, MoveRule> getMovablePoints(Board board, ChessPoint source, Piece piece);

    public boolean isAttackable() {
        return true;
    }

    private static Piece getPromotedPiece(Piece.Color color) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("프로모션할 말을 선택하세요.");
            System.out.println("1. Queen");
            System.out.println("2. Rook");
            System.out.println("3. Bishop");
            System.out.println("4. Knight");
            System.out.print("> ");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    return Queen.create(color);
                case 2:
                    return Rook.create(color);
                case 3:
                    return Bishop.create(color);
                case 4:
                    return Knight.create(color);
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

}
