package com.example.EdufyUser.repositories;

import com.example.EdufyUser.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByActiveTrue();//ED-87-SA

    //ED-88-AA
    Optional<User> findByUuid(String sub);

    //ED-89-AA
    @Query(value = "SELECT song_history_id FROM song_history_user WHERE user_id = :userId", nativeQuery = true)
    Set<Long> findSongHistoryIdsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT album_history_id FROM album_history_user WHERE user_id = :userId", nativeQuery = true)
    Set<Long> findAlbumHistoryIdsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT video_clip_history_id FROM video_clip_history_user WHERE user_id = :userId", nativeQuery = true)
    Set<Long> findVideoClipHistoryIdsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT video_playlist_history_id FROM video_playlist_history_user WHERE user_id = :userId", nativeQuery = true)
    Set<Long> findVideoPlaylistHistoryIdsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT pod_episode_history_id FROM pod_episode_history_user WHERE user_id = :userId", nativeQuery = true)
    Set<Long> findPodEpisodeHistoryIdsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT pod_season_history_id FROM pod_season_history_user WHERE user_id = :userId", nativeQuery = true)
    Set<Long> findPodSeasonHistoryIdsByUserId(@Param("userId") Long userId);
}
