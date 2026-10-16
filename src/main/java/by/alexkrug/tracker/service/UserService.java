package by.alexkrug.tracker.service;


import by.alexkrug.tracker.dto.UserDto;
import by.alexkrug.tracker.entity.User;
import by.alexkrug.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::userToDto)
                .toList();
    }

    private UserDto userToDto(User user) {
        return new UserDto (
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getRegisteredAt()
        );

    }


}
