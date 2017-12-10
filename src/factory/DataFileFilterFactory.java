package factory;

import filter.DataFileFilter;

public interface DataFileFilterFactory {
	
	public DataFileFilter<?> getDataFileFilter();
	
}
