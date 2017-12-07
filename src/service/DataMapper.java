package service;

public interface DataMapper <T> {
	public abstract T createModelFromString(String str);
}
