package by.alexkrug.tracker.service;


import by.alexkrug.tracker.dto.UserDto;
import by.alexkrug.tracker.entity.User;
import by.alexkrug.tracker.repository.UserRepository;
import by.alexkrug.tracker.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public UserDto getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser
                .map(this::userToDto)
                .orElseThrow(UserNotFoundException::new);
    }

    public void saveUser(UserDto userDto) {
        userRepository.save(toUser(userDto));
    }

    private UserDto userToDto(User user) {
        return new UserDto (
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getRegisteredAt()
        );
    }

    private User toUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.name());
        user.setSurname(userDto.surname());
        user.setRegisteredAt(LocalDate.now());
        return user;
    }


}
