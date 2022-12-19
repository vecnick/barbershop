package mirea.barbershop.services;

import mirea.barbershop.models.Supplies;
import mirea.barbershop.models.TypeOfSupplies;
import mirea.barbershop.repositories.SuppliesRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SuppliesService {
    private final SuppliesRepository suppliesRepository;

    public SuppliesService(SuppliesRepository suppliesRepository) {
        this.suppliesRepository = suppliesRepository;
    }

    public List<Supplies> findAll(){
        return suppliesRepository.findAll();
    }

    public List<Supplies> findAll(boolean sortByName) {
        if (sortByName)
            return suppliesRepository.findAll(Sort.by("name"));
        else
            return suppliesRepository.findAll();
    }

    public List<Supplies> findWithPagination(Integer page, Integer suppliesPerPage, boolean sortByName) {
        if (sortByName)
            return suppliesRepository.findAll(PageRequest.of(page, suppliesPerPage, Sort.by("name"))).getContent();
        else
            return suppliesRepository.findAll(PageRequest.of(page, suppliesPerPage)).getContent();
    }

    public Supplies findOne(int id){
        Optional<Supplies> foundSupply =  suppliesRepository.findById(id);
        return foundSupply.orElse(null);
    }

    @Transactional
    public void save(Supplies supplies){
        suppliesRepository.save(supplies);
    }

    @Transactional
    public void update(int id, Supplies updatedSupplies){
        updatedSupplies.setId(id);
        suppliesRepository.save(updatedSupplies);
    }

    @Transactional
    public void delete(int id){
        suppliesRepository.deleteById(id);
    }

    public List<Supplies> searchByName(String query) {
        return suppliesRepository.findByNameStartingWith(query);
    }
    public List<mirea.barbershop.models.Service> getServicesBySupplyId(int id) {
        Optional<Supplies> supplies = suppliesRepository.findById(id);

        if (supplies.isPresent()) {
            Hibernate.initialize(supplies.get().getServices());
            return supplies.get().getServices();
        }
        else {
            return Collections.emptyList();
        }
    }

    public TypeOfSupplies getSupplyOwner(int id) {
        return suppliesRepository.findById(id).map(Supplies::getType).orElse(null);
    }


    @Transactional
    public void release(int id){
        suppliesRepository.findById(id).ifPresent(
                supplies -> {
                    supplies.setType(null);
                });
    }
    @Transactional
    public void assign(int id, TypeOfSupplies selectedTextile) {
        suppliesRepository.findById(id).ifPresent(
                supplies -> {
                    supplies.setType(selectedTextile);
                }
        );
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}