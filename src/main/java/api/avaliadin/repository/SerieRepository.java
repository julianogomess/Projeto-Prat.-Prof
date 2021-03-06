package api.avaliadin.repository;

import org.springframework.data.repository.CrudRepository;

import api.avaliadin.model.Serie;

public interface SerieRepository extends CrudRepository<Serie, Integer>{
	Serie findByTitulo(String titulo);
	Serie findById(int id);
}
