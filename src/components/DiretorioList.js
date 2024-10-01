import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFolder, faFile } from '@fortawesome/free-solid-svg-icons';
import './DiretorioList.css';

const DiretorioList = () => {
  const [rootDirectory, setRootDirectory] = useState(null);
  const [openDirectories, setOpenDirectories] = useState({});
  const [showMessage, setShowMessage] = useState(true);

  // Função para buscar o diretório raiz
  const fetchRootDirectory = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/diretorios/nome/Root');
      console.log("Dados recebidos: ", response.data); // Verificar a resposta
      setRootDirectory(response.data);
    } catch (error) {
      console.error("Houve um erro ao buscar o diretório Root!", error);
    }
  };

  useEffect(() => {
    fetchRootDirectory();

    const timer = setTimeout(() => {
      setShowMessage(false);
    }, 5000);

    return () => clearTimeout(timer);
  }, []);

  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws');
    const stompClient = new Client({
      webSocketFactory: () => socket,
      debug: (str) => {
        console.log(str);
      },
      onConnect: () => {
        // Inscreve-se nos tópicos de WebSocket
        stompClient.subscribe('/topic/diretorios', (message) => {
          console.log("Mensagem recebida: ", message.body); // Verificar a mensagem recebida
          fetchRootDirectory(); // Recarrega os diretórios após uma exclusão ou mudança
        });
        stompClient.subscribe('/topic/arquivos', (message) => {
          console.log("Mensagem de arquivo recebida: ", message.body); // Verificar a mensagem recebida
          fetchRootDirectory(); // Recarrega os arquivos após uma exclusão ou mudança
        });
      },
      onStompError: (error) => {
        console.error('Erro de WebSocket:', error);
      },
    });

    stompClient.activate();

    return () => {
      stompClient.deactivate();
    };
  }, []);

  // Função que alterna o estado de abertura de diretórios
  const toggleDirectory = (id) => {
    setOpenDirectories((prev) => ({
      ...prev,
      [id]: !prev[id],  // Inverte o estado aberto/fechado
    }));
  };

  // Renderiza a lista de arquivos
  const renderArquivos = (arquivos) => {
    if (!arquivos || arquivos.length === 0) return null;

    return (
      <ul className="file-list">
        {arquivos.map((arquivo) => (
          <li key={arquivo.id} className="file-item">
            <FontAwesomeIcon icon={faFile} /> {arquivo.nome} ({arquivo.extensao}, {arquivo.tamanho} bytes)
          </li>
        ))}
      </ul>
    );
  };

  // Renderiza a lista de diretórios, incluindo subdiretórios e arquivos
  const renderDiretorios = (diretorios) => {
    if (!diretorios || diretorios.length === 0) return null;

    return (
      <ul className="directory-list">
        {diretorios.map((diretorio) => (
          <li key={diretorio.id} className="directory-item">
            <span onClick={() => toggleDirectory(diretorio.id)}>
              <FontAwesomeIcon icon={faFolder} style={{ color: 'rgb(255, 153, 0)' }} /> <strong>{diretorio.nome}</strong>
            </span>
            {openDirectories[diretorio.id] && (
              <>
                {renderArquivos(diretorio.arquivos)}
                {diretorio.subDiretorios && diretorio.subDiretorios.length > 0 && renderDiretorios(diretorio.subDiretorios)}
              </>
            )}
          </li>
        ))}
      </ul>
    );
  };

  return (
    <div className="directory-container">
      <h1>Diretório Root</h1>
      {showMessage && (
        <div className="message">
          <p>Clique na pasta Root para visualizar os subdiretórios.</p>
        </div>
      )}
      {rootDirectory ? renderDiretorios([rootDirectory]) : <p>Carregando...</p>}
    </div>
  );
};

export default DiretorioList;
