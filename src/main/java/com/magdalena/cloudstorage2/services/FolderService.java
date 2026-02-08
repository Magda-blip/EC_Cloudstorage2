package com.magdalena.cloudstorage2.services;

import com.magdalena.cloudstorage2.models.Folder;
import com.magdalena.cloudstorage2.models.User;
import com.magdalena.cloudstorage2.repositories.FolderRepository;
import com.magdalena.cloudstorage2.repositories.UserRepository;
import com.magdalena.cloudstorage2.dto.FolderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor


public class FolderService {


    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    /**
     * Creates a folder for the currently authenticated user.
     */
    public Folder createFolder(String name) {
        User user = userService.getCurrentUser();

        Folder folder = new Folder();
        folder.setName(name);
        folder.setUser(user);

        return folderRepository.save(folder);
    }

    /**
     * Returns all folders owned by the current user.
     */
    public List<FolderResponse> getMyFolders() {
        User user = userService.getCurrentUser();

        return folderRepository.findByUserId(user.getId())
                .stream()
                .map(folder -> {
                    FolderResponse response = new FolderResponse();
                    response.setId(folder.getId());
                    response.setName(folder.getName());
                    return response;
                })
                .toList();
    }

}
