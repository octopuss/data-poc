package cz.generali.gef.poc.dao;

/**
 * Created by Ivan Dolezal(T911552) on 16.2.2015.
 *
 * @Author Ivan Dolezal
 */
public interface GefDao<T> {
	T save(T entity);

	T find(Long id);

	void delete(Long id);

	Long count();
}
