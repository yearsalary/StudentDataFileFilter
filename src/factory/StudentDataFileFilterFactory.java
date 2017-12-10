package factory;

import java.util.ArrayList;
import java.util.List;

import services.UserInteractionService;
import services.loader.DataLoader;
import services.loader.StudentDataLoader;
import services.mapper.DataMapper;
import services.mapper.StudentDataMapper;
import services.solution.SolutionService;
import services.solution.StudentSolutionService;
import models.Student;
import filter.DataFileFilter;

public class StudentDataFileFilterFactory implements DataFileFilterFactory {

	@Override
	public DataFileFilter<?> getDataFileFilter() {
		List<Student> students = new ArrayList<Student>();
		DataMapper<Student> studentDataMapper = new StudentDataMapper();
		DataLoader<Student> studentDataLoader = new StudentDataLoader();
		UserInteractionService userInteractionService = new UserInteractionService();
		SolutionService<Student> studentSolutionService = new StudentSolutionService(students, userInteractionService);
		
		return new DataFileFilter<Student>(students, studentDataMapper, studentDataLoader, userInteractionService, studentSolutionService );
	}

}
