package converter.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="VRZ_DL_DISC_PHONE")
public class VerizonDLDiscPhoneData {
	
	@Id
	@Column(name="id", updatable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="atn")
	private String atn;
	
	@Column(name="status")
	private String status=null;
	
	@Column(name="updated_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAtn() {
		return atn;
	}

	public void setAtn(String atn) {
		this.atn = atn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atn == null) ? 0 : atn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerizonDLDiscPhoneData other = (VerizonDLDiscPhoneData) obj;
		if (atn == null) {
			if (other.atn != null)
				return false;
		} else if (!atn.equals(other.atn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VerizonDLDiscPhoneData [userName='" + userName + "', atn=" + atn
				+ "]";
	}
	

}
