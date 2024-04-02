package coisas;

class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    public Usuario(String nome, String email, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
@Override
    public String toString() {
        return "Usuario(" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha=" + senha + 
                ", telefone=" + telefone +
                ')';
    }
}