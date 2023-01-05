package miniautorizador.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@Table(name = "cartao")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoEntity {
	
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", unique = true, nullable = false )
    private Long id;

    @Column(name = "numero_cartao", nullable = false)
    @NotBlank( message = "Número do cartão é obrigatório" )
    private String numeroCartao;

    @Column(name = "senha",  nullable = false)
    @NotBlank( message = "Senha é obrigatória" )
    private String senha;
    
    @OneToOne
    @JoinColumn(name = "id_saldo", nullable = false)
    private SaldoEntity saldoEntity;

  
}