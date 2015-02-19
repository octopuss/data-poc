import cz.generali.gef.poc.Application;
import cz.generali.gef.poc.dao.PolicyDao;
import cz.generali.gef.poc.domain.Car;
import cz.generali.gef.poc.domain.Policy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Ivan Dolezal(T911552) on 17.2.2015.
 *
 * @Author Ivan Dolezal
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class PolicyRepositoryTest {


	@Autowired
	@Qualifier("PolicyDaoDBFRImpl")
	PolicyDao policyDao;

	@PersistenceContext
	EntityManager em;

	@Test
	public void savePolicyTest(){
		Car car = new Car();
		car.setCarMake("Škoda");
		car.setCarType("105");
		car.setRegNo("AA12345");
		car.setVin("123asf456qet789dd");
		Policy policy = new Policy();
		policy.setCar(car);
		policy.setProduct("PMV");
		policy.setPolicyNo(12345l);
		policy.setPremium(BigDecimal.valueOf(1000.50));
		policy.setOpenDate(new Date());

		policyDao.save(policy);

		Policy policyEntity = em.find(Policy.class,policy.getPolicyNo());
		Assert.assertEquals(policyEntity.getCar().getCarId(), car.getCarId());
	}
}
