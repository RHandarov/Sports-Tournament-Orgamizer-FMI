package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.CredentialsDTO;
import fmi.sports.tournament.organizer.backend.dtos.LoginDTO;
import fmi.sports.tournament.organizer.backend.dtos.NewUserDTO;
import fmi.sports.tournament.organizer.backend.dtos.UserDTO;
import fmi.sports.tournament.organizer.backend.entities.user.User;
import fmi.sports.tournament.organizer.backend.entities.user.UserRole;
import fmi.sports.tournament.organizer.backend.exceptions.IncorrectPasswordException;
import fmi.sports.tournament.organizer.backend.exceptions.NoUserWithSuchEmailException;
import fmi.sports.tournament.organizer.backend.exceptions.NoUserWithSuchIdException;
import fmi.sports.tournament.organizer.backend.exceptions.UserAlreadyExistsException;
import fmi.sports.tournament.organizer.backend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final JWTService jwtService;
    private final PasswordService passwordService;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository,
                           JWTService jwtService,
                           PasswordService passwordService) {
        this.usersRepository = usersRepository;
        this.jwtService = jwtService;
        this.passwordService = passwordService;
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
                .password(passwordService.hash(newUser.getPassword()))
                .isActive(false)
                .build();

        return UserDTO.fromEntity(usersRepository.save(entity));
    }

    @Override
    public LoginDTO login(CredentialsDTO userCredentials) {
        Optional<User> userOptional =
                usersRepository.findByEmail(userCredentials.getEmail());

        if (userOptional.isEmpty()) {
            throw new NoUserWithSuchEmailException(userCredentials.getEmail());
        }

        User user = userOptional.get();

        if (!passwordService.verify(userCredentials.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }

        String token = jwtService.generateToken(UserDTO.fromEntity(user));
        return LoginDTO.builder()
                .token(token)
                .expires(jwtService.getExpirationTime(token))
                .build();
    }

    @Override
    public void updateUserInfo(Long id, NewUserDTO newUserInfo) {
        User user = usersRepository.findById(id).orElseThrow(
                () -> new NoUserWithSuchIdException(String.format("User with id %d does not exist", id))
        );

        User userWithNewEmail = usersRepository.findByEmail(newUserInfo.getEmail()).orElse(null);
        if (userWithNewEmail != null && !userWithNewEmail.getId().equals(user.getId())) {
            throw new UserAlreadyExistsException(String.format("Email %s is already taken", newUserInfo.getEmail()));
        }

        user.setEmail(newUserInfo.getEmail());
        user.setPassword(newUserInfo.getPassword());
        user.setFirstName(newUserInfo.getFirstName());
        user.setPassword(passwordService.hash(newUserInfo.getPassword()));
        user.setLastName(newUserInfo.getLastName());
        user.setBirthDate(newUserInfo.getBirthDate());
        usersRepository.save(user);
    }
}
