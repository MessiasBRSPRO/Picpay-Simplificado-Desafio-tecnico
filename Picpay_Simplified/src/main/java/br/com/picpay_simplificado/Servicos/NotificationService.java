package br.com.picpay_simplificado.Servicos;

import br.com.picpay_simplificado.DTOS.DTONotificationRequest;
import br.com.picpay_simplificado.Entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(Usuario usuario, String msg){
        String email = usuario.getEmail();
        DTONotificationRequest dtoNotificationRequest = new DTONotificationRequest(email, msg);
        ResponseEntity<String> response = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", dtoNotificationRequest, String.class);

        if(!(response.getStatusCode() == HttpStatus.OK)){
            throw new RuntimeException("Serviço de notificação esta fora do ar");
        }
    }
}
