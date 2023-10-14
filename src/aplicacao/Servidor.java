package aplicacao;

import entidade.Veiculo;

public class Servidor {
    private TabelaHash tabela;

    public Servidor(TabelaHash oi) {
        tabela = oi;
    }

    public void inserir(String comprimida) {
        String descompressao = Protocolo.huffman.descomprimirTexto(comprimida);
        String[] dados = descompressao.split("#");
        Veiculo veiculo = new Veiculo(dados[1], dados[0], dados[2], dados[3], dados[4], Integer.parseInt(dados[5]));
        tabela.inserir(veiculo);
    }

    public Veiculo buscarPorPlacaOuRenavam(String compressao){
        String descompressao = Protocolo.huffman.descomprimirTexto(compressao);
        return tabela.buscarPorPlacaOuRenavam(descompressao);
    }

    public void atualizarVeiculo(String comprimida) {
        String descompressao = Protocolo.huffman.descomprimirTexto(comprimida);
        String[] dados = descompressao.split("#");
        Veiculo veiculo = new Veiculo(dados[1], dados[0], dados[2], dados[3], dados[4], Integer.parseInt(dados[5]));
        tabela.atualizarVeiculo(veiculo);
    }

    public String removerPorRenavam(String compressao) {
        String descompressao = Protocolo.huffman.descomprimirTexto(compressao);
        return tabela.removerPorRenavam(descompressao);
    }

    public int contarVeiculos() {
        return tabela.contarVeiculos();
    }

    public void imprimir() {
        tabela.imprimirVeiculos();
    }

    public float fatorDeCarga() {
        return tabela.fatorDeCarga();
    }
}
