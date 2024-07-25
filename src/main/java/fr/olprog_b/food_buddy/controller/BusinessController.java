package fr.olprog_b.food_buddy.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.olprog_b.food_buddy.dto.business.BusinessResponseDTO;
import fr.olprog_b.food_buddy.service.BusinessService;
import fr.olprog_b.food_buddy.service.UploadImageService;

@RestController
@RequestMapping("/businesses")
public class BusinessController {
  private final String BUSINESS_ROOT_DIR = "upload-dir/business";
  private final UploadImageService uploadImageService;
  private final BusinessService businessService;

  public BusinessController(UploadImageService uploadImageService, BusinessService businessService) {
    this.uploadImageService = uploadImageService;
    this.businessService = businessService;
  }

  @PostMapping("upload-logo/{businessId}")
  public ResponseEntity<BusinessResponseDTO> handleFileUpload(@PathVariable Long businessId, @RequestBody MultipartFile file) {
    try {
      System.out.println("Entrée dans handleFileUpload !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      System.out.println(file.getOriginalFilename());
          String avatarUrl = uploadImageService.uploadImage(BUSINESS_ROOT_DIR, file);
          if (avatarUrl != null) {
              return ResponseEntity.ok(businessService.businessLogoPatch(businessId, avatarUrl));
          }
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }
  }

  @GetMapping("/logo/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    try {
        Resource resource = uploadImageService.getImage(BUSINESS_ROOT_DIR, filename);
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }
}
