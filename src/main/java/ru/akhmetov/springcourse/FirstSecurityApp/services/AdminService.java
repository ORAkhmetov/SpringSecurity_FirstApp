package ru.akhmetov.springcourse.FirstSecurityApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Oleg Akhmetov on 01.12.2022
 */
@Service
public class AdminService {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doAdminStuff() {
        System.out.println("Only admin here");
    }
}
