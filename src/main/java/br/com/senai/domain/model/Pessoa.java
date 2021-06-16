package br.com.senai.domain.model;

import br.com.senai.domain.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.asm.Advice;

import static lombok.AccessLevel.PRIVATE;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = PRIVATE)
public class Pessoa {

    @NotNull(groups = ValidationGroups.ClienteId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 60)
    String nome;

    @NotBlank
    @Email
    @Size(min = 5)
    String email;

    @NotBlank
    @Size(min = 13)
    String telefone;

}
