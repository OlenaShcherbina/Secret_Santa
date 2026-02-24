package com.example.santa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, length = 60)
    private String email;
    @Column(name = "interest_1", length = 60)
    private String interest1;
    @Column(name = "interest_2", length = 60)
    private String interest2;
    @Column(name = "interest_3", length = 60)
    private String interest3;
    @Column(nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    public Member(){}

    public Member(Team team, String name, String email, String interest1, String interest2, String interest3){
        this.team = team;
        this.name = name;
        this.email = email;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

    public String getInterest3() {
        return interest3;
    }

    public void setInterest3(String interest3) {
        this.interest3 = interest3;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}
