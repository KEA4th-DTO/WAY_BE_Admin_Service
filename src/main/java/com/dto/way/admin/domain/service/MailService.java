package com.dto.way.admin.domain.service;

import com.dto.way.admin.global.CertificationGenerator;
import com.dto.way.admin.web.response.EmailCertificationResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;
    private final CertificationGenerator certificationGenerator;
    private final RedisService redisService;

    public EmailCertificationResponse sendEmailForCertification(String email) throws NoSuchAlgorithmException, MessagingException {

        String certificationNumber = certificationGenerator.createCertificationNumber();
        String content = String.format("%s/member-service/verify?certificationNumber=%s&email=%s   해당 링크로 10분 이내에 접속해주세요.", "localhost:8080", certificationNumber, email);

        saveEmailInRedis(email, certificationNumber);

        try {
            sendMail(email, content);
            return new EmailCertificationResponse(email, certificationNumber);
        } catch (MessagingException e) { // 메일 전송에 실패한 경우
            log.error("메일 전송 실패! 유효하지 않은 이메일: {}", email);
            throw e;
        }
    }

    private void saveEmailInRedis(String email, String certificationNumber) {
        if (redisService.getValues(email).isEmpty()) { // redis 안에 이메일 키가 없다면
            redisService.setValues(email, certificationNumber, Duration.ofMinutes(10));
        } else { // redis 안에 이메일 키가 있다면 -> 이미 인증번호 요청을 한 상태이기 때문에 데이터를 삭제하고 다시 만든다.
            redisService.deleteValues(email);
            redisService.setValues(email, certificationNumber, Duration.ofMinutes(10));
        }
    }


    private void sendMail(String email, String content) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(email);
        helper.setSubject("인증 메일 입니다.");
        helper.setText(content);
        javaMailSender.send(mimeMessage);
    }
}
