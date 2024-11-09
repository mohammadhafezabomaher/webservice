package tn.esprit.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Sender ID (userId in the Node.js service)
    private String senderId;

    // Receiver ID (userId in the Node.js service)
    private String receiverId;

    @Column(length = 1000)
    private String message;

    private Date dateSent;


}
