package br.com.processo_seletivo.processo_seletivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.processo_seletivo.processo_seletivo.entidades.Arquivo;
import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.repositorios.DiretorioRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {

    @Autowired
    private DiretorioRepository diretorioRepository;

    @PostConstruct
    public void init() {
        if (!diretorioRepository.findByNome("Root").isPresent()) {
            Diretorio root = new Diretorio();
            root.setNome("Root");

            Diretorio diretorio1 = new Diretorio();
            diretorio1.setNome("Diretorio1");
            diretorio1.setDiretorioPai(root);

            Diretorio diretorio2 = new Diretorio();
            diretorio2.setNome("Diretorio2");
            diretorio2.setDiretorioPai(root);

            Diretorio subDiretorio1 = new Diretorio();
            subDiretorio1.setNome("SubDiretorio1");
            subDiretorio1.setDiretorioPai(diretorio2);

            // Adicionando os arquivos
            Arquivo arquivo1 = new Arquivo();
            arquivo1.setNome("Arquivo1.txt");
            arquivo1.setExtensao("txt");
            arquivo1.setTamanho(1024l);
            arquivo1.setDiretorio(diretorio1);

            Arquivo arquivo2 = new Arquivo();
            arquivo2.setNome("Arquivo2.txt");
            arquivo2.setExtensao("txt");
            arquivo2.setTamanho(2048l);
            arquivo2.setDiretorio(diretorio1);

            Arquivo arquivo3 = new Arquivo();
            arquivo3.setNome("Arquivo3.txt");
            arquivo3.setExtensao("txt");
            arquivo3.setTamanho(3072l);
            arquivo3.setDiretorio(subDiretorio1);

            // Relacionando os diretórios e arquivos
            root.getSubDiretorios().add(diretorio1);
            root.getSubDiretorios().add(diretorio2);
            diretorio1.getArquivos().add(arquivo1);
            diretorio1.getArquivos().add(arquivo2);
            diretorio2.getSubDiretorios().add(subDiretorio1);
            subDiretorio1.getArquivos().add(arquivo3);

            // Salvando no repositório
            diretorioRepository.save(root);
        }
    }
}