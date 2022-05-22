package com.example.purchase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements Serializable {

    @Id
    @Column
    private Long id;
    private String firstName;
    private String lastName;
    @NotNull
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    private LocalDate joinDate;
    private String role;
    private boolean isNotLocked;

}
