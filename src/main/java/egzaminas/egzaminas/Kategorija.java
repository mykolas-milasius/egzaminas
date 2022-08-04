package egzaminas.egzaminas;

import javax.persistence.*;

@Entity
@Table(name = "kategorija")
public class Kategorija
{
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
		
	    @Column(nullable = false)
	    private String pavadinimas;

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
}
