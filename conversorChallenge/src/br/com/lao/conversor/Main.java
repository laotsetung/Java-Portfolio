package br.com.lao.conversor;

import javax.swing.*;

public class Main {
    static primeiroMenu menuPrincipal;
    static conversorMoedas convMoedas;
    static conversorTemperatura convTemp;
    static executarNovamente recomecar;

    public static void main(String[] args) {

        boolean rodandoApp = true;

        while (rodandoApp) {

            //abre menu para escolher qual conversor o usuário deseja
            menuPrincipal = new primeiroMenu();

            if (menuPrincipal.conversor == null) { //Se clicou em CANCEL -> Sair
                JOptionPane.showMessageDialog(null, "Fim do Programa", "Tchau!", JOptionPane.INFORMATION_MESSAGE);
                break;
            }else if (menuPrincipal.conversor == "Conversor de moedas") { //SE escolher conversor moedas

                //abre menu para escolher qual conversão de moeda o usuário deseja
                convMoedas = new conversorMoedas();

                //abre o menu para o usuário digitar o valor da moeda
                convMoedas.pegaValorParaConversao();

                //Converte o valor
                convMoedas.converteMoeda();

                //mostra o resultado da conversão das moedas
                convMoedas.mostraResultado();

            }else if (menuPrincipal.conversor == "Conversor de Temperatura"){ //SE escolher conversor de temperatura

                //abre o menu para escolher o tipo de conversão de temperatura
                convTemp = new conversorTemperatura();

                //abre o menu para o usuário digitar o valor da temperatura
                convTemp.pegaValorParaConversao();

                //converte a temperatura
                convTemp.converteTemp();

                //mostra o resultado da conversão
                convTemp.mostraResultado();
            }

            //recomecar?
            recomecar = new executarNovamente();

            if (recomecar.getResposta() == 0) { //recomecar
                //zerar as classes dos dialogos.
                menuPrincipal = null;
                convMoedas=null;
                convTemp=null;
            } else { //terminar
                JOptionPane.showMessageDialog(null, "Fim do Programa", "Tchau!", JOptionPane.INFORMATION_MESSAGE);
                rodandoApp = false;
            }
        }
    }
}