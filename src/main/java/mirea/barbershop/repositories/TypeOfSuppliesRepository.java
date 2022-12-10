package mirea.barbershop.repositories;


import mirea.barbershop.models.Tool;
import mirea.barbershop.models.TypeOfSupplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfSuppliesRepository extends JpaRepository<TypeOfSupplies,Integer> {
    List<TypeOfSupplies> findByNameStartingWith(String query);
}
