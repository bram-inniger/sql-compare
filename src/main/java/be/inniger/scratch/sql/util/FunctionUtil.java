package be.inniger.scratch.sql.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Optional;

@UtilityClass
public class FunctionUtil {

    public static <T> void noop(T __) {
        // Do nothing
    }

    public static <T> Optional<T> trySingle(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Optional.empty();
        } else if (collection.size() >= 2) {
            throw new IndexOutOfBoundsException("Expected 0 or 1 elements, got %d".formatted(collection.size()));
        } else {
            return Optional.of(collection.iterator().next());
        }
    }
}
