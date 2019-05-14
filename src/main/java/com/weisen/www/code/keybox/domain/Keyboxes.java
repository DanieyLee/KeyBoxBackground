package com.weisen.www.code.keybox.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Keyboxes.
 */
@Entity
@Table(name = "keyboxes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Keyboxes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "passwordtext")
    private String passwordtext;

    @Column(name = "levelpasswordtext")
    private String levelpasswordtext;

    @Column(name = "address")
    private String address;

    @Column(name = "create_date")
    private Long createDate;

    @Column(name = "other")
    private String other;

    @ManyToOne
    @JsonIgnoreProperties("userids")
    private Usertoken usertoken;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Keyboxes name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public Keyboxes login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordtext() {
        return passwordtext;
    }

    public Keyboxes passwordtext(String passwordtext) {
        this.passwordtext = passwordtext;
        return this;
    }

    public void setPasswordtext(String passwordtext) {
        this.passwordtext = passwordtext;
    }

    public String getLevelpasswordtext() {
        return levelpasswordtext;
    }

    public Keyboxes levelpasswordtext(String levelpasswordtext) {
        this.levelpasswordtext = levelpasswordtext;
        return this;
    }

    public void setLevelpasswordtext(String levelpasswordtext) {
        this.levelpasswordtext = levelpasswordtext;
    }

    public String getAddress() {
        return address;
    }

    public Keyboxes address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public Keyboxes createDate(Long createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getOther() {
        return other;
    }

    public Keyboxes other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Usertoken getUsertoken() {
        return usertoken;
    }

    public Keyboxes usertoken(Usertoken usertoken) {
        this.usertoken = usertoken;
        return this;
    }

    public void setUsertoken(Usertoken usertoken) {
        this.usertoken = usertoken;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Keyboxes keyboxes = (Keyboxes) o;
        if (keyboxes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), keyboxes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Keyboxes{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", login='" + getLogin() + "'" +
            ", passwordtext='" + getPasswordtext() + "'" +
            ", levelpasswordtext='" + getLevelpasswordtext() + "'" +
            ", address='" + getAddress() + "'" +
            ", createDate=" + getCreateDate() +
            ", other='" + getOther() + "'" +
            "}";
    }
}
