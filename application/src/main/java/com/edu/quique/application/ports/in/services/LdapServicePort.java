package com.edu.quique.application.ports.in.services;

import java.util.List;

public interface LdapServicePort {
    List<String> getOUsByEmail(String email);
}
