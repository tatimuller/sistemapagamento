package com.sistemapagamento.services;

import com.sistemapagamento.domain.user.User;
import com.sistemapagamento.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String mensage) throws Exception {

        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, mensage);
        ResponseEntity<String> response = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class);

        if (!response.equals(HttpStatus.OK)){
            System.out.println("Erro ao enviar notificação");
            throw new Exception("O Serviço de notificação está fora do ar");

        }


    }
}
