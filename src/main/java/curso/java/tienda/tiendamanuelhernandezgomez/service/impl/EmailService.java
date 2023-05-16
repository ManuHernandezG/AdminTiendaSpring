package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService{
    
    @Autowired
    private JavaMailSenderImpl mailSender; 

    public void sendEmail(String destino, String asunto, String body){
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(destino);
        mail.setSubject(asunto);
        mail.setText(body);

        mailSender.send(mail);
    }

   
    
}
