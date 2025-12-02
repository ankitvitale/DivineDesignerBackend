package com.DivineDesignerDen.Controller;



import com.DivineDesignerDen.DTO.JwtRequest;
import com.DivineDesignerDen.DTO.JwtResponse;
import com.DivineDesignerDen.Entity.Admin;
import com.DivineDesignerDen.Service.AuthService;
import com.DivineDesignerDen.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    @PostMapping({"/registerAdmin"})
    public Admin registerNewAdmin(@RequestBody Admin admin) {
        return userService.registerAdmin(admin);
    }



    @PostMapping("/auth/login")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return authService.createJwtToken(jwtRequest);
    }

    @GetMapping("/hii")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String hello(){
        return "i am ankit";
    }
}