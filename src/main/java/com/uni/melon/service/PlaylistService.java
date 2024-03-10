package com.uni.melon.service;

import com.uni.melon.dto.MelonchartDTO;
import com.uni.melon.dto.PlaylistDTO;
import com.uni.melon.dto.SongDTO;
import com.uni.melon.mapper.PlaylistMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class PlaylistService {

    private final PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistService(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    public List<MelonchartDTO> getMelonchartData() {
        return playlistMapper.getMelonchartData();
    }

    public List<PlaylistDTO> getAllPlaylistsAndSongs() {
        List<PlaylistDTO> playlists = playlistMapper.getAllPlaylists();
        log.info(playlists.toString());
        for (PlaylistDTO playlist : playlists) {
            List<SongDTO> songs = playlistMapper.findSongsByPlaylistId(playlist.getPlaylistId());
            playlist.setSongs(songs);
        }
        log.info(playlists.toString());
        return playlists;
    }


    public PlaylistDTO getPlaylistById(Long id) {
        List<SongDTO> songs = playlistMapper.findSongsByPlaylistId(id);
        PlaylistDTO playlistDTO = playlistMapper.getPlaylistById(id);
        playlistDTO.setSongs(songs);
        return playlistDTO;
    }

    public void insertPlaylist(PlaylistDTO playlist) {
        playlistMapper.insertPlaylist(playlist);
    }

    public void updatePlaylistName(Long playlistId, String playlistName) {
        playlistMapper.updatePlaylistName(playlistId, playlistName);
    }

    public int deletePlaylist(int id) {
        return playlistMapper.deletePlaylist(id);
    }


}
