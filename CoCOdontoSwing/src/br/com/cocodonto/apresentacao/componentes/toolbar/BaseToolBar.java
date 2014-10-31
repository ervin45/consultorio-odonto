/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cocodonto.apresentacao.componentes.toolbar;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 *
 * @author felipewom
 */
public class BaseToolBar extends JToolBar{
    private Map<String, JButton> buttons;
    private ActionListener listener;
    
    public BaseToolBar() {
        super();
        buttons = new HashMap<String, JButton>();
    }

    public BaseToolBar(ActionListener listener) {
        this();
        this.listener = listener;
        loadDefaultButtons();
    }
    
    public Map<String, JButton> getButtons() {
        return buttons;
    }
    
    public BaseToolBar addButton(String text, String iconPath){
        JButton button = buildButton(text, iconPath);
        buttons.put(text.toLowerCase(), button);
        add(button);
        return this;
    }
    
    private JButton buildButton(String text, String iconPath){
        JButton button = new JButton();
        button.setText(text);
        button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.addActionListener(listener);
        return button;
    }
    
    protected void loadDefaultButtons(){
        this.addButton("Incluir", "/br/com/cocodonto/apresentacao/images/add16.png")
            .addButton("Alterar", "/br/com/cocodonto/apresentacao/images/edit16.png")
            .addButton("Excluir", "/br/com/cocodonto/apresentacao/images/remove16.png")
            .addButton("Salvar", "/br/com/cocodonto/apresentacao/images/save16.png")
            .addButton("Cancelar", "/br/com/cocodonto/apresentacao/images/cancel16.png");
    }
}
