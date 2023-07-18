package br.com.lao.conversor;

import javax.swing.*;

public class executarNovamente {
    Object[] options = {"Sim",
            "Não"};
    int n = JOptionPane.showOptionDialog(null,
            "Você deseja realizar outra conversão?",
            "Recomeçar?",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]);

    int getResposta(){
        return this.n;
    }
}
