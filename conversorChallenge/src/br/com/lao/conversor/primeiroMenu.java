package br.com.lao.conversor;

import javax.swing.*;

public class primeiroMenu {

    ImageIcon iconePrincipal = new ImageIcon(primeiroMenu.class.getResource("imgs/one_80.png"));

    //ImageIcon iconePrincipal = createImageIcon
    Object[] opcoes = {"Conversor de moedas", "Conversor de Temperatura"};

    Object conversor = JOptionPane.showInputDialog(null,
            "Escolha uma opção: ", "Menu",
            JOptionPane.INFORMATION_MESSAGE, iconePrincipal,
            opcoes, opcoes[0]);


}
