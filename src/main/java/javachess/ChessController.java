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

    @PostMapping("/init")
    @ResponseBody
    public void init() {
        game.initialize();
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

    @GetMapping("/state")
    @ResponseBody
    public StateDto state() {
        if (game.getState() == Game.State.BLACK_WIN) {
            return StateDto.win("흑 승리");
        } else if (game.getState() == Game.State.WHITE_WIN) {
            return StateDto.win("백 승리");
        } else {
            String message = game.getPlayer() == Piece.Color.WHITE ? "백 차례" : "흑 차례";
            if (game.isCheck(game.getPlayer())) {
                Position kingPosition = game.getKingPosition(game.getPlayer());
                return StateDto.playing(message, kingPosition.toString());
            } else {
                return StateDto.playing(message, null);
            }
        }
    }

    @PostMapping("/move/{from}/{to}")
    @ResponseBody
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
