package com.magdalena.cloudstorage2.services;

import com.magdalena.cloudstorage2.models.Folder;
import com.magdalena.cloudstorage2.models.User;
import com.magdalena.cloudstorage2.repositories.FolderRepository;
import com.magdalena.cloudstorage2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor


public class FolderService {


    private final FolderRepository folderRepository;
    private final UserRepository userRepository;

    public Folder createFolder(String name, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Folder folder = new Folder();
        folder.setName(name);
        folder.setUser(user);

        return folderRepository.save(folder);
    }
    /**
     * Returns all folders that belong to a specific user.
     *
     * @param userId the ID of the user
     * @return list of folders owned by the user
     */
    public List<Folder> getFoldersByUser(UUID userId) {
        return folderRepository.findByUserId(userId);
    }
}
