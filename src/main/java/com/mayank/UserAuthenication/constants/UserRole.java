package com.mayank.UserAuthenication.constants;

public enum UserRole {
	
	ROLE_USER(0l),ROLE_ADMIN(1l);
	
	public final Long id;

    private UserRole(Long id) {
        this.id = id;
    }

}
