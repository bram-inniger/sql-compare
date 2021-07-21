package be.inniger.scratch.sql.util;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("unused")
public class Lazy<T> {

    private Supplier<T> supplier;
    private T result = null;

    private Lazy(Supplier<T> supplier) {
        this.supplier = requireNonNull(supplier);
    }

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }

    public T get() {
        if (supplier == null) {
            return result;
        }

        result = supplier.get();
        supplier = null;

        return result;
    }
}
