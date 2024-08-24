package br.com.picpay_simplificado.Servicos;

import br.com.picpay_simplificado.Entidades.TipoUsuario;
import br.com.picpay_simplificado.Entidades.Usuario;
import br.com.picpay_simplificado.repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Usuario cadastrarUsuario(Usuario user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("The email already exists");
        }if(userRepository.existsByCpfCnpj(user.getCpfCnpj())){
            throw new RuntimeException("The cpf/cnpj already exists");
        }
        return userRepository.save(user);
    }

    public void validarTransacao(Usuario usuario, BigDecimal valor){
        if(usuario.getTipoUsuario() == TipoUsuario.LOJISTA){
            throw new RuntimeException("Lojistas nao podem realizar transferencias, so receber!");
        }

        if(usuario.getWallet().compareTo(valor) < 0){
            throw new RuntimeException("Saldo insuficiente");
        }
    }

    public Usuario getUsuarioById(long id){
        return userRepository.getReferenceById(id);
    }

}
