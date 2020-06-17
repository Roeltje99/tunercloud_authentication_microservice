package com.tunercloud.authentication_microservice.rabbitmq;


import com.tunercloud.authentication_microservice.models.Account;

public class ArtistWrapper {
    private int id;
    private String artistName;
    private String bio;

    public ArtistWrapper() { }

    public ArtistWrapper(Account account) {
        this.id = account.getId();
        this.artistName = account.getName();
        this.bio = account.getBio();
    }

    //Getters and Setters
    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
