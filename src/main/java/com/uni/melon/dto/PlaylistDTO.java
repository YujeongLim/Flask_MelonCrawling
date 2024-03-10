package com.uni.melon.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistDTO {
    private Long playlistId;
    private String playlistName;
    private List<SongDTO> songs; // Playlist에 포함된 Song 목록
}
