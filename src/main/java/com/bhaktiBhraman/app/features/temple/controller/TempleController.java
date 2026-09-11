package com.bhaktiBhraman.app.features.temple.controller;

import com.bhaktiBhraman.app.features.temple.dto.TempleRequest;
import com.bhaktiBhraman.app.features.temple.entity.Temple;
import com.bhaktiBhraman.app.features.temple.services.TempleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/temples")
public class TempleController {

    private final TempleService templeService;

    @PostMapping
    public Temple createTemple(
            @ModelAttribute TempleRequest request,
            @RequestPart("image") MultipartFile file
    ) throws IOException {

        return templeService.createTemple(request, file);
    }

    // 🚀 Paginated GET Endpoint
    @GetMapping
    public ResponseEntity<ApiResponse<DataWrapper<Page<Temple>>>> getTemples(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Page<Temple> templePage = templeService.getPaginatedTemples(page, limit);

        DataWrapper<Page<Temple>> innerData = new DataWrapper<>(templePage);
        ApiResponse<DataWrapper<Page<Temple>>> response = new ApiResponse<>(innerData);

        return ResponseEntity.ok(response);
    }

    // Helper wrapper classes for res.data.data.content structure
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiResponse<T> {
        private T data;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataWrapper<T> {
        private T data;
    }
}
