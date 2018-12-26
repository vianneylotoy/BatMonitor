package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the battery database table.
 * 
 */
@Entity
@Table(name="battery")
@NamedQuery(name="Battery.findAll", query="SELECT b FROM Battery b")
public class Battery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_battery", unique=true, nullable=false)
	private int idBattery;

	@Column(nullable=false, length=50)
	private String type;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user", nullable=false)
	private User user;

	//bi-directional many-to-one association to BatteryLevel
	@OneToMany(mappedBy="battery")
	private List<BatteryLevel> batteryLevels;

	public Battery() {
	}

	public int getIdBattery() {
		return this.idBattery;
	}

	public void setIdBattery(int idBattery) {
		this.idBattery = idBattery;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BatteryLevel> getBatteryLevels() {
		return this.batteryLevels;
	}

	public void setBatteryLevels(List<BatteryLevel> batteryLevels) {
		this.batteryLevels = batteryLevels;
	}

	public BatteryLevel addBatteryLevel(BatteryLevel batteryLevel) {
		getBatteryLevels().add(batteryLevel);
		batteryLevel.setBattery(this);

		return batteryLevel;
	}

	public BatteryLevel removeBatteryLevel(BatteryLevel batteryLevel) {
		getBatteryLevels().remove(batteryLevel);
		batteryLevel.setBattery(null);

		return batteryLevel;
	}

}