package br.com.wsb.DonJose.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.dto.CategoriaDTO;
import br.com.wsb.DonJose.model.Categoria;
import br.com.wsb.DonJose.repository.CategoriaRepository;
import br.com.wsb.DonJose.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository Catrepository;

	public Categoria find(long id) {
		Optional<Categoria> obj = Catrepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {

		return Catrepository.save(obj);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

}
