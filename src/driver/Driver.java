package driver;

import factory.DataFileFilterFactory;
import factory.StudentDataFileFilterFactory;

public class Driver {
	
	public static void main(String[] args) {
		DataFileFilterFactory dataFileFilterFactory = new StudentDataFileFilterFactory();
		dataFileFilterFactory.getDataFileFilter().run();
	}
	
}
