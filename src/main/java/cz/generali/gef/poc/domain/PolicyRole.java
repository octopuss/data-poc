package cz.generali.gef.poc.domain;

import javax.persistence.*;

/**
 * Created by Ivan Dolezal(T911552) on 6.2.2015.
 *
 * @Author Ivan Dolezal
 */
@Entity(name = "V_POLICY_ROLE")

public class PolicyRole  extends AuditableEntity{

	@Id PolicyRoleId policyRoleId;
	Integer roleTypeId;
}
