package cz.generali.gef.poc.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Dolezal(T911552) on 6.2.2015.
 *
 * @Author Ivan Dolezal
 */
@Entity(name = "V_POLICY")
public class Policy extends AuditableEntity {

	@Id
	@Column(name="POLICY_NO")
	Long policyNo;

	String product;

	BigDecimal premium;

	@ManyToOne
	@JoinTable(name="R_POLICY_CAR",
			joinColumns={@JoinColumn(name="POLICY_NO")},
			inverseJoinColumns={@JoinColumn(name="CAR_ID")})
	Car car;

	Date openDate;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<Partner> getPartners() {
		return partners;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

	@JoinTable(name="V_POLICY_ROLE",
			joinColumns={@JoinColumn(name="POLICY_NO", referencedColumnName="POLICY_NO")},inverseJoinColumns ={@JoinColumn(name="PRT_ID", referencedColumnName="PRT_ID")})
	@ElementCollection(targetClass = Partner.class)
	List<Partner> partners;

	public Long getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(Long policyNo) {
		this.policyNo = policyNo;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}


}
