package services.loader;

import java.io.IOException;

public interface DataLoader {
	public abstract String[] loadDataFromStartLineToEndLine(int startLine, int endLine) throws IOException;
}
