package chegamais.com.chagamais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull @NotEmpty
    private String horario;
    
    public Horario() {

    }

    public Horario(@NotNull @NotEmpty String horario) {
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    // Dúvida: é adequado estabelecer esse método de mudança de horário?
    /*
    public void setHorario(String horario) {
        this.horario = horario;
    }
    */


}