package ru.yank0vy3rdna.soa.lab3.common.models;

import javax.validation.constraints.*;
import javax.validation.Valid;


import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Organization implements Serializable {
    private static final long serialVersionUID = -558553967080513790L;

    private @Valid String fullName = null;

    private @Valid OrganizationType type = null;

    private @Valid Address postalAddress = null;

    /**
     *
     **/
    public Organization fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }


    @JsonProperty("fullName")
    @Size(max = 1544)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     **/
    public Organization type(OrganizationType type) {
        this.type = type;
        return this;
    }


    @JsonProperty("type")
    @NotNull

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    /**
     *
     **/
    public Organization postalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
        return this;
    }


    @JsonProperty("postalAddress")

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Organization organization = (Organization) o;
        return Objects.equals(fullName, organization.fullName) &&
                Objects.equals(type, organization.type) &&
                Objects.equals(postalAddress, organization.postalAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, type, postalAddress);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Organization {\n");

        sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    postalAddress: ").append(toIndentedString(postalAddress)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
