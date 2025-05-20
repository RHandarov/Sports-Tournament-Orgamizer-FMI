package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.UserDTO;
import fmi.sports.tournament.organizer.backend.entities.user.User;
import fmi.sports.tournament.organizer.backend.response.ResponseResult;
import fmi.sports.tournament.organizer.backend.response.UserResponse;
import fmi.sports.tournament.organizer.backend.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final JWTService jwtService;

    @Autowired
    public ProfileController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getProfile(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.startsWith("Bearer ") ?
                authorizationHeader.substring(7) : authorizationHeader;

        UserDTO user = jwtService.getUser(token);

        return ResponseEntity.ok(
                UserResponse
                        .fromDTO(user)
                        .responseResult(ResponseResult.VALID_TOKEN)
                        .build()
        );
    }
}
