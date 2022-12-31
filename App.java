package src;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import src.Registro.Produto;
import src.Registro.Venda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;

public class App{

    public static void main(String[] args) throws Exception{
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Venda> vendas = new ArrayList<>();
        int opcao;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n======\nMenu\n======");
            System.out.println("1- Incluir produto ");
            System.out.println("2- Consultar produto ");
            System.out.println("3- Listagem de produtos ");
            System.out.println("4- Vendas por período - detalhado");
            System.out.println("5- Realizar venda ");
            System.out.println("0- Sair");

            opcao = in.nextInt();
            in.nextLine();

            if (opcao == 1){
                
                Produto produto = new Produto();
                System.out.println("\nPara incluir um produto, preencha a seguir: ");
                
                
                try {
                System.out.println("\nCódigo do produto: ");
                produto.setCodigo(in.nextInt());                                    
                } catch (InputMismatchException d) {
                System.out.println("O código deve ser de números inteiros!");
                voltarMenu(in);
                continue;
                } 
                 
                boolean verif = false;

                for (Produto p : produtos) {
                    if(produto.getCodigo() == p.getCodigo()){
                    verif = true;
                    }
                }

                if(verif != false){
                    System.out.println("\nEsse código já existe, tente novamente!");
                    voltarMenu(in);
                    continue;
                }

                in.nextLine(); 
                           
                System.out.println("\nNome do produto: ");
                produto.setNome(in.nextLine());                

                try {
                System.out.println("\nValor: ");
                produto.setValor(in.nextInt());
                } catch (InputMismatchException d) {
                System.out.println("O valor deve ser um número inteiro maior que 0!");
                voltarMenu(in);
                continue;
                }

                try {                   
                System.out.println("\nQuantidade em estoque: ");
                produto.setQuant(in.nextInt());
                }
                catch (InputMismatchException d) {
                    System.out.println("A quantidade deve ser um número inteiro!");
                    voltarMenu(in);
                    continue;
                }

                produtos.add(produto);
                
                System.out.println("\nProduto incluido com sucesso ");
                voltarMenu(in);
                continue;                    
                
                

            } 

            else if (opcao == 2){
                if(produtos.size() == 0){
                    System.out.println("\nNão há serem buscados, por favor tente novamente.");
                } else{
                
                System.out.println("\nDigite o código do produto: ");
                int c;
                c = in.nextInt();
                in.nextLine();

                for (Produto produto : produtos) {
                    if(c == produto.getCodigo()){
                        System.out.println("\nProduto encontrado: ");
                        System.out.println(produto);
                    } else{
                        System.out.println("\nProduto não encontrado, tente novamente.");
                    }
                    
                }

            }
                voltarMenu(in);
                continue;
            }

            else if (opcao == 3){
                if(produtos.size() == 0){
                    System.out.println("\nNão há produtos cadastrados, por favor tente novamente.");
                } else{

                produtos.sort(new Comparar());
                System.out.println("\n== Produtos registrados ==\n");
                for (Produto p : produtos){
                    System.out.printf("\nCódigo: %d\nNome: %s\nValor Unitário: %d\nQuantidade em estoque: %d\n", p.getCodigo(), p.getNome(), p.getValor(), p.getQuant());
                }

                Double media = produtos.stream().filter(p -> p instanceof Produto)
                .collect(Collectors.averagingInt(Produto::getQuant));

                System.out.println("\nA média de quantidade em estoque é: " + media);

                Produto max = produtos.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Produto::getValor))).get();

                System.out.println("\nO valor máximo de produtos em estoque é: " + max);

                Produto min = produtos.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Produto::getValor))).get();

                System.out.println("\nE o valor mínimo de produtos em estoque é: " + min);

                voltarMenu(in);
                continue;
            
            }
        }

            else if (opcao == 4){
                if(produtos.size() == 0){
                    System.out.println("\nNão há produtos cadastrados, por favor tente novamente.");
                } else{
                System.out.println("\nDigite o período que deseja buscar por: ");
                String databusca = in.nextLine();
                LocalDate ld = LocalDate.parse(databusca, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Venda buscado2 = busca2(vendas, ld);

                if(busca2(vendas, ld) != null){
                    System.out.println("\nProduto(s) encontrados: ");
                    System.out.println(buscado2);
                } else {
                    System.out.println("\nNão parece que houve vendas nesse período, tente novamente! ");
                    voltarMenu(in);
                    continue;
                }
                
                }
                voltarMenu(in);
                continue;
            }

            else if (opcao == 5){
                if(produtos.size() == 0){
                    System.out.println("\nNão há produtos na lista, por favor tente novamente.");
                }else{
                    
                Venda v = new Venda();

                System.out.println("\nDigite o código do produto a vender: ");
                int code = in.nextInt();
                Produto buscado = busca(produtos, code);
                
                if(busca(produtos, code) != null){
                    System.out.println("\nProduto encontrado!");
                    v.setProdutovendido(buscado);
                    System.out.println(buscado.getNome());
                } else {
                    System.out.println("\nEsse produto não foi encontrado, tente novamente!");
                    voltarMenu(in);
                    continue;
                }
                in.nextLine();

                System.out.println("\nQuantidade desejada?: ");
                int quant2 = in.nextInt();
                if(quant2 <= 0){
                    System.out.println("\nQuantidade invalida!\nVenda interrompida! Tente novamente.");
                    voltarMenu(in);
                    continue;
                }
                if(quant2 > buscado.getQuant()){
                    System.out.println("\nA quantidade desejada é menor que a quantidade em estoque, tente novamente!");
                    voltarMenu(in);
                    continue;
                }
                v.setQuantvendida(quant2);
                in.nextLine();

                System.out.println("Digite a data: ");
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String datav = in.nextLine();
                v.setDatavenda(LocalDate.parse(datav, f));

                
                v.setProdutovendido(buscado);
                v.setQuantvendida(quant2);
                vendas.add(v);
                System.out.println("\nVenda finalizada!");

                //fazer baixa do estoque
                }
                voltarMenu(in);
                continue;
            }

            else if(opcao != 0){
                System.out.println("\nOpção inválida!");
                voltarMenu(in);
                continue;
            }            

    } while (opcao != 0);
    System.out.println("\nFim do programa! :D!");
    in.close();
}

private static void voltarMenu(Scanner in) throws InterruptedException, IOException{
    System.out.println("\nPressione ENTER para voltar ao menu.");
    in.nextLine();

}

public static Venda busca2(List<Venda> vendas, LocalDate databusca){
    for(Venda venda : vendas){
        if(databusca == venda.getDatavenda()){
            return venda;
        }
    }
    return null;
}

public static Produto busca(List<Produto> produtos, int codigo){
    for (Produto produto : produtos){
        if(codigo == produto.getCodigo()){
            return produto;
        }
    }
    return null;
}
}