package com.lfa.lfa.service;

import com.lfa.lfa.domain.Organization;
import com.lfa.lfa.domain.User;
import com.lfa.lfa.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> getAllOrganization() {return organizationRepository.findAll(); }


    public Organization authenticateOrganization(String email, String password) {
        // 이메일을 기반으로 단체를 데이터베이스에서 조회
        Organization organization = organizationRepository.findByEmail(email);

        if (organization != null && password.equals(organization.getPassword())) {
            // 비밀번호 일치
            return organization;
        } else {
            // 사용자가 없거나 비밀번호 불일치
            return null;
        }
    }
}
