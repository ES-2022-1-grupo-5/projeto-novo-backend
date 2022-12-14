package chegamais.com.chagamais.controller.Form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import chegamais.com.chagamais.controller.DTO.UsuarioDTO;


public class UsuarioForm {

    @NotNull @NotEmpty 
    private String nome;
    @NotNull @NotEmpty
    private String dataNascimento;
    private String posicaoFavorita;
    @NotNull @NotEmpty 
    private String email;
   @NotNull @NotEmpty 
    private String senha;

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

    public UsuarioDTO converterParaDTO(){

        UsuarioDTO usuarioDTO = new UsuarioDTO(this.nome, this.dataNascimento, this.posicaoFavorita, this.email, this.senha);
        return usuarioDTO;
        
    }

    

    
    
}
