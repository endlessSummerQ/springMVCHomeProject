package hw.project.domainService;

import hw.project.dto.User;
import hw.project.service.ContainerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final ContainerService containerService;

    public UserService(ContainerService containerService) {
        this.containerService = containerService;
    }

    public List<User> getAllUser() {
        return containerService.getAllUsers();
    }

    public User getUserForLogin(User user) {
        return containerService.getUserForLogin(user);
    }
    public User getUser(User user) {
        return containerService.getUser(user);
    }
    public User getUserForId(int id) {
        return containerService.getUserForId(id);
    }
    public User changePassword(User user) {
        return containerService.getUserForPassword(user);
    }
    public void create(User user) {
        containerService.create(user);
    }

    public void update(User user) {
        containerService.update(user);
    }


}
