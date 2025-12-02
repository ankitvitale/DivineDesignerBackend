package com.DivineDesignerDen.Service;


import com.DivineDesignerDen.Entity.Admin;
import com.DivineDesignerDen.Entity.Role;
import com.DivineDesignerDen.Repository.AdminRepository;
import com.DivineDesignerDen.Repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminDao;



    public void initRoleAndUser() {
        if (!roleDao.existsById("Admin")) {
            Role adminRole = new Role();
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");
            roleDao.save(adminRole);
        }

    }

    public Admin registerAdmin(Admin admin) {
        Role role = roleDao.findById("Admin").get();
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(role);
        admin.setRole(adminRoles);
        admin.setPassword(getEncodedPassword(admin.getPassword()));

        adminDao.save(admin);
        return admin;
    }


    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }


}