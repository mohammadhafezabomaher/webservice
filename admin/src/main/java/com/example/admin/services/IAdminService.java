package com.example.admin.services;

import com.example.admin.dto.AdminDTO;
import com.example.admin.dto.ContactDTO;
import com.example.admin.entities.Admin;

import java.util.List;

public interface IAdminService {
    public Admin addAdmin(AdminDTO a);
    public AdminDTO getAdminById(Long id);
    public List<AdminDTO> getAllAdmin();
    public Admin updateAdmin(Admin a);
    public void deleteAdmin(Long id);

}
