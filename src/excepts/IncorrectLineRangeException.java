package excepts;

public class IncorrectLineRangeException extends Exception {

	private static final long serialVersionUID = 1L;

	public IncorrectLineRangeException() {
		super("incorrect line scope!");
	}
}
