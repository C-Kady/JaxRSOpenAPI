package fr.istic.taa.jaxrs.service;

import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.dao.AdminDao;
import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.dto.UserResponseDto;

public class AdminService {
    
    private AdminDao adminDao;

    // Injection par constructeur
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

	// Liste des Admins
	public List<UserResponseDto> listeAdmins() {
	    List<Admin> admins = adminDao.findAll();
	    List<UserResponseDto> dtos = new ArrayList<>();

	    for (Admin a : admins) {
	        UserResponseDto dto = new UserResponseDto();
	        dto.setId(a.getUserId());
	        dto.setNom(a.getNom());
	        dto.setPrenom(a.getPrenom());
	        dto.setEmail(a.getEmail());
	        dto.setTelephone(a.getTelephone());
	        dto.setRole(a.getRole());
	        dto.setStatut_user(a.isStatut_user());
	        dto.setAdmin_niveau(a.getAdmin_niveau());
	        dtos.add(dto);
	    }
	    return dtos;
	}
	
}
