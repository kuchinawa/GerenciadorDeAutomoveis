package aplicacao;

import entidade.Veiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabelaHash{
    private No<Veiculo>[] tabela;
    private int tamanho;
    private BufferedWriter logWriter;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new No[tamanho];

        try {
            logWriter = new BufferedWriter(new FileWriter("logEE.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calcularHash(String chave) {
        return Integer.parseInt(chave) % tamanho;
    }

    public void inserir(Veiculo veiculo) {
        String renavam = veiculo.getRenavam();
        int hash = calcularHash(renavam);

        if (tabela[hash] == null) {
            tabela[hash] = new No<>(renavam, veiculo);
        } else {
            No<Veiculo> atual = tabela[hash];

            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }

            atual.setProximo(new No<>(renavam, veiculo));
        }

        registrarLog("Inserção - Renavam: " + renavam + ", Índice: " + hash + ", Fator de Carga: " + fatorDeCarga());
    }


    public void atualizarVeiculo(Veiculo veiculo) {
        String renavam = veiculo.getRenavam();
        int hash = calcularHash(renavam);

        if (tabela[hash] == null) {
            return;
        }

        No<Veiculo> anterior = null;
        No<Veiculo> atual = tabela[hash];

        while (atual != null) {
            if (atual.getChave().equals(renavam)) {
                atual.setValor(veiculo);
                acessarElemento(renavam);
                registrarLog("Atualização - Renavam: " + renavam + ", Índice: " + hash + ", Fator de Carga: " + fatorDeCarga());
                return;
            }

            anterior = atual;
            atual = atual.getProximo();
        }
    }


    public String removerPorRenavam(String renavam) {
        int hash = calcularHash(renavam);

        if (tabela[hash] == null) {
            return "Veículo não encontrado.";
        }

        No<Veiculo> anterior = null;
        No<Veiculo> atual = tabela[hash];

        while (atual != null) {
            if (atual.getChave().equals(renavam)) {
                if (anterior == null) {
                    tabela[hash] = atual.getProximo();
                } else {
                    anterior.setProximo(atual.getProximo());
                }

                registrarLog("Remoção - Renavam: " + renavam + ", Índice: " + hash + ", Fator de Carga: " + fatorDeCarga());
                return "Veículo removido com sucesso.";
            }
            anterior = atual;
            atual = atual.getProximo();
        }

        return "Veículo não encontrado.";
    }

    public int contarVeiculos() {
        int total = 0;

        for (int i = 0; i < tamanho; i++) {
            No<Veiculo> atual = tabela[i];

            while (atual != null) {
                total++;
                atual = atual.getProximo();
            }
        }

        return total;
    }

    public void imprimirHash() {
        for (int i = 0; i < tamanho; i++) {
            No<Veiculo> atual = tabela[i];
            List<Veiculo> veiculos = new ArrayList<>();

            while (atual != null) {
                veiculos.add(atual.getValor());
                atual = atual.getProximo();
            }

            if (!veiculos.isEmpty()) {
                System.out.print("Posição " + i + ": ");

                for (int j = 0; j < veiculos.size(); j++) {
                    System.out.print(veiculos.get(j).getRenavam());

                    if (j < veiculos.size() - 1) {
                        System.out.print(" --> ");
                    }
                }

                System.out.println();
            }

            int colisoes = veiculos.size() - 1;

            if (colisoes > 0) {
                System.out.println("Total de colisões nesta posição: " + colisoes);
            }
        }
    }
    public void acessarElemento(String chave) {
        int hash = calcularHash(chave);

        if (tabela[hash] == null) {
            return;
        }

        No<Veiculo> atual = tabela[hash];
        No<Veiculo> anterior = null;

        while (atual != null) {
            if (atual.getChave().equals(chave)) {

                if (anterior != null) {
                    anterior.setProximo(atual.getProximo());
                    atual.setProximo(tabela[hash]);
                    tabela[hash] = atual;
                }
                return;
            }

            anterior = atual;
            atual = atual.getProximo();
        }
    }
    public void imprimirVeiculos() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Índice");
        tableModel.addColumn("Renavam");
        tableModel.addColumn("Placa");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Modelo");
        tableModel.addColumn("Ano");

        for (int i = 0; i < tamanho; i++) {
            No<Veiculo> atual = tabela[i];

            while (atual != null) {
                List<Object> rowData = new ArrayList<>();
                rowData.add(i);
                rowData.add(atual.getValor().getRenavam());
                rowData.add(atual.getValor().getPlaca());
                rowData.add(atual.getValor().getCondutor().getNome());
                rowData.add(atual.getValor().getCondutor().getCpf());
                rowData.add(atual.getValor().getModelo());
                rowData.add(atual.getValor().getAno());

                tableModel.addRow(rowData.toArray());

                atual = atual.getProximo();
            }
        }

        JTable table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 600));
        panel.add(scrollPane, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel, "Informações da Tabela Hash", JOptionPane.INFORMATION_MESSAGE);
    }

    public Veiculo buscarPorPlacaOuRenavam(String placaOuRenavam) {
        for (int i = 0; i < tamanho; i++) {
            No<Veiculo> atual = tabela[i];

            while (atual != null) {
                Veiculo veiculo = atual.getValor();
                if (veiculo.getPlaca().equals(placaOuRenavam) || veiculo.getRenavam().equals(placaOuRenavam)) {
                    acessarElemento(placaOuRenavam);
                    return veiculo;
                }

                atual = atual.getProximo();
            }
        }

        return null;
    }

    public float fatorDeCarga() {
        return (float) contarVeiculos() / tamanho;
    }

    private void registrarLog(String mensagem) {
        try {
            logWriter.write(mensagem);
            logWriter.newLine();
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

