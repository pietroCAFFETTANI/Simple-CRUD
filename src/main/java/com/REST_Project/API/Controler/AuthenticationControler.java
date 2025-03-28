package com.REST_Project.API.Controler;


import com.REST_Project.API.Models.AuthenticationDTO;
import com.REST_Project.API.Models.RegisterDTO;
import com.REST_Project.API.Models.User;
import com.REST_Project.API.Repo.UserRepo;
import com.REST_Project.API.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("auth")

public class AuthenticationControler {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO user){
        try{
            var userNamePassword = new UsernamePasswordAuthenticationToken(user.login(), user.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);
            var token = tokenService.createToken((User) auth.getPrincipal());
            if (tokenService == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Token service is not available");
            }
            return ResponseEntity.ok(token);
        }catch(BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO register){
        if(this.userRepo.findByEmail(register.login()) != null){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Este E-mail já está registrado.");
        }

        String encyptedpassword = new BCryptPasswordEncoder().encode(register.password());
        User user = new User(register.login(), encyptedpassword, register.role());

        this.userRepo.save(user);
        return ResponseEntity.ok().build();
    }
}
