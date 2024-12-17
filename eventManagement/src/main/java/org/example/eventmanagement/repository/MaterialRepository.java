package org.example.eventmanagement.repository;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.Material;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MaterialRepository {

    private static final String FILE_PATH = "materials.xml";
    private final File file;
    private final JAXBContext context;

    public MaterialRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(MaterialListWrapper.class);


            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    private void saveAll(List<Material> materials) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new MaterialListWrapper(materials), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving materials to XML", e);
        }
    }

    public void save(Material material) {
        List<Material> materials = findAll();
        if (materials == null) {
            materials = new ArrayList<>();
        }
        long maxId = materials.stream()
                .mapToLong(Material::getId)
                .max()
                .orElse(0);
        material.setId(maxId + 1);

        materials.add(material);
        saveAll(materials);
    }

    public List<Material> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MaterialListWrapper wrapper = (MaterialListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getMaterials() : new ArrayList<>();
        } catch (JAXBException e) {
            System.out.println("Error reading materials from XML. Returning empty list.");
            return new ArrayList<>();
        }
    }

    public Material findById(Long id) {
        List<Material> materials = findAll();
        return materials.stream()
                .filter(material -> material.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Material findByName(String name) {
        List<Material> materials = findAll();
        return materials.stream()
                .filter(material -> material.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Material> findByNameContaining(String keyword) {
        return findAll().stream()
                .filter(material -> material.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void update(Material material) {
        List<Material> materials = findAll();
        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i).getId() == material.getId()) {
                materials.set(i, material);
                break;
            }
        }
        saveAll(materials);
    }

    public void deleteById(Long id) {
        List<Material> materials = findAll();
        materials.removeIf(material -> material.getId() == id);
        saveAll(materials);
    }

    public void deleteByName(String name) {
        List<Material> materials = findAll();
        materials.removeIf(material -> material.getName().equals(name));
        saveAll(materials);
    }


    @XmlRootElement(name = "materials")
    private static class MaterialListWrapper {
        private List<Material> materials;

        public MaterialListWrapper() {
        }

        public MaterialListWrapper(List<Material> materials) {
            this.materials = materials;
        }

        @XmlElement(name = "material")
        public List<Material> getMaterials() {
            return materials;
        }

        public void setMaterials(List<Material> materials) {
            this.materials = materials;
        }
    }
}
