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
     * Creates a new folder owned by the currently authenticated user.
     *
     * @param name name of the folder
     * @return created folder
     */
    public Folder createFolder(String name) {
        User user = userService.getCurrentUser();

        Folder folder = new Folder();
        folder.setName(name);
        folder.setUser(user);

        return folderRepository.save(folder);
    }


    /**
     * Retrieves all folders belonging to the currently authenticated user.
     *
     * @return list of folders owned by the user
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
