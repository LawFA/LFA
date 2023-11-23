package com.lfa.lfa.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organization_name;
    private String password;
    private String category;
    private String email;

    public Object getOrganizationName() {
        return this.organization_name;
    }

    public String getPassword() {
        return this.password;
    }
}
