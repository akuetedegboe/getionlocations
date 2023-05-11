package com.akuetedegboe.getionlocations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Table(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    private Long id;

    @ManyToOne
    private Employe employe;

    @ManyToOne
    private Voiture voiture;

    @ManyToOne
    private Client client;

    @Column(name = "dateLocation")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateLocation;

    @Column(name = "dureLocation")
    private int dureLocation;
}
