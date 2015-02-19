package cz.generali.gef.poc.dao.impl;

import cz.generali.gef.poc.dao.CarDao;
import cz.generali.gef.poc.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Ivan Dolezal(T911552) on 19.2.2015.
 *
 * @Author Ivan Dolezal
 */
@Component("CarDaoDBFRImpl")
public class CarDaoDBFRImpl implements CarDao {

	private JdbcTemplate jdbcTemplate;
	@Autowired
	public CarDaoDBFRImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override public Car save(final Car car) {
		final String query = "INSERT INTO V_CAR (CAR_ID,REG_NO, VIN, CAR_MAKE, CAR_TYPE) values (?,?,?,?,?)";
		final Long newId = getId();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps =
						connection.prepareStatement(query);
				ps.setLong(1,newId);
				ps.setString(2,car.getRegNo());
				ps.setString(3,car.getVin());
				ps.setString(4,car.getCarMake());
				ps.setString(5,car.getCarType());
				return ps;
			}
		});
		car.setCarId(newId);
		return car;
	}

	@Override public Car find(Long id) {
		return null;
	}

	@Override public void delete(Long id) {

	}

	@Override public Long count() {
		return null;
	}


	private Long getId() {
		final String query = "select seq_d_car.nextval from dual";
		return jdbcTemplate.queryForObject(query, Long.class);
	}
}
