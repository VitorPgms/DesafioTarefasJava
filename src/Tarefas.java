public class Tarefas {
    private int codigo;
    private String nome;
    private String situacao;

    public Tarefas(int codigo, String nome, String situacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.situacao = situacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\nNome: " + nome + "\nSituação: " + situacao + "\n";
    }
}

