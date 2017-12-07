package service;

import java.io.IOException;

public interface DataLoader<T> {
	public abstract String[] loadDataFromStartLineToEndLine(int startLine, int endLine) throws IOException;
}
