package mirea.barbershop.services;

import mirea.barbershop.models.Client;
import mirea.barbershop.models.Supplies;
import mirea.barbershop.models.TypeOfSupplies;
import mirea.barbershop.repositories.TypeOfSuppliesRepository;
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
public class TypeOfSuppliesService {
    private final TypeOfSuppliesRepository typeOfSuppliesRepository;

    public TypeOfSuppliesService(TypeOfSuppliesRepository typeOfSuppliesRepository) {
        this.typeOfSuppliesRepository = typeOfSuppliesRepository;
    }

    public List<TypeOfSupplies> findAll(){
        return typeOfSuppliesRepository.findAll();
    }

    public List<TypeOfSupplies> findAll(boolean sortByName) {
        if (sortByName)
            return typeOfSuppliesRepository.findAll(Sort.by("name"));
        else
            return typeOfSuppliesRepository.findAll();
    }

    public List<TypeOfSupplies> findWithPagination(Integer page, Integer typeOfSuppliesPerPage, boolean sortByName) {
        if (sortByName)
            return typeOfSuppliesRepository.findAll(PageRequest.of(page, typeOfSuppliesPerPage, Sort.by("name"))).getContent();
        else
            return typeOfSuppliesRepository.findAll(PageRequest.of(page, typeOfSuppliesPerPage)).getContent();
    }
    public TypeOfSupplies findOne(int id){
        Optional<TypeOfSupplies> foundType =  typeOfSuppliesRepository.findById(id);
        return foundType.orElse(null);
    }

    @Transactional
    public void save(TypeOfSupplies typeOfSupplies){
        typeOfSuppliesRepository.save(typeOfSupplies);
    }

    @Transactional
    public void update(int id, TypeOfSupplies typeOfSupplies){
        typeOfSupplies.setId(id);
        typeOfSuppliesRepository.save(typeOfSupplies);
    }

    @Transactional
    public void delete(int id){
        typeOfSuppliesRepository.deleteById(id);
    }

    public List<TypeOfSupplies> searchByName(String query) {
        return typeOfSuppliesRepository.findByNameStartingWith(query);
    }


    public List<Supplies> getSuppliesByTypeId(int id) {
        Optional<TypeOfSupplies> type = typeOfSuppliesRepository.findById(id);

        if (type.isPresent()) {
            Hibernate.initialize(type.get().getSupplies());
            return type.get().getSupplies();
        }
        else {
            return Collections.emptyList();
        }
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
