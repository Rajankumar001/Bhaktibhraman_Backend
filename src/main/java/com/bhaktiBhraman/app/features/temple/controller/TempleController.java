package com.bhaktiBhraman.app.features.temple.controller;

import com.bhaktiBhraman.app.features.temple.dto.TempleRequest;
import com.bhaktiBhraman.app.features.temple.entity.Temple;
import com.bhaktiBhraman.app.features.temple.services.TempleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
