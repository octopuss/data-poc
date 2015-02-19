package cz.generali.gef.poc.endpoint;

import cz.generali.gef.poc.dao.PartnerDao;
import cz.generali.gef.poc.domain.Partner;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.text.SimpleDateFormat;

/**
 * Created by Ivan Dolezal(T911552) on 16.2.2015.
 *
 * @Author Ivan Dolezal
 *
 */

@Endpoint
public class GefPocDataEndpoint {


	private static final String NAMESPACE_URI = "http://generali.com/gef/schemas";

	@Qualifier("PartnerDaoRapidImpl")
	PartnerDao partnerDao;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "savePartnerRequest")
	public void savePartnerRequest(@RequestPayload Element savePartnerRequest) throws  Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		//System.out.println(savePartnerRequest.toString());
		Partner partner = new Partner();
		partner.setFirstName(getElemenValue(savePartnerRequest, "firstName"));
		partner.setSurname(getElemenValue(savePartnerRequest, "surname"));
		partner.setBirthCertNr(getElemenValue(savePartnerRequest, "birthCertNr"));
		partner.setBirthDate(dateFormat.parse(getElemenValue(savePartnerRequest, "birthDate")));

		System.out.println(partner.toString());
		partnerDao.save(partner);
		}

	private String getElemenValue(Element savePartnerRequest, String elementName) {
		return savePartnerRequest.getChild(elementName, Namespace.getNamespace(NAMESPACE_URI)).getText();
	}
}
