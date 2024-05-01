package com.rentstate.post_service.domain.model.entity;


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
@Table(name="posts")
public class Post {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String title;

     private String content;

     private float price;

     @Column(name = "property_id")
     private Long propertyId;

     @Column(name = "user_id")
     private Long userId;
}
