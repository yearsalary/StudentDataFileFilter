package constants;

public enum ProfCode {
	CODE_A('A',5),
	CODE_B('B',15),
	CODE_C('C',20);
	
	private char value;
	private int point;
	
	ProfCode(char value, int point) {
		this.value = value;
		this.point = point;
	}
	
	public char getValue() {
		return this.value;
	}

	public int getPoint() {
		return point;
	}
}
