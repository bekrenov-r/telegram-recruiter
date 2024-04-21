package com.lordsofcookies.telegramrecruiter.service;

import com.lordsofcookies.telegramrecruiter.dto.request.ApplicationRequest;
import com.lordsofcookies.telegramrecruiter.entity.Application;
import com.lordsofcookies.telegramrecruiter.entity.Offer;
import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import com.lordsofcookies.telegramrecruiter.repository.ApplicationRepository;
import com.lordsofcookies.telegramrecruiter.repository.OfferRepository;
import com.lordsofcookies.telegramrecruiter.repository.TelegramUserRepository;
import com.lordsofcookies.telegramrecruiter.util.CurrentAuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final TelegramUserRepository telegramUserRepository;
    private final OfferRepository offerRepository;

    private static final String CV_DIR = "backend/src/main/resources/files/cv";


    public void createApplication(ApplicationRequest request) {
        TelegramUser telegramUser = telegramUserRepository.findByTelegramIdOrThrowDefault(CurrentAuthUtil.getCurrentAuth().getName());
        Offer offer = offerRepository.findByIdOrThrowDefault(request.offerId());
        Application application = new Application(
                null,
                telegramUser,
                offer,
                request.firstName(),
                request.lastName(),
                request.email(),
                request.phoneNumber(),
                request.messageToRecruiter(),
                null
        );
        applicationRepository.save(application);
    }

    public void attachCVToApplication(UUID id, MultipartFile file) throws IOException {
        Application application = applicationRepository.findByIdOrThrowDefault(id);
        removeCVIfPresent(application.getCvPath());
        String cvPath = saveCV(file, new File(CV_DIR).getAbsoluteFile());
        application.setCvPath(cvPath);
        applicationRepository.save(application);
    }

    private void removeCVIfPresent(String cvPath) throws IOException {
        if(cvPath != null) Files.delete(Paths.get(cvPath));
    }

    private String saveCV(MultipartFile file, File targetDir) throws IOException {
            /*BufferedImage doc = ImageIO.read(fileInputStream);
            String name = generateFileName();
            File output = new File(targetDir, name);
            ImageIO.write(doc, "pdf", output);
            return targetDir.getPath() + File.separator + name;*/
        var name = generateFileName();

        file.transferTo(new File(new File(CV_DIR + File.separator + name).getAbsolutePath()));
        return targetDir.getPath() + File.separator + name;
    }

    private String generateFileName(){
        LocalDateTime now = LocalDateTime.now();
        return new StringBuilder()
                .append(now.toLocalDate())
                .append('_')
                .append(now.toLocalTime().getHour())
                .append(now.toLocalTime().getMinute())
                .append(now.toLocalTime().getSecond())
                .append('_')
                .append(now.toLocalTime().getNano())
                .append(".")
                .append("pdf")
                .toString();
    }
}
