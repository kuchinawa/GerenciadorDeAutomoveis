package entidade;

import aplicacao.Protocolo;
import javax.swing.*;

public class Cliente {
    static Protocolo protocolo = new Protocolo();

    public static void main(String[] args) {


        inserir50Veiculos();
        while (true) {
            String opcaoStr = JOptionPane.showInputDialog(
                    "Escolha uma opção:\n" +
                            "1. Buscar veículo\n" +
                            "2. Cadastrar veículo\n" +
                            "3. Listar veículos\n" +
                            "4. Alterar veículo\n" +
                            "5. Remover veículo\n" +
                            "6. Mais\n" +
                            "7. Sair"
            );
            try {
                int opcao = Integer.parseInt(opcaoStr);

                switch (opcao) {
                    case 1:
                        String renavamBusca = JOptionPane.showInputDialog("Digite o renavam do veículo:");

                        Veiculo veiculoEncontrado = protocolo.buscarPorPlacaouRenavam(renavamBusca);

                        if (veiculoEncontrado != null) {
                            JOptionPane.showMessageDialog(null, "Veículo encontrado:\n" + veiculoEncontrado.toString2());
                        } else {
                            JOptionPane.showMessageDialog(null, "Veículo não encontrado.");
                        }
                        break;

                    case 2:
                        try {
                            String placa = JOptionPane.showInputDialog("Digite a placa do veículo: \n" +
                                    "A placa deve estar no formato AAA1A11.");
                            String renavam = JOptionPane.showInputDialog("Digite o renavam do veículo: \n" +
                                    "O renavam deve ter 9 dígitos.");
                            String nomeCondutor = JOptionPane.showInputDialog("Digite o nome do condutor:");
                            String cpfCondutor = JOptionPane.showInputDialog("Digite o CPF do condutor:");
                            String modelo = JOptionPane.showInputDialog("Digite o modelo do veículo:");
                            int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação do veículo:"));

                            Veiculo veiculo = new Veiculo(renavam, placa, nomeCondutor, cpfCondutor, modelo, ano);
                            protocolo.inserirVeiculo(veiculo);

                            JOptionPane.showMessageDialog(null, "Veículo inserido com sucesso:\n" + veiculo.toString2());

                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Erro ao inserir veículo: " + e.getMessage());
                        }
                        break;

                    case 3:
                        protocolo.imprimir();
                        break;

                    case 4:
                        String renavamAlteracao = JOptionPane.showInputDialog("Digite o renavam do veículo a ser alterado:");
                        Veiculo veiculoParaAlterar = protocolo.buscarPorPlacaouRenavam(renavamAlteracao);

                        if (veiculoParaAlterar != null) {
                            String opcaoAlteracaoStr = JOptionPane.showInputDialog(
                                    "Escolha o atributo a ser alterado:\n" +
                                            "1. Placa\n" +
                                            "2. Nome do Condutor\n" +
                                            "3. CPF do Condutor\n" +
                                            "4. Modelo\n" +
                                            "5. Ano de Fabricação"
                            );

                            int opcaoAlteracao = Integer.parseInt(opcaoAlteracaoStr);

                            switch (opcaoAlteracao) {
                                case 1:
                                    String novaPlaca = JOptionPane.showInputDialog("Digite a nova placa:");
                                    veiculoParaAlterar.setPlaca(novaPlaca);
                                    break;
                                case 2:
                                    String novoNomeCondutor = JOptionPane.showInputDialog("Digite o novo nome do condutor:");
                                    veiculoParaAlterar.getCondutor().setNome(novoNomeCondutor);
                                    break;
                                case 3:
                                    String novoCpfCondutor = JOptionPane.showInputDialog("Digite o novo CPF do condutor:");
                                    veiculoParaAlterar.getCondutor().setCpf(novoCpfCondutor);
                                    break;
                                case 4:
                                    String novoModelo = JOptionPane.showInputDialog("Digite o novo modelo:");
                                    veiculoParaAlterar.setModelo(novoModelo);
                                    break;
                                case 5:
                                    int novoAno = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ano de fabricação:"));
                                    veiculoParaAlterar.setAno(novoAno);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opção de alteração inválida.");
                            }
                            protocolo.atualizarVeiculo(veiculoParaAlterar);
                            JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso:\n" + veiculoParaAlterar.toString2());
                        } else {
                            JOptionPane.showMessageDialog(null, "Veículo não encontrado.");
                        }
                        break;
                    case 5:
                        String renavamRemocaoStr = JOptionPane.showInputDialog("Digite o renavam do veículo a ser removido:");
                        try {
                            String mensagemRemocao = protocolo.removerVeiculoPorRenavam(renavamRemocaoStr);
                            JOptionPane.showMessageDialog(null, mensagemRemocao);
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 6:
                        String subOpcaoStr = JOptionPane.showInputDialog(
                                "Escolha uma opção:\n" +
                                        "1. Quantidade de veículos\n" +
                                        "2. Exibir Fator de Carga\n" +
                                        "3. Voltar"
                        );

                        try {
                            int subOpcao = Integer.parseInt(subOpcaoStr);

                            switch (subOpcao) {
                                case 1:
                                    int quantidadeVeiculos = protocolo.contarVeiculos();
                                    JOptionPane.showMessageDialog(null, "Quantidade de veículos: " + quantidadeVeiculos);
                                    break;
                                case 2:
                                    float fatorDeCarga = protocolo.fatorDeCarga();
                                    JOptionPane.showMessageDialog(null, "Atual fator de carga é " + fatorDeCarga);
                                    break;
                                case 3:
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opção inválida. Escolha uma opção válida.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Opção inválida. Escolha uma opção numérica.");
                        }
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Encerrando cliente...");
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida. Escolha uma opção numérica.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void inserir50Veiculos() {
        try {
            protocolo.inserirVeiculo(new Veiculo("123456789", "QWE8R90", "Beatriz", "89012345678", "Crossover", 2020));
            protocolo.inserirVeiculo(new Veiculo("111111112", "ABC1D23", "Ícaro", "12345678922", "KWID", 2021));
            protocolo.inserirVeiculo(new Veiculo("111111113", "AAA1A13", "Maria", "23456789012", "Hatch", 2021));
            protocolo.inserirVeiculo(new Veiculo("111111114", "AAA1A14", "Pedro", "34567890123", "SUV", 2020));
            protocolo.inserirVeiculo(new Veiculo("999999999", "AAA1A19", "Ana", "45678901234", "Crossover", 2019));
            protocolo.inserirVeiculo(new Veiculo("617295837", "POI5Q67", "Carlos", "56789012345", "Sedan", 2018));
            protocolo.inserirVeiculo(new Veiculo("840562973", "ZXC6V78", "Mariana", "67890123456", "Hatch", 2022));
            protocolo.inserirVeiculo(new Veiculo("475916238", "ASD7F89", "Lucas", "78901234567", "SUV", 2021));
            protocolo.inserirVeiculo(new Veiculo("216837594", "AAA1A12", "Paulo Henrique", "12345678901", "Ferrari", 2022));
            protocolo.inserirVeiculo(new Veiculo("358614729", "TYU9I01", "Gustavo", "90123456789", "Sedan", 2019));
            protocolo.inserirVeiculo(new Veiculo("182795346", "PLK1A12", "Fernanda", "23456789090", "Hatch", 2023));
            protocolo.inserirVeiculo(new Veiculo("695148237", "FGH2J23", "Thiago", "34567890101", "SUV", 2022));
            protocolo.inserirVeiculo(new Veiculo("274951386", "ZXC3K34", "Carolina", "45678901212", "Crossover", 2021));
            protocolo.inserirVeiculo(new Veiculo("834527916", "QWE4L45", "Rafael", "56789012323", "Sedan", 2020));
            protocolo.inserirVeiculo(new Veiculo("489632157", "ASD5M56", "Larissa", "67890123434", "Hatch", 2019));
            protocolo.inserirVeiculo(new Veiculo("763859124", "TYU6N67", "Eduardo", "78901234545", "SUV", 2018));
            protocolo.inserirVeiculo(new Veiculo("921674538", "PLK7O78", "Amanda", "89012345656", "Crossover", 2017));
            protocolo.inserirVeiculo(new Veiculo("156782429", "FGH8P89", "Guilherme", "90123456767", "Sedan", 2022));
            protocolo.inserirVeiculo(new Veiculo("349821657", "ZXC9Q90", "Sophia", "23456789078", "Hatch", 2021));
            protocolo.inserirVeiculo(new Veiculo("567921483", "ASD1R01", "Leonardo", "34567890189", "SUV", 2020));
            protocolo.inserirVeiculo(new Veiculo("846592371", "QWE2S12", "Isabela", "45678901290", "Crossover", 2019));
            protocolo.inserirVeiculo(new Veiculo("238476915", "TYU3T23", "Matheus", "56789012301", "Sedan", 2018));
            protocolo.inserirVeiculo(new Veiculo("915283764", "PLK4U34", "Julia", "67890123412", "Hatch", 2022));
            protocolo.inserirVeiculo(new Veiculo("457132869", "FGH5V45", "Ricardo", "78901234523", "SUV", 2021));
            protocolo.inserirVeiculo(new Veiculo("648293157", "ZXC6W56", "Aline", "89012345634", "Crossover", 2020));
            protocolo.inserirVeiculo(new Veiculo("369574218", "QWE7X67", "Bruno", "90123456745", "Sedan", 2019));
            protocolo.inserirVeiculo(new Veiculo("724916385", "TYU8Y78", "Laura", "23456789056", "Hatch", 2021));
            protocolo.inserirVeiculo(new Veiculo("149682735", "PLK9Z89", "Joana", "34567890167", "SUV", 2022));
            protocolo.inserirVeiculo(new Veiculo("836241597", "HJK0B01", "Felipe", "45678901278", "Crossover", 2023));
            protocolo.inserirVeiculo(new Veiculo("324869571", "FGH1C02", "Bianca", "56789091234", "Sedan", 2022));
            protocolo.inserirVeiculo(new Veiculo("927156384", "POG2D23", "Ricardo", "67890182345", "Hatch", 2021));
            protocolo.inserirVeiculo(new Veiculo("618735294", "MNB3E34", "Mariana", "78901273456", "SUV", 2020));
            protocolo.inserirVeiculo(new Veiculo("413269857", "TYU4F45", "Gustavo", "89012364567", "Crossover", 2019));
            protocolo.inserirVeiculo(new Veiculo("847592631", "ZXC5G56", "Amanda", "90123455678", "Sedan", 2018));
            protocolo.inserirVeiculo(new Veiculo("536814972", "PLK6H67", "Thiago", "12345656789", "Hatch", 2022));
            protocolo.inserirVeiculo(new Veiculo("249681753", "ASD7I78", "Isabela", "23456747890", "SUV", 2021));
            protocolo.inserirVeiculo(new Veiculo("751936482", "QWE8J89", "Ricardo", "34567838901", "Crossover", 2020));
            protocolo.inserirVeiculo(new Veiculo("812547693", "FGH9K90", "Bianca", "45678929012", "Sedan", 2019));
            protocolo.inserirVeiculo(new Veiculo("659418237", "ZXC1L01", "Mariana", "56789010123", "Hatch", 2018));
            protocolo.inserirVeiculo(new Veiculo("437895216", "TYU2M12", "Gustavo", "67890101234", "SUV", 2022));
            protocolo.inserirVeiculo(new Veiculo("283764951", "PLK3N23", "Amanda", "78901212345", "Crossover", 2021));
            protocolo.inserirVeiculo(new Veiculo("941623875", "ASD4O34", "Thiago", "89012323456", "Sedan", 2020));
            protocolo.inserirVeiculo(new Veiculo("765139284", "QWE5P45", "Isabela", "90123434567", "Hatch", 2019));
            protocolo.inserirVeiculo(new Veiculo("526497138", "FGH6Q56", "Ricardo", "12345645678", "SUV", 2018));
            protocolo.inserirVeiculo(new Veiculo("314782965", "ZXC7R67", "Bianca", "23456756789", "Crossover", 2022));
            protocolo.inserirVeiculo(new Veiculo("879256314", "TYU8S78", "Mariana", "34567867890", "Sedan", 2021));
            protocolo.inserirVeiculo(new Veiculo("692314875", "PLK9T89", "Gustavo", "45678978901", "Hatch", 2020));
            protocolo.inserirVeiculo(new Veiculo("138457926", "ASD0U90", "Amanda", "56789089012", "SUV", 2019));
            protocolo.inserirVeiculo(new Veiculo("417692358", "QWE1V01", "Thiago", "67890190123", "Crossover", 2018));
            protocolo.inserirVeiculo(new Veiculo("895263412", "FGH2W12", "Isabela", "78901201234", "Sedan", 2022));

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao inserir veículo: " + e.getMessage());
        }
    }
}

