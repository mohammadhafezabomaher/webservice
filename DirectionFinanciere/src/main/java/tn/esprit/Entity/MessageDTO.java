package tn.esprit.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private Long id;

    private Contact senderContact;


    private Contact receiverContact;
    private String message;

    private Date dateSent;
}
