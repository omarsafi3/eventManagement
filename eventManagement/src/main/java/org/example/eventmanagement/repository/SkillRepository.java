//package org.example.eventmanagement.repository;
//
//import jakarta.xml.bind.JAXBContext;
//import jakarta.xml.bind.JAXBException;
//import jakarta.xml.bind.Marshaller;
//import jakarta.xml.bind.Unmarshaller;
//import jakarta.xml.bind.annotation.XmlRootElement;
//
//import java.io.File;
//import java.util.ArrayList;
//
//public class SkillRepository {
//    private static final String FILE_PATH = "skills.xml";
//    private final File file;
//    private final JAXBContext context;
//
//    public SkillRepository() {
//        try {
//            this.file = new File(FILE_PATH);
//            this.context = JAXBContext.newInstance(MaterialRepository.SkillListWrapper.class);
//
//            // Initialize the file if it doesn't exist
//            if (!file.exists()) {
//                saveAll(new ArrayList<>());
//            }
//        } catch (JAXBException e) {
//            throw new RuntimeException("Error initializing JAXB context", e);
//        }
//    }
//
//    private void saveAll(List<Skill> skills) {
//        try {
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(new SkillListWrapper(skills), file);
//        } catch (JAXBException e) {
//            throw new RuntimeException("Error saving skills to XML", e);
//        }
//    }
//
//    public void save(Skill skill) {
//        List<Skill> skills = findAll();
//        if (skills == null) {
//            skills = new ArrayList<>();
//        }
//        long maxId = skills.stream()
//                .mapToLong(Skill::getId)
//                .max()
//                .orElse(0);
//        skill.setId(maxId + 1);
//
//        skills.add(skill);
//        saveAll(skills);
//    }
//
//    public List<Skill> findAll() {
//        try {
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            SkillListWrapper wrapper = (SkillListWrapper) unmarshaller.unmarshal(file);
//            return wrapper.getSkills();
//        } catch (JAXBException e) {
//            throw new RuntimeException("Error reading skills from XML", e);
//        }
//    }
//
//    public void deleteById(long id) {
//        List<Skill> skills = findAll();
//        skills.removeIf(skill -> skill.getId() == id);
//        saveAll(skills);
//    }
//
//    public Skill findById(long id) {
//        List<Skill> skills = findAll();
//        return skills.stream()
//                .filter(skill -> skill.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public Skill findByName(String name) {
//        List<Skill> skills = findAll();
//        return skills.stream()
//                .filter(skill -> skill.getName().equalsIgnoreCase(name))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<Skill> findByNameContaining(String keyword) {
//        return findAll().stream()
//                .filter(skill -> skill.getName().toLowerCase().contains(keyword.toLowerCase()))
//                .collect(Collectors.toList());
//    }
//
//    public void update(Skill skill) {
//        List<Skill> skills = findAll();
//        for (int i = 0; i < skills.size(); i++) {
//            if (skills.get(i).getId() == skill.getId()) {
//                skills.set(i, skill);
//                break;
//            }
//        }
//        saveAll(skills);
//    }
//
//    @XmlRootElement(name = "skills")
//    public static class SkillListWrapper {
//        private List<Skill> skills;
//
//        public SkillListWrapper() {
//        }
//
//        public SkillListWrapper(List<Skill> skills) {
//            this.skills = skills;
//        }
//
//        public List<Skill> getSkills() {
//            return skills;
//        }
//
//        public void setSkills(List<Skill> skills) {
//            this.skills = skills;
//        }
//    }