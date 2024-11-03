package com.example.demo.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private int pin;
    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="user_role",joinColumns=@JoinColumn(name="user_id",referencedColumnName = "id"),inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Role> roles=new HashSet<>();

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval= true)
    private List<Comments> commentses;
    
  
}
