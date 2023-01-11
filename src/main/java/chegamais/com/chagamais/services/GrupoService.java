package chegamais.com.chagamais.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chegamais.com.chagamais.controller.DTO.GrupoDTO;
import chegamais.com.chagamais.controller.DTO.UsuarioDTO;
import chegamais.com.chagamais.model.Grupo;
import chegamais.com.chagamais.model.Usuario;
import chegamais.com.chagamais.repository.GrupoRepository;

@Service
public class GrupoService implements ServiceInteface<GrupoDTO> {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public List<GrupoDTO> obterTodos() {
		List<Grupo> Grupos = grupoRepository.findAll();

		return this.converterLista(Grupos);
	}

	@Override
	public GrupoDTO obterPorId(Long id) {
		Optional<Grupo> GrupoOp = grupoRepository.findById(id);

		return this.converterOptional(GrupoOp, false);
	}

	public List<UsuarioDTO> obterMembrosPorId(Long id) {
		Optional<Grupo> GrupoOp = grupoRepository.findById(id);

		if (GrupoOp.isPresent()) {
			return this.converterListaUsuarios(GrupoOp.get().getMembros());
		} else {
			return null;
		}
	}

	public Grupo addMembro(Long idGrupo, Long idMembro) {
		Optional<Grupo> GrupoOp = grupoRepository.findById(idGrupo);
		UsuarioDTO user = usuarioService.obterPorId(idMembro);

		if (GrupoOp.isPresent() && user != null) {
			Usuario user1 = new Usuario();
			user1.setId(user.getId());
			GrupoOp.get().addMembro(user1);
			this.grupoRepository.saveAndFlush(GrupoOp.get());
			return GrupoOp.get();
		} else {
			return null;
		}
	}

	public Grupo removeMembro(Long idGrupo, Long idMembro) {
		Optional<Grupo> GrupoOp = grupoRepository.findById(idGrupo);
		UsuarioDTO user = usuarioService.obterPorId(idMembro);

		if (GrupoOp.isPresent() && user != null) {
			Usuario user1 = new Usuario();
			user1.setId(user.getId());
			if (GrupoOp.get().removeMembro(user1) != null) {
				this.grupoRepository.saveAndFlush(GrupoOp.get());
				return GrupoOp.get();
			}
			return null;
		} else {
			return null;
		}
	}

	public List<GrupoDTO> getGruposPorIdMembro(Long idMembro) {
		UsuarioDTO user = usuarioService.obterPorId(idMembro);
		Usuario usuario = new Usuario();

		if (user != null) {
			usuario.setId(user.getId());
		} else {
			return null;
		}

		List<Grupo> Grupos = grupoRepository.findAll();
		List<Grupo> gruposParticipantes = new ArrayList<Grupo>();

		for (Grupo grupo : Grupos) {
			if (grupo.containsMembro(usuario)) {
				gruposParticipantes.add(grupo);
			}
		}

		return this.converterLista(gruposParticipantes);
	}

	@Override
	public GrupoDTO adicionar(GrupoDTO dto) {
		dto.setId(null);

		Grupo Grupo = dto.converterParaModel();

		grupoRepository.save(Grupo);

		dto.setId(Grupo.getId());

		return dto;
	}

	@Override
	public GrupoDTO atualizar(GrupoDTO dto, Long id) {
		Optional<Grupo> GrupoOp = grupoRepository.findById(id);

		Grupo Grupo = GrupoOp.get();

		this.verificarEAtualizar(Grupo, dto);

		grupoRepository.save(Grupo);

		return this.converterModelParaDTO(Grupo);
	}

	@Override
	public GrupoDTO deletarPorId(Long id) {
		Optional<Grupo> GrupoOp = grupoRepository.findById(id);

		if (!GrupoOp.isPresent()) {
			return null;
		}

		GrupoDTO GrupoDTO = this.converterOptional(GrupoOp, true);

		grupoRepository.deleteById(id);

		return GrupoDTO;
	}

	// funcoes auxiliares

	private GrupoDTO converterModelParaDTO(Grupo Grupo) {
		GrupoDTO dto = new GrupoDTO(Grupo.getNome());
		dto.setId(Grupo.getId());

		return dto;

	}

	private List<UsuarioDTO> converterListaUsuarios(List<Usuario> Usuarios) {
		List<UsuarioDTO> DTOs = new ArrayList<UsuarioDTO>();

		for (Usuario Usuario : Usuarios) {
			UsuarioDTO dto = new UsuarioDTO(Usuario.getNome(), Usuario.getDataNascimento(),
					Usuario.getPosicaoFavorita(), Usuario.getEmail(), null);
			dto.setId(Usuario.getId());
			DTOs.add(dto);
		}

		return DTOs;

	}

	private List<GrupoDTO> converterLista(List<Grupo> Grupos) {
		List<GrupoDTO> DTOs = new ArrayList<GrupoDTO>();

		for (Grupo Grupo : Grupos) {
			GrupoDTO dto = this.converterModelParaDTO(Grupo);
			DTOs.add(dto);
		}

		return DTOs;

	}

	private GrupoDTO converterOptional(Optional<Grupo> GrupoOp, Boolean confere) {

		if (!confere) {
			if (!GrupoOp.isPresent()) {
				return null;
			}
		}

		Grupo Grupo = GrupoOp.get();
		GrupoDTO dto = this.converterModelParaDTO(Grupo);

		return dto;

	}

	private void verificarEAtualizar(Grupo Grupo, GrupoDTO GrupoDTO) {

		String nomeDTO = GrupoDTO.getNome();
		if (nomeDTO != null) {
			if (nomeDTO != "") {
				Grupo.setNome(nomeDTO);
			}
		}

	}

}
