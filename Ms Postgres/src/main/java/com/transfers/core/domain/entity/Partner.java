package com.transfers.core.domain.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "partner_id")
    private String partnerId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "total")
    private Double total;

    @Column(name = "currency")
    private String currency;

    @Column(name = "title")
    private String title;

}