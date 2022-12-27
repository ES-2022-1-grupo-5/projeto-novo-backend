package chegamais.com.chagamais.controller.DTO;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import chegamais.com.chagamais.controller.Response.UsuarioResponse;
import chegamais.com.chagamais.model.Usuario;

public class UsuarioDTO implements DTO_Interface<Usuario, UsuarioResponse>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String dataNascimento;
    private String posicaoFavorita;
    private String email;
    private String senha;


    public UsuarioDTO() {
    }


    public UsuarioDTO(String nome, String dataNascimento, String posicaoFavorita, String email, String senha) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.posicaoFavorita = posicaoFavorita;
        this.email = email;
        this.senha = senha;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDataNascimento() {
        return dataNascimento;
    }


    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public String getPosicaoFavorita() {
        return posicaoFavorita;
    }


    public void setPosicaoFavorita(String posicaoFavorita) {
        this.posicaoFavorita = posicaoFavorita;
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

    public Usuario converterParaModel(){
        Usuario usuario = new Usuario(this.nome, this.dataNascimento, this.posicaoFavorita, this.email, this.senha, null);
        return usuario;
    }

    public UsuarioResponse converterParaResponse(){

        UsuarioResponse UsuarioResponse = new UsuarioResponse(this.id, this.nome, this.dataNascimento, this.posicaoFavorita, this.email);
        return UsuarioResponse;

    }

    

    

    

    
    
}
