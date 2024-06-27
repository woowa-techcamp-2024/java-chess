package chess;

import java.util.Arrays;

public class Command {

	public static final int START_INCLUSIVE = 1;
	public static final int END_EXCLUSIVE = 3;
	public static final String SPLITERATOR = " ";

	private final CommandType commandType;
	private final String[] arguments;

	public Command(String arguments) {
		commandType = Arrays.stream(CommandType.values())
			.filter(type -> arguments.startsWith(type.getValue().toLowerCase()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("invalid command type."));
		this.arguments = parseCommand(arguments);
	}

	public String[] parseCommand(String arguments) {
		String[] split = arguments.split(SPLITERATOR);
		if (commandType == CommandType.MOVE) {
			validateCommand(split);
		}
		return Arrays.stream(split, START_INCLUSIVE, split.length)
			.toArray(String[]::new);
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public String[] getArguments() {
		return arguments;
	}

	private void validateCommand(String[] split) {
		if (split.length != END_EXCLUSIVE) {
			throw new IllegalArgumentException("Invalid number of arguments.");
		}
	}
}
