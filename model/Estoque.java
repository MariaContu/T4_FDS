package model;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

    public Estoque() {
        this.produtos = new ArrayList<>();

        produtos.add(new Produto(1, "Camiseta", 19.99, 100));
        produtos.add(new Produto(2, "Calça Jeans", 49.99, 50));
        produtos.add(new Produto(3, "Tênis", 79.99, 30));
        produtos.add(new Produto(4, "Boné", 9.99, 80));
        produtos.add(new Produto(5, "Short", 19.90, 15));

    }

    public List<Produto> todosOsProdutos()   {
        return produtos;
    }

    // Método para registrar entrada no estoque
    public void registrarEntradaEstoque(int codigo, int quantidade) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.setEstoque(produto.getEstoque() + quantidade);
        } else {
            // Se o produto não existir, criar uma nova entrada
            Produto novoProduto = new Produto(codigo, "", 0, quantidade);
            produtos.add(novoProduto);
        }
    }

    // Método para listar produtos com estoque abaixo do mínimo
    public List<Produto> produtosComEstoqueAbaixoDoMinimo(int estoqueMinimo) {
        List<Produto> produtosAbaixoDoMinimo = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getEstoque() < estoqueMinimo) {
                produtosAbaixoDoMinimo.add(produto);
            }
        }
        return produtosAbaixoDoMinimo;
    }

    // Método para buscar um produto pelo código
    public Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null; // Produto não encontrado
    }
}
