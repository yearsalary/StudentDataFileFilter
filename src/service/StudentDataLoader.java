package service;

import java.io.FileInputStream;
import java.io.IOException;

import constants.SDFileFilterConstant;
import models.Student;

public class StudentDataLoader implements DataLoader<Student> {
	/***
	 * read lines within a specified range from file
	 */
	@Override
	public String[] loadDataFromStartLineToEndLine(int startLine, int endLine) throws IOException {
		byte[] inputLinesByte = new byte[SDFileFilterConstant.LINE_LENGTH * (endLine - startLine + 1)];
		String[] inputLineStrings = null;
		FileInputStream inputStream = new FileInputStream(SDFileFilterConstant.INPUT_FILE_PATH);
		
		inputStream.skip(SDFileFilterConstant.LINE_LENGTH * (startLine - 1)); //set offset.
		inputStream.read(inputLinesByte, 0, SDFileFilterConstant.LINE_LENGTH * (endLine - startLine + 1)); //read file by a specified size.
		inputStream.close();
		inputLineStrings = new String(inputLinesByte, SDFileFilterConstant.DECODING_METHOD).split("\r\n"); //decode Byte to String with UTF-8 and split line.
		return inputLineStrings;
	}

}
