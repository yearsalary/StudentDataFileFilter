package service;

import models.Student;

public class StudentDataMapper implements DataMapper<Student> {
	/**
	 * parse and create new Student.
	 * @param str
	 * @return Student
	 */
	@Override
	public Student createModelFromString(String str) {
		int stuNum = Integer.parseUnsignedInt(str.substring(0, 6));
		String email = str.substring(6, 10);
		int musicScore = Integer.parseUnsignedInt(str.substring(10, 13).trim());
		int sociologyScore = Integer.parseUnsignedInt(str.substring(13, 16).trim());
		int ethicsScore = Integer.parseUnsignedInt(str.substring(16, 19).trim());
		int artScore = Integer.parseUnsignedInt(str.substring(19, 22).trim());
		int historyScore = Integer.parseUnsignedInt(str.substring(22, 25).trim());
		int totalScore = Integer.parseUnsignedInt(str.substring(25, 28).trim());
		char profCode = str.charAt(28);
		char academicPerformance = str.charAt(29);
		char cityNum = str.charAt(30);
		
		return new Student(stuNum, email, musicScore, sociologyScore, ethicsScore, artScore, historyScore, totalScore, profCode, academicPerformance, cityNum);
	}

}
