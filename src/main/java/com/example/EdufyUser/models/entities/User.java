package com.example.EdufyUser.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// ED-138-SJ
@Entity
@Table(name = "edufy_user")
public class User {

// Attributes ----------------------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_sub",unique = true, nullable = false)
    private String uuid;

    @Column(name = "user_username", nullable = false)
    private String username;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_creator")
    private boolean creator;

    @Column(name = "user_active")
    private boolean active;

    //ED-89-AA
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "song_history_user",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "song_history_id", nullable = false)
    private List<Long> songHistoryIds = new ArrayList<>();

    //ED-89-AA
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "album_history_user",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "album_history_id", nullable = false)
    private List<Long> albumHistoryIds = new ArrayList<>();

    //ED-89-AA
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "video_clip_history_user",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "video_clip_history_id", nullable = false)
    private List<Long> videoClipHistoryIds = new ArrayList<>();

    //ED-89-AA
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "video_playlist_history_user",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "video_playlist_history_id", nullable = false)
    private List<Long> videoPlaylistHistoryIds = new ArrayList<>();

    //ED-89-AA
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "pod_episode_history_user",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "pod_episode_history_id", nullable = false)
    private List<Long> podEpisodeHistoryIds = new ArrayList<>();

    //ED-89-AA
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "pod_season_history_user",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "pod_season_history_id", nullable = false)
    private List<Long> podSeasonHistoryIds = new ArrayList<>();

// Constructors --------------------------------------------------------------------------------------------------------

    public User() {}

    public User(
            String uuid,
            String username,
            String email,
            boolean creator,
            boolean active,
            List<Long> songHistoryIds,
            List<Long> albumHistoryIds,
            List<Long> videoClipHistoryIds,
            List<Long> videoPlaylistHistoryIds,
            List<Long> podEpisodeHistoryIds,
            List<Long> podSeasonHistoryIds)
    {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.creator = creator;
        this.active = active;
        this.songHistoryIds = songHistoryIds;
        this.albumHistoryIds = albumHistoryIds;
        this.videoClipHistoryIds = videoClipHistoryIds;
        this.videoPlaylistHistoryIds = videoPlaylistHistoryIds;
        this.podEpisodeHistoryIds = podEpisodeHistoryIds;
        this.podSeasonHistoryIds = podSeasonHistoryIds;
    }

    public User(
            Long id,
            String uuid,
            String username,
            String email,
            boolean creator,
            boolean active,
            List<Long> songHistoryIds,
            List<Long> albumHistoryIds,
            List<Long> videoClipHistoryIds,
            List<Long> videoPlaylistHistoryIds,
            List<Long> podEpisodeHistoryIds,
            List<Long> podSeasonHistoryIds)
    {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.creator = creator;
        this.active = active;
        this.songHistoryIds = songHistoryIds;
        this.albumHistoryIds = albumHistoryIds;
        this.videoClipHistoryIds = videoClipHistoryIds;
        this.videoPlaylistHistoryIds = videoPlaylistHistoryIds;
        this.podEpisodeHistoryIds = podEpisodeHistoryIds;
        this.podSeasonHistoryIds = podSeasonHistoryIds;
    }

    public User(User user)
    {
        this.id = user.id;
        this.uuid = user.uuid;
        this.username = user.username;
        this.email = user.email;
        this.creator = user.creator;
        this.active = user.active;
        this.songHistoryIds = user.songHistoryIds;
        this.albumHistoryIds = user.albumHistoryIds;
        this.videoClipHistoryIds = user.videoClipHistoryIds;
        this.videoPlaylistHistoryIds = user.videoPlaylistHistoryIds;
        this.podEpisodeHistoryIds = user.podEpisodeHistoryIds;
        this.podSeasonHistoryIds = user.podSeasonHistoryIds;
    }

// Getters & Setters ---------------------------------------------------------------------------------------------------

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getUuid() {return uuid;}
    public void setUuid(String uuid) {this.uuid = uuid;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public boolean isCreator() {return creator;}
    public void setCreator(boolean creator) {this.creator = creator;}

    public boolean isActive() {
        return active;}
    public void setActive(boolean active) {
        this.active = active;}

    public List<Long> getSongHistoryIds() {
        return songHistoryIds;
    }

    public void setSongHistoryIds(List<Long> songHistoryIds) {
        this.songHistoryIds = songHistoryIds;
    }

    public List<Long> getAlbumHistoryIds() {
        return albumHistoryIds;
    }

    public void setAlbumHistoryIds(List<Long> albumHistoryIds) {
        this.albumHistoryIds = albumHistoryIds;
    }

    public List<Long> getVideoClipHistoryIds() {
        return videoClipHistoryIds;
    }

    public void setVideoClipHistoryIds(List<Long> videoClipHistoryIds) {
        this.videoClipHistoryIds = videoClipHistoryIds;
    }

    public List<Long> getVideoPlaylistHistoryIds() {
        return videoPlaylistHistoryIds;
    }

    public void setVideoPlaylistHistoryIds(List<Long> videoPlaylistHistoryIds) {
        this.videoPlaylistHistoryIds = videoPlaylistHistoryIds;
    }

    public List<Long> getPodEpisodeHistoryIds() {
        return podEpisodeHistoryIds;
    }

    public void setPodEpisodeHistoryIds(List<Long> podEpisodeHistoryIds) {
        this.podEpisodeHistoryIds = podEpisodeHistoryIds;
    }

    public List<Long> getPodSeasonHistoryIds() {
        return podSeasonHistoryIds;
    }

    public void setPodSeasonHistoryIds(List<Long> podSeasonHistoryIds) {
        this.podSeasonHistoryIds = podSeasonHistoryIds;
    }

    // toString ------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", creator=" + creator +
                ", active=" + active +
                ", SongHistoryIds=" + songHistoryIds.size() +
                ", albumHistoryIds=" + albumHistoryIds.size() +
                ", videoClipHistoryIds=" + videoClipHistoryIds.size() +
                ", videoPlaylistHistoryIds=" + videoPlaylistHistoryIds.size() +
                ", podEpisodeHistoryIds=" + podEpisodeHistoryIds.size() +
                ", podSeasonHistoryIds=" + podSeasonHistoryIds.size() +
                '}';
    }
}
