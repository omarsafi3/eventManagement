package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Material;
//import org.example.eventmanagement.repository.MaterialRepository;
//import org.example.eventmanagement.repository.MaterialRepository;
import org.example.eventmanagement.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository repository;


    public String addMaterial(Material material) {
        repository.save(material);
        return "Material Added Successfully";
    }

    public Material findById(Long id) {
        return repository.findById(id);
    }

    public List<Material> allMaterials() {
        return repository.findAll();
    }


    public Material findByName(String name) {
        return repository.findByName(name);
    }

    public List<Material> findByNameContaining(String keyword) {
        return repository.findByNameContaining(keyword);
    }


    public String updateMaterial(Material material) {
        repository.update(material);
        return "Material Updated Successfully";
    }


    public String deleteById(Long id) {
        repository.deleteById(id);
        return "Material Deleted Successfully";
    }


    public String deleteByName(String name) {
        repository.deleteByName(name);
        return "Material Deleted Successfully";
    }
}
