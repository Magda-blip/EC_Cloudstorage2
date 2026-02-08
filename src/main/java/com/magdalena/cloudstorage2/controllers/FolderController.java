package com.magdalena.cloudstorage2.controllers;

import com.magdalena.cloudstorage2.models.Folder;
import com.magdalena.cloudstorage2.services.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/folders")
@RequiredArgsConstructor

public class FolderController {

    private final FolderService folderService;

    @PostMapping
    public Folder createFolder(
            @RequestParam String name,
            @RequestParam UUID userId
    ) {
        return folderService.createFolder(name, userId);
    }

    @GetMapping("/user/{userId}")
    public List<Folder> getFoldersByUser(@PathVariable UUID userId) {
        return folderService.getFoldersByUser(userId);
    }
}