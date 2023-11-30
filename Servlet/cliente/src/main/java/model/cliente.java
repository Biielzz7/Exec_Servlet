package model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class cliente {
private String cpf;
private String nome;
private String email;
private Float limite_credito;
private Date data_nasc;
}
