package org.testinfected.petstore;

public class Members {
	private String members;

	public Members()
	{
	}
	
	public void addMember(String member)
	{
		this.members = member;
	}
	
	public String getMembers() {
		return members;
	}
}
