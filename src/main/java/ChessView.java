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

    private void print(String string) {
        System.out.println(string);
    }
}
