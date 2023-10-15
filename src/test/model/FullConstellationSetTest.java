package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class FullConstellationSetTest {
    FullConstellationSet f1;
    @BeforeEach
    public void setup(){
        f1 = new FullConstellationSet();
    }

    @Test
    public void testGetAllNames(){
        assertEquals("Cepheus",f1.getAllNames().get(87));
        assertEquals("Telescopium",f1.getAllNames().get(0));
        assertEquals("Ophiuchus",f1.getAllNames().get(44));

    }

    @Test
    public void testGetMatchingDescription(){
        assertEquals("Daughter of Cassiopeia, in the Greek myth, who was chained to a rock to be "
                                + "eaten by the sea monster Cetus.",
                f1.getMatchingDescription("Andromeda"));
        assertEquals("Its name means the little fox in Latin",
                f1.getMatchingDescription("Vulpecula"));
        assertEquals("Associated with being associated with Chiron, a king of the centaurs",
                f1.getMatchingDescription("Centaurus"));
    }
}
