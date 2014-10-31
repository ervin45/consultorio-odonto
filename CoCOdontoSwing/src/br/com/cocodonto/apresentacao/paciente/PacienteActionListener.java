/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cocodonto.apresentacao.paciente;

import br.com.cocodonto.modelo.entidade.Contato;
import br.com.cocodonto.modelo.entidade.Endereco;
import br.com.cocodonto.modelo.entidade.Paciente;
import br.com.cocodonto.modelo.entidade.SexoType;
import br.com.cocodonto.modelo.service.PacienteService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author felipewom
 */
public final class PacienteActionListener implements ActionListener
                                                    , ListSelectionListener{
    
    private final PacienteForm form;
    private final PacienteService service;
    private PacienteTableModel tableModel;
    
    public PacienteActionListener(PacienteForm form) {
        this.form = form;
        service = new PacienteService();
        inicializaTableModel();
        disableButtonsToSave();
        adicionaListener();
    }
    
    public void adicionaListener(){
        form.getBtIncluir().addActionListener(this);
        form.getBtAlterar().addActionListener(this);
        form.getBtExcluir().addActionListener(this);
        form.getBtSalvar().addActionListener(this);
        form.getBtCancelar().addActionListener(this);
    }
    
    public void inicializaTableModel(){
        tableModel = new PacienteTableModel(service.getPacientes());
        form.getTbPacientes().setModel(tableModel);
        form.getTbPacientes()
                .getSelectionModel()
                .addListSelectionListener(this);
    }
    
    private void enableButtonsToSave(){
        enableOrDisableButtonsToSave(true);
    }
    
    private void disableButtonsToSave(){
        enableOrDisableButtonsToSave(false);
    }
    
    private void enableOrDisableButtonsToSave(boolean enabled){
        form.getBtIncluir().setEnabled(!enabled);
        form.getBtAlterar().setEnabled(!enabled);
        form.getBtExcluir().setEnabled(!enabled);
        form.getBtSalvar().setEnabled(enabled);
        form.getBtCancelar().setEnabled(enabled);
    }
    
    private Paciente formToPaciente(){
        Paciente paciente = new Paciente();
        if(!"".equals(form.getLblIdPaciente().getText())){
            paciente.setId(Long.parseLong(form.getLblIdPaciente().getText()));
        }
        if(form.getCmbGenero().getSelectedItem().toString().equals(SexoType.M.getDescricao())){
            paciente.setSexo(SexoType.M);
        }else{
            paciente.setSexo(SexoType.F);
        }
        paciente.setNome(form.getTxtNome().getText());
        paciente.setCpf(form.getTxtCpf().getText());
        paciente.setRg(form.getTxtRg().getText());
        paciente.setContato(formToContato());
        paciente.setEndereco(formToEndereco());
        return paciente;
    }
    
    private void pacienteToForm(Paciente paciente){
        form.getLblIdPaciente().setText(Long.toString(paciente.getId()));
        form.getTxtNome().setText(paciente.getNome());
        form.getTxtRg().setText(paciente.getRg());
        form.getTxtCpf().setText(paciente.getCpf());
        form.getCmbGenero().setSelectedItem(paciente.getSexo().getDescricao());
    }
    
    private Contato formToContato(){
        Contato contato = new Contato();
        if(!"".equals(form.getLblIdContato().getText())){
            contato.setId(Long.parseLong(form.getLblIdContato().getText()));
        }
        contato.setEmail(form.getTxtEmail().getText());
        contato.setTelefone(form.getTxtTelefone().getText());
        contato.setCelular(form.getTxtCelular().getText());
        contato.setFax(form.getTxtTelefone().getText());
        return contato;
    }
    
     private Endereco formToEndereco(){
        Endereco endereco = new Endereco();
        if(!"".equals(form.getLblIdEndereco().getText())){
            endereco.setId(Long.parseLong(form.getLblIdEndereco().getText()));
        }
        endereco.setEndereco(form.getTxtLogradouro().getText());
        endereco.setBairro(form.getTxtBairro().getText());
        endereco.setCidade(form.getTxtCidade().getText());
        endereco.setCep(form.getTxtCep().getText());
        return endereco;
    }
    
    private void inserir(){
        enableButtonsToSave();
    }
    
    private void salvar(){
        service.salvar(formToPaciente());
        JOptionPane.showMessageDialog(form, "Paciente salvo com sucesso!"
                ,"Sucesso",JOptionPane.INFORMATION_MESSAGE);
        disableButtonsToSave();
    }
    
    private void excluir(){
        
    }
    
    private void alterar(){
        enableButtonsToSave();
    }
    
    private void cancelar(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Incluir")){
            inserir();
        }else if(e.getActionCommand().equals("Alterar")){
            alterar();
        }else if(e.getActionCommand().equals("Excluir")){
            excluir();
        }else if(e.getActionCommand().equals("Salvar")){
            salvar();
        }else if(e.getActionCommand().equals("Cancelar")){
            cancelar();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Paciente paciente = tableModel.getPacientes().get(form.getTbPacientes().getSelectedRow());
        pacienteToForm(paciente);
        //System.out.println(e);
    }
}
