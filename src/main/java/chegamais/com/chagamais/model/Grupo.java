package chegamais.com.chagamais.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull @NotEmpty 
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupo_membro", joinColumns = @JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "membro_id"))
    private List<Usuario> membros;

    public Grupo() {
    }

    public Grupo( @NotNull @NotEmpty String nome) {
        this.nome = nome;
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
    
    public List<Usuario> getMembros() {
    	return this.membros;
    }
    
    public Usuario addMembro(Usuario user) {
    	if (!containsMembro(user)) {
    		this.membros.add(user);
    		return user;
    	}
    	return null;
    }

    public boolean containsMembro(Usuario user) {
    	return this.membros.contains(user);
    }
    
    public Usuario removeMembro(Usuario user) {
    	if (containsMembro(user)) {
    		this.membros.remove(user);
    		return user;
    	}
    	return null;
    }

    



    


    
}
