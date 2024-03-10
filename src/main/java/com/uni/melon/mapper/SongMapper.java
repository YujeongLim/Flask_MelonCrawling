package com.uni.melon.mapper;

import com.uni.melon.dto.SongDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface SongMapper {

    @Select("SELECT * FROM songs")
    List<SongDTO> getAllSongs();


    @Select("SELECT * FROM melonchart WHERE title LIKE CONCAT('%', #{title}, '%')")
    List<SongDTO> searchSongs(String title);

    @Delete("DELETE FROM songs WHERE song_id = #{songId}")
    void deleteSong(@Param("songId") Long songId);

}
