package driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import models.Student;
import constants.CityNumber;
import constants.ProfCode;
import constants.SDFileFilterConstant;
import excepts.IncorrectLineRangeException;
import excepts.ListSizeUnder5Exception;

public class Driver {
	private static int startLine;
	private static int endLine;
	private static FileInputStream inputStream = null;
	private static FileOutputStream outputStream = null;
	
	public static void main(String[] args) {
		try {
			requestUserInput();
			switch(SDFileFilterConstant.PROBLEM_NUMBER) {
				case 1: solveProblem_01(createStudentsFromData()); break;
				case 2:	solveProblem_02(createStudentsFromData()); break;
				case 3:	solveProblem_03(createStudentsFromData()); break;
				case 4:	solveProblem_04(createStudentsFromData()); break;
			}
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		} finally {
			try {
				if(inputStream != null) inputStream.close();
				if(outputStream != null) outputStream.close();
			} catch (IOException e) {
				System.out.println("Exception: "+e.getMessage());
			}
		}
	}
	
	/**
	 * request userInput.
	 * @throws IncorrectLineRangeException
	 */
	private static void requestUserInput() throws IncorrectLineRangeException {
		Scanner scan = new Scanner(System.in);
		System.out.println("start line:");
		startLine = scan.nextInt();
		System.out.println("end line:");
		endLine = scan.nextInt();
		scan.close();
		
		if(startLine > endLine)
			throw new IncorrectLineRangeException();
	}
	
	/**
	 * read lines within a specified range from file and create students.
	 * @return List
	 * @throws IOException
	 */
	private static List<Student> createStudentsFromData() throws IOException {
		byte[] inputLinesByte = new byte[SDFileFilterConstant.LINE_LENGTH * (endLine - startLine + 1)];
		String[] inputLineStrings = null;
		List<Student> students = new ArrayList<Student>();
		
		inputStream = new FileInputStream(SDFileFilterConstant.INPUT_FILE_PATH);
		inputStream.skip(SDFileFilterConstant.LINE_LENGTH * (startLine - 1)); //set offset.
		inputStream.read(inputLinesByte, 0, SDFileFilterConstant.LINE_LENGTH * (endLine - startLine + 1)); //read file by a specified size.
		inputLineStrings = new String(inputLinesByte, SDFileFilterConstant.DECODING_METHOD).split("\r\n"); //decode Byte to String with UTF-8 and split line.
		for (int i = 0; i < inputLineStrings.length; i++)
			students.add(createStudentFromString(inputLineStrings[i])); //parse and create new Student.
		
		return students;
	}
	
	/**
	 * parse and create new Student.
	 * @param str
	 * @return Student
	 */
	private static Student createStudentFromString(String str) {
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
	
	/**
	 * print and write Answer form result.
	 * @param result
	 * @param fileName
	 * @throws IOException
	 */
	private static void printAndWriteAnswer(int result, String fileName) throws IOException {
		String answer = startLine + " " + endLine + " " + result;
		
		System.out.println(answer);
		outputStream = new FileOutputStream(SDFileFilterConstant.OUTPUT_FILE_PATH + "/" + fileName);
		outputStream.write(answer.getBytes());
	}
	
	/**
	 * solve prblem_01.
	 * @param students
	 * @throws ListSizeUnder5Exception 
	 * @throws IOException 
	 */
	private static void solveProblem_01(List<Student> students) throws IOException, ListSizeUnder5Exception {
		List<Student> filteredStudents = null;
		int result = 0;
		
		filteredStudents = students.stream().filter(student -> 'A' == student.getCityNum())
						 					.collect(Collectors.toList());
		filteredStudents.sort((Student s1, Student s2) -> ((s1.getArtScore() + s1.getEthicsScore())
															- (s2.getArtScore() + s2.getEthicsScore())));
		if(filteredStudents.size() < 5)
				throw new ListSizeUnder5Exception();
		result = filteredStudents.get(4).getArtScore() + filteredStudents.get(4).getEthicsScore();
		printAndWriteAnswer(result, SDFileFilterConstant.OUTPUT_FILE_NAME[0]);
	}
	
	/**
	 * solve problem_02.
	 * @param students
	 * @throws IOException
	 */
	private static void solveProblem_02(List<Student> students) throws IOException {
		List<Student> filteredStudents = null;
		Student resultStudent = null;
		int result = 0;
		
		filteredStudents = students.stream().filter(student -> 'A' == student.getCityNum())
 											.collect(Collectors.toList());
		resultStudent = filteredStudents.stream().min((Student s1, Student s2) -> ((s1.getArtScore() + s1.getEthicsScore())
																					- (s2.getArtScore() + s2.getEthicsScore()))).get();
		result = resultStudent.getArtScore() + resultStudent.getEthicsScore();
		printAndWriteAnswer(result, SDFileFilterConstant.OUTPUT_FILE_NAME[1]);
	}
	
	/**
	 * solve problem_03.
	 * @param students
	 * @throws IOException
	 */
	private static void solveProblem_03(List<Student> students) throws IOException {
		List<Student> filteredStudents = null;
		int result = 0;
		
		filteredStudents = students.stream().filter(student -> 150 <= student.getMusicScore() + student.getSociologyScore())
											.collect(Collectors.toList());
		for(Student student : filteredStudents) {
			for(ProfCode profCode : ProfCode.values()) {
				if(student.getProfCode() == profCode.getValue()) {
					result += profCode.getPoint();
					break;
				}
			}
			result += student.getTotalScore();
		}
		printAndWriteAnswer(result, SDFileFilterConstant.OUTPUT_FILE_NAME[2]);
	}
	
	/**
	 * solve problem_04.
	 * @param students
	 * @throws IOException
	 */
	private static void solveProblem_04(List<Student> students) throws IOException {
		List<Student> filteredStudents = null;
		int result = 0;
		
		filteredStudents = students.stream().filter(student -> 'A' == student.getAcademicPerformance() || 'B' == student.getAcademicPerformance())
											.collect(Collectors.toList());
		for(Student student : filteredStudents) {
			for(CityNumber cityNumber : CityNumber.values()) {
				if(student.getCityNum() == cityNumber.getValue()) {
					result += cityNumber.getPoint();
					break;
				}
			}
			result += student.getHistoryScore();
		}
		printAndWriteAnswer(result, SDFileFilterConstant.OUTPUT_FILE_NAME[3]);
	}
}
