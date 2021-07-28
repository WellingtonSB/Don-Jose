package br.com.wsb.DonJose.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.dto.CategoriaDTO;
import br.com.wsb.DonJose.model.Categoria;
import br.com.wsb.DonJose.repository.CategoriaRepository;
import br.com.wsb.DonJose.service.exceptions.DataIntegrityException;
import br.com.wsb.DonJose.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository Catrepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = Catrepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {

		return Catrepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return Catrepository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			Catrepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return Catrepository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return Catrepository.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
