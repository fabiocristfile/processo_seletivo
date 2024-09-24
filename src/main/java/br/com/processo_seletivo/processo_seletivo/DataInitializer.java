
package br.com.processo_seletivo.processo_seletivo;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.processo_seletivo.processo_seletivo.entidades.Arquivo;
import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.repositorios.ArquivoRepository;
import br.com.processo_seletivo.processo_seletivo.repositorios.DiretorioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DiretorioRepository diretorioRepository;
    private final ArquivoRepository arquivoRepository;

    public DataInitializer(DiretorioRepository diretorioRepository, ArquivoRepository arquivoRepository) {
        this.diretorioRepository = diretorioRepository;
        this.arquivoRepository = arquivoRepository;
    }

    @Override
    public void run(String... args) {
        // Criação do diretório raiz
        Diretorio root = new Diretorio(null, "Root", new ArrayList<>(), new ArrayList<>(), null);
        diretorioRepository.save(root); // Salva o diretório raiz antes de criar os subdiretórios

        // Criação de Diretório 1 e seus arquivos
        Diretorio dir1 = new Diretorio(null, "Diretorio1", new ArrayList<>(), new ArrayList<>(), root);
        diretorioRepository.save(dir1);
        
        for (int j = 1; j <= 2; j++) {
            Arquivo arquivo = new Arquivo(null, "Arquivo" + j + ".txt", "txt", 1024L * j, dir1);
            arquivoRepository.save(arquivo);
            dir1.getArquivos().add(arquivo);
        }
        
        root.getSubDiretorios().add(dir1);

        // Criação de Diretório 2 e seu subdiretório
        Diretorio dir2 = new Diretorio(null, "Diretorio2", new ArrayList<>(), new ArrayList<>(), root);
        diretorioRepository.save(dir2);

        // Criação do SubDiretorio1 dentro do Diretorio2
        Diretorio subDir1 = new Diretorio(null, "SubDiretorio1", new ArrayList<>(), new ArrayList<>(), dir2);
        diretorioRepository.save(subDir1);

        // Adicionar Arquivo3 ao SubDiretorio1
        Arquivo arquivo3 = new Arquivo(null, "Arquivo3.txt", "txt", 1024L * 3, subDir1);
        arquivoRepository.save(arquivo3);
        subDir1.getArquivos().add(arquivo3);
        
        dir2.getSubDiretorios().add(subDir1);
        root.getSubDiretorios().add(dir2);

        // Salvar as alterações no diretório raiz
        diretorioRepository.save(root);
    }
}