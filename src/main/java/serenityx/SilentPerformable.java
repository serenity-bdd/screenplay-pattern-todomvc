package serenityx;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class SilentPerformable implements Performable {
    @Override
    public <T extends Actor> void performAs(T actor) {}
}
