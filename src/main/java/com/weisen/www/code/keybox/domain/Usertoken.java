package com.weisen.www.code.keybox.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Usertoken.
 */
@Entity
@Table(name = "usertoken")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usertoken implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "state")
    private String state;

    @Column(name = "end_date")
    private Long endDate;

    @OneToMany(mappedBy = "usertoken")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Keyboxes> userids = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public Usertoken userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getState() {
        return state;
    }

    public Usertoken state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getEndDate() {
        return endDate;
    }

    public Usertoken endDate(Long endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Set<Keyboxes> getUserids() {
        return userids;
    }

    public Usertoken userids(Set<Keyboxes> keyboxes) {
        this.userids = keyboxes;
        return this;
    }

    public Usertoken addUserid(Keyboxes keyboxes) {
        this.userids.add(keyboxes);
        keyboxes.setUsertoken(this);
        return this;
    }

    public Usertoken removeUserid(Keyboxes keyboxes) {
        this.userids.remove(keyboxes);
        keyboxes.setUsertoken(null);
        return this;
    }

    public void setUserids(Set<Keyboxes> keyboxes) {
        this.userids = keyboxes;
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
        Usertoken usertoken = (Usertoken) o;
        if (usertoken.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usertoken.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Usertoken{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", state='" + getState() + "'" +
            ", endDate=" + getEndDate() +
            "}";
    }
}
