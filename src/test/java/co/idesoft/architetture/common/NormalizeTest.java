package co.idesoft.architetture.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NormalizeTest {

    @Test
    public void dovrebbeMantenerSpaziTraParole() {
        Assertions.assertEquals(new Normalize("!!mA..ria P@auLa").get(), "mAria PauLa");
        Assertions.assertEquals(new Normalize("!!mA..ria M@ddalen4").get(), "mAria Mddalen");
        Assertions.assertEquals(new Normalize("!!mA..riana  11pa0ol@a    gi242NA").get(), "mAriana paola giNA");

    }

    @Test
    public void dovrebbePulireCaratteriStranni() {
        Assertions.assertEquals(new Normalize("33ROjas").get(), "ROjas");
        Assertions.assertEquals(new Normalize("°°Roj11Itas").get(), "RojItas");
    }


}