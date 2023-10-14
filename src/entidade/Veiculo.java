package entidade;

public class Veiculo {

    private String placa;
    private String renavam;
    private Condutores condutor;
    private String modelo;
    private int ano;

    public Veiculo( String renavam, String placa, String nomeCondutor, String cpfCondutor, String modelo, int ano) {
        setPlaca(placa);
        setRenavam(renavam);
        setCondutor(nomeCondutor, cpfCondutor);
        setModelo(modelo);
        setAno(ano);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (!placa.matches("[A-Z]{3}\\d{1}[A-Z]{1}\\d{2}")) {
            throw new IllegalArgumentException("Placa inválida. A placa deve estar no formato AAA1A11.");
        }
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }
    public int getRenavamInt() {
        return Integer.parseInt(renavam);
    }

    public void setRenavam(String renavam) {
        if (!renavam.matches("\\d{9}")) {
            throw new IllegalArgumentException("Renavam inválido. O renavam deve ter 9 dígitos.");
        }
        this.renavam = renavam;
    }

    public Condutores getCondutor() {
        return condutor;
    }

    public void setCondutor(String nomeCondutor, String cpfCondutor) {
        this.condutor = new Condutores(nomeCondutor, cpfCondutor);
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        if (modelo.isEmpty()) {
            throw new IllegalArgumentException("Modelo inválido. O modelo não pode estar vazio.");
        }
        this.modelo = modelo;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        if (ano < 1900 || ano > 3000) {
            throw new IllegalArgumentException("Ano de fabricação inválido. O ano deve estar entre 1900 e 3000.");
        }
        this.ano = ano;
    }
    public String toString() {
        return placa + "#" + renavam + "#" + condutor.getNome() + "#" + condutor.getCpf() + "#" + modelo + "#" + ano;
    }
    public String toString2() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", renavam='" + renavam + '\'' +
                condutor +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                '}';
    }
}
