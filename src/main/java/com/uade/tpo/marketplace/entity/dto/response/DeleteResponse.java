package com.uade.tpo.marketplace.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteResponse<T> {
    private String mensaje;
    private T data;
}
