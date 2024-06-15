package com.thembelanimkhize.Mail_send2.service.impl;

import com.thembelanimkhize.Mail_send2.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

import static com.thembelanimkhize.Mail_send2.utils.EmailUtils.getEmailMessage;
import static com.thembelanimkhize.Mail_send2.utils.EmailUtils.getVerificationUrl;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    public static final String NEW_USER_ACCOUNT_VERIFICATION = "360 Reviews";
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String EMAIL_TEMPLATE = "emailtemplate";
    public static final String TEXT_HTML_ENCONDING = "text/html";
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.verify.host}")
    private String host;
//    @Value("${spring.mail.username}")
//    private String fromEmail;

    @Override
    @Async
    public void sendHtmlEmail(String name, String to /**, String token*/) {
        try {
            Context context = new Context();
            /*context.setVariable("name", name);
            context.setVariable("url", getVerificationUrl(host, token));*/
//            context.setVariables(Map.of("name", name, "url", getVerificationUrl(host, token)));
            String a="https://www.w3schools.com/typescript/typescript_aliases_and_interfaces.php";
            context.setVariables(Map.of("name", name, "url", a));
            String text = templateEngine.process(EMAIL_TEMPLATE, context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom("360reviews.ioco@eoh.com");
            helper.setTo(to);
            helper.setText(text, true);
            //Add attachments (Optional)
            /*FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/fort.jpg"));
            FileSystemResource dog = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/dog.jpg"));
            FileSystemResource homework = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/homework.docx"));
            helper.addAttachment(fort.getFilename(), fort);
            helper.addAttachment(dog.getFilename(), dog);
            helper.addAttachment(homework.getFilename(), homework);*/
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage() +"wwwwwwwwwwwwS");
            throw new RuntimeException(exception.getMessage());
        }
    }


    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }
}
