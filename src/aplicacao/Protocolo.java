package aplicacao;

import entidade.Veiculo;

public class Protocolo {
    Servidor servidor = new Servidor(new TabelaHash(73));;

    public static Huffman huffman = new Huffman();
    public Protocolo() {
    }

    public void inserirVeiculo(Veiculo veiculo) {
        String compressao = huffman.comprimirTexto(veiculo.toString());
        servidor.inserir(compressao);
    }

    public Veiculo buscarPorPlacaouRenavam(String placarenavam) {
        String compressao = huffman.comprimirTexto(placarenavam);
        return servidor.buscarPorPlacaOuRenavam(compressao);
    }

    public void atualizarVeiculo(Veiculo veiculo) {

        String compressao = huffman.comprimirTexto(veiculo.toString());
        servidor.atualizarVeiculo(compressao);
    }

    public int contarVeiculos() {
        return servidor.contarVeiculos();
    }

    public void imprimir() {
        servidor.imprimir();
    }

    public String removerVeiculoPorRenavam(String renavam) {
        String compressao = huffman.comprimirTexto(renavam);

        return servidor.removerPorRenavam(compressao);
    }

    public float fatorDeCarga() {
        return servidor.fatorDeCarga();
    }
}