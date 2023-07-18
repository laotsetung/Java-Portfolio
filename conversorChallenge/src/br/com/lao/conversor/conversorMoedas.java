package br.com.lao.conversor;

import javax.swing.*;

public class conversorMoedas {
    ImageIcon iconeMoedas = new ImageIcon(primeiroMenu.class.getResource("imgs/moedas.png"));

    Object[] opcoesConvMoedas = {"Reais para Dólares",
            "Reais para euros",
            "Reais para libras",
            "Reais para Pesos Argentinos",
            "Reais para Pesos Chilenos",
            "Dólares para reais",
            "Euros para reais",
            "Libras para reais",
            "Pesos Argentinos para Reais",
            "Pesos Chilenos para Reais"};

    Object escolha = JOptionPane.showInputDialog(null,
            "Escolha a moeda a converter:", "Moedas",
            JOptionPane.INFORMATION_MESSAGE, iconeMoedas,
            opcoesConvMoedas, opcoesConvMoedas[0]);

    public static double dolar = 4.88;
    public static double euros = 5.36;
    public static double libras = 6.27;
    public static double pesosArg = 0.018;
    public static double pesosChi = 0.0059;
    public static double resultado;
    public static String moeda;
    //public void converteMoeda(String conversao, double valor){

    public static double valorConverter;
    public void converteMoeda(){

        switch (this.escolha.toString()){
            case "Reais para Dólares":
                this.resultado = valorConverter / dolar;
                this.moeda = "$";
                break;
            case "Reais para euros":
                this.resultado = valorConverter / euros;
                this.moeda = "€";
                break;
            case "Reais para libras":
                this.resultado = valorConverter / libras;
                this.moeda = "£";
                break;
            case "Reais para Pesos Argentinos":
                this.resultado = valorConverter / pesosArg;
                this.moeda = "";
                break;
            case "Reais para Pesos Chilenos":
                this.resultado = valorConverter / pesosChi;
                this.moeda = "";
                break;
            case "Dólares para reais":
                this.resultado = dolar * valorConverter;
                this.moeda = "R$";
                break;
            case "Euros para reais":
                this.resultado = euros * valorConverter;
                this.moeda = "R$";
                break;
            case "Libras para reais":
                this.resultado = libras * valorConverter;
                this.moeda = "R$";
                break;
            case "Pesos Argentinos para Reais":
                this.resultado = pesosArg * valorConverter;
                this.moeda = "R$";
                break;
            case "Pesos Chilenos para Reais":
                this.resultado = pesosChi * valorConverter;
                this.moeda = "R$";
                break;
        }
    }

    public void pegaValorParaConversao(){

        boolean oValorEstaCerto = false;
        double valorInseridoMoeda = 0.0;

        while(!oValorEstaCerto){
            try{
                //pega o valor do usuario
                valorInseridoMoeda = Double.parseDouble(JOptionPane.showInputDialog("Insira um valor"));

                //verifica
                if(valorInseridoMoeda <= 0 ){
                    JOptionPane.showMessageDialog(null, "Digite um valor correto (somente numeros com ponto e maior que zero)");
                }else{
                    oValorEstaCerto=true;
                }
            }catch (Exception e){ //verifica
                JOptionPane.showMessageDialog(null, "Digite um valor correto (somente numeros com ponto e maior que zero)");
                continue;
            }
        }

        this.valorConverter = valorInseridoMoeda;
    }

    public void mostraResultado(){
        JOptionPane.showMessageDialog(null,
                "O resultado da conversão "+ this.escolha.toString() + " é " + this.moeda + String.format("%.2f",this.resultado));
    }

}
