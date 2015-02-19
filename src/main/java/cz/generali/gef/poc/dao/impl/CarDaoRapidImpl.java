package cz.generali.gef.poc.dao.impl;

import cz.generali.gef.poc.dao.CarDao;
import cz.generali.gef.poc.domain.Car;
import cz.generali.gef.poc.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ivan Dolezal(T911552) on 16.2.2015.
 *
 * @Author Ivan Dolezal
 */
@Component("CarDaoRapidImpl")
public class CarDaoRapidImpl implements CarDao {

 	@Autowired
	CarRepository carRepository;


	@Override public Car save(Car entity) {
		carRepository.save(entity);
		return entity;
	}

	@Override public Car find(Long id) {
		return null;
	}

	@Override public void delete(Long id) {

	}

	@Override public Long count() {
		return null;
	}
}
