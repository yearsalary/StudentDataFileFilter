package excepts;

public class ListSizeUnder5Exception extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ListSizeUnder5Exception() {
		super("filteredStudents size under 5!");
	}
}
