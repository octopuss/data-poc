package cz.generali.gef.poc.config;

import cz.generali.gef.poc.util.UserNameAuditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by Ivan Dolezal(T911552) on 30.1.2015.
 *
 * @Author Ivan Dolezal
 */
@Configuration
@EnableJpaAuditing
public class Config {
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new UserNameAuditor();
	}
}
