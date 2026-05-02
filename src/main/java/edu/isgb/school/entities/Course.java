package edu.isgb.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_course")
    private Integer IdCourse;
    @Column(name = "name_course",nullable = false,length = 50)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Instructor> instructor = new ArrayList<>();


}
