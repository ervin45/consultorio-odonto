package br.com.cocodonto.modelo.service;

import java.util.List;

import br.com.cocodonto.modelo.dao.PacienteDao;
import br.com.cocodonto.modelo.entidade.Paciente;

public class PacienteService {
	
	private final PacienteDao dao;
	
	public PacienteService() {
		dao = new PacienteDao();
	}

	public void salvar ( Paciente paciente ) {
            if (paciente.getId() != 0 ) {
                    dao.atualizar(paciente);
            } else {
                    dao.inserir(paciente);
            }
	}
        
        public void delete ( Paciente paciente ) {
            dao.delete(paciente);
	}
	
	public List<Paciente> getPacientes() {
            return dao.listaTodosPacientes();
	}
	
}
