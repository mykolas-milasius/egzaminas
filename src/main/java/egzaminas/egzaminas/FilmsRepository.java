package egzaminas.egzaminas;

import org.springframework.data.repository.CrudRepository;

public interface FilmsRepository extends CrudRepository<Films, Integer>
{
	Films findByPavadinimas(String pavadinimas);
}
