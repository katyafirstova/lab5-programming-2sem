package model;

import com.sun.xml.internal.fastinfoset.algorithm.UUIDEncodingAlgorithm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;


public class Worker  {
    private final long max = 100000;
    private long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private Integer salary;
    private java.time.LocalDate startDate;
    private java.util.Date endDate;
    private Status status;
    private Person person;

    public Worker(String name, Coordinates coordinates, Integer salary,
                  LocalDate startDate, Date endDate, Status status, Person person) {
        this.id = (long) (Math.random()*max);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.person = person;
    }

    public Worker() {
        this.id = (long) (Math.random()*max);
        this.creationDate = LocalDateTime.now();

    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name=" + name +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", person=" + person +
                '}';
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) {
            throw new NullPointerException("Can't be null");
        }
        if(name == " ") {
            throw new IllegalArgumentException("Can't be empty");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if(coordinates == null) {
            throw new NullPointerException("Can't be null");
        }
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        if(salary < 0 ) {
            throw new IllegalArgumentException("Can't be less than 0");
        }
        if(salary == null) {
            throw new NullPointerException("Can't be null");
        }
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate == null){
            throw new NullPointerException("Can't be null");
        }
        this.startDate = startDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }



}
