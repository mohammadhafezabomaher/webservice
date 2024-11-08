package tn.esprit.Entity;

import jakarta.persistence.*;

import java.util.Date;

public class MessageDTO {

    private Long id;

    private Contact senderContact;


    private Contact receiverContact;
    private String message;

    private Date dateSent;
}
