package org.example;

import lombok.Getter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBUtil {
    @Getter
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("data.odb");
    }

}

