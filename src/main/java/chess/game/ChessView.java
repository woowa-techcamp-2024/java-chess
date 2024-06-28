package chess.game;

public class ChessView {
    public void startMessage() {
        print("체스게임시작~");
    }

    public void pressKeyMessage() {
        print("start/move/end를 입력하세요");
    }

    public void showBoard(String string) {
        String[] lines = string.split("\n");

        StringBuilder boardWithRank = new StringBuilder("   a b c d e f g h\n");
        boardWithRank.append("   ________________\n");
        for (int i = 0; i < lines.length; i++) {
            String line = String.join(" ", lines[i].split(""));

            boardWithRank.append(7 - i + 1).append("ㅣ ");
            boardWithRank.append(line).append("\n");
        }

        print(boardWithRank.toString());
    }

    public void showWinner(GameState state) {
        switch (state) {
            case WHITE_WIN:
                print("흰색 승리!!!");
                break;
            case BLACK_WIN:
                print("검정 승리!!!");
                break;
            case DRAW:
                print("비겼다~");
                break;
            default:
                print("게임이 끝나지 않았습니다");
        }
    }

    private void print(String string) {
        System.out.println(string);
    }

    public void wrongPosMessage() {
        System.out.println("잘못된 좌표입니다! 다시 입력해주세요.");
    }

    public void wrongMoveMessage() {
        System.out.println("불가능한 경로입니다. 다시 입력해주세요.");
    }

    public void wrongCommandMessage() {
        System.out.println("존재하지 않는 명령입니다. 다시 입력해주세요.");
    }
}
