import java.util.ArrayList;
import java.util.List;

public enum Taste {
    SWEET("słodki"), SALTY("słony"), SOUR("kwaśny"),
    BITTER("gorzki"), NEUTRAL("neutralny");

    private final String description;
    Taste(String description) {
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

}
