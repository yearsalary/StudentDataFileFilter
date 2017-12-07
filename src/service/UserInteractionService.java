package service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import constants.SDFileFilterConstant;
import excepts.IncorrectLineRangeException;

public class UserInteractionService {
	private Integer startLine;
	private Integer endLine;
	private static FileOutputStream outputStream = null;
	
	/**
	 * request userInput.
	 * @throws IncorrectLineRangeException
	 */
	public void requestUserInput() throws IncorrectLineRangeException {
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
	 * print and write Answer form result.
	 * @param result
	 * @param fileName
	 * @throws IOException
	 */
	public void printAndWriteAnswer(int result, String fileName) throws IOException {
		String answer = this.getStartLine() + " " + this.getEndLine()+ " " + result;
		
		System.out.println(answer);
		outputStream = new FileOutputStream(SDFileFilterConstant.OUTPUT_FILE_PATH + "/" + fileName);
		outputStream.write(answer.getBytes());
	}
	
	public Integer getStartLine() {
		return startLine;
	}
	
	public Integer getEndLine() {
		return endLine;
	}
	
}
