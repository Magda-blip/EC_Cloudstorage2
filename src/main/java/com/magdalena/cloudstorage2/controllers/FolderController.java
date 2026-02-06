package com.magdalena.cloudstorage2.controllers;

import com.magdalena.cloudstorage2.models.Folder;
import com.magdalena.cloudstorage2.repositories.FolderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")

public class FolderController {
    private final FolderRepository folderRepository;

    public FolderController(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    //Create new folder
    @PostMapping
    public Folder createFolder(@RequestBody Folder folder) {
        return folderRepository.save(folder);
    }

    // Get all folders
    @GetMapping
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

}
