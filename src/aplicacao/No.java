package aplicacao;

public class No<T> implements Comparable<String>{

    private String chave;
    private T valor;
    private No <T> proximo;

    public No(String chave, T valor) {
        this.chave = chave;
        this.valor = valor;
        this.proximo = null;
    }

    @Override
    public int compareTo(String o) {
        if(this.getChave().compareTo(o) < 0)
            return -1;
        if(this.getChave().compareTo(o) > 0)
            return 1;
        return 0;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public No<T> getProximo() {
        return proximo;
    }
    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }
}
