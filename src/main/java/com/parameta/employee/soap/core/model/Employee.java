package com.parameta.employee.soap.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Basic
    @Column(name = "nameEmployee", nullable = false, length = 100)
    private String nameEmployee;

    @Basic
    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Basic
    @Column(name = "typeDocument", nullable = false, length = 2)
    private String typeDocument;

    @Basic
    @Column(name = "document", nullable = false, length = 50)
    private String document;

    @Basic
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Basic
    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Basic
    @Column(name = "roleEmployee", nullable = false, length = 50)
    private String roleEmployee;

    @Basic
    @Column(name = "salary", nullable = false, precision = 0)
    private Double salary;
}
