package com.example.driversApp.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_and_surename")
    @NotBlank(message = "Imie i nazwisko jest wymagane!")
    private String nameAndSurename;

    @Column(name = "team")
    @NotBlank(message = "Team jest wymagany!")
    private String team;

    @Column(name = "points")
    private int points;

    @Column(name = "nationality")
    @NotBlank(message = "Narodowość jest wymagana!")
    private String nationality;

    @Column(name = "image")
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAndSurename() {
        return nameAndSurename;
    }

    public void setNameAndSurename(String nameAndSurename) {
        this.nameAndSurename = nameAndSurename;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }


    public void setPoints(int points) {
        this.points = points;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Transient
    public String getLogoPath(){
        if(logo == null || id == 0) return null;
        return "./saved-images/" +  logo;
    }
}
