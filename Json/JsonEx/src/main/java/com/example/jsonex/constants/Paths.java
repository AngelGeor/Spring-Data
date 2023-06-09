package com.example.jsonex.constants;

import java.nio.file.Path;

public enum Paths {
    ;

    public static final Path USER_JSON_PATH = Path.of("src", "main", "resources", "dbContent",
            "users.json");

    public static final Path CATEGORIES_JSON_PATH = Path.of("src", "main", "resources", "dbContent",
            "categories.json");

    public static final Path PRODUCTS_JSON_PATH = Path.of("src", "main", "resources", "dbContent",
            "products.json");


}
