package view;

import controller.LojaController;
import model.Estoque;
import model.Produto;
import java.util.Scanner;

public class LojaUI {
    private LojaController controller;
    private Scanner scanner;

    public LojaUI() {
        this.controller = new LojaController(new Estoque());
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("=== Loja Online ===");
            System.out.println("1. Efetuar Venda");
            System.out.println("2. Listar Produtos Disponíveis");
            System.out.println("3. Registrar Entrada no Estoque");
            System.out.println("4. Produtos com Estoque Abaixo do Mínimo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número

            switch (opcao) {
                case 1:
                    efetuarVenda();
                    break;
                case 2:
                    listarProdutosDisponiveis();
                    break;
                case 3:
                    registrarEntradaEstoque();
                    break;
                case 4:
                    listarProdutosAbaixoDoMinimo();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private void efetuarVenda() {
        System.out.print("Digite o código do produto: ");
        int codigoProduto = scanner.nextInt();
        System.out.print("Digite a quantidade desejada: ");
        int quantidade = scanner.nextInt();

        double custoVenda = controller.efetuarVenda(codigoProduto, quantidade);

        System.out.println("Custo da venda: R$" + custoVenda);
        
    }

    private void listarProdutosDisponiveis() {
        System.out.println("Produtos Disponíveis:");
        for (Produto produto : controller.listarProdutosDisponiveis()) {
            System.out.println(produto.getCodigo() + " - " + produto.getDescricao() + " - R$" + produto.getPreco() + " - Estoque: " + produto.getEstoque());
        }
    }

    private void registrarEntradaEstoque() {
        System.out.print("Digite o código do produto: ");
        int codigoProduto = scanner.nextInt();
        System.out.print("Digite a quantidade a ser adicionada ao estoque: ");
        int quantidade = scanner.nextInt();

        controller.registrarEntradaEstoque(codigoProduto, quantidade);
        System.out.println("Entrada no estoque registrada com sucesso.");
    }

    private void listarProdutosAbaixoDoMinimo() {
        System.out.println("Produtos com Estoque Abaixo do Mínimo:");
        for (Produto produto : controller.produtosComEstoqueAbaixoDoMinimo(10)) {
            System.out.println(produto.getCodigo() + " - " + produto.getDescricao() + " - Estoque: " + produto.getEstoque());
        }
    }

    public static void main(String[] args) {
        LojaUI lojaOnlineUI = new LojaUI();
        lojaOnlineUI.iniciar();
    }
}
