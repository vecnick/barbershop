package mirea.barbershop.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminsService {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doAdminStaff(){
        System.out.println("ADMIIIN");
    }
}
