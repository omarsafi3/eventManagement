package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Material;
import org.example.eventmanagement.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository repository;

    public MaterialService(MaterialRepository repository) {
        this.repository = repository;
    }

    // Add a new material
    public String addMaterial(Material material) {
        repository.save(material);
        return "Material Added Successfully";
    }

    // Find material by ID
    public Material findById(Long id) {
        return repository.findById(id);
    }

    // Find all materials
    public List<Material> allMaterials() {
        return repository.findAll();
    }

    // Find material by name
    public Material findByName(String name) {
        return repository.findByName(name);
    }

    // Find materials by name containing a keyword
    public List<Material> findByNameContaining(String keyword) {
        return repository.findByNameContaining(keyword);
    }

    // Update a material
    public String updateMaterial(Material material) {
        repository.update(material);
        return "Material Updated Successfully";
    }

    // Delete material by ID
    public String deleteById(Long id) {
        repository.deleteById(id);
        return "Material Deleted Successfully";
    }

    // Delete material by name
    public String deleteByName(String name) {
        repository.deleteByName(name);
        return "Material Deleted Successfully";
    }
}
