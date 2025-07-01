package ControleGastos.ControleGastos.Domain.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
  @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
  private Long id;

  @Column(name = "NOME", nullable = false)
  private String nome;

  @Column(name = "EMAIL", nullable = false, unique = true)
  private String email;


}
