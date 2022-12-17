package ru.yank0vy3rdna.soa.lab3.common.models;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "error")
public class BadRequestError {
    @XmlElement
    private @Valid String field = null;

    @XmlElement
    private @Valid String error = null;

    /**
     *
     **/
    public BadRequestError field(String field) {
        this.field = field;
        return this;
    }


    @JsonProperty("field")
    @NotNull

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    /**
     *
     **/
    public BadRequestError error(String error) {
        this.error = error;
        return this;
    }


    @JsonProperty("error")
    @NotNull

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BadRequestError badRequestError = (BadRequestError) o;
        return Objects.equals(field, badRequestError.field) &&
                Objects.equals(error, badRequestError.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, error);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BadRequestError {\n");

        sb.append("    field: ").append(toIndentedString(field)).append("\n");
        sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
