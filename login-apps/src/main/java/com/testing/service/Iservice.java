package com.testing.service;

public interface Iservice<T> {
	
	T save(T entity);
	void upadate(T entity);


	void delete(int id);

}