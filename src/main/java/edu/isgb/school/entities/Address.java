package edu.isgb.school.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
@Entity
@Table(name = "t_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ADDRESS")
    private Integer idAddress;
    @Column(name = "cl_street", nullable = false,unique = true, length = 100)
    private String street;
    @Column(name = "cl_city", nullable = false, length = 100)
    private String city;
    @Column(name = "cl_postal_Code", length = 10)
    private String postalCode;
    @OneToOne
    private Student student;
}
