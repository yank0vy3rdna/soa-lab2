package ru.yank0vy3rdna.hr.models;



import ru.yank0vy3rdna.hr.utils.xmladapters.LocalDateTimeXmlAdapter;
import ru.yank0vy3rdna.hr.utils.xmladapters.ZonedDateTimeXmlAdapter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkerInput {

    private String name = null;

    private Coordinates coordinates = null;

    private Integer salary = null;
    @XmlJavaTypeAdapter(ZonedDateTimeXmlAdapter.class)
    private ZonedDateTime startDate = null;
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime endDate = null;

    private Status status = null;

    private Organization organization = null;

    /**
     *
     **/
    public WorkerInput name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     **/
    public WorkerInput coordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * minimum: 1
     **/
    public WorkerInput salary(Integer salary) {
        this.salary = salary;
        return this;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     *
     **/
    public WorkerInput startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }


    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     *
     **/
    public WorkerInput endDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }


    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     *
     **/
    public WorkerInput status(Status status) {
        this.status = status;
        return this;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     **/
    public WorkerInput organization(Organization organization) {
        this.organization = organization;
        return this;
    }


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
        WorkerInput workerInput = (WorkerInput) o;
        return Objects.equals(name, workerInput.name) &&
                Objects.equals(coordinates, workerInput.coordinates) &&
                Objects.equals(salary, workerInput.salary) &&
                Objects.equals(startDate, workerInput.startDate) &&
                Objects.equals(endDate, workerInput.endDate) &&
                Objects.equals(status, workerInput.status) &&
                Objects.equals(organization, workerInput.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, salary, startDate, endDate, status, organization);
    }

    @Override
    public String toString() {

        return "class WorkerInput {\n" +
                "    name: " + toIndentedString(name) + "\n" +
                "    coordinates: " + toIndentedString(coordinates) + "\n" +
                "    salary: " + toIndentedString(salary) + "\n" +
                "    startDate: " + toIndentedString(startDate) + "\n" +
                "    endDate: " + toIndentedString(endDate) + "\n" +
                "    status: " + toIndentedString(status) + "\n" +
                "    organization: " + toIndentedString(organization) + "\n" +
                "}";
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

    public Worker convert() {

        Worker worker = new Worker();
        worker.setName(this.getName());
        worker.setCoordinates(this.getCoordinates());
        worker.setSalary(this.getSalary());
        worker.setStartDate(this.getStartDate());
        worker.setEndDate(this.getEndDate());
        worker.setStatus(this.getStatus());
        worker.setOrganization(this.getOrganization());
        return worker;
    }

    public Worker update(Worker worker) {
        if (this.getName() != null)
            worker.setName(this.getName());
        if (this.getCoordinates() != null)
            worker.setCoordinates(this.getCoordinates());
        if (this.getSalary() != null)
            worker.setSalary(this.getSalary());
        if (this.getStartDate() != null)
            worker.setStartDate(this.getStartDate());
        if (this.getEndDate() != null)
            worker.setEndDate(this.getEndDate());
        if (this.getStatus() != null)
            worker.setStatus(this.getStatus());
        if (this.getOrganization() != null)
            worker.setOrganization(this.getOrganization());
        return worker;
    }
}
