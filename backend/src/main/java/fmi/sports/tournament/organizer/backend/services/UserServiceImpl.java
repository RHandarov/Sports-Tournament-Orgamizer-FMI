package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.NewUserDTO;
import fmi.sports.tournament.organizer.backend.dtos.UserDTO;
import fmi.sports.tournament.organizer.backend.entities.user.User;
import fmi.sports.tournament.organizer.backend.entities.user.UserRole;
import fmi.sports.tournament.organizer.backend.exceptions.UserAlreadyExistsException;
import fmi.sports.tournament.organizer.backend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDTO registerUser(NewUserDTO newUser) {
        if (usersRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    String.format("User with email %s already exists!", newUser.getEmail())
            );
        }

        User entity = User.builder()
                .email(newUser.getEmail())
                .birthDate(newUser.getBirthDate())
                .creationDate(LocalDate.now())
                .firstName(newUser.getFirstName())
                .role(UserRole.USER)
                .lastName(newUser.getLastName())
                .password(newUser.getPassword())
                .isActive(false)
                .build();

        return UserDTO.fromEntity(usersRepository.save(entity));
    }
}
