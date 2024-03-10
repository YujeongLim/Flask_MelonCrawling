package com.uni.melon.service;

import com.uni.melon.dto.SongDTO;
import com.uni.melon.mapper.SongMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SongService {
    private final SongMapper songMapper;

    @Autowired
    public SongService(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    public List<SongDTO> getAllSongs() {
        return songMapper.getAllSongs();
    }

    public List<SongDTO> searchSongs(String songName) {
        log.info(songName);
        return songMapper.searchSongs(songName);
    }

    public void deleteSong(Long songId) {
        songMapper.deleteSong(songId);
    }

}
