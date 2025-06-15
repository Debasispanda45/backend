package com.tastytown.backend.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastytown.backend.service.IImageSerice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/images")
@Tag(name = "Image API", description = "This controller is resposible for extracting the food image.")
@RequiredArgsConstructor
public class ImageController {
    private final IImageSerice iImageSerice;

    @GetMapping("/{fileName}")
    @ApiResponse(responseCode = "200", description = "Extract food image with image name.")
    @Operation(summary = "eXTRACT fOOD iMAGE")
    public ResponseEntity<byte[]> saveIamge(@PathVariable String fileName) throws IOException {
        return iImageSerice.extractFoodImage(fileName);
    }
}
