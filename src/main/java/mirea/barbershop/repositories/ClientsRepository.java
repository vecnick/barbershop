package mirea.barbershop.repositories;


import mirea.barbershop.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<Client,Integer> {
    List<Client> findBySecondNameStartingWith(String query);
}
