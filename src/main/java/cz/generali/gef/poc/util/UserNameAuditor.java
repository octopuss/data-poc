package cz.generali.gef.poc.util;

import org.springframework.data.domain.AuditorAware;

/**
 * Created by Ivan Dolezal(T911552) on 30.1.2015.
 *
 * @Author Ivan Dolezal
 */

public class UserNameAuditor implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "GEF"; //The user name needs to be retrieved from your application's SecurityContext
	}

}