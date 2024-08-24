package br.com.picpay_simplificado.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@EqualsAndHashCode(of = {"cpf_cnpj", "email"})
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @NotBlank
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    private BigDecimal wallet = new BigDecimal(0);

}
