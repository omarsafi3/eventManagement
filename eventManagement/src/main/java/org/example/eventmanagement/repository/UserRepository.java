package org.example.eventmanagement.repository;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String FILE_PATH = "users.xml";
    private final File file;
    private final JAXBContext context;

    public UserRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(UserListWrapper.class);

            // Initialize the file if it doesn't exist
            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }


    public User findByEmail(String email) {
        List<User> users = findAll();
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    public User findById(Long id){
        List<User> users = findAll();
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);

    }


    public List<User> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserListWrapper wrapper = (UserListWrapper) unmarshaller.unmarshal(file);
            return wrapper.getUsers();
        } catch (JAXBException e) {
            throw new RuntimeException("Error reading users from XML", e);
        }
    }




    public void save(User user) {
        List<User> users = findAll();
        users.add(user);
        saveAll(users);
    }


    public void deleteByEmail(String email) {
        List<User> users = findAll();
        users.removeIf(user -> user.getEmail().equals(email));
        saveAll(users);
    }


    public void update(User user) {
        List<User> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(user.getEmail())) {
                users.set(i, user);
                saveAll(users);
                return;
            }
        }
        throw new RuntimeException("User with email " + user.getEmail() + " not found.");
    }

    private void saveAll(List<User> users) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new UserListWrapper(users), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving users to XML", e);
        }
    }

    // Wrapper class for marshalling and unmarshalling
    @XmlRootElement
    private static class UserListWrapper {
        private List<User> users;

        public UserListWrapper() {
        }

        public UserListWrapper(List<User> users) {
            this.users = users;
        }

        @XmlElement(name = "user")
        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }
}
