package mirea.barbershop.repositories;


import mirea.barbershop.models.Supplies;
import mirea.barbershop.models.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolsRepository extends JpaRepository<Tool,Integer> {
    List<Tool> findByNameStartingWith(String query);
}
