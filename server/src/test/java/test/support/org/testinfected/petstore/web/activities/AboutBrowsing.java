package test.support.org.testinfected.petstore.web.activities;

import test.support.org.testinfected.petstore.web.page.PetStore;

/**
 * Created by formation on 19/09/14.
 */
public class AboutBrowsing {

    private final PetStore petstore;

    public AboutBrowsing(PetStore petstore) {
        this.petstore = petstore;
    }

    public void showImage(){
        petstore.goToAboutPage().showImageAbout();
    }
}
