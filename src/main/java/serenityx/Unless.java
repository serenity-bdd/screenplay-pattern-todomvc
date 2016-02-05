package serenityx;

import net.serenitybdd.screenplay.Performable;

public class Unless {
    public static Performable the(Boolean shouldNotPerform, Performable task) {
        return (shouldNotPerform) ? new SilentPerformable() : task;
    }
}
