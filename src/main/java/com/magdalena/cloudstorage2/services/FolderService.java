package com.magdalena.cloudstorage2.services;

import com.magdalena.cloudstorage2.models.Folder;
import com.magdalena.cloudstorage2.repositories.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    //Creates and saves new folder
    public Folder createFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    //Gets all folders
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    //Find folder by id
    public Folder getFolderById(UUID id) {
        return folderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Folder not found"));
    }
}
