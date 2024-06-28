package com.wootecam.chess.game;

import com.wootecam.chess.pieces.property.Color;

public record ChessResult(double whiteScore, double blackScore, Color winner) {
}
