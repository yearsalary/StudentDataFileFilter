package services.solution;
import java.io.IOException;
import excepts.ListSizeUnder5Exception;

public interface SolutionService<T> {
	public abstract void solveProblem_01() throws IOException, ListSizeUnder5Exception;
	public abstract void solveProblem_02() throws IOException;
	public abstract void solveProblem_03() throws IOException;
	public abstract void solveProblem_04() throws IOException;
}
