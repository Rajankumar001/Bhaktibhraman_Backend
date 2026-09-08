package com.bhaktiBhraman.app.features.temple.services;
import com.bhaktiBhraman.app.features.temple.dto.TempleRequest;
import com.bhaktiBhraman.app.features.temple.entity.Temple;
import com.bhaktiBhraman.app.features.temple.repository.TempleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class TempleService {
    private final TempleRepository templeRepository;

    // 🔥 constructor
    public TempleService(TempleRepository templeRepository) {
        this.templeRepository = templeRepository;
    }

    public Temple createTemple(TempleRequest request, MultipartFile file) throws IOException {

        // 🔥 Generate unique filename
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 📁 Save file locally
        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File uploadPath = new File(uploadDir);

        if (!uploadPath.exists()) {
            boolean created = uploadPath.mkdirs();;
            if (!created) {
                throw new RuntimeException("Failed to create directory");
            }
        }
        file.transferTo(new File(uploadDir + fileName));

        // 👉 Save in DB
        Temple temple = Temple.builder()
                .name(request.getName())
                .location(request.getLocation())
                .description(request.getDescription())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .imageUrl(uploadDir + fileName) // 🔥 store path
                .build();

        return templeRepository.save(temple);
    }
}
