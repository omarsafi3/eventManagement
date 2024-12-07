package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.Material;
import org.example.eventmanagement.service.impl.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/material")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    // Get all materials
    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materials = materialService.allMaterials();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    // Get material by ID
    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable long id) {
        try {
            Material material = materialService.findById(id);
            return new ResponseEntity<>(material, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get material by Name
    @GetMapping("/name/{name}")
    public ResponseEntity<Material> getMaterialByName(@PathVariable String name) {
        try {
            Material material = materialService.findByName(name);
            return new ResponseEntity<>(material, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Search materials by keyword in the name
    @GetMapping("/search")
    public ResponseEntity<List<Material>> searchMaterialsByName(@RequestParam String keyword) {
        List<Material> materials = materialService.findByNameContaining(keyword);
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    // Create a new material
    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        materialService.addMaterial(material);
        return new ResponseEntity<>(material, HttpStatus.CREATED);
    }

    // Update an existing material
    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable long id, @RequestBody Material material) {
        material.setId(id);
        try {
            materialService.updateMaterial(material);
            return new ResponseEntity<>(material, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete material by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialById(@PathVariable long id) {
        try {
            materialService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete material by Name
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteMaterialByName(@PathVariable String name) {
        try {
            materialService.deleteByName(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
