package com.uni.melon.controller;

import com.uni.melon.dto.PlaylistDTO;
import com.uni.melon.dto.MelonchartDTO;
import com.uni.melon.service.PlaylistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@Slf4j
@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/melonchart")
    public ResponseEntity<List<MelonchartDTO>> getMelonchartData() {
        return ResponseEntity.ok(playlistService.getMelonchartData());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
        ResponseEntity<List<PlaylistDTO>> result = ResponseEntity.ok(playlistService.getAllPlaylistsAndSongs());
        log.info(result.toString());
        return result;
    }

    @PostMapping("/createplaylist")
    public ResponseEntity<?> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        playlistService.insertPlaylist(playlistDTO);
        return new ResponseEntity<>("Playlist created successfully", HttpStatus.CREATED);
    }


    @GetMapping("/{playlistId}")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable("playlistId") Long playlistId) {
        PlaylistDTO playlist = playlistService.getPlaylistById(playlistId);
        if (playlist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(playlist);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePlaylist(@PathVariable int id) {
        return ResponseEntity.ok(playlistService.deletePlaylist(id));
    }

    // 플레이리스트 이름 수정
    @PostMapping("/updateName/{playlistId}")
    public String updatePlaylistName(@PathVariable Long playlistId, @RequestParam String playlistName, RedirectAttributes redirectAttributes) {
        playlistService.updatePlaylistName(playlistId, playlistName);
        redirectAttributes.addFlashAttribute("successMessage", "Playlist name updated successfully.");
        return "redirect:/playlists/detail/" + playlistId;
    }
}
