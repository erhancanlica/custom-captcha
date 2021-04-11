package tr.edu.duzce.mf.bm470.captcha.model.enums;


import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum UserType {
    CLIENT("CLIENT", "ROLE_CLIENT" ),
    ADMIN("ADMIN", "ROLE_ADMIN");


    private static final Map<String, UserType> lookup = new ConcurrentHashMap<>();

    static {
        Arrays.stream(UserType.values()).forEach(userType -> lookup.put(userType.getRole(), userType));
    }

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String role;

    UserType(String description, String role) {
        this.description = description;
        this.role = role;
    }

    public static UserType get(String role) {
        return lookup.get(role);
    }

}
