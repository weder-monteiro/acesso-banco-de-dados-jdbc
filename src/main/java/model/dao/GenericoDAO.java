package model.dao;

import java.util.List;

public interface GenericoDAO<T, K> {
	
	void insert(T t);
	void update(T t);
	void deleteById(K id);
	T findById(K id);
	List<T> findAll();
}
