package cz.generali.gef.poc.dao.impl;

import cz.generali.gef.poc.dao.PartnerDao;
import cz.generali.gef.poc.domain.Partner;
import cz.generali.gef.poc.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ivan Dolezal(T911552) on 2.2.2015.
 *
 * @Author Ivan Dolezal
 */

@Component("PartnerDaoRapidImpl")
public class PartnerDaoRapidImpl implements PartnerDao {



	@Autowired
	PartnerRepository partnerRepository;



	@Override public Partner save(Partner partner) {
		return partnerRepository.save(partner);
	}

	@Override public Partner find(Long id) {
		return partnerRepository.findOne(id);
	}

	@Override public void delete(Long id) {
		partnerRepository.delete(id);
	}

	@Override public Long count() {
		return partnerRepository.count();
	}

}
