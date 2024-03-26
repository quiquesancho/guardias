package com.edu.quique.application.ports.out;

import java.util.List;

public interface LdapRepositoryPort {
    List<String> getOUsByMail(String email);
}
