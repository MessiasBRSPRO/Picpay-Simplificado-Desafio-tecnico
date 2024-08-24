package br.com.picpay_simplificado.repositorios;

import br.com.picpay_simplificado.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    boolean existsByCpfCnpj(String cpf);
}
