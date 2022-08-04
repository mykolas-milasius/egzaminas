package egzaminas.egzaminas;

import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "films")
public class Films
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    @Column(nullable = false)
    private String pavadinimas;
    
    @Column(nullable = false)
    private String aprasas;
    
    @Column(nullable = false)
    private String reitingas;
    
    @Column(nullable = false)
    private String kategorija;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPavadinimas() {
		return pavadinimas;
	}

	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public String getAprasas() {
		return aprasas;
	}

	public void setAprasas(String aprasas) {
		this.aprasas = aprasas;
	}

	public String getReitingas() {
		return reitingas;
	}

	public void setReitingas(String reitingas) {
		this.reitingas = reitingas;
	}
}
