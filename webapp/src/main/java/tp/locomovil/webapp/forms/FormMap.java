package tp.locomovil.webapp.forms;

import org.hibernate.validator.constraints.NotBlank;

public class FormMap {

	@NotBlank
	String name;

	public FormMap () {
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}
}
