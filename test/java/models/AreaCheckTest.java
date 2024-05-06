    package models;

    import org.junit.jupiter.api.Test;
    import ru.ravvcheck.web3lab.model.services.AreaCheck;

    import java.awt.geom.Area;

    import static org.junit.jupiter.api.Assertions.*;

    public class AreaCheckTest {
        @Test
        public void onCircleTest() {
            assertTrue(AreaCheck.isHit(0, 0, 3));
            assertTrue(AreaCheck.isHit(0, 2, 4));
            assertTrue(AreaCheck.isHit(2, 0, 2));
            assertTrue(AreaCheck.isHit(Math.sqrt(3), 1, 2));
        }

        @Test
        public void onTriangleTest() {
            assertFalse(AreaCheck.isHit(0, 0, 2));
            assertTrue(AreaCheck.isHit(0, 2, 4));
            assertTrue(AreaCheck.isHit(-2, 0, 5));
            assertTrue(AreaCheck.isHit( -1, 0.5, 2));
        }

        @Test
        public void onRectangleTest() {
            assertTrue(AreaCheck.isHit(0, 0, 4));
            assertTrue(AreaCheck.isHit(-2, 0, 2));
            assertTrue(AreaCheck.isHit(0, -3, 3));
            assertTrue(AreaCheck.isHit(-2, -2, 2));
        }

        @Test
        public void inCircleTest() {
            assertTrue(AreaCheck.isHit(1, 1, 5));
            assertTrue(AreaCheck.isHit(2, 1, 4));
            assertTrue(AreaCheck.isHit(2, 1, 3));
            assertTrue(AreaCheck.isHit(3, 3, 4.5));
        }

        @Test
        public void inTriangleTest() {
            assertTrue(AreaCheck.isHit(-1, 1, 5));
            assertTrue(AreaCheck.isHit(-1, 1, 3));
            assertTrue(AreaCheck.isHit(-1, 0.5, 3));
            assertTrue(AreaCheck.isHit(-1, 0.75, 4));
        }

        @Test
        public void intRectangleTest() {
            assertTrue(AreaCheck.isHit(-2, -2.5, 5));
            assertTrue(AreaCheck.isHit(-1, -1, 2));
            assertTrue(AreaCheck.isHit(-1.5, -2, 5));
            assertTrue(AreaCheck.isHit(-0.5, -1.5, 4));
        }

        @Test
        public void onEmptyTest() {
            assertFalse(AreaCheck.isHit(1, 2, 2));
            assertFalse(AreaCheck.isHit(3, -2, 1));
            assertFalse(AreaCheck.isHit(-3, 2, 5));
            assertFalse(AreaCheck.isHit(-3, -3, 2));
        }
    }
