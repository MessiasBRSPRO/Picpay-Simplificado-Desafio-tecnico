package br.com.picpay_simplificado.Servicos;

import br.com.picpay_simplificado.DTOS.DTOTransaction;
import br.com.picpay_simplificado.Entidades.Transacao;
import br.com.picpay_simplificado.Entidades.Usuario;
import br.com.picpay_simplificado.repositorios.TransacaoRepository;
import br.com.picpay_simplificado.repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class TransacaoService {

    //A class RestTemplate é utilizada para nos comunicarmos com serviços externos utilizando o protocolo HTTP

    @Autowired
    private UserService userService;

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private RestTemplate restTemplate; // chamando um serviço externo de autorização(Nesse caso utilizamos ele para nos comunicarmos com ajuda do HTTP com os 2 serviços externos postos no desafio)

    @Autowired
    private NotificationService notificationService;

    public Transacao criarTransacao(DTOTransaction dtoTransaction){
        Usuario pagador = userService.getUsuarioById(dtoTransaction.pagadorId());
        Usuario destinatatio  =userService.getUsuarioById(dtoTransaction.destinatarioId());

        userService.validarTransacao(pagador, dtoTransaction.amount());

        boolean foiAutorizado = autorizarTransacao(pagador, dtoTransaction.amount());
        if(!(foiAutorizado)){
            throw new RuntimeException("Transação nao foi autorizada");
        }

        Transacao transacao = criarObjTransacao(dtoTransaction.amount(), pagador, destinatatio);

        atualizarCarteira(pagador, destinatatio, dtoTransaction.amount());

        salvarEntiades(transacao, pagador, destinatatio);

        notificationService.sendNotification(pagador, "Transferencia de $"+dtoTransaction.amount() + " foi feita para "+destinatatio.getNomeCompleto());
        notificationService.sendNotification(destinatatio, "Você recebeu uma transferencia de $"+dtoTransaction.amount() + " de "+pagador.getNomeCompleto());
        return transacao;
    }

    private boolean autorizarTransacao(Usuario usuario, BigDecimal valor){
        ResponseEntity<Map> responseAuthorization = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(responseAuthorization.getStatusCode() == HttpStatus.OK){
            String message = (String) responseAuthorization.getBody().get("authorization");
            return true;
        }else return false;
    }


    private Transacao criarObjTransacao(BigDecimal valorTransferencia, Usuario pagador, Usuario destinatario){
        Transacao transacao = new Transacao();
        transacao.setValorTransferencia(valorTransferencia);
        transacao.setPagador(pagador);
        transacao.setDestinatario(destinatario);
        transacao.setHora(LocalDate.now());

        return transacao;
    }

    private void atualizarCarteira(Usuario pagador, Usuario destinatario, BigDecimal valor){
        pagador.setWallet(pagador.getWallet().subtract(valor));
        destinatario.setWallet(destinatario.getWallet().add(valor));
    }

    private void salvarEntiades(Transacao transacao, Usuario pagador, Usuario destinatario){
        repository.save(transacao);
        repositoryUser.save(pagador);
        repositoryUser.save(destinatario);
    }
}
