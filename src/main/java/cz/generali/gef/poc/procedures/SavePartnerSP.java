package cz.generali.gef.poc.procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;
import java.sql.Date;
import java.util.Map;

/**
 * Created by Ivan Dolezal(T911552) on 2.2.2015.
 *
 * @Author Ivan Dolezal
 */
@Component
public class SavePartnerSP extends StoredProcedure{

	private static final String SPROC_NAME = "as_partner_pckg.insupd_partner";

//	as_partner_pckg.insupd_partner(in_prt_id => NULL,
//	iv_firstname => NULL,
//	iv_surname => NULL,
//	iv_person_in => NULL,
//	id_birth_date => NULL,
//	iv_userlogin => NULL,
//	on_prt_id => ln_prt_id);


	@Autowired
		public SavePartnerSP(DataSource dataSource)	{
			super( dataSource, SPROC_NAME );
			declareParameter(new SqlParameter("in_prt_id", Types.INTEGER));
			declareParameter(new SqlParameter("iv_firstname", Types.VARCHAR));
			declareParameter(new SqlParameter("iv_surname", Types.VARCHAR));
			declareParameter(new SqlParameter("iv_person_in", Types.VARCHAR));
			declareParameter(new SqlParameter("id_birth_date", Types.DATE));
			declareParameter(new SqlParameter("iv_userlogin", Types.VARCHAR));
			declareParameter(new SqlOutParameter("on_prt_id", Types.INTEGER));
			compile();
		}


	public Long execute(Long id,
			String firstName,
			String surname,
			String birthCertNr,
			Date birthDate,
			String userLogin) {
		Map<String,Object> results  = super.execute(id, firstName, surname, birthCertNr, birthDate, userLogin);
		return Long.valueOf(results.get("on_prt_id").toString());
	}

}
