package org.telerik.web.beertag.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.telerik.web.beertag.exceptions.AuthorizationException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.services.UserService;

@Component
public class AuthenticationHelper {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String INVALID_AUTHENTICATION = "Invalid authentication.";
    private final UserService service;

    @Autowired
    public AuthenticationHelper(UserService service) {
        this.service = service;
    }

    public User tryGetUser(HttpHeaders headers) {
        if (!headers.containsKey(AUTHORIZATION_HEADER_NAME)) {
            throw new AuthorizationException(INVALID_AUTHENTICATION);
        }
        try {
            String data = headers.getFirst(AUTHORIZATION_HEADER_NAME);
            String username = getUsername(data);
            String password = getPassword(data);
            User user = service.getByUsername(username);
            if (!user.getPassword().equals(password))
                throw new AuthorizationException(INVALID_AUTHENTICATION);
            return user;
        } catch (EntityNotFoundException e) {
            throw new AuthorizationException(INVALID_AUTHENTICATION);
        }
    }

    private String getUsername(String data) {
            int firstSpaceIndex = data.indexOf(":");
        if (firstSpaceIndex == -1) {
            throw new AuthorizationException(INVALID_AUTHENTICATION);
        }
        return data.substring(0, firstSpaceIndex);
    }

    private String getPassword(String data) {
        int firstSpaceIndex = data.indexOf(":");
        if (firstSpaceIndex == -1) {
                throw new AuthorizationException(INVALID_AUTHENTICATION);
        }
        return data.substring(firstSpaceIndex + 1);
    }
}
