package ecom.icet.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;

    @ManyToOne
    @JoinColumn(name = "user_Id",nullable = false)
    private Long userId;

    private String details;
    private LocalDateTime timestamp;
}