package entidade;

public class Condutores {

    private String nome;
    private String cpf;

    public Condutores(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return " Nome do condutor: " + nome + ", cpf do condutor: " + cpf + "]";
    }
}
