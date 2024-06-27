package javachess;

import javachess.chess.Game;
import javachess.chess.Position;
import javachess.chess.piece.Piece;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChessController {

    private Game game = createGame();

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/board")
    @ResponseBody
    public Map<String, String> board() {
        Map<String, String> board = new HashMap<>();
        for (int rank = 1; rank <= 8; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                Position pos = Position.of(file, rank);
                board.put(pos.toString(), game.getPiece(pos));
            }
        }
        return board;
    }

    @GetMapping("/turn")
    @ResponseBody
    public String turn() {
        return game.getPlayer() == Piece.Color.WHITE ? "흰색의 차례입니다." : "검은색의 차례입니다";
    }

    @PostMapping("/move/{from}/{to}")
    public void move(@PathVariable String from, @PathVariable String to) {
        Position fromPos = Position.of(from);
        Position toPos = Position.of(to);
        game.move(fromPos, toPos);
    }

    @GetMapping("/moves/{pos}")
    @ResponseBody
    public List<String> moves(@PathVariable String pos) {
        return game.getValidMoves(Position.of(pos)).stream().map(p -> p.toString()).toList();
    }

    private static Game createGame() {
        Game game = new Game();
        game.initialize();
        return game;
    }

}
