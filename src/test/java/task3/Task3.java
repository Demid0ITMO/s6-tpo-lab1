package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import task3.fish.Fish;
import task3.fish.YellowFish;
import task3.item.CornFlakes;
import task3.item.DentrassiUnderwear;
import task3.item.GlassVial;
import task3.item.ScvorshneineMatrass;
import task3.person.Arthur;
import task3.person.Ford;
import task3.person.Person;

import static org.junit.jupiter.api.Assertions.*;

public class Task3 {

    @Nested
    class ArthurTest {
        private Arthur arthur;

        @BeforeEach
        void setUp() {
            arthur = new Arthur();
        }

        @Test
        void constructorShouldSetCorrectNameAndPlanet() {
            assertEquals("Артур", arthur.getName());
            assertEquals("Земля", arthur.getHomePlanet());
        }

        @Test
        void initialConfidenceShouldBe50() {
            assertEquals(50, arthur.getConfidence());
        }

        @Test
        void initialBlinkingShouldBeFalse() {
            assertFalse(arthur.isBlinking());
        }

        @Test
        void lookAtNonCornFlakesShouldNotChangeConfidence() {
            DentrassiUnderwear underwear = new DentrassiUnderwear();
            String result = arthur.lookAt(underwear);

            assertEquals(50, arthur.getConfidence());
            assertTrue(arthur.isBlinking());
            assertEquals("Артур смотрит на нижнее белье дентрасси", result);
        }

        @Test
        void lookAtCornFlakesShouldIncreaseConfidenceBy10() {
            CornFlakes flakes = new CornFlakes();
            String result = arthur.lookAt(flakes);

            assertEquals(60, arthur.getConfidence());
            assertTrue(arthur.isBlinking());
            assertEquals("Артур смотрит на кукурузные хлопья", result);
        }

        @Test
        void resetBlinkShouldSetBlinkingToFalse() {
            arthur.lookAt(new CornFlakes());
            assertTrue(arthur.isBlinking());

            arthur.resetBlink();
            assertFalse(arthur.isBlinking());
        }
    }

    @Nested
    class FordTest {
        private Ford ford;
        private Arthur arthur;

        @BeforeEach
        void setUp() {
            ford = new Ford();
            arthur = new Arthur();
        }

        @Test
        void constructorShouldSetCorrectNameAndPlanet() {
            assertEquals("Форд", ford.getName());
            assertEquals("Бетельгейзе", ford.getHomePlanet());
        }

        @Test
        void vialShouldBeInitializedWithYellowFish() {
            assertNotNull(ford.getVial());
            assertTrue(ford.getVial().getFish() instanceof YellowFish);
        }

        @Test
        void offerFishToEarWithNonNullPersonShouldReturnCorrectString() {
            String expected = "Форд предложил Артур засунуть желтый рыба, которая плавает  и переливается в ухо";
            String actual = ford.offerFishToEar(arthur);

            assertEquals(expected, actual);
        }

        @Test
        void offerFishToEarWithNullPersonShouldThrowNullPointerException() {
            assertThrows(NullPointerException.class, () -> ford.offerFishToEar(null));
        }
    }

    @Nested
    class PersonTest {
        @Test
        void constructorAndGettersShouldWork() {
            Person person = new Person("Тест", "Марс");
            assertEquals("Тест", person.getName());
            assertEquals("Марс", person.getHomePlanet());
        }

        @Test
        void toStringShouldReturnName() {
            Person person = new Person("Тест", "Марс");
            assertEquals("Тест", person.toString());
        }
    }

    @Nested
    class ItemTest {
        @Test
        void cornFlakesToString() {
            CornFlakes flakes = new CornFlakes();
            assertEquals("кукурузные хлопья", flakes.toString());
        }

        @Test
        void dentrassiUnderwearToString() {
            DentrassiUnderwear underwear = new DentrassiUnderwear();
            assertEquals("нижнее белье дентрасси", underwear.toString());
        }

        @Test
        void glassVialToStringAndGetFish() {
            YellowFish fish = new YellowFish();
            GlassVial vial = new GlassVial(fish);

            assertEquals("стеклянный флакон", vial.toString());
            assertSame(fish, vial.getFish());
        }

        @Test
        void scvorshneineMatrassToString() {
            ScvorshneineMatrass matrass = new ScvorshneineMatrass();
            assertEquals("скворшнельские матрасы", matrass.toString());
        }
    }

    @Nested
    class FishTest {
        @Test
        void fishConstructorShouldSetColorAndDefaultStates() {
            Fish fish = new Fish("красный");
            assertEquals("красный", fish.getColor());
            assertTrue(fish.swimming);
            assertTrue(fish.shimmering);
        }

        @Test
        void fishToStringWithDefaultStates() {
            Fish fish = new Fish("синий");
            String expected = "синий рыба, которая плавает  и переливается";
            assertEquals(expected, fish.toString());
        }

        @Test
        void fishToStringAfterChangingStates() {
            Fish fish = new Fish("зеленый");
            fish.swimming = false;
            fish.shimmering = false;

            String expected = "зеленый рыба, которая не плавает  и не переливается";
            assertEquals(expected, fish.toString());
        }

        @Test
        void yellowFishShouldBeYellow() {
            YellowFish yellow = new YellowFish();
            assertEquals("желтый", yellow.getColor());
            assertTrue(yellow.swimming);
            assertTrue(yellow.shimmering);
        }
    }
}