package org.testinfected.petstore.controllers;

import org.testinfected.petstore.Members;
import org.testinfected.petstore.View;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;

public class About implements Application {
    private final View<Members> aboutView;
	private final Members members;

    public About(Members members, View<Members> aboutView) {
		this.members = members;
		this.aboutView = aboutView;
	}

	public void handle(Request request, Response response) throws Exception {
		aboutView.render(response, members);
    }
}