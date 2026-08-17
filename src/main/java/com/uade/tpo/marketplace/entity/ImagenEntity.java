package com.uade.tpo.marketplace.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImagenEntity {

    private int imagenId;
    private String url;
    
}
