package ru.yank0vy3rdna.soa.lab3.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ru.yank0vy3rdna.soa.lab3.common.utils.xmladapters.LocalDateTimeXmlAdapter;
import ru.yank0vy3rdna.soa.lab3.common.utils.xmladapters.LocalDateXmlAdapter;
import ru.yank0vy3rdna.soa.lab3.common.utils.xmladapters.ZonedDateTimeXmlAdapter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity(name = "worker")
@Table(name = "workers")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker implements Serializable {
    private static final long serialVersionUID = -558553967080513790L;
    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Valid Long id = null;

    @XmlElement
    @Column(name = "name")
    private @Valid String name = null;

    @XmlElement
    @Column(name = "coordinates")
    private @Valid Coordinates coordinates = null;

    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    @XmlElement
    @Column(name = "created_at")
    private @Valid LocalDate creationDate = LocalDate.now();

    @Column(name = "salary")
    private @Valid Integer salary = null;

    //    @Basic
    @XmlJavaTypeAdapter(ZonedDateTimeXmlAdapter.class)
    @Column(name = "started_at")
    private @Valid ZonedDateTime startDate = null;

    //    @Basic
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    @Column(name = "ended_at")
    private @Valid LocalDateTime endDate = null;

    @XmlElement
    @Column(name = "status")
    private @Valid Status status = null;
    @XmlElement
    @Column(name = "organization")
    private @Valid Organization organization = null;

    /**
     * minimum: 1
     **/
    public Worker id(Long id) {
        this.id = id;
        return this;
    }


    //    @JsonProperty("id")
    @NotNull
    @Min(1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     **/
    public Worker name(String name) {
        this.name = name;
        return this;
    }


    @JsonProperty("name")
    @NotNull
    @Size(min = 1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     **/
    public Worker coordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }


    @JsonProperty("coordinates")
    @NotNull
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     *
     **/
    public Worker creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }


    @JsonProperty("creationDate")
    @NotNull
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * minimum: 1
     **/
    public Worker salary(Integer salary) {
        this.salary = salary;
        return this;
    }


    @JsonProperty("salary")
    @Min(1)
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     *
     **/
    public Worker startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }


    @JsonProperty("startDate")
    @NotNull
    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     *
     **/
    public Worker endDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }


    @JsonProperty("endDate")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     *
     **/
    public Worker status(Status status) {
        this.status = status;
        return this;
    }


    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     **/
    public Worker organization(Organization organization) {
        this.organization = organization;
        return this;
    }


    @JsonProperty("organization")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id) &&
                Objects.equals(name, worker.name) &&
                Objects.equals(coordinates, worker.coordinates) &&
                Objects.equals(creationDate, worker.creationDate) &&
                Objects.equals(salary, worker.salary) &&
                Objects.equals(startDate, worker.startDate) &&
                Objects.equals(endDate, worker.endDate) &&
                Objects.equals(status, worker.status) &&
                Objects.equals(organization, worker.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, salary, startDate, endDate, status, organization);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Worker {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n");
        sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
        sb.append("    salary: ").append(toIndentedString(salary)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    organization: ").append(toIndentedString(organization)).append("\n");
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
