package com.example.santa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "team_code", nullable = false, unique = true, length = 60)
    private String teamCode;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Account owner;
    @Enumerated(EnumType.STRING)
    @Column (name = "team_status")
    private TeamStatus status = TeamStatus.OPEN;
    @Column(nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    public Team(){}

    public Team(String teamCode, Account owner, TeamStatus status){
        this.teamCode = teamCode;
        this.owner = owner;
        this.status = status;
    }
    public Team(String teamCode, Account owner){
        this.teamCode = teamCode;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}
