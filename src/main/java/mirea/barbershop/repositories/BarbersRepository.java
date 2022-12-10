package mirea.barbershop.repositories;


import mirea.barbershop.models.Barber;
import mirea.barbershop.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarbersRepository extends JpaRepository<Barber,Integer> {
    List<Barber> findBySecondNameStartingWith(String query);
}
