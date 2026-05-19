package org.sebsy.grasps.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Pure Fabrication : classe artificielle pour extraire la responsabilité
 * de conversion de date hors du controller (haute cohésion).
 */
public class DateConverter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public LocalDateTime toLocalDateTime(String dateStr) {
        return LocalDateTime.parse(dateStr, FORMATTER);
    }
}
