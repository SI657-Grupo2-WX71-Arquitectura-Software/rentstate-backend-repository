package com.rentstate.property_service.domain.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String district;

    private String location;

    private String latitude;

    private String longitude;

    private String description;

    private String characteristics;

    private Boolean available = true;

    private Boolean rented = false;

    private String cardimage;

    private Float price;

    @Column(name = "user_id")
    private Long userId;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> interestedUserIds;

    @Column(name = "tenant_id")
    private Long tenantId;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> exTenantIds;


}