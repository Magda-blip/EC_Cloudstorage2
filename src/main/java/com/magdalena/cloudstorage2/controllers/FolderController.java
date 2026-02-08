package com.magdalena.cloudstorage2.controllers;

import com.magdalena.cloudstorage2.dto.CreateFolderRequest;
import com.magdalena.cloudstorage2.dto.FolderResponse;
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
    public Folder createFolder(@RequestBody CreateFolderRequest request) {
        return folderService.createFolder(
                request.getName(),
                request.getUserId()
        );
    }


    @GetMapping("/user/{userId}")
    public List<FolderResponse> getFoldersByUser(@PathVariable UUID userId) {
        return folderService.getFoldersByUser(userId);
    }
}