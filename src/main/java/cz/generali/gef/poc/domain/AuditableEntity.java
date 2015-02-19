package cz.generali.gef.poc.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by Ivan Dolezal(T911552) on 30.1.2015.
 *
 * @Author Ivan Dolezal
 */
@MappedSuperclass
public abstract class AuditableEntity {

	@CreatedDate
	@Column(name="AUD_TIME_CREATED")
	private Date createdDate;

	// neni nakonfigurovan interceptror
	@LastModifiedDate
	@Column(name = "AUD_TIME_MODIFIED")
	private Date updatedDate;


	@CreatedBy
	@Column(name = "AUD_USER_CREATED")
	private String createdBy;


	@LastModifiedBy
	@Column(name = "AUD_USER_MODIFIED")
	private String lastModifiedBy;




}
