package service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import constants.CityNumber;
import constants.ProfCode;
import constants.SDFileFilterConstant;
import excepts.ListSizeUnder5Exception;
import models.Student;

public class StudentSolutionService implements SolutionService<Student>{
	private List<Student> students = null; // student repository.
	private UserInteractionService userInteractionService = null; // current view.
	private List<Student> filteredStudents = null;
	private int result = 0;
	
	public StudentSolutionService(List<Student> students, UserInteractionService userInteractionService) {
		this.students = students;
		this.userInteractionService = userInteractionService;
	}
	
	@Override
	public void solveProblem_01() throws IOException, ListSizeUnder5Exception {
		filteredStudents = students.stream().filter(student -> 'A' == student.getCityNum())
						 					.collect(Collectors.toList());
		filteredStudents.sort((Student s1, Student s2) -> ((s1.getArtScore() + s1.getEthicsScore())
															- (s2.getArtScore() + s2.getEthicsScore())));
		if(filteredStudents.size() < 5)
				throw new ListSizeUnder5Exception();
		result = filteredStudents.get(4).getArtScore() + filteredStudents.get(4).getEthicsScore();
		userInteractionService.printAndWriteAnswer(result, SDFileFilterConstant.OUTPUT_FILE_NAME[0]);
	}

	@Override
	public void solveProblem_02() throws IOException {
		Student resultStudent = null;
				
		filteredStudents = students.stream().filter(student -> 'A' == student.getCityNum())
 											.collect(Collectors.toList());
		resultStudent = filteredStudents.stream().min((Student s1, Student s2) -> ((s1.getArtScore() + s1.getEthicsScore())
																					- (s2.getArtScore() + s2.getEthicsScore()))).get();
		result = resultStudent.getArtScore() + resultStudent.getEthicsScore();
		userInteractionService.printAndWriteAnswer(result, SDFileFilterConstant.OUTPUT_FILE_NAME[1]);
	}

	@Override
	public void solveProblem_03() throws IOException {
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
		userInteractionService.printAndWriteAnswer(result, SDFileFilterConstant.OUTPUT_FILE_NAME[2]);
	}

	@Override
	public void solveProblem_04() throws IOException {
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
		userInteractionService.printAndWriteAnswer(result, SDFileFilterConstant.OUTPUT_FILE_NAME[3]);
	}

}
