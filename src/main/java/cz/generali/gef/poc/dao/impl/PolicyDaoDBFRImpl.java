package cz.generali.gef.poc.dao.impl;

import cz.generali.gef.poc.dao.CarDao;
import cz.generali.gef.poc.dao.PolicyDao;
import cz.generali.gef.poc.domain.Car;
import cz.generali.gef.poc.domain.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Ivan Dolezal(T911552) on 17.2.2015.
 *
 * @Author Ivan Dolezal
 */
@Component("PolicyDaoDBFRImpl")
public class PolicyDaoDBFRImpl implements PolicyDao{

	private JdbcTemplate jdbcTemplate;
	@Autowired
	public PolicyDaoDBFRImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	DataSource dataSource;

	@Autowired
	AuditorAware<String> userNameAuditor;

	@Autowired @Qualifier("CarDaoDBFRImpl") CarDao carDao;

	@Override public Policy save(Policy policy) {


		String query = "INSERT INTO V_CAR_POLICY(CAR_ID,REG_NO,VIN,CAR_MAKE,CAR_TYPE, POLICY_NO, PRODUCT, PREMIUM, OPEN_DATE) VALUES (? ,? , ?, ?, ?, ?, ?, ?, ?)";
		Car car = policy.getCar();
		if(car != null ) {
			Car copyCar = car.copy();
			carDao.save(copyCar);
			saveInternal(policy);
			jdbcTemplate.update(query,
					new Object[] { car.getCarId(), car.getRegNo(), car.getVin(), car.getCarMake(), car.getCarType(),
							policy.getPolicyNo(), policy.getProduct(), policy.getPremium(), policy.getOpenDate()});
			policy.setCar(copyCar);
		} else {
			saveInternal(policy);
		}

		return policy;
	}

	@Override public Policy find(Long id) {
		return null;
	}

	@Override public void delete(Long id) {

	}

	@Override public Long count() {
		return null;
	}

	private Policy saveInternal(Policy policy) {
		if (policy == null) {
			throw new IllegalArgumentException("No entity provided");
		}
		String statement = "{call as_policy_pckg.insupd_policy(?, ?, ?, ?, ?)}";
		CallableStatement callableStatement;
		try {
			callableStatement = dataSource.getConnection().prepareCall(statement);
			if(policy.getPolicyNo()!=null) {
				callableStatement.setLong(1, policy.getPolicyNo());
			}else {
				callableStatement.setNull(1, Types.INTEGER);
			}
			callableStatement.setString(2, policy.getProduct());
			callableStatement.setBigDecimal(3, policy.getPremium());
			callableStatement.setDate(4, new Date(policy.getOpenDate().getTime()));
			callableStatement.setString(5, userNameAuditor.getCurrentAuditor());
			callableStatement.executeUpdate();
			return policy;
		} catch (SQLException e) {
			e.printStackTrace();
			//log etc..
			return null;
		}
	}
}
