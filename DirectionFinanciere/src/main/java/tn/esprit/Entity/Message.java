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
    private Long senderId;

    // Receiver ID (userId in the Node.js service)
    private Long receiverId;

    @Column(length = 1000)
    private String message;

    private Date dateSent;

    // Additional field for status or type (optional)
    private String status; // For example, "sent", "read", "delivered"

    // If needed, you can add more fields like 'messageType', 'attachments', etc.
}
