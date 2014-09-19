package org.testinfected.petstore.controllers;

import org.testinfected.petstore.Members;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;

public class CreateMembers implements Application{

	private final Members members;

	public CreateMembers(Members members) {
		this.members = members;
	}

	public void handle(Request request, Response response) {
		members.addMember(request.parameter("members"));
		response.status(HttpStatus.CREATED);
	}

}
