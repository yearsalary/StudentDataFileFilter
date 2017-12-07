package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import service.DataLoader;
import service.DataMapper;
import service.SolutionService;
import service.StudentDataLoader;
import service.StudentDataMapper;
import service.StudentSolutionService;
import service.UserInteractionService;
import models.Student;
import constants.SDFileFilterConstant;

public class Driver {
	private static FileInputStream inputStream = null;
	private static DataMapper<Student> dataMapper = null;
	private static DataLoader<Student> dataLoader = null;
	private static List<Student> modelList = null;
	private static UserInteractionService userInteractionService = null;
	private static SolutionService<Student> solutionService = null;
	
	public static void main(String[] args) {
		dataMapper = new StudentDataMapper();
		dataLoader = new StudentDataLoader();
		userInteractionService = new UserInteractionService();
		
		try {
			userInteractionService.requestUserInput();
			modelList = createStudentsFromData();
			solutionService = new StudentSolutionService(modelList, userInteractionService);
			switch(SDFileFilterConstant.PROBLEM_NUMBER) {
				case 1: solutionService.solveProblem_01(); break;
				case 2:	solutionService.solveProblem_02(); break;
				case 3:	solutionService.solveProblem_03(); break;
				case 4:	solutionService.solveProblem_04(); break;
			}
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		} finally {
			try {
				if(inputStream != null) inputStream.close();
			} catch (IOException e) {
				System.out.println("Exception: "+e.getMessage());
			}
		}
	}
	
	/**
	 *  create students.
	 * @return List
	 * @throws IOException
	 */
	private static List<Student> createStudentsFromData() throws IOException {
		List<Student> students = new ArrayList<Student>();
		String[] inputLineStrings = dataLoader.loadDataFromStartLineToEndLine(userInteractionService.getStartLine(), userInteractionService.getEndLine());
		
		for (int i = 0; i < inputLineStrings.length; i++)
			students.add(dataMapper.createModelFromString(inputLineStrings[i])); //parse and create new Student.
		
		return students;
	}
	
}
