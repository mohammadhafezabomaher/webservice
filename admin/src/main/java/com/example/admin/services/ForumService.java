package com.example.admin.services;

import com.example.admin.entities.Forum;
import com.example.admin.repositories.IForumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ForumService implements IForumService{
    IForumRepository forumRepository;
   // EmailService emailService;
    //IContactRepository contactRepository;
    //ContactService contactService;

    @Override
    public Forum addForum(Forum f) {
     /*   List<Contact> l =contactRepository.findAll();
        for (Contact c:l) {
            if (c.getRole() != null) {
                if (c.getRole().ordinal() == 1 || c.getRole().ordinal() == 2) {
                    String to = c.getEmail();
                    String subject = f.getTitre();
                    String text = c.getNom() + " " + c.getPrenom() + ", Nous vous invitons pour participer au forum : " + f.getTitre()
                            + "\n L'organisateur de ce forum est : " + f.getOrganisateur()
                            + "\n" + f.getDescription()
                            + "\n" + "La date de ce forum est : " + f.getDateForum().toString();
                    emailService.sendSimpleMessage(to, subject, text);
                }
            }
        }
        f.setNbrEtudiantInvite(contactService.nbrEtudiant());
        f.setNbrAlumniInvite(contactService.nbrAlumni());
        f.setNbrEnseignantInvite(contactService.nbrEnseignant());
        f.setNbrExposantInvite(contactService.nbrExposant());*/
        return forumRepository.save(f);
    }
    @Override
    public Forum getForumById(Long id) {
        return forumRepository.findById(id).get();
    }
    @Override
    public List<Forum> getAllForum() {
        return forumRepository.findAll();
    }
    @Override
    public Forum updateForum(Forum f) {
        return forumRepository.save(f);
    }
    @Override
    public void deleteForum(Long id) {
        forumRepository.deleteById(id);
    }
}
