package com.weisen.www.code.keybox.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Keyboxes entity.
 */
public class Rewrite_001_KeyboxesDTO implements Serializable {

    private Long id;

    private String name;

    private String login;

    private String passwordtext;

    private String levelpasswordtext;

    private String address;
    
    private Long createDate;

    private String other;

    private Long usertokenId;
    
    public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getUsertokenId() {
		return usertokenId;
	}

	public void setUsertokenId(Long usertokenId) {
		this.usertokenId = usertokenId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordtext() {
        return passwordtext;
    }

    public void setPasswordtext(String passwordtext) {
        this.passwordtext = passwordtext;
    }

    public String getLevelpasswordtext() {
        return levelpasswordtext;
    }

    public void setLevelpasswordtext(String levelpasswordtext) {
        this.levelpasswordtext = levelpasswordtext;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rewrite_001_KeyboxesDTO keyboxesDTO = (Rewrite_001_KeyboxesDTO) o;
        if (keyboxesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), keyboxesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KeyboxesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", login='" + getLogin() + "'" +
            ", passwordtext='" + getPasswordtext() + "'" +
            ", levelpasswordtext='" + getLevelpasswordtext() + "'" +
            ", address='" + getAddress() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
