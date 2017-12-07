package driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import service.DataLoader;
import service.DataMapper;
import service.StudentDataLoader;
import service.StudentDataMapper;
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
	private static DataMapper<Student> dataMapper = null;
	private static DataLoader<Student> dataLoader = null;
	private static List<Student> modelList = null;
	
	public static void main(String[] args) {
		dataMapper = new StudentDataMapper();
		dataLoader = new StudentDataLoader();
		
		try {
			requestUserInput();
			modelList = createStudentsFromData();
			switch(SDFileFilterConstant.PROBLEM_NUMBER) {
				case 1: solveProblem_01(); break;
				case 2:	solveProblem_02(); break;
				case 3:	solveProblem_03(); break;
				case 4:	solveProblem_04(); break;
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
	 *  create students.
	 * @return List
	 * @throws IOException
	 */
	private static List<Student> createStudentsFromData() throws IOException {
		List<Student> students = new ArrayList<Student>();
		String[] inputLineStrings = dataLoader.loadDataFromStartLineToEndLine(startLine, endLine);
		
		for (int i = 0; i < inputLineStrings.length; i++)
			students.add(dataMapper.createModelFromString(inputLineStrings[i])); //parse and create new Student.
		
		return students;
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
	private static void solveProblem_01() throws IOException, ListSizeUnder5Exception {
		List<Student> filteredStudents = null;
		int result = 0;
		
		filteredStudents = modelList.stream().filter(student -> 'A' == student.getCityNum())
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
	private static void solveProblem_02() throws IOException {
		List<Student> filteredStudents = null;
		Student resultStudent = null;
		int result = 0;
		
		filteredStudents = modelList.stream().filter(student -> 'A' == student.getCityNum())
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
	private static void solveProblem_03() throws IOException {
		List<Student> filteredStudents = null;
		int result = 0;
		
		filteredStudents = modelList.stream().filter(student -> 150 <= student.getMusicScore() + student.getSociologyScore())
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
	private static void solveProblem_04() throws IOException {
		List<Student> filteredStudents = null;
		int result = 0;
		
		filteredStudents = modelList.stream().filter(student -> 'A' == student.getAcademicPerformance() || 'B' == student.getAcademicPerformance())
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
