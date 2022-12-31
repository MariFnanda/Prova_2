package src.Registro;

import java.util.InputMismatchException;

public class Produto{
    private String  nome;
    private int valor, codigo, quant;

    
    public Produto(int codigo2, String nome2, int valor2, int quant2) {
    }

    public Produto() {
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        if(valor <= 0){
        throw new InputMismatchException("O valor deve ser um número maior que 0!");
        }
        this.valor = valor;
        
    }
    public int getQuant() {
        return quant;
    }
    public void setQuant(int quant) {
        this.quant = quant;
    }


    @Override
    public String toString() {
        return String.format("%d", getQuant());
    }


    
}