package com.example.restapi.domain.model;

import com.example.restapi.domain.enums.PropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "land_lord_id", nullable = false)
    private LandLord landLord;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type")
    private PropertyType propertyType;

    @Column
    private String address;

    @Column
    private String number;

    @Column
    private String neighborhood;

    @Column
    private String city;

    @Column
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column
    private int capacity;

    @Column(name = "daily_rate")
    private double dailyRate;

    @Column
    private boolean avaliable;

    @Column
    private String notes;

    @Column(nullable = false)
    private boolean active;
}
