package com.mysite.cuffee.media.controller;

import com.mysite.cuffee.media.service.MediaService;
import com.mysite.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee")
public class MediaController {

    private final MediaService mediaService;

    public record UploadRes(String imageUrl) {}

    @PostMapping(value = "/products/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RsData<UploadRes> upload(@RequestPart("file") MultipartFile file) {
        String imageUrl = mediaService.saveToResources(file); // /images/<uuid>.<ext>
        return new RsData<>("201-1", "이미지 업로드 성공", new UploadRes(imageUrl));
    }

    @DeleteMapping("/products/image")
    public RsData<Void> deleteByImageUrl(@RequestParam String imageUrl) {
        mediaService.deleteByImageUrl(imageUrl);
        return new RsData<>("204-1", "이미지 삭제 성공");
    }

}
