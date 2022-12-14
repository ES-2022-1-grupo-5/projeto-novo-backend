package chegamais.com.chagamais.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chegamais.com.chagamais.controller.DTO.GrupoDTO;
import chegamais.com.chagamais.controller.Form.GrupoForm;
import chegamais.com.chagamais.controller.Form.GrupoFormUpdate;
import chegamais.com.chagamais.controller.Response.GrupoResponse;
import chegamais.com.chagamais.services.GrupoService;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public List<GrupoResponse> listar(){

        List<GrupoDTO> Grupos = this.grupoService.obterTodos();

        return this.converterLista(Grupos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoResponse> selecionarPorId(@PathVariable Long id){

        GrupoDTO GrupoDTO = grupoService.obterPorId(id);

        return this.gerarResposta(GrupoDTO, 200);
    }

    @PostMapping
    public ResponseEntity<GrupoResponse> cadastrar(@RequestBody @Valid GrupoForm GrupoForm){

        GrupoDTO GrupoDTO = this.grupoService.adicionar(GrupoForm.converterParaDTO());

        return this.gerarResposta(GrupoDTO, 200);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoResponse> atualizar(@RequestBody GrupoFormUpdate GrupoFormUpdate, @PathVariable Long id){

        GrupoDTO GrupoDTO = this.grupoService.atualizar(GrupoFormUpdate.converterParaDTO(), id);

        return this.gerarResposta(GrupoDTO, 200);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GrupoResponse> deletarPorId(@PathVariable Long id){

        GrupoDTO GrupoDTO = this.grupoService.deletarPorId(id);

        return this.gerarResposta(GrupoDTO, 200);
    }



    //funcoes auxiliares
    private ResponseEntity<GrupoResponse> gerarResposta(GrupoDTO grupoDTO, int status) {

        if(grupoDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return ResponseEntity.status(status).body(grupoDTO.converterParaResponse());
    }
    
    private List<GrupoResponse> converterLista(List<GrupoDTO> grupos){
    	List<GrupoResponse> responses = new ArrayList<GrupoResponse>();
    	
    	for(GrupoDTO dto: grupos) {
    		responses.add(dto.converterParaResponse());
    	}
    	
    	return responses;
    }
    
}
