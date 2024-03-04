package com.edu.quique.repository.adapter;

import com.edu.quique.application.ports.out.LdapRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

@Service
@AllArgsConstructor
public class LdapRepositoryAdapter implements LdapRepositoryPort {
    private LdapTemplate ldapTemplate;

    public List<String> getOUsByMail(String email) {
        return ldapTemplate.search("", "(mail=" + email + ")", new AttributesMapper<String>() {
            @Override
            public String mapFromAttributes(Attributes attributes) throws NamingException {
                return attributes.get("ou").get().toString();
            }
        });
    }
}
