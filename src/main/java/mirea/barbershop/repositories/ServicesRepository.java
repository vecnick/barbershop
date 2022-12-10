package mirea.barbershop.repositories;


import mirea.barbershop.models.Client;
import mirea.barbershop.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Service,Integer> {
    List<Service> findByNameStartingWith(String query);
}
