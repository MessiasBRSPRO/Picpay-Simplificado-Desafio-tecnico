package br.com.picpay_simplificado.Controllers;

import br.com.picpay_simplificado.Entidades.Usuario;
import br.com.picpay_simplificado.Servicos.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/cadastrarusuario")
    public ResponseEntity<Usuario> cadastroDeUsuarios(@RequestBody @Valid Usuario usuario){
        Usuario registrado = userService.cadastrarUsuario(usuario);
        return ResponseEntity.ok().body(registrado);
    }

}
