package com.bhaktiBhraman.app.features.temple.dto;

import lombok.Data;

@Data
public class TempleRequest {
    private String name;
    private String description;
    private String imageUrl;
    private String location;
    private String state;
    private Double latitude;
    private Double longitude;
}
