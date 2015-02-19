import cz.generali.gef.poc.Application;
import cz.generali.gef.poc.dao.CarDao;
import cz.generali.gef.poc.domain.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

/**
 * Created by Ivan Dolezal(T911552) on 16.2.2015.
 *
 * @Author Ivan Dolezal
 */

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class CarDaoTest {


	@Autowired
	@Qualifier("CarDaoDBFRImpl")
	CarDao carDao;

	//@Rollback(value = false)
	@Test public void saveCarTest(){
		Car car = new Car();
		car.setCarMake("Škoda");
		car.setCarType("105");
		car.setRegNo("AA12345");
		car.setVin("123asf456qet789sd");
		carDao.save(car);
		Assert.notNull(car.getCarId());
	}
}
