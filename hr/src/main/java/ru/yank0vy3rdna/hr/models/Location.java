package ru.yank0vy3rdna.hr.models;

import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Location {

    private @Valid Integer x = null;

    private @Valid Double y = null;

    private @Valid String name = null;

    /**
     *
     **/
    public Location x(Integer x) {
        this.x = x;
        return this;
    }


    @ApiModelProperty(required = true, value = "")
    @JsonProperty("x")
    @NotNull

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    /**
     *
     **/
    public Location y(Double y) {
        this.y = y;
        return this;
    }


    @ApiModelProperty(required = true, value = "")
    @JsonProperty("y")
    @NotNull

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    /**
     *
     **/
    public Location name(String name) {
        this.name = name;
        return this;
    }


    @ApiModelProperty(value = "")
    @JsonProperty("name")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(x, location.x) &&
                Objects.equals(y, location.y) &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Location {\n");

        sb.append("    x: ").append(toIndentedString(x)).append("\n");
        sb.append("    y: ").append(toIndentedString(y)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
