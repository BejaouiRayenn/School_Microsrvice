package edu.isgb.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Student",nullable = false)
    private Integer idStudent;
    @Column(name = "cl_name", nullable = false, length = 50)
    private String name;
    @Column(name = "cl_birthdate", nullable = false)
    private Date birthDate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private School school;




}
