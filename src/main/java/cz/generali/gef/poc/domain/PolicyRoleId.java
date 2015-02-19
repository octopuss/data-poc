package cz.generali.gef.poc.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Ivan Dolezal(T911552) on 6.2.2015.
 *
 * @Author Ivan Dolezal
 */
@Embeddable
public class PolicyRoleId implements Serializable{

	@ManyToOne
	Partner partner;
	@ManyToOne
	Policy policy;


}
