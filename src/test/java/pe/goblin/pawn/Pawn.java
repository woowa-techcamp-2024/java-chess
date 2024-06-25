package pe.goblin.pawn;

public class Pawn {
    private String color;

    public Pawn() {
        this("white");
    }

    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
