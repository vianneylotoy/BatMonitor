package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the battery_level database table.
 * 
 */
@Entity
@Table(name="battery_level")
@NamedQuery(name="BatteryLevel.findAll", query="SELECT b FROM BatteryLevel b")
public class BatteryLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BatteryLevelPK id;

	@Column(nullable=false)
	private int level;

	//bi-directional many-to-one association to Battery
	@ManyToOne
	@JoinColumn(name="id_battery", nullable=false, insertable=false, updatable=false)
	private Battery battery;

	public BatteryLevel() {
	}

	public BatteryLevelPK getId() {
		return this.id;
	}

	public void setId(BatteryLevelPK id) {
		this.id = id;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Battery getBattery() {
		return this.battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

}