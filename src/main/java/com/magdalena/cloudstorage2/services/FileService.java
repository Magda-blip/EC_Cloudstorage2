package com.magdalena.cloudstorage2.services;

import com.magdalena.cloudstorage2.dto.FileResponse;
import com.magdalena.cloudstorage2.models.Folder;
import com.magdalena.cloudstorage2.models.StoredFile;
import com.magdalena.cloudstorage2.repositories.FolderRepository;
import com.magdalena.cloudstorage2.repositories.StoredFileRepository;
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

    /**
     * Uploads a file and stores it in the database, linked to a folder.
     *
     * @param folderId the ID of the folder
     * @param file     the uploaded file
     * @return the stored file entity
     */
    public FileResponse uploadFile(UUID folderId, MultipartFile file) {

        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        try {
            StoredFile storedFile = new StoredFile();
            storedFile.setFilename(file.getOriginalFilename());
            storedFile.setContent(file.getBytes());
            storedFile.setFolder(folder);

            StoredFile saved = storedFileRepository.save(storedFile);

            FileResponse response = new FileResponse();
            response.setId(saved.getId());
            response.setFilename(saved.getFilename());

            return response;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    /**
     * Retrieves a stored file by its ID.
     *
     * @param fileId the ID of the file
     * @return the stored file
     */
    public StoredFile getFile(UUID fileId) {
        return storedFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }


}