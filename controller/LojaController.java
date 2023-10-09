package controller;

import model.Estoque;
import model.Produto;
import java.util.List;

public class LojaController {
    private Estoque estoque;

    public LojaController(Estoque estoque) {
        this.estoque = estoque;
    }

    public double efetuarVenda(int codigoProduto, int quantidade) {
        Produto produto = estoque.buscarProdutoPorCodigo(codigoProduto);

        if (produto != null && produto.getEstoque() >= quantidade) {
            double valorTotal = produto.getPreco() * quantidade;

            // Aplicar desconto de 10% para quantidades maiores que 10
            if (quantidade > 10) {
                valorTotal *= 0.9;
            }

            // Aplicar imposto de 5%
            valorTotal *= 1.05;

            // Dar baixa no estoque
            produto.setEstoque(produto.getEstoque() - quantidade);

            return valorTotal;
        } else {
            return -1; // Venda não é possível devido a estoque insuficiente ou produto não encontrado
        }
    }

    public List<Produto> listarProdutosDisponiveis() {
        return estoque.todosOsProdutos(); 
    }

    public void registrarEntradaEstoque(int codigoProduto, int quantidade) {
        estoque.registrarEntradaEstoque(codigoProduto, quantidade);
    }

    public List<Produto> produtosComEstoqueAbaixoDoMinimo(int estoqueMinimo) {
        return estoque.produtosComEstoqueAbaixoDoMinimo(estoqueMinimo);
    }
}
