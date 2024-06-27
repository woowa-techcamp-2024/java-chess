package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Course;
import chess.pieces.Direction;

import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

public class ChessGame {
    private Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void init() {
        board.initialize();
    }

    public void showBoard() {
        System.out.println("====================================");
        System.out.println(board.print());
        System.out.println("====================================");
    }

    private void startGame() {
        init();
        System.out.println("게임을 시작하지");
    }

    public boolean operation(String line) {
        StringTokenizer st = new StringTokenizer(line);
        String op = st.nextToken();
        boolean isFinish = false;

        switch (op) {
            case "start" -> startGame();
            case "move" -> {
                if (st.countTokens() != 2) {
                    throw new IllegalArgumentException("명령어를 잘못입력했다. `move a1 b1` 형식으로 입력해라.");
                }
                String source = st.nextToken();
                String target = st.nextToken();
                if (move(source, target)) {
                    return true;
                } else {
                    throw new IllegalStateException(new StringBuilder().append(source).append(" -> ").append(target).append(" 로 이동할 수 없습니다.")
                            .toString());
                }
            }
            case "end" -> isFinish = true;
        }
        return isFinish;
    }

    private boolean move(String source, String target) {
        Optional<ChessPiece> sourcePiece = board.findPiece(source);

        return sourcePiece.map(sp->{
            if(isPossibleMove(sp,source,target)){
                board.move(source,target);
                return true;
            }
            else{
                return false;
            }
        }).orElse(false);
    }

    private boolean isSameTeam(ChessPiece p1, ChessPiece p2) {
        return p1 != null && p2 != null && p1.getColor().equals(p2.getColor());
    }

    private boolean isPossibleMove(ChessPiece piece, String source, String target) {
        Course pieceCourse = piece.getCourse();
        boolean recursive = pieceCourse.isRecursive();
        List<Direction> directions = pieceCourse.getDirections();
        Optional<ChessPiece> sourcePiece = board.findPiece(source);

        return sourcePiece.map(p->{
            for (Direction direction : directions) {
                int multiple = 1;
                boolean flag = recursive;
                do {
                    String nextPosition = getNextPosition(source, direction, multiple++);
                    if (!board.isIn(nextPosition)) {
                        flag = false;
                    }
                    Optional<ChessPiece> nextPieceOptional = board.findPiece(nextPosition);
                    ChessPiece nextPiece = nextPieceOptional.get();
                    if (nextPosition.equals(target) && !isSameTeam(p, nextPiece)) return true;
                } while (flag);
            }
            return false;
        }).orElse(false);
    }

    private String getNextPosition(String sourcePosition, Direction direction) {
        return getNextPosition(sourcePosition, direction, 1);
    }

    private String getNextPosition(String sourcePosition, Direction direction, int multiple) {
        int row = getRow(sourcePosition);
        int col = getCol(sourcePosition);
        return switchPosition(row + direction.getXDegree() * multiple, col + direction.getYDegree() * multiple);
    }

    private String switchPosition(int row, int col) {
        return new StringBuilder().append((char) ('a' + col)).append(Character.forDigit(row + 1, 10)).toString();
    }

    private int getCol(String position) {
        if (position == null || position.length() > 2)
            throw new IllegalArgumentException("position은 a1 ~ h8 까지 입력해주세요");
        char col = position.charAt(0);
        if (!('a' <= col && col <= 'h')) throw new IllegalArgumentException("position은 a1 ~ h8 까지 입력해주세요");
        return col - 'a';
    }

    private int getRow(String position) {
        if (position == null || position.length() > 2)
            throw new IllegalArgumentException("position은 a1 ~ h8 까지 입력해주세요");
        char row = position.charAt(1);

        if (!('1' <= row && row <= '8')) throw new IllegalArgumentException("position은 a1 ~ h8 까지 입력해주세요");
        return row - '1';
    }


    public void finishGame() {
        System.out.println("게임을 종료하지");
    }
}
