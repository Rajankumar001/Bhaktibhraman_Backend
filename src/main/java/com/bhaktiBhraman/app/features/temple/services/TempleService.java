package com.bhaktiBhraman.app.features.temple.services;
import com.bhaktiBhraman.app.features.temple.dto.TempleRequest;
import com.bhaktiBhraman.app.features.temple.entity.Temple;
import com.bhaktiBhraman.app.features.temple.repository.TempleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class TempleService {

    private final TempleRepository templeRepository;
    private final Cloudinary cloudinary;

    public TempleService(TempleRepository templeRepository, Cloudinary cloudinary) {
        this.templeRepository = templeRepository;
        this.cloudinary = cloudinary;
    }

    public Temple createTemple(TempleRequest request, MultipartFile file) throws IOException {

        // 1. Upload binary file to Cloudinary CDN
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", "bhaktibhraman/temples")
        );

        // 2. Extract public secure HTTPS URL returned by Cloudinary
        String secureUrl = uploadResult.get("secure_url").toString();

        // 3. Save URL in MySQL Database
        Temple temple = Temple.builder()
                .name(request.getName())
                .location(request.getLocation())
                .description(request.getDescription())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .imageUrl(secureUrl) // e.g., "https://res.cloudinary.com/demo/image/upload/..."
                .build();

        return templeRepository.save(temple);
    }

    public Page<Temple> getPaginatedTemples(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return templeRepository.findAll(pageable);
    }
}
