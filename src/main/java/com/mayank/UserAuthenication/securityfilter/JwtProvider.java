package com.mayank.UserAuthenication.securityfilter;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.mayank.UserAuthenication.constants.JwtConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
//	private SecretKey key= Keys.hmacShaKeyFor (JwtConstant.SECRET_KEY.getBytes());
	
	
	public String generateToken(Authentication auth){
		SecretKey key= Keys.hmacShaKeyFor (JwtConstant.SECRET_KEY.getBytes());
		Collection<? extends GrantedAuthority>authorities=auth.getAuthorities();
		String roles =populateAuthorities(authorities);
		String jwt= Jwts.builder().setIssuedAt(new Date())
				.setExpiration((new Date(new Date().getTime()+84600000)))
		
		.claim("email",auth.getName())
		.claim("authorities",roles)
		.signWith (key)
		.compact();
		return jwt;
	}
	
	public String getEmailFromJwtToken(String jwt){
		SecretKey key= Keys.hmacShaKeyFor (JwtConstant.SECRET_KEY.getBytes());
		jwt = jwt.substring(7);
		Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email=String. valueOf(claims.get ("email"));
		return email;
	}


	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		// TODO Auto-generated method stub
		
		Set<String> auths=new HashSet<>();
		for (GrantedAuthority authority:authorities){
		auths.add (authority.getAuthority());
		}
		return String.join(",", auths);
		
	}
	
	
	
	

}
