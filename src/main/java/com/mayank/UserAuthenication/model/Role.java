package com.mayank.UserAuthenication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Data

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name",nullable = false)
    private String roleName;

    // Getters and setters
}

