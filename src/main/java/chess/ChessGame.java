package chess;


import chess.pieces.*;
import chess.pieces.Piece.Color;
import utils.StringUtils;

import java.util.*;

/**
 *
 *
 */
public class ChessGame {
    private static final int CHESS_ROW_SIZE = 8;
    private static final int CHESS_COLUMN_SIZE = 8;
    private Set<Piece> whitePieces;
    private Set<Piece> blackPieces;

    private Board board;

    public ChessGame() {
        initializeCmdToPos();
    }

    public void initialize() {
        board = new Board(CHESS_ROW_SIZE, CHESS_COLUMN_SIZE);
        whitePieces = new HashSet<>();
        blackPieces = new HashSet<>();

        createWhitePieces();
        createWhitePawns();
        createBlanks(CHESS_ROW_SIZE / 2);
        createBlackPawns();
        createBlackPieces();
    }

    public void initializeEmpty() {
        board = new Board(CHESS_ROW_SIZE, CHESS_COLUMN_SIZE);
        whitePieces = new HashSet<>();
        blackPieces = new HashSet<>();

        createBlanks(CHESS_ROW_SIZE);

    }

    private void initializeCmdToPos() {
        for (int i = '1'; i <= '9'; i++ ) {
            for (int j = 'a'; j <= 'h'; j++ ) {
                // change int to char and concat them
                String command = Character.toString(j) + Character.toString(i);
                CommandChanger.setPosition(command, new Position(command));
            }
        }
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                sb.append(board.getPiece(i, j).getType());
            }
            sb.append(i + 1);
            sb.append("\n");
        }
        sb.append("abcdefgh\n");
        return StringUtils.replaceNewLine(sb.toString());
    }

    public Piece findPiece(String pos) {
        return board.findPiece(pos);
    }



    public void addInPieceSet(List<Piece> list, Set<Piece> set) {
        set.addAll(list);
    }

    public List<Piece> getWhitePieces() {
        return whitePieces.stream().toList();
    }

    public List<Piece> getBlackPieces() {
        return blackPieces.stream().toList();
    }

    public void add(String position, Piece piece) {
        piece.setPosition(position);
        board.add(position, piece);
        if (Objects.equals(piece.getColor(), Color.WHITE)) {
            whitePieces.add(piece);
            return ;
        }

        blackPieces.add(piece);
    }


    public void move(String source, String target) {
        Piece sourcePiece = findPiece(source);
        sourcePiece.setPosition(CommandChanger.getPosition(target));

        Position sourcePos = CommandChanger.getPosition(source);
        Position targetPos = CommandChanger.getPosition(target);
        // boardMap 수정
        board.replacePiece(sourcePos.getRow(), sourcePos.getColumn(), Piece.of(NoPiece.class, Color.NOCOLOR, sourcePos));
        board.replacePiece(targetPos.getRow(), targetPos.getColumn(), sourcePiece);
    }


    public double calculatePoint(Color color) {
        double score = 0.0;

        // KING
        score += Piece.Type.KING.getDefaultPoint() * board.findPiece(color, Piece.Type.KING);

        // QUEEN
        score += Piece.Type.QUEEN.getDefaultPoint() * board.findPiece(color, Piece.Type.QUEEN);

        // BISHOP
        score += Piece.Type.BISHOP.getDefaultPoint() * board.findPiece(color, Piece.Type.BISHOP);

        // ROOK
        score += Piece.Type.ROOK.getDefaultPoint() * board.findPiece(color, Piece.Type.ROOK);

        // KNIGHT
        score += Piece.Type.KNIGHT.getDefaultPoint() * board.findPiece(color, Piece.Type.KNIGHT);

        // PAWN
        for (int i = 0; i < 8; i++) {
            int cnt = 0;
            for (int j = 0; j < 8; j++) {
                if (Objects.equals(board.getPiece(j, i).getType(), Piece.Type.PAWN.getRepresentation(color))) {
                    cnt++;
                }
            }
            if (cnt >= 2) {
                score += cnt * 0.5;
            } else {
                score += cnt;
            }
        }

        return score;
    }

    public void createWhitePieces() {
        List<Piece> p = new ArrayList<>();
        p.add(Piece.of(Rook.class, Color.WHITE, CommandChanger.getPosition("a1")));
        p.add(Piece.of(Knight.class, Color.WHITE, CommandChanger.getPosition("b1")));
        p.add(Piece.of(Bishop.class, Color.WHITE, CommandChanger.getPosition("c1")));
        p.add(Piece.of(Queen.class, Color.WHITE, CommandChanger.getPosition("d1")));
        p.add(Piece.of(King.class, Color.WHITE, CommandChanger.getPosition("e1")));
        p.add(Piece.of(Bishop.class, Color.WHITE, CommandChanger.getPosition("f1")));
        p.add(Piece.of(Knight.class, Color.WHITE, CommandChanger.getPosition("g1")));
        p.add(Piece.of(Rook.class, Color.WHITE, CommandChanger.getPosition("h1")));

        board.appendLine(p);

        addInPieceSet(p,whitePieces);
    }

    public void createWhitePawns() {
        List<Piece> p = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            p.add(Piece.of(Pawn.class, Color.WHITE, CommandChanger.getPosition(Character.toString('a' + j) + "2")));
        }
        board.appendLine(p);

        addInPieceSet(p,whitePieces);
    }

    public void createBlackPieces() {

        List<Piece> p = new ArrayList<>();
        p.add(Piece.of(Rook.class, Color.BLACK, CommandChanger.getPosition("a8")));
        p.add(Piece.of(Knight.class, Color.BLACK, CommandChanger.getPosition("b8")));
        p.add(Piece.of(Bishop.class, Color.BLACK, CommandChanger.getPosition("c8")));
        p.add(Piece.of(Queen.class, Color.BLACK, CommandChanger.getPosition("d8")));
        p.add(Piece.of(King.class, Color.BLACK, CommandChanger.getPosition("e8")));
        p.add(Piece.of(Bishop.class, Color.BLACK, CommandChanger.getPosition("f8")));
        p.add(Piece.of(Knight.class, Color.BLACK, CommandChanger.getPosition("g8")));
        p.add(Piece.of(Rook.class, Color.BLACK, CommandChanger.getPosition("h8")));

        board.appendLine(p);

        addInPieceSet(p,blackPieces);
    }

    public void createBlackPawns(){
        List<Piece> p = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            p.add(Piece.of(Pawn.class, Color.BLACK, CommandChanger.getPosition(Character.toString('a' + j)+"7")));
        }
        board.appendLine(p);

        addInPieceSet(p,blackPieces);
    }

    private void createBlanks(int row) {
        for (int i = 0; i < row; i++) {
            List<Piece> p = new ArrayList<>();
            for (int j = 0; j < CHESS_COLUMN_SIZE; j++) {
                p.add(Piece.of(NoPiece.class, Color.NOCOLOR, CommandChanger.getPosition(Character.toString('a' + j ) + Character.toString(i))));
            }
            board.appendLine(p);
        }
    }

}
