package com.testing.service;

import com.testing.repository.UserRepository;

public interface Iservice<T> {
	
	T save(T entity);
	void upadate(T entity);


	void delete(int id);

}