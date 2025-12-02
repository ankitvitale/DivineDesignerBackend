package com.DivineDesignerDen.DTO;


import com.DivineDesignerDen.Entity.Admin;

public class JwtResponse {

	private Admin admin;
    private String jwtToken;

    public JwtResponse(Admin admin, String jwtToken) {
        this.admin = admin;
        this.jwtToken = jwtToken;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}