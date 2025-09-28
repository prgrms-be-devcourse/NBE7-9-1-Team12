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
    public RsData<UploadRes> uploadImg(@RequestPart("file") MultipartFile file) {
        String imageUrl = mediaService.uploadImg(file); // /images/<uuid>.<ext>
        return new RsData<>("201-1", "이미지 업로드 성공", new UploadRes(imageUrl));
    }

    @DeleteMapping("/products/image")
    public RsData<Void> deleteImg(@RequestParam String imageUrl) {
        mediaService.deleteImg(imageUrl);
        return new RsData<>("204-1", "이미지 삭제 성공");
    }


    @PutMapping(value = "/products/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RsData<UploadRes> modifyImg(
            @RequestPart("file") MultipartFile file,
            @RequestPart("oldImageUrl") String oldImageUrl
    ) {
        String newUrl = mediaService.modifyImg(file, oldImageUrl);
        return new RsData<>("200-2", "이미지 수정 성공", new UploadRes(newUrl));
    }
}
