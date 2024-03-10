package com.uni.melon.controller;

import com.uni.melon.dto.SongDTO;
import com.uni.melon.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/search")
    public ResponseEntity<List<SongDTO>> searchSongs(@RequestParam String songName) {
        log.info(songName);

        List<SongDTO> searchResults = songService.searchSongs(songName);

        log.info(searchResults.toString());
        return ResponseEntity.ok(searchResults);
    }

    // 노래 삭제
    @PostMapping("/detail/{playlistId}/deleteSong/{songId}")
    public String deleteSong(@PathVariable("playlistId") Long playlistId, @RequestParam("songId") Long songId, RedirectAttributes redirectAttributes) {
        System.out.println("songId: " + songId); // 디버깅 메시지 추가
        songService.deleteSong(songId);
        redirectAttributes.addFlashAttribute("successMessage", "Song deleted successfully.");
        return "redirect:/playlists/detail/" + playlistId;
    }
}
