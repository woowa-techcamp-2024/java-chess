package chess;

public enum CommandType {

	START("start"),
	END("end"),
	MOVE("move"),
	PROMOTION("promotion");

	private final String value;

	CommandType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
