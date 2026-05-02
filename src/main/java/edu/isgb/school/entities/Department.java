package edu.isgb.school.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Entity
@Table(name = "t_department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_department")
    private Integer idDepartment;
    @Column(name = "cl_name", nullable = false, length = 50)
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private School school;


}
