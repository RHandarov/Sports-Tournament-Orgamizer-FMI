package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.CredentialsDTO;
import fmi.sports.tournament.organizer.backend.dtos.LoginDTO;
import fmi.sports.tournament.organizer.backend.dtos.NewUserDTO;
import fmi.sports.tournament.organizer.backend.dtos.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO registerUser(NewUserDTO newUser);
    LoginDTO login(CredentialsDTO userCredentials);
}
