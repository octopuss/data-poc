package cz.generali.gef.poc.repository;

import cz.generali.gef.poc.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ivan Dolezal(T911552) on 16.2.2015.
 *
 * @Author Ivan Dolezal
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}
