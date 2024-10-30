package com.rentstate.user_service.model.aggregates;



import com.rentstate.user_service.api.resource.userresource.RegisterRequest;
import com.rentstate.user_service.api.resource.userresource.UpdateUserResource;
import com.rentstate.user_service.model.valueobjects.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class  User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=50, message = "The name must have at least 2 characters and a maximum of 50")
    private String name;

    @NotNull
    @Size(min=2, max=50, message = "The last name must have at least 2 characters and a maximum of 50")
    private String lastName;

    private String district;

    private Date birthDate;

    private String description = "Here you can write a description about yourself.";

    private String email;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;


    private String gender;

    private String photoUrl = "";

    private Role role;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> contacts = new ArrayList<>();

    private Double latitude;
    private Double longitude;
    private String department;
    private String city;
    private String address;

    @Column(unique = true)
    private String dni;

    private String phone;

    private Boolean premium;
    private Boolean chatNewMessage;
    private Boolean newPropertyNear;

    @Convert(converter = CoverageAreaInterestConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<List<Coordinates>> coverageAreaInterest = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_favorite_properties", joinColumns = @JoinColumn(name = "user_id"))
    private List<Long> favoriteProperties = new ArrayList<>();


    @Convert(converter = UserNeedsConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> userNeeds;



   public User(RegisterRequest registerRequest){
       name = registerRequest.getName();
       lastName = registerRequest.getLastName();
       username = registerRequest.getUsername();
       email = registerRequest.getEmail();
       district = registerRequest.getDistrict();
       birthDate = registerRequest.getBirthDate();
       gender = registerRequest.getGender();
       password = registerRequest.getPassword();
       role = registerRequest.getRole();
       contacts = new ArrayList<>();
       latitude = registerRequest.getLatitude();
       longitude = registerRequest.getLongitude();
       photoUrl = registerRequest.getPhotoUrl();
       department = registerRequest.getDepartment();
       city = registerRequest.getCity();
       address = registerRequest.getAddress();
       phone = registerRequest.getPhone();
       dni = registerRequest.getDni();
   }

    public void updateUser(UpdateUserResource resource){
        this.name = resource.getName();
        this.lastName = resource.getLastName();
        this.gender = resource.getGender();
        this.district = resource.getDistrict();
        this.email = resource.getEmail();
        this.description = resource.getDescription();
        this.photoUrl = resource.getPhotoUrl();
        this.role = resource.getRole();
        this.latitude = resource.getLatitude();
        this.longitude = resource.getLongitude();
        this.department = resource.getDepartment();
        this.city = resource.getCity();
        this.address = resource.getAddress();
        this.dni = resource.getDni();
        this.phone = resource.getPhone();
        this.premium = resource.getPremium();
        this.chatNewMessage = resource.getChatNewMessage();
        this.newPropertyNear = resource.getNewPropertyNear();
        this.coverageAreaInterest = resource.getCoverageAreaInterest();
        this.favoriteProperties = resource.getFavoriteProperties();
        this.userNeeds = (Map<String, Object>) resource.getUserNeeds();

    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}