package javachess.chess;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OffsetTest {

    @Test
    public void isMultipleOf() {
        Offset zero = new Offset(0, 1);
        Offset one = new Offset(1, 1);
        Offset two = new Offset(2, 2);
        Offset three = new Offset(3, 3);
        Offset minusTwo = new Offset(-2, -2);
        Offset oneTwo = new Offset(1, 2);

        assertThat(one.isMultipleOf(zero)).isFalse();
        assertThat(one.isMultipleOf(one)).isTrue();
        assertThat(two.isMultipleOf(one)).isTrue();
        assertThat(one.isMultipleOf(two)).isFalse();
        assertThat(three.isMultipleOf(one)).isTrue();
        assertThat(three.isMultipleOf(two)).isFalse();
        assertThat(minusTwo.isMultipleOf(one)).isFalse();
        assertThat(minusTwo.isMultipleOf(two)).isFalse();
        assertThat(two.isMultipleOf(oneTwo)).isFalse();
    }

}
