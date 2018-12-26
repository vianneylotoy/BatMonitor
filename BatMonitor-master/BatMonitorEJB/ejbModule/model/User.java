package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_user", unique=true, nullable=false)
	private int idUser;

	@Column(nullable=false, length=50)
	private String mail;

	@Column(nullable=false, length=100)
	private String password;

	@Column(nullable=false, length=20)
	private String pseudo;

	//bi-directional many-to-one association to Battery
	@OneToMany(mappedBy="user")
	private List<Battery> batteries;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public List<Battery> getBatteries() {
		return this.batteries;
	}

	public void setBatteries(List<Battery> batteries) {
		this.batteries = batteries;
	}

	public Battery addBattery(Battery battery) {
		getBatteries().add(battery);
		battery.setUser(this);

		return battery;
	}

	public Battery removeBattery(Battery battery) {
		getBatteries().remove(battery);
		battery.setUser(null);

		return battery;
	}

}