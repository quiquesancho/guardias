package com.edu.quique.application.service;

import com.edu.quique.application.ports.in.services.LdapServicePort;
import com.edu.quique.application.ports.out.LdapRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LdapService implements LdapServicePort {
    private LdapRepositoryPort ldapRepositoryPort;
    @Override
    public List<String> getOUsByEmail(String email) {
        return ldapRepositoryPort.getOUsByMail(email);
    }
}
