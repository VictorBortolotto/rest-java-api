package com.example.restapi.domain.model;

import com.example.restapi.domain.enums.LandLordType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "land_lord")
public class LandLord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String document;

    @Enumerated(EnumType.STRING)
    @Column(name = "land_lord_type", nullable = false)
    private LandLordType landLordType;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private boolean active;

}
