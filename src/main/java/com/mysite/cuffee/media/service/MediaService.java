package com.mysite.cuffee.media.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

@Service
public class MediaService {

    private final Path imagesDir = Paths.get("src/main/resources/static/images")
            .toAbsolutePath().normalize();

    private static final Set<String> ALLOWED = Set.of("jpg","jpeg","png","gif","webp");

    public String saveToResources(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일입니다.");
        }
        String original = file.getOriginalFilename();
        String ext = getExt(original);
        if (!ALLOWED.contains(ext)) {
            throw new IllegalArgumentException("허용되지 않는 확장자: " + ext);
        }

        try {
            Files.createDirectories(imagesDir);
            String filename = UUID.randomUUID().toString().replace("-", "") + "." + ext;
            Path target = imagesDir.resolve(filename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            return "/images/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 실패", e);
        }
    }

    private static String getExt(String name) {
        if (name == null) return "";
        int i = name.lastIndexOf('.');
        return (i < 0) ? "" : name.substring(i + 1).toLowerCase();
    }

    public boolean deleteByImageUrl(String imageUrlOrFileName) {
        if (!StringUtils.hasText(imageUrlOrFileName)) return false;

        String lower = imageUrlOrFileName.toLowerCase();
        if (lower.startsWith("http://") || lower.startsWith("https://")) return false;

        String name = imageUrlOrFileName.trim();
        if (name.startsWith("/images/")) name = name.substring("/images/".length());
        if (name.startsWith("/")) return false; // /images/가 아닌 다른 절대경로는 거부

        if (name.contains("/") || name.contains("..")) return false;

        try {
            Files.createDirectories(imagesDir);
            Path target = imagesDir.resolve(name).normalize();
            if (!target.startsWith(imagesDir)) return false; // 폴더 이탈 방지
            return Files.deleteIfExists(target);
        } catch (IOException e) {
            System.err.println("이미지 삭제 실패: " + e.getMessage());
            return false;
        }
    }
}
