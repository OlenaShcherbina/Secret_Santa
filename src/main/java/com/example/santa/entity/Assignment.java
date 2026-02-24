package com.example.santa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @ManyToOne
    @JoinColumn(name = "santa_id", nullable = false)
    private Member santa;
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;
    @Column(nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    public Assignment(){}

    public Assignment(Team team,  Member santa, Member receiver){
        this.team = team;
        this.santa = santa;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public Member getSanta() {
        return santa;
    }

    public Member getReceiver() {
        return receiver;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}
