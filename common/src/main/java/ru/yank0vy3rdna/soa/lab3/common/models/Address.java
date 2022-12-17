package ru.yank0vy3rdna.soa.lab3.common.models;

import javax.validation.constraints.*;
import javax.validation.Valid;



import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Address {

    private @Valid String street = null;

    private @Valid String zipCode = null;

    private @Valid Location town = null;

    /**
     *
     **/
    public Address street(String street) {
        this.street = street;
        return this;
    }


    @JsonProperty("street")
    @NotNull

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *
     **/
    public Address zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }



    @JsonProperty("zipCode")

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     *
     **/
    public Address town(Location town) {
        this.town = town;
        return this;
    }



    @JsonProperty("town")
    @NotNull

    public Location getTown() {
        return town;
    }

    public void setTown(Location town) {
        this.town = town;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(town, address.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, town);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Address {\n");

        sb.append("    street: ").append(toIndentedString(street)).append("\n");
        sb.append("    zipCode: ").append(toIndentedString(zipCode)).append("\n");
        sb.append("    town: ").append(toIndentedString(town)).append("\n");
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
