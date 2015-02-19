package cz.generali.gef.poc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Dolezal(T911552) on 7.1.2015.
 *
 * @Author Ivan Dolezal
 */
@Entity(name = "V_PARTNER")
@SequenceGenerator(name = "seq_d_partner", sequenceName = "seq_d_partner", allocationSize = 1)
@NamedStoredProcedureQuery(name = "Partner.save", procedureName = "as_partner_pckg.insupd_partner", parameters = {
		@StoredProcedureParameter(name = "in_prt_id", type = Long.class),
		@StoredProcedureParameter( name = "iv_firstname", type = String.class),
		@StoredProcedureParameter( name = "iv_surname", type = String.class),
		@StoredProcedureParameter( name = "iv_person_in", type = String.class),
@StoredProcedureParameter( name = "id_birth_date", type = java.sql.Date.class),
@StoredProcedureParameter( name = "iv_userlogin", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "on_prt_id", type = Long.class)})
public class Partner extends AuditableEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_d_partner")
	@Column(name = "PRT_ID")
	private Long id;
	@Column(name = "FIRSTNAME")
	private String firstName;

	private String surname;

	@Column(name = "PERSON_IN")
	private String birthCertNr;

	private Date birthDate;

	@ManyToMany
	@JoinTable(name="V_POLICY_ROLE",
			joinColumns={@JoinColumn(name="PRT_ID", referencedColumnName="PRT_ID")},inverseJoinColumns = {@JoinColumn(name="POLICY_NO", referencedColumnName="POLICY_NO")})
	@ElementCollection(targetClass = Policy.class)
	private List<Policy> policies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthCertNr() {
		return birthCertNr;
	}

	public void setBirthCertNr(String birthCertNr) {
		this.birthCertNr = birthCertNr;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

	@Override public String toString() {
		return "Partner{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", surname='" + surname + '\'' +
				", birthCertNr='" + birthCertNr + '\'' +
				", birthDate=" + birthDate +
				", policies=" + policies +
				'}';
	}
}
