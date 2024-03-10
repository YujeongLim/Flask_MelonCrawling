package com.uni.melon.mapper;

import com.uni.melon.dto.MelonchartDTO;
import com.uni.melon.dto.PlaylistDTO;
import com.uni.melon.dto.SongDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlaylistMapper {

    @Select("SELECT * FROM melonchart")
    List<MelonchartDTO> getMelonchartData();

    @Select("SELECT * FROM playlist")
    @Results(value = {
            @Result(property = "playlistId", column = "playlist_id"),
            @Result(property = "playlistName", column = "playlist_name")
    })
    List<PlaylistDTO> getAllPlaylists();

    @Insert("INSERT INTO playlist (playlist_name) VALUES (#{playlistName})")
    int insertPlaylist(PlaylistDTO playlist);

    @Select("SELECT * FROM playlist WHERE playlist_id = #{id}")
    @Results(value = {
            @Result(property = "playlistId", column = "playlist_id"),
            @Result(property = "playlistName", column = "playlist_name")
    })
    PlaylistDTO getPlaylistById(Long id);

    @Update("UPDATE playlist SET playlist_name = #{playlistName} WHERE playlist_id = #{id}")
    void updatePlaylistName(@Param("id") Long id, @Param("playlistName") String playlistName);



    @Delete("DELETE FROM playlist WHERE playlist_id = #{id}")
    int deletePlaylist(int id);

    @Select("SELECT s.song_id, s.title, s.artist FROM songs s " +
            "JOIN playlist_songs ps ON s.song_id = ps.song_id " +
            "WHERE ps.playlist_id = #{playlistId}")
    List<SongDTO> findSongsByPlaylistId(@Param("playlistId") Long playlistId);


}
