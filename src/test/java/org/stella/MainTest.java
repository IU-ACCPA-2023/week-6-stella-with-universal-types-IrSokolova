package org.stella;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class MainTest {


    @ParameterizedTest(name = "{index} Typechecking well-typed program {0}")
    @ValueSource(strings = {
            "tests/universal-types/well-typed/universal-types-1.stella",
            "tests/universal-types/well-typed/universal-types-2.stella",
            "tests/universal-types/well-typed/universal-types-3.stella",
            "tests/universal-types/well-typed/universal-types-4.stella",
            "tests/universal-types/well-typed/universal-types-5.stella",
            "tests/universal-types/well-typed/universal-types-6.stella"
    })
    void testWellTyped(String filepath) throws Exception {
        String[] args = new String[0];
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(filepath);
        System.setIn(fips);
        Assertions.assertDoesNotThrow(() -> Main.main(args));
        System.setIn(original);
    }

    @ParameterizedTest(name = "{index} Typechecking ill-typed program {0}")
    @ValueSource(strings = {
            "tests/universal-types/ill-typed/bad-universal-types-1.stella",
            "tests/universal-types/ill-typed/bad-universal-types-2.stella",
            "tests/universal-types/ill-typed/bad-universal-types-3.stella",
            "tests/universal-types/ill-typed/bad-universal-types-4.stella",
            "tests/universal-types/ill-typed/bad-universal-types-5.stella",
            "tests/universal-types/ill-typed/bad-universal-types-6.stella"
    })
    void testIllTyped(String filepath) throws Exception {
        String[] args = new String[0];
        final FileInputStream fips = new FileInputStream(filepath);
        System.setIn(fips);

        // Change Exception class to your specific
        Exception exception = assertThrows(Exception.class, () -> Main.main(args), "Expected the type checker to fail!");
        System.out.println("Type Error: " + exception.getMessage());
    }
}
