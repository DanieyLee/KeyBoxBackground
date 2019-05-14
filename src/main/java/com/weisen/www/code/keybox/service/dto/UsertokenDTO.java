package com.weisen.www.code.keybox.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Usertoken entity.
 */
public class UsertokenDTO implements Serializable {

    private Long id;

    @NotNull
    private String userid;

    private String state;

    private Long endDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsertokenDTO usertokenDTO = (UsertokenDTO) o;
        if (usertokenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usertokenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsertokenDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", state='" + getState() + "'" +
            ", endDate=" + getEndDate() +
            "}";
    }
}
