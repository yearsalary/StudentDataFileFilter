package models;

public class Student {
	private int stuNum;
	private String email;
	private int musicScore;
	private int sociologyScore;
	private int ethicsScore;
	private int artScore;
	private int historyScore;
	private int totalScore;
	private char profCode;
	private char academicPerformance;
	private char cityNum;

	public Student(int stuNum, String email, int musicScore,
			int sociologyScore, int ethicsScore, int artScore,
			int historyScore, int totalScore, char profCode,
			char academicPerformance, char cityNum) {
		super();
		this.setStuNum(stuNum);
		this.setEmail(email);
		this.setMusicScore(musicScore);
		this.setSociologyScore(sociologyScore);
		this.setEthicsScore(ethicsScore);
		this.setArtScore(artScore);
		this.setHistoryScore(historyScore);
		this.setTotalScore(totalScore);
		this.setProfCode(profCode);
		this.setAcademicPerformance(academicPerformance);
		this.setCityNum(cityNum);
	}

	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMusicScore() {
		return musicScore;
	}

	public void setMusicScore(int musicScore) {
		this.musicScore = musicScore;
	}

	public int getSociologyScore() {
		return sociologyScore;
	}

	public void setSociologyScore(int sociologyScore) {
		this.sociologyScore = sociologyScore;
	}

	public int getEthicsScore() {
		return ethicsScore;
	}

	public void setEthicsScore(int ethicsScore) {
		this.ethicsScore = ethicsScore;
	}

	public int getArtScore() {
		return artScore;
	}

	public void setArtScore(int artScore) {
		this.artScore = artScore;
	}

	public int getHistoryScore() {
		return historyScore;
	}

	public void setHistoryScore(int historyScore) {
		this.historyScore = historyScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public char getProfCode() {
		return profCode;
	}

	public void setProfCode(char profCode) {
		this.profCode = profCode;
	}

	public char getAcademicPerformance() {
		return academicPerformance;
	}

	public void setAcademicPerformance(char academicPerformance) {
		this.academicPerformance = academicPerformance;
	}

	public char getCityNum() {
		return cityNum;
	}

	public void setCityNum(char cityNum) {
		this.cityNum = cityNum;
	}

	@Override
	public String toString() {
		return "Student [stuNum=" + stuNum + ", email=" + email
				+ ", musicScore=" + musicScore + ", sociologyScore="
				+ sociologyScore + ", ethicsScore=" + ethicsScore
				+ ", artScore=" + artScore + ", historyScore=" + historyScore
				+ ", totalScore=" + totalScore + ", profCode=" + profCode
				+ ", academicPerformance=" + academicPerformance + ", cityNum="
				+ cityNum + "]";
	}

}
