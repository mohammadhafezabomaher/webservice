package com.example.pidev.repositories;

import com.example.pidev.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IContactRepository extends JpaRepository <Contact , Long> {

}
