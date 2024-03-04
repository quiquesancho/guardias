package com.edu.quique.application.usecases;

import com.edu.quique.application.ports.in.services.LdapServicePort;
import com.edu.quique.application.ports.in.usecases.GetOUsUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetOUsUseCase implements GetOUsUseCasePort {
    private LdapServicePort ldapServicePort;

    @Override
    public List<String> execute(String email) {
        return ldapServicePort.getOUsByEmail(email);
    }
}
