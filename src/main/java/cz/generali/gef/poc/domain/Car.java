package cz.generali.gef.poc.domain;

import javax.persistence.*;

/**
 * Created by Ivan Dolezal(T911552) on 16.2.2015.
 *
 * @Author Ivan Dolezal
 */
@Entity(name = "V_CAR")
@SequenceGenerator(name = "seq_d_car", sequenceName = "seq_d_car", allocationSize = 1)
public class Car extends AuditableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_d_car")
	Long carId;
	String regNo;
	String vin;
	String carMake;
	String carType;

	public Car() {
	}

	public Car(Long carId, String regNo, String vin, String carMake, String carType) {
		this.carId=carId;
		this.regNo = regNo;
		this.vin = vin;
		this.carMake = carMake;
		this.carType = carType;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Car copy() {
		return new Car(this.getCarId(),this.getRegNo(),this.getVin(),this.getCarMake(),this.getCarType());
	}
}
