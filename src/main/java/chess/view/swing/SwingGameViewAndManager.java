package chess.view.swing;

import chess.Board;
import chess.ChessGame;
import chess.GameManager;
import chess.PieceCreatorWithFactory;
import chess.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

public class SwingGameViewAndManager extends SwingViewComponent implements GameView, GameManager {
    private static final int BOARD_SIZE = 8;
    private static final int TILE_SIZE = 50; // 크기 조정
    private ChessGame chessGame;
    private JPanel boardPanel;
    private boolean start = false;
    private PieceTileComponent[][] tiles = new PieceTileComponent[BOARD_SIZE][BOARD_SIZE];

    private Stack<PieceTileComponent> selectedPieces = new Stack<>();

    public SwingGameViewAndManager(ChessGame chessGame) {
        this.chessGame = chessGame;
        this.chessGame.init();
        initStartButton(chessGame);
        initFinishButton(chessGame);
        initBoardPanel(chessGame.showBoard());
    }

    private void boardRepresentationSetting(String board) {
        board = board.replace(System.lineSeparator(), "");
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                tiles[row][col].setRepresentation(board.charAt((row) * BOARD_SIZE + (col)));
                tiles[row][col].reDraw();
            }
        }
    }

    private void turnChange() {
        chessGame.turnChange();
        changeTurnLabel(chessGame.isWhiteTurn() ? "White Turn" : "Black Turn");
    }

    private void initBoardPanel(String board) {
        board = board.replace(System.lineSeparator(), "");
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                tiles[row][col] = new PieceTileComponent(TILE_SIZE, (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY, row, col, board.charAt((row) * BOARD_SIZE + (col)));
            }
        }

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE + 2, BOARD_SIZE + 2));
        // 좌표 표시 및 체스판 타일 생성
        for (int row = 0; row <= BOARD_SIZE + 1; row++) {
            for (int col = 0; col <= BOARD_SIZE + 1; col++) {
                if ((row == 0 || row == BOARD_SIZE + 1) && (col == 0 || col == BOARD_SIZE + 1)) {
                    boardPanel.add(new JLabel(""));
                } else if (row == 0 || row == BOARD_SIZE + 1) {
                    boardPanel.add(new JLabel(String.valueOf((char) ('A' + col - 1)), SwingConstants.CENTER));
                } else if (col == 0 || col == BOARD_SIZE + 1) {
                    boardPanel.add(new JLabel(String.valueOf(BOARD_SIZE - row + 1), SwingConstants.CENTER));
                } else {
                    int px = row - 1;
                    int py = col - 1;
                    tiles[px][py] = new PieceTileComponent(TILE_SIZE, (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY, px, py, board.charAt(px * BOARD_SIZE + py));
                    tiles[px][py].addActionListener(e -> {
                        try {
                            PieceTileComponent target = (PieceTileComponent) e.getSource();
                            if (selectedPieces.contains(target)) {//같은걸 클릭함.
                                selectedPieces.remove(target);
                                target.unSelectTile();
                                turnOffPossiblePosition();
                                return;
                            }

                            selectedPieces.add(target);
                            target.selectTile();

                            if(selectedPieces.size() == 1){
                                List<String> possibleMovePositions = chessGame.possibleMovePositions(target.getPosition());
                                turnOnPossiblePosition(possibleMovePositions);
                            }

                            if (selectedPieces.size() == 2) {
                                PieceTileComponent targetComponent = selectedPieces.pop();
                                PieceTileComponent sourceComponent = selectedPieces.pop();

                                String sourcePosition = sourceComponent.getPosition();
                                String targetPosition = targetComponent.getPosition();
                                boolean move = chessGame.move(sourcePosition, targetPosition);
                                if (move) {
                                    boardRepresentationSetting(chessGame.showBoard());
                                    turnChange();
                                } else {
                                    showError("이동할 수 없습니다.");
                                }
                                turnOffPossiblePosition();
                            }
                        } catch (Exception ee) {
                            showError(ee.getMessage());
                            turnOffPossiblePosition();
                            selectedPieces.clear();
                        }
                    });
                    boardPanel.add(tiles[px][py]);
                }
            }
        }
        setBoardPanel(boardPanel);
        revalidate();
        repaint();
    }

    private void turnOnPossiblePosition(List<String> possibleMovePositions) {
        for(String possibleMovePosition : possibleMovePositions){
            for(int row =0;row<BOARD_SIZE;row++){
                for(int col = 0;col<BOARD_SIZE;col++){
                    if(tiles[row][col].getPosition().equals(possibleMovePosition)){
                        tiles[row][col].selectTile();
                    }
                }
            }
        }
    }

    private void turnOffPossiblePosition() {
        for(int row =0;row<BOARD_SIZE;row++){
            for(int col = 0;col<BOARD_SIZE;col++){
                tiles[row][col].unSelectTile();
            }
        }
    }

    private void initFinishButton(ChessGame chessGame) {
        JButton finishButton = new JButton("End Game");
        finishButton.addActionListener(e -> {
            if (!start) {
                showError("게임이 실행중이 아닙니다.");
                return;
            }
            chessGame.finishGame();
            endGame();
            showMessage("게임을 종료합니다.");
        });
        setEndButton(finishButton);
    }

    private void initStartButton(ChessGame chessGame) {
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            if (start) {
                showError("이미 게임이 실행중입니다.");
            }
            chessGame.startGame();
            startGame();
            showMessage("게임을 시작합니다.");
        });
        setStartButton(startButton);
    }

    @Override
    public String getOperation() {
        return null;
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "chessGame", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showError(String error) {
        JOptionPane.showMessageDialog(null, error, "chessGame", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new SwingGameViewAndManager(new ChessGame(new Board(new PieceCreatorWithFactory())));
    }

    @Override
    public void runningGame() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void action(String operation) {

    }

    @Override
    public void stopGame() {

    }

    protected class StartButtonClickAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            chessGame.startGame();
            showMessage("게임을 시작합니다.");
        }
    }

    protected class FinishButtonClickAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            chessGame.finishGame();
            showMessage("게임을 종료합니다.");
        }
    }
}
