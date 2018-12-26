package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the battery_level database table.
 * 
 */
@Embeddable
public class BatteryLevelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_battery", unique=true, nullable=false)
	private int idBattery;

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date date;

	public BatteryLevelPK() {
	}
	public int getIdBattery() {
		return this.idBattery;
	}
	public void setIdBattery(int idBattery) {
		this.idBattery = idBattery;
	}
	public java.util.Date getDate() {
		return this.date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BatteryLevelPK)) {
			return false;
		}
		BatteryLevelPK castOther = (BatteryLevelPK)other;
		return 
			(this.idBattery == castOther.idBattery)
			&& this.date.equals(castOther.date);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBattery;
		hash = hash * prime + this.date.hashCode();
		
		return hash;
	}
}