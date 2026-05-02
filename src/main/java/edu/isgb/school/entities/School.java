package edu.isgb.school.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="t_school")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_school")
    private Integer idSchool;

    @Column(name = "cl_name_school", nullable = false, length = 50)
    private String name ;
    @Column(name = "cl_phone", unique = true)
    private Integer phone;
    //@OneToMany (cascade =CascadeType.ALL)
    //private List<Department> departments = new ArrayList<>();
    //@OneToMany(cascade = CascadeType.ALL)
   // private List<Department> departments = new ArrayList<>();
    @OneToMany( mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Department> departments = new ArrayList<>();
    @OneToMany(mappedBy = "school",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Instructor> instructor = new ArrayList<>();
    @OneToMany(mappedBy = "school",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> student = new ArrayList<>();



}
