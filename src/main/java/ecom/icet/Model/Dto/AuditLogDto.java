package ecom.icet.Model.Dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class AuditLogDto {
    private Long id;
    private String action;
    private Long userId;
    private String details;
    private LocalDateTime timestamp;
}