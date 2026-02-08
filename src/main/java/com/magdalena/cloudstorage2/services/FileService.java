package com.magdalena.cloudstorage2.services;

import com.magdalena.cloudstorage2.dto.FileResponse;
import com.magdalena.cloudstorage2.models.Folder;
import com.magdalena.cloudstorage2.models.StoredFile;
import com.magdalena.cloudstorage2.models.User;
import com.magdalena.cloudstorage2.repositories.FolderRepository;
import com.magdalena.cloudstorage2.repositories.StoredFileRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final StoredFileRepository storedFileRepository;
    private final FolderRepository folderRepository;
    private final UserService userService;

    /**
     * Uploads a file to a specific folder owned by the current user.
     * The file content is stored directly in the database.
     *
     * @param folderId ID of the target folder
     * @param file multipart file uploaded by the client
     * @return basic file information
     * @throws RuntimeException if folder is not found or access is denied
     */
    public FileResponse uploadFile(UUID folderId, MultipartFile file) {

        User user = userService.getCurrentUser();

        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        if (!folder.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        try {
            StoredFile storedFile = new StoredFile();
            storedFile.setFilename(file.getOriginalFilename());
            storedFile.setContent(file.getBytes());
            storedFile.setFolder(folder);

            StoredFile saved = storedFileRepository.save(storedFile);

            return new FileResponse(saved.getId(), saved.getFilename());

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    /**
     * Retrieves a stored file by its ID.
     *
     * @param fileId ID of the file
     * @return stored file entity
     * @throws RuntimeException if file is not found
     */
    public StoredFile getFile(UUID fileId) {
        return storedFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }
    /**
     * Deletes a file by its ID.
     *
     * @param fileId ID of the file to delete
     * @throws RuntimeException if the file does not exist
     */
    public void deleteFile(UUID fileId) {

        if (!storedFileRepository.existsById(fileId)) {
            throw new RuntimeException("File not found");
        }

        storedFileRepository.deleteById(fileId);
    }
    /**
     * Retrieves all files within a specific folder.
     *
     * @param folderId ID of the folder
     * @return list of files belonging to the folder
     */
    public List<FileResponse> getFilesByFolder(UUID folderId) {

        return storedFileRepository.findFileResponsesByFolderId(folderId);
    }


}