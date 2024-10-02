package br.com.processo_seletivo.processo_seletivo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diretorio implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonManagedReference
    @OneToMany(mappedBy = "diretorio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arquivo> arquivos = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "diretorioPai", cascade = CascadeType.ALL,  orphanRemoval = true) 
    private List<Diretorio> subDiretorios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "diretorio_pai_id")
    @JsonBackReference
    private Diretorio diretorioPai;
    
}
