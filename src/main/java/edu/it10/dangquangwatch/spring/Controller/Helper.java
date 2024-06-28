package edu.it10.dangquangwatch.spring.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Helper {
  public static String getCurrentDateFormatted() {
    // Lấy ngày hiện tại
    LocalDate today = LocalDate.now();

    // Định dạng ngày theo yyyy-MM-dd
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = today.format(formatter);

    // In ra ngày hiện tại theo định dạng yyyy-MM-dd
    return formattedDate;
  }

  public static String getCurrentDateFormatted(Date date) {
    // Lấy ngày hiện tại
    LocalDate today = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());

    // Định dạng ngày theo yyyy-MM-dd
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = today.format(formatter);

    // In ra ngày hiện tại theo định dạng yyyy-MM-dd
    return formattedDate;
  }

  public static String getCurrentDateFormatted(LocalDate date) {
    // Định dạng ngày theo yyyy-MM-dd
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = date.format(formatter);

    // In ra ngày hiện tại theo định dạng yyyy-MM-dd
    return formattedDate;
  }
}
