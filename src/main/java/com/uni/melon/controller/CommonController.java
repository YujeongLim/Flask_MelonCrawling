package com.uni.melon.controller;

import com.uni.melon.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.uni.melon.dto.PlaylistDTO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
public class CommonController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlists/detail/{playlistId}")
    public String getPlaylistById(@PathVariable("playlistId") Long playlistId, Model model) {
        PlaylistDTO playlist = playlistService.getPlaylistById(playlistId);
        if (playlist == null) {
            return "playlistNotFound";
        }
        model.addAttribute("playlist", playlist);
        model.addAttribute("songs", playlist.getSongs());
        return "playlist_detail";
    }



}

