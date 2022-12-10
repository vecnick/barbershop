package mirea.barbershop.repositories;


import mirea.barbershop.models.Client;
import mirea.barbershop.models.Supplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliesRepository extends JpaRepository<Supplies,Integer> {
    List<Supplies> findByNameStartingWith(String query);
}
