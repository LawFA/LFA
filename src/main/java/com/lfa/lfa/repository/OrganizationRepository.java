package com.lfa.lfa.repository;

import com.lfa.lfa.domain.Organization;
import com.lfa.lfa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    //Organization findByOrganizationName(String organization_name);

    Organization findByEmail(String email);


}
