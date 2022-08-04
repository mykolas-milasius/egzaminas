package egzaminas.egzaminas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "films_kategorija")
public class FilmsKategorija
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer id_films;
    
    @Column(nullable = false)
    private Integer id_kategorijos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_films() {
		return id_films;
	}

	public void setId_films(Integer id_films) {
		this.id_films = id_films;
	}

	public Integer getId_kategorijos() {
		return id_kategorijos;
	}

	public void setId_kategorijos(Integer id_kategorijos) {
		this.id_kategorijos = id_kategorijos;
	}
}
