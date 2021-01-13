package com.example.myfinalproj.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("confirmed_email")
    @Expose
    private String confirmedEmail;
    @SerializedName("from_social")
    @Expose
    private String fromSocial;
    @SerializedName("videos_count")
    @Expose
    private String videosCount;
    @SerializedName("playlists_count")
    @Expose
    private String playlistsCount;
    @SerializedName("favourite_videos_count")
    @Expose
    private String favouriteVideosCount;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("favourite_playlists_count")
    @Expose
    private String favouritePlaylistsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfirmedEmail() {
        return confirmedEmail;
    }

    public void setConfirmedEmail(String confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
    }

    public String getFromSocial() {
        return fromSocial;
    }

    public void setFromSocial(String fromSocial) {
        this.fromSocial = fromSocial;
    }

    public String getVideosCount() {
        return videosCount;
    }

    public void setVideosCount(String videosCount) {
        this.videosCount = videosCount;
    }

    public String getPlaylistsCount() {
        return playlistsCount;
    }

    public void setPlaylistsCount(String playlistsCount) {
        this.playlistsCount = playlistsCount;
    }

    public String getFavouriteVideosCount() {
        return favouriteVideosCount;
    }

    public void setFavouriteVideosCount(String favouriteVideosCount) {
        this.favouriteVideosCount = favouriteVideosCount;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getFavouritePlaylistsCount() {
        return favouritePlaylistsCount;
    }

    public void setFavouritePlaylistsCount(String favouritePlaylistsCount) {
        this.favouritePlaylistsCount = favouritePlaylistsCount;
    }

}
