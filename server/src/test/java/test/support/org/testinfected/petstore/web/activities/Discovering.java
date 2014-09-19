package test.support.org.testinfected.petstore.web.activities;

import test.support.org.testinfected.petstore.web.page.PetStore;

public class Discovering {

	private PetStore petstore;

	public Discovering(PetStore petstore) {
		this.petstore = petstore;
	}

	public void teamMembersAre(String... members) {
		petstore.goToAboutPage().showsTeamMembers(members);
	}
}
