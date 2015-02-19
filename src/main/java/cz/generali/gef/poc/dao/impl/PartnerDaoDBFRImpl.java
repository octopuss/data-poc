package cz.generali.gef.poc.dao.impl;

import cz.generali.gef.poc.dao.PartnerDao;
import cz.generali.gef.poc.domain.Partner;
import cz.generali.gef.poc.procedures.SavePartnerSP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Ivan Dolezal(T911552) on 6.2.2015.
 *
 * @Author Ivan Dolezal
 */

@Component("PartnerDaoDBFRImpl")
public class PartnerDaoDBFRImpl implements PartnerDao {

	@Autowired
	EntityManager entityManager;

	@Autowired
	DataSource dataSource;

	@Autowired
	AuditorAware<String> userNameAuditor;

	@Autowired
	SavePartnerSP savePartnerSP;

	//null, partner.getFirstName(), partner.getSurname(), partner.getBirthCertNr(),
	//new Date(partner.getBirthDate().getTime()), userNameAuditor.getCurrentAuditor()

//	private Long saveProcedure(Partner partner) {
//		return savePartnerSP.execute(null,partner.getFirstName(), partner.getSurname(), partner.getBirthCertNr(),
//				new Date(partner.getBirthDate().getTime()), userNameAuditor.getCurrentAuditor());
//	}
//
//	private Long saveEm(Partner partner) {
//		entityManager.persist(partner);
//		return partner.getId();
//	}

//
//	private Long saveNamed(Partner partner) {
//		Object output = entityManager.createNamedStoredProcedureQuery("Partner.saveNamed")
//				.setParameter("in_prt_id", false)
//				.setParameter("iv_firstname", partner.getFirstName())
//				.setParameter("iv_surname",partner.getSurname())
//				.setParameter("iv_person_in",partner.getBirthCertNr())
//				.setParameter("id_birth_date",new Date(partner.getBirthDate().getTime()))
//				.setParameter("iv_userlogin",userNameAuditor.getCurrentAuditor())
//				.getOutputParameterValue("on_prt_id");
//		return Long.valueOf(output.toString());
//	}


	@Override public Partner save(Partner partner)  {
		if (partner == null) {
			throw new IllegalArgumentException("No entity provided");
		}
		String statement = "{call as_partner_pckg.insupd_partner(?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement callableStatement;
		try {
		callableStatement = dataSource.getConnection().prepareCall(statement);
		if(partner.getId()!=null) {
			callableStatement.setLong(1, partner.getId());
		}else {
			callableStatement.setNull(1, Types.INTEGER);
		}
		callableStatement.setString(2, partner.getFirstName());
		callableStatement.setString(3, partner.getSurname());
		callableStatement.setString(4, partner.getBirthCertNr());
		callableStatement.setDate(5, new Date(partner.getBirthDate().getTime()));
		callableStatement.setString(6, userNameAuditor.getCurrentAuditor());
		callableStatement.registerOutParameter(7, Types.INTEGER);
		callableStatement.executeUpdate();
		partner.setId(callableStatement.getLong(7));
		return partner;
		} catch (SQLException e) {
			e.printStackTrace();
			//log etc..
			return null;
		}

	}

	@Override public Partner find(Long id) {
		return entityManager.find(Partner.class, id);
	}

	@Override public void delete(Long id) {
		String statement = "{call as_partner_pckg.del_partner(?)}";
		CallableStatement callableStatement;
		try {
			callableStatement = dataSource.getConnection().prepareCall(statement);
			callableStatement.setLong(1, id);
			callableStatement.execute();
		}catch (SQLException e) {
			e.printStackTrace();
			//log etc..
		}
	}

	@Override public Long count() {
		return ((BigDecimal)entityManager.createNamedQuery("Partner.count").getSingleResult()).longValue();
	}
}
