package com.wootecam.chess;

import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public final class Converter {

    private Converter() {
    }

    public static class PieceTypeConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            if (source instanceof String && PieceType.class.isAssignableFrom(targetType)) {
                return PieceType.valueOf((String) source);
            }
            throw new ArgumentConversionException("Conversion failed.");
        }
    }

    public static class ColorConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            if (source instanceof String && Color.class.isAssignableFrom(targetType)) {
                return Color.valueOf((String) source);
            }
            throw new ArgumentConversionException("Conversion failed.");
        }
    }

    public static class DirectionConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            if (source instanceof String && Direction.class.isAssignableFrom(targetType)) {
                return Direction.valueOf((String) source);
            }
            throw new ArgumentConversionException("Conversion failed.");
        }
    }
}
