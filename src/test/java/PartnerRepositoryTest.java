import cz.generali.gef.poc.Application;
import cz.generali.gef.poc.dao.PartnerDao;
import cz.generali.gef.poc.domain.Partner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Ivan Dolezal(T911552) on 30.1.2015.
 *
 * @Author Ivan Dolezal
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class PartnerRepositoryTest {


	@Autowired
	//@Qualifier("PartnerDaoDBFRImpl")
	@Qualifier("PartnerDaoRapidImpl")
	PartnerDao partnerDao;

	@Test
	//@Rollback(value = false)
	public void partnerSaveTest() {
		Partner partner = new Partner();
		partner.setFirstName("Ivan");
		partner.setSurname("Doležal");
		partner.setBirthCertNr("8501018988");
		partner.setBirthDate(new Date());
		Long count = partnerDao.count();
		partnerDao.save(partner);
		assertNotNull(partner.getId());

		Partner savedPartner = partnerDao.find(partner.getId());
		assertNotNull(savedPartner);

		partnerDao.delete(savedPartner.getId());

		assertEquals(count, partnerDao.count());

	}
	@Test
	public void cardinalityTest(){
		Partner partner = partnerDao.find(1L);
		assertEquals(1,partner.getPolicies().size());
	}

}
