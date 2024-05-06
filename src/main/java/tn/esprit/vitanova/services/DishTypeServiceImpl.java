package tn.esprit.vitanova.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.DishType;
import tn.esprit.vitanova.repository.DishTypeRepository;

import java.util.List;
@Service
public class DishTypeServiceImpl implements IDishType {
    @Autowired
private DishTypeRepository dishTypeRepository;
    @Override
    public List<DishType> getAllDishTypes()  {
        return dishTypeRepository.findAll();
    }
}
