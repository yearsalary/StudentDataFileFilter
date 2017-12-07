package constants;

public enum CityNumber {
	CODE_A('A',10),
	CODE_B('B',15),
	CODE_C('C',20);
	
	private char value;
	private int point;
	
	private CityNumber(char value, int point) {
		this.value = value;
		this.point = point;
	}

	public char getValue() {
		return value;
	}

	public int getPoint() {
		return point;
	}
}
