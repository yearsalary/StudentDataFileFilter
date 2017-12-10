package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import constants.SDFileFilterConstant;
import models.Student;
import services.UserInteractionService;
import services.loader.DataLoader;
import services.loader.StudentDataLoader;
import services.mapper.DataMapper;
import services.mapper.StudentDataMapper;
import services.solution.SolutionService;
import services.solution.StudentSolutionService;

public class DataFileFilter<T> {
	private List<T> modelList = null;
	private DataMapper<T> dataMapper = null;
	private DataLoader<T> dataLoader = null;
	private UserInteractionService userInteractionService = null;
	private SolutionService<T> solutionService = null;
	
	public DataFileFilter(List<T> modelList,
			DataMapper<T> dataMapper,
			DataLoader<T> dataLoader,
			UserInteractionService userInteractionService,
			SolutionService<T> solutionService) {
		this.modelList = modelList;
		this.dataMapper = dataMapper;
		this.dataLoader = dataLoader;
		this.userInteractionService = userInteractionService;
		this.solutionService = solutionService;
	}

	public void run() {
		try {
			userInteractionService.requestUserInput();
			modelList.addAll(createModelListFromData());
			switch(SDFileFilterConstant.PROBLEM_NUMBER) {
				case 1: solutionService.solveProblem_01(); break;
				case 2:	solutionService.solveProblem_02(); break;
				case 3:	solutionService.solveProblem_03(); break;
				case 4:	solutionService.solveProblem_04(); break;
			}
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		} 
	}
	
	/**
	 *  create modelList.
	 * @return List
	 * @throws IOException
	 */
	private List<T> createModelListFromData() throws IOException {
		List<T> modelList = new ArrayList<T>();
		String[] inputLineStrings = dataLoader.loadDataFromStartLineToEndLine(userInteractionService.getStartLine(), userInteractionService.getEndLine());
		
		for (int i = 0; i < inputLineStrings.length; i++)
			modelList.add(dataMapper.createModelFromString(inputLineStrings[i])); //parse and create new Student.
		
		return modelList;
	}
}
