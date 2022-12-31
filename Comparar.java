package src;

import java.util.Comparator;

import src.Registro.Produto;

public class Comparar implements Comparator<Produto>{

    @Override
    public int compare(Produto p1, Produto p2){
        return p1.getNome().compareTo(p2.getNome());
    }
    
}
