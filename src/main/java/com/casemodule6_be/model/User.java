package com.casemodule6_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Size(min = 3, max = 80)
    private String name;
    @NotBlank
    @Size(min = 3, max = 80)
    private String username;
    @JsonIgnore
    @NotBlank
    @Size(min = 3, max = 80)
    private String password;
    @NaturalId
    @NotBlank
    @Email
    @Size(min = 3, max = 80)
    private String email;
    @Lob
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Room> roomList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> commentList;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bill> billList;

    public User(@NotBlank @Size(min = 3, max = 80)String name,
                @NotBlank @Size(min = 3, max = 80)String username,
                @NotBlank @Size(min = 3, max = 80)String email,
                @NotBlank @Size(min = 3, max = 80)String encode) {
        this.name =name;
        this.username=username;
        this.email=email;
        this.password=encode;
    }
}
