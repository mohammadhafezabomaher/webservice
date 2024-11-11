package com.example.admin.controllers;

import com.example.admin.dto.AdminDTO;
import com.example.admin.dto.ContactDTO;
import com.example.admin.entities.Admin;
import com.example.admin.services.IAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Admin")
public class AdminController {
    IAdminService adminService;
    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody AdminDTO a){
        Admin createdAdmin = adminService.addAdmin(a);
        return ResponseEntity.ok(createdAdmin);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<AdminDTO>  getAdminById(@PathVariable("id") Long id){
        AdminDTO a=adminService.getAdminById(id);
        return ResponseEntity.ok(a);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<AdminDTO>> getAllAdmin(){
        List<AdminDTO> adminDTOS=adminService.getAllAdmin();
        return ResponseEntity.ok(adminDTOS);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Long id){
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin a){
        Admin admin= adminService.updateAdmin(a);
        return ResponseEntity.ok(admin);
    }
}
