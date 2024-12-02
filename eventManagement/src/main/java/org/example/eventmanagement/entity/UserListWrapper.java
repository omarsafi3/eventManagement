package org.example.eventmanagement.entity;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class UserListWrapper {

    private List<User> users;

    public UserListWrapper() {}

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
