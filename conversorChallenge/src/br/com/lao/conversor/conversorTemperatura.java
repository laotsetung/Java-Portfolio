package br.com.lao.conversor;

import javax.swing.*;

public class conversorTemperatura {

    ImageIcon iconeTemperatura = new ImageIcon(primeiroMenu.class.getResource("imgs/temperatura.png"));
    Object[] opcoesConvMoedas = {"Celsius para Fahrenheit",
            "Fahrenheit para Celsius"};

    Object escolha = JOptionPane.showInputDialog(null,
            "Escolha uma conversão:", "Temperatura",
            JOptionPane.INFORMATION_MESSAGE, iconeTemperatura,
            opcoesConvMoedas, opcoesConvMoedas[0]);

    static double resultado;
    static String simbolo;

    static double valorInseridoTemp;
    public void converteTemp(){
        if(escolha.toString() == "Celsius para Fahrenheit"){
            this.resultado = (valorInseridoTemp * 9/5)+32;
            //(100 °C × 9/5) + 32 = 212 °F
            this.simbolo = "°F";
        }else if(escolha.toString() == "Fahrenheit para Celsius"){
            //(100 °F − 32) × 5/9 = 37,778 °C
            this.resultado = (valorInseridoTemp - 32) * 5/9;
            this.simbolo = "°C";
        }
    }

    public void mostraResultado(){

        if(escolha.toString() == "Celsius para Fahrenheit"){
            JOptionPane.showMessageDialog(null,
                    valorInseridoTemp + "º Celsius é igual a " + String.format("%.2f",this.resultado) + " " + this.simbolo);
        }else if(escolha.toString() == "Fahrenheit para Celsius"){
            JOptionPane.showMessageDialog(null,
                    valorInseridoTemp + "º Fahrenheit é igual a " + String.format("%.2f",this.resultado) + " " + this.simbolo);
        }
    }

    public void pegaValorParaConversao(){
        boolean oValorEstaCerto = false;
        double valorInsert = 0.0;

        while(!oValorEstaCerto){
            try{
                valorInsert = Double.parseDouble(JOptionPane.showInputDialog("Insira um valor"));

                oValorEstaCerto=true;
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Digite um valor correto (somente numeros com ponto)");
            }
        }
        valorInseridoTemp = valorInsert;
    }

}
