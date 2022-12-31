package src.Registro;

import java.time.LocalDate;

public class Venda {
    public LocalDate datavenda;
    public Produto produtovendido;
    public int quantvendida;
    
    public LocalDate getDatavenda() {
        return datavenda;
    }
    public void setDatavenda(LocalDate datavenda) {
        this.datavenda = datavenda;
    }
    public Produto getProdutovendido() {
        return produtovendido;
    }
    public void setProdutovendido(Produto produtovendido) {
        this.produtovendido = produtovendido;
    }
    public int getQuantvendida() {
        return quantvendida;
    }
    public void setQuantvendida(int quantvendida) {
        this.quantvendida = quantvendida;
    }


}
