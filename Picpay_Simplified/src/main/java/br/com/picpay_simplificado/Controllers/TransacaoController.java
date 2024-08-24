package br.com.picpay_simplificado.Controllers;

import br.com.picpay_simplificado.DTOS.DTOTransaction;
import br.com.picpay_simplificado.Entidades.Transacao;
import br.com.picpay_simplificado.Servicos.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @RequestMapping("/criartransacao")
    public ResponseEntity<Transacao> criarTransacao(@RequestBody DTOTransaction transacao){
        Transacao created = transacaoService.criarTransacao(transacao);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
