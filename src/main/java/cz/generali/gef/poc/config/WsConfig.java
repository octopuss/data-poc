package cz.generali.gef.poc.config;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by Ivan Dolezal(T911552) on 16.2.2015.
 *
 * @Author Ivan Dolezal
 */
@EnableWs
@Configuration
public class WsConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		return new ServletRegistrationBean(servlet, "/service/*");
	}

	@Bean(name = "gefPocData")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema gefPocDataSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("GefPocData");
		wsdl11Definition.setLocationUri("/service/");
		wsdl11Definition.setTargetNamespace("http://generali.com/gef/data-poc");
		wsdl11Definition.setSchema(gefPocDataSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema gefPocDataSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/schemas/gef-poc-data.xsd"));
	}
}
