package fr.olprog_b.food_buddy.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageService {

  public String uploadImage(String rootLocation, MultipartFile file) {
    // Récupération du chemin du fichier
    Path rootLocationPath = Paths.get(rootLocation);
    try {
      // Création du dossier si nécessaire
      Files.createDirectories(rootLocationPath);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize storage", e);
    }

    // Récupération du nom du fichier
    String originalFilename = file.getOriginalFilename();

    int lastDotIndex = originalFilename.lastIndexOf(".");
        String name = originalFilename.substring(0, lastDotIndex);
        String extension = originalFilename.substring(lastDotIndex);
    
    // Récupération du nom du fichier
    String fileName = UUID.randomUUID() + "." + name.trim().toLowerCase().replaceAll("[^a-zA-Z0-9-]", "") + extension;
    try {
      // Copie du fichier dans le dossier spécifié
      Files.copy(file.getInputStream(), rootLocationPath.resolve(fileName));
    } catch (IOException e) {
      throw new RuntimeException("Could not upload file", e);
    }
    return fileName;
  }

  public Resource getImage(String rootLocation, String filename) {
    try {
      Path rootLocationPath = Paths.get(rootLocation);
      Path file = rootLocationPath.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      return resource;
    } catch (MalformedURLException e) {
      return null;
    }
  }
}