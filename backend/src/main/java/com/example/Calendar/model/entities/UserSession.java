package com.example.Calendar.model.entities;

// Spring packages
import jakarta.persistence.*;
import lombok.Data;

// Java packages
import java.time.LocalDateTime;

@Entity
@Table(name = "user_sessions")
@Data
public class UserSession extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name="jwt_token", nullable = false)
    private String jwtToken;

    @Column(name = "refresh_token_expiration", nullable = false)
    private LocalDateTime refreshTokenExpiration;

    @Column(nullable = false)
    private Boolean redeemed = false;
}
