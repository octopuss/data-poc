package cz.generali.gef.poc.repository;

import cz.generali.gef.poc.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ivan Dolezal(T911552) on 7.1.2015.
 *
 * @Author Ivan Dolezal
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
/*
	@Procedure(name="Partner.save")
	 Long saveProcedure(@Param("in_prt_id") Long id,
			@Param("iv_firstname") String firstName,
			@Param("iv_surname") String surname,
			@Param("iv_person_in") String birthCertNr,
			@Param("id_birth_date") Date birthDate,
			@Param("iv_userlogin") String userLogin);

	@Procedure(name = "Partner.saveNamed")
	Long saveN( Long id,
			 String firstName,
			String surname,
			 String birthCertNr,
			 Date birthDate,
			String userLogin);
			*/
}
