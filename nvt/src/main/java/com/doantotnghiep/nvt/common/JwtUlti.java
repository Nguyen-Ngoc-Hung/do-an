package com.doantotnghiep.nvt.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Jwts;


@Component
public class JwtUlti {

    public String getUserNameFromJwtToken(String token) {
        String jwtSecret = "do-an-nvt";
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

    public String getUserNameFromCookie() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) attr.getRequest();
		String userName = null;
		if (null != request.getCookies() && request.getCookies().length > 0) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals("do-na-nvt")) {
					String authToken = c.getValue();
					userName = getUserNameFromJwtToken(authToken);
				}
			}
		}
		return userName;
	}
}
