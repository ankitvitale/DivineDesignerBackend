package com.DivineDesignerDen.Service;


import com.DivineDesignerDen.DTO.JwtRequest;
import com.DivineDesignerDen.DTO.JwtResponse;
import com.DivineDesignerDen.Entity.Admin;
import com.DivineDesignerDen.Repository.AdminRepository;
import com.DivineDesignerDen.Security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtUtil;

    @Autowired
    private AdminRepository adminDao;



    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {

        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();

        authenticate(email, password);

        UserDetails userDetails = loadUserByUsername(email);
        String token = jwtUtil.generateToken(userDetails);

        Admin admin = adminDao.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found with email: " + email));

        return new JwtResponse(admin, token);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        Optional<Admin> adminOpt = adminDao.findByEmail(email);

        if (adminOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Admin admin = adminOpt.get();

        // Authorities
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (admin.getRole() != null) {
            admin.getRole().forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
            );
        }

        return new org.springframework.security.core.userdetails.User(
                admin.getEmail(),
                admin.getPassword(),
                authorities
        );
    }


    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}