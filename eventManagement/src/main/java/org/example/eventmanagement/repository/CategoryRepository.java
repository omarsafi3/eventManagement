package org.example.eventmanagement.repository;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.User;
import org.example.eventmanagement.entity.generated.Category;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {

    private static final String FILE_PATH = "categories.xml";
    private final File file;
    private final JAXBContext context;

    public CategoryRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(CategoryListWrapper.class);

            // Initialize the file if it doesn't exist
            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    public List<Category> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CategoryListWrapper wrapper = (CategoryListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getCategories() : new ArrayList<>();
        } catch (JAXBException e) {
            System.out.println("Error reading categories from XML. Returning empty list.");
            return new ArrayList<>();
        }
    }

    public Category findById(Long id){
        List<Category> categories = findAll();
        return categories.stream()
                .filter(category -> category.getId()== id)
                .findFirst()
                .orElse(null);

    }


    public void save(Category category) {
        List<Category> categories = findAll();
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
        saveAll(categories);
    }

    private void saveAll(List<Category> categories) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new CategoryListWrapper(categories), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving categories to XML", e);
        }
    }

    // Wrapper class for marshalling and unmarshalling
    @XmlRootElement(name = "categories")
    private static class CategoryListWrapper {
        private List<Category> categories;

        public CategoryListWrapper() {
        }

        public CategoryListWrapper(List<Category> categories) {
            this.categories = categories;
        }

        @XmlElement(name = "category") // Match individual item names in XML
        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }
    }
}
