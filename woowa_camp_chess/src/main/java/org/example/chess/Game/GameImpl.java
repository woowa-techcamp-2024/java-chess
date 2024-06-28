package org.example.chess.Game;

import org.example.chess.board.Board;
import org.example.chess.board.Direction;
import org.example.chess.board.Position;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

import static org.example.chess.pieces.Piece.Color;

public class GameImpl extends Game {

    private Color turnColor = Color.WHITE;

    public GameImpl(Board board) {
        super(board);
    }

    @Override
    double calculatePoint() {
        //todo : 규칙에 맞는 포인트 계산
        return 0;
    }

    @Override
    public void move(String src, String des) {

        // 판 크기를 벗어나는 위치로 이동을 하지 못 한다.
        Position srcPos = Position.fromStr(src);
        Position desPos = Position.fromStr(des);

        Piece srcPiece = board.findPiece(src);
        Piece desPiece = board.findPiece(desPos);

        // src 에 Blank 가 있다면 이동을 하지 못한다.
        if(srcPiece.getPieceType().equals(PieceType.NO_PIECE)) throw new IllegalArgumentException("말 선택이 잘못 되었습니다.");

        // 자기 말이 아니면 이동하지 못 한다. -> 어떤 색깔의 턴인지 확인해야 한다.
        if(!srcPiece.getColor().equals(turnColor)) throw new IllegalArgumentException("말 선택이 잘못 되었습니다.");

        // 도착할 수 있는 리스트안에 dest 가 있어야 한다.
        List<Position> possibleMovePosition = getPossibleMovePosition(srcPos, board);

        System.out.println(possibleMovePosition.size());

        boolean isPossibleDes = possibleMovePosition.stream()
                .peek(System.out::println)
        .anyMatch((position) -> position.getRow() == desPos.getRow() && position.getColumn() == desPos.getColumn());

        if(!isPossibleDes) throw new IllegalArgumentException("잘못된 도착지 입니다.");



        //todo : 상대 말을 먹는 행위를 하면 점수를 높여주어야 한다.
        if (!desPiece.getPieceType().equals(PieceType.NO_PIECE)) {
            pointManager.updatePointByPosition(desPos);
        }

        board.move(src,des);

        srcPiece.increaseMoveCount();

        changeTurn();
    }

    public static List<Position> getPossibleMovePosition(Position srcPosition, Board board) {
        Piece piece = board.findPiece(srcPosition);

        if (piece == null) {
            throw new IllegalArgumentException("Source position does not contain a piece.");
        }

        PieceType pieceType = piece.getPieceType();
        Color color = piece.getColor();
        List<Direction> directions = Direction.getDirectionsByTypeAndColor(pieceType, color);

        List<Position> possiblePositions = new ArrayList<>();
        System.out.println(directions.size());

        for (Direction direction : directions) {
            Position currentPosition = srcPosition.move(direction);
            System.out.println(currentPosition.getRow()+" "+currentPosition.getColumn());
            while (Position.validPosition(currentPosition.getRow(), currentPosition.getColumn())) {
                Piece targetPiece = board.findPiece(currentPosition);

                //아무것도 없으면
                if (targetPiece.getPieceType().equals(PieceType.NO_PIECE)) {
                    possiblePositions.add(currentPosition);
                    System.out.println(currentPosition.getRow() + " " + currentPosition.getColumn());
                } else { // 도착지에 상대말이 있으면 추가하고 break , 없으면 그냥 break
                    if (targetPiece.getColor() != color) {
                        possiblePositions.add(currentPosition);
                        System.out.println(currentPosition.getRow() + " " + currentPosition.getColumn());
                    }
                    System.out.println("도착지에 말이 있다.");
                    break;
                }
                currentPosition = currentPosition.move(direction);

                if (pieceType == PieceType.KING || pieceType == PieceType.KNIGHT || (pieceType == PieceType.PAWN && piece.getMoveCount() !=0)) {
                    break;
                }
            }

        }

        return possiblePositions;
    }

    private void changeTurn() {
        if(turnColor == Color.BLACK) turnColor = Color.WHITE;
        else turnColor = Color.BLACK;
    }
}
