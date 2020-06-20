package net.anglocraft.claim;

import java.util.Objects;

/**
 * @author Ben Kinney
 * @since 2020/06/20
 */
public final class Claim {

    static class Key {
        private final int x;
        private final int z;

        public Key(int x, int z) {
            this.x = x;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return x == key.x &&
                   z == key.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, z);
        }
    }

    private final Key key;

    public Claim(Key key) {
        this.key = key;
    }

    public int getX() {
        return key.x;
    }

    public int getZ() {
        return key.z;
    }
}
