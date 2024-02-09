package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PersistenceStorage {
    public String getLastState();
    public void store(String state);
}
