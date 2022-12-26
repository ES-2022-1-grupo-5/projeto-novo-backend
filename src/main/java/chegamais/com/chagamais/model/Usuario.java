package chegamais.com.chagamais.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull @NotEmpty 
    private String nome;
    @NotNull @NotEmpty
    private String dataNascimento;
    private String posicaoFavorita;
    @NotNull @NotEmpty 
    private String email;
   @NotNull @NotEmpty 
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public Usuario() {

    }


    public Usuario(@NotNull @NotEmpty String nome, @NotNull @NotEmpty String dataNascimento, String posicaoFavorita,
            @NotNull @NotEmpty String email, @NotNull @NotEmpty String senha) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.posicaoFavorita = this.analisarPosicaoFavorita(posicaoFavorita);
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
        this.posicaoFavorita = this.analisarPosicaoFavorita(posicaoFavorita);
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

    private String analisarPosicaoFavorita(String posicao){
        if( posicao == null || posicao == ""){
            return "Não delcarada";
        }
        else {
            return posicao;
        }
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
