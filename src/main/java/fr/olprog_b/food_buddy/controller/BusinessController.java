package fr.olprog_b.food_buddy.controller;

import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.olprog_b.food_buddy.dto.business.BusinessResponseDTO;
import fr.olprog_b.food_buddy.dto.business.PostBusinessDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.BusinessService;
import fr.olprog_b.food_buddy.service.UploadImageService;
import org.springframework.web.bind.annotation.PutMapping;


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

  @PostMapping("/upload-logo")
  public ResponseEntity<BusinessResponseDTO> handleFileUpload(@AuthenticationPrincipal User user, @RequestBody MultipartFile file) {
    try {
          String avatarUrl = uploadImageService.uploadImage(BUSINESS_ROOT_DIR, file);
          if (avatarUrl != null) {
              return ResponseEntity.ok(businessService.businessLogoPatch(user.getId(), avatarUrl));
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

  @GetMapping()
  public ResponseEntity<BusinessResponseDTO> getBusiness(@AuthenticationPrincipal User user) {
    Optional<BusinessResponseDTO> business = businessService.getBusiness(user.getId());
    if (!business.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(business.get());
  }

  @PutMapping()
  public ResponseEntity<BusinessResponseDTO> updateBusiness(@AuthenticationPrincipal User user, @RequestBody PostBusinessDTO postBusinessDTO) {
      Optional<BusinessResponseDTO> updatedBusiness = businessService.updateBusiness(user.getId(), postBusinessDTO);
      if (!updatedBusiness.isPresent()) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(updatedBusiness.get());
  }
}
