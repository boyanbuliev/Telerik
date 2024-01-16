package org.telerik.web.beertag;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.FilterOptions;
import org.telerik.web.beertag.models.Style;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.models.dtos.BeerDto;

public class Helpers {

    public static User createMockAdmin() {
        User mockUser = createMockUser();
        mockUser.setAdmin(true);
        return mockUser;
    }

    public static User createMockUser() {
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("MockUsername");
        mockUser.setPassword("MockPassword");
        mockUser.setLastName("MockLastName");
        mockUser.setFirstName("MockFirstName");
        mockUser.setEmail("mock@user.com");
        return mockUser;
    }

    public static Beer createMockBeer() {
        var mockBeer = new Beer();
        mockBeer.setId(1);
        mockBeer.setName("MockBeer");
        mockBeer.setCreatedBy(createMockUser());
        mockBeer.setStyle(createMockStyle());
        return mockBeer;
    }

    public static Style createMockStyle() {
        var mockStyle = new Style();
        mockStyle.setId(1);
        mockStyle.setName("MockStyle");
        return mockStyle;
    }

    public static FilterOptions createMockFilterOptions() {
        return new FilterOptions(
                "name",
                0.0,
                10.0,
                1,
                "sort",
                "order");
    }

    public static BeerDto createBeerDto() {
        return new BeerDto("MockBeer", 4.5, 1);
    }

    /**
     * Accepts an object and returns the stringified object.
     * Useful when you need to pass a body to a HTTP request.
     */
    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
