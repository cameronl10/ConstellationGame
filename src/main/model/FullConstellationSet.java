package model;

import java.util.ArrayList;
import java.util.HashMap;

//Represents a class that holds all 88 official constellations and their matching descriptions, can give either just
// the names or descriptions separately.
public class FullConstellationSet {
    private final HashMap<String, String> constellations = new HashMap<>();

    //MODIFIES: this
    //EFFECTS: creates the full HashMap of the 88 official constellation names as a key and their
    //         description value pair
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void initializeConstellationMap() {
        constellations.put("Andromeda", "Daughter of Cassiopeia, in the Greek myth, who was chained to a rock to be "
                + "eaten by the sea monster Cetus.");
        constellations.put("Antlia", "Its name means pump in Latin and Greek; it represents an air pump.");
        constellations.put("Apus", " It represents a bird-of-paradise, and its name means without feet in Greek");
        constellations.put("Aquarius", "represent a cup bearer or water bearer, "
                + "which is what its name means in Latin.");
        constellations.put("Aquila", "The constellation represents the eagle of the god Jupiter in Roman mythology.");
        constellations.put("Ara", "The constellation represents the altar used by Zeus and other Greek gods to swear a"
                + "vow of allegiance");
        constellations.put("Aries", "Its representation as a ram is identified with the Egyptian god Amon and,in "
                + "Greek mythology");
        constellations.put("Auriga", "The mythological character is based on is often depicted holding a "
                + "female goat and her kids, along with the reins of a chariot.");
        constellations.put("Boötes", " a constellation in the northern hemisphere containing one of the brightest stars"
                + "in the night sky, Arcturus.");
        constellations.put("Caelum", "Its name means “the chisel” in Latin.");
        constellations.put("Camelopardalis", "a large but faint constellation of the northern sky "
                + "representing a giraffe.");
        constellations.put("Cancer", "represents the giant crab that attacked Hercules during the second of the "
                + "12 labors");
        constellations.put("Canes Venatici", "is supposed to represent the two hunting dogs held on a leash");
        constellations.put("Canis Major", "thought to represent the larger of the two hunting dogs");
        constellations.put("Canis Minor", "thought to represent the smaller of the two hunting dogs");
        constellations.put("Capricornus", "Its name means “the goat” in Latin.");
        constellations.put("Carina", "this means the keel or bottom of a ship.");
        constellations.put("Cassiopeia", "a constellation of the northern sky easily recognized by a group of five "
                + "bright stars forming a slightly irregular W.");
        constellations.put("Centaurus", "Associated with being associated with Chiron, a king of the centaurs");
        constellations.put("Cepheus", "named after the mythical king of Aethiopia.");
        constellations.put("Cetus", "a sea monster in Greek mythology which both Perseus and Heracles needed to slay");
        constellations.put("Chamaeleon", "is a small constellation in the deep southern sky named after a lizard");
        constellations.put("Circinus", " its name means “the compass” in Latin.");
        constellations.put("Columba", "Its name is Latin for dove.");
        constellations.put("Coma Berenices", "named after a queen that sacrificed her hair to the goddess Aphrodite to "
                + "ensure the safe return of her husband,");
        constellations.put("Corona Australis", "Its Latin name means southern crown");
        constellations.put("Corona Borealis", "a small constellation in the Northern Celestial Hemisphere");
        constellations.put("Corvus", "Its name means  crow in Latin.");
        constellations.put("Crater", "represents the cup of the god Apollo. The cup is usually depicted as a two-handed"
                + "chalice.");
        constellations.put("Crux", " constellation of the southern sky that is centred on four bright stars in a "
                + "cross-shaped asterism ");
        constellations.put("Cygnus", "its name from the Latinized Greek word for swan.");
        constellations.put("Delphinus", "Its name means “dolphin” in Latin.");
        constellations.put("Dorado", "Its name refers to the dolphinfish in Spanish");
        constellations.put("Draco", "the constellation represents Ladon, the dragon");
        constellations.put("Equuleus", " the horse struck from Neptune's trident during the contest between "
                + "him and Athena");
        constellations.put("Eridanus", "a constellation visible in the Northern and Southern hemispheres, "
                + "representing a river");
        constellations.put("Fornax", "Its name means “the furnace” in Latin.");
        constellations.put("Gemini", "made up of two twins: Castor and Pollux.");
        constellations.put("Grus", "Its name is Latin for the crane, a type of bird.");
        constellations.put("Hercules", "The Sumerians associated the constellation with the hero Gilgamesh.");
        constellations.put("Horologium", "named this constellation to honor Christian Huygens, the inventor "
                + "of the pendulum clock ");
        constellations.put("Hydra", " a fresh-water serpent born to Echidne and Typhon.");
        constellations.put("Hydrus", "It represents the sea snakes that the Dutch explorers would have seen on "
                + "their voyages.");
        constellations.put("Indus", "The constellation was created by the Dutch astronomer Petrus "
                + "Plancius in the late 16th century ");
        constellations.put("Lacerta", "Latin for lizard.");
        constellations.put("Leo", "To the ancient Greeks and Romans the constellation represented the Nemean lion");
        constellations.put("Leo Minor", "Its name is Latin for the smaller lion");
        constellations.put("Lepus", "Its name is Latin for hare.");
        constellations.put("Libra", "Latin for the weighing scales");
        constellations.put("Lupus", "Its name is Latin for wolf.");
        constellations.put("Lynx", "created by the Polish astronomer Johannes Hevelius in the 17th century to "
                + "fill a large gap between the constellations Auriga and Ursa Major. ");
        constellations.put("Lyra", "It represents the lyre");
        constellations.put("Mensa", "represents Table Mountain near Cape Town");
        constellations.put("Microscopium", "It represents the microscope");
        constellations.put("Monoceros", "Its name means unicorn in Latin.");
        constellations.put("Musca", "named for its shape, which resembles that of a housefly.");
        constellations.put("Norma", "It represents a carpenter's tool called a square, or a level");
        constellations.put("Octans", "Its name is Latin for the eighth part of a circle");
        constellations.put("Ophiuchus", "represented as a man grasping a snake.");
        constellations.put("Orion", "looks like a hunter with a line of three bright stars for a belt");
        constellations.put("Pavo", "Latin for peacock");
        constellations.put("Pegasus", "represents the famous winged horse in Greek mythology");
        constellations.put("Perseus", "represents the hero that slew the Medusa and rescued the "
                + "princess Andromeda from the sea monster Cetus. ");
        constellations.put("Phoenix", "represents the legendary bird that would be consumed by "
                + "fire and then emerge from the flames reborn.");
        constellations.put("Pictor", "Its name means painter in Latin");
        constellations.put("Pisces", "represents the mythical fish that appeared in the story of "
                + "Aphrodite and her son Eros. ");
        constellations.put("Piscis Austrinus", "The name is Latin for the southern fish");
        constellations.put("Puppis", "It represents the stern of the ship Argo");
        constellations.put("Pyxis", "its name is Latin for a mariner's compass");
        constellations.put("Reticulum", "Its name is Latin for a small net");
        constellations.put("Sagitta", "Its name is Latin for archer");
        constellations.put("Sagittarius", "commonly thought to represent a centaur");
        constellations.put("Scorpius", "It represents a scorpion sent by the goddess Artemis ");
        constellations.put("Sculptor", "depicted as a carved head lying on a table with a "
                + "sculptor's mallet and chisels");
        constellations.put("Scutum", "Its name is Latin for shield");
        constellations.put("Serpens", "represents a snake held by the healer Asclepius");
        constellations.put("Sextans", "represents an astronomer's device once used"
                + " to measure the positions of the stars");
        constellations.put("Taurus", "represents a bull ");
        constellations.put("Telescopium", "It represents a telescope.");
        constellations.put("Triangulum", "Its name is Latin for triangle");
        constellations.put("Triangulum Australe", "Its name is Latin for the southern triangle");
        constellations.put("Tucana", "named after the toucan");
        constellations.put("Ursa Major", "also called the Great Bear");
        constellations.put("Ursa Minor", "represents a small bear with a long tail.");
        constellations.put("Vela", "The constellation represents the sails of the Argo Navis");
        constellations.put("Virgo", "represents both the goddess of justice and the goddess of the harvest");
        constellations.put("Volans", "represents a type of tropical fish that can jump out of the water "
                + "and glide through the air on wings");
        constellations.put("Vulpecula", "Its name means the little fox in Latin");
        EventLog.getInstance().logEvent(new Event("created full set of constellations"));
    }

    //EFFECTS: returns an ArrayList of strings that is only the name part of the full constellation hashmap
    public ArrayList<String> getAllNames() {
        initializeConstellationMap();
        ArrayList<String> names = new ArrayList<>();
        for (String name : constellations.keySet()) {
            names.add(name);
        }
        EventLog.getInstance().logEvent(new Event("returned list of all constellation names"));
        return names;
    }

    //EFFECTS: returns a String that matches a given constellationName key to its description
    public String getMatchingDescription(String constellationName) {
        initializeConstellationMap();
        EventLog.getInstance().logEvent(new Event("returned matching description of: "
                + constellations.get(constellationName)));
        return constellations.get(constellationName);
    }


}
