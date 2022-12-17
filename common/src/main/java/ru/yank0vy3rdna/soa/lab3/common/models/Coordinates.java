package ru.yank0vy3rdna.soa.lab3.common.models;


import java.util.Objects;


public class Coordinates {

    private Long x = null;

    private Long y = null;

    /**
     *
     **/
    public Coordinates x(Long x) {
        this.x = x;
        return this;
    }


    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    /**
     *
     **/
    public Coordinates y(Long y) {
        this.y = y;
        return this;
    }


    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates coordinates = (Coordinates) o;
        return Objects.equals(x, coordinates.x) &&
                Objects.equals(y, coordinates.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Coordinates {\n");

        sb.append("    x: ").append(toIndentedString(x)).append("\n");
        sb.append("    y: ").append(toIndentedString(y)).append("\n");
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
