package com.wootecam.chess.game;

import com.wootecam.chess.pieces.Piece;
import java.util.List;

public record BoardState(List<List<Piece>> boardState) {
}
