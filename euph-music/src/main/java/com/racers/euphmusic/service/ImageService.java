package com.racers.euphmusic.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static com.racers.euphmusic.utils.StringUtils.IMAGE_BUCKET;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
public class ImageService {

    @SneakyThrows
    public void upload(String imagePath, InputStream content, Class clazz) {
        Path fullImagePath = Path.of(IMAGE_BUCKET + "/" + clazz.getSimpleName(), imagePath);
        try (content) {
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, content.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> get(String imagePath, Class clazz) {
        Path fullImagePath = Path.of(IMAGE_BUCKET + "/" + clazz.getSimpleName(), imagePath);
        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }

    @SneakyThrows
    public Optional<byte[]> findDefaultPersonAvatar(String imagePath, Class clazz) {
        Path fullImagePath = Path.of(IMAGE_BUCKET + "/" + clazz.getSimpleName(), imagePath);
        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }

}
