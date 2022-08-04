package egzaminas.egzaminas;

import org.springframework.data.repository.CrudRepository;

public interface KategorijaRepository extends CrudRepository<Kategorija, Integer>
{
	Kategorija findByPavadinimas(String pavadinimas);
}