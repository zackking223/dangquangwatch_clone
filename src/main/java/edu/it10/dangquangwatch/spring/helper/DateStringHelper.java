package edu.it10.dangquangwatch.spring.helper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateStringHelper {
  /**
   * @return String: MM/YYYY
   */
  public static String getPreviousMonthYear() {
    // Lấy ngày hiện tại và trừ 1 tháng
    LocalDate previousMonth = LocalDate.now().minusMonths(1);
    // Định dạng theo MM/YYYY
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
    return previousMonth.format(formatter);
  }
  
  /**
   * @return String: MM/YYYY
   */
  public static String getCurrentMonthYear() {
    // Lấy ngày hiện tại
    LocalDate today = LocalDate.now();
    // Định dạng theo MM/YYYY
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
    return today.format(formatter);
  }

  /**
   * @return String: yyyy-MM-dd
   */
  public static String getCurrentDateFormatted() {
    // Lấy ngày hiện tại
    LocalDate today = LocalDate.now();

    // Định dạng ngày theo yyyy-MM-dd
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = today.format(formatter);

    // In ra ngày hiện tại theo định dạng yyyy-MM-dd
    return formattedDate;
  }

  /**
   * @return String: yyyy-MM-dd
   */
  public static String getCurrentDateFormatted(Date date) {
    // Lấy ngày hiện tại
    LocalDate today = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());

    // Định dạng ngày theo yyyy-MM-dd
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = today.format(formatter);

    // In ra ngày hiện tại theo định dạng yyyy-MM-dd
    return formattedDate;
  }

  /**
   * @return String: yyyy-MM-dd
   */
  public static String getCurrentDateFormatted(LocalDate date) {
    // Định dạng ngày theo yyyy-MM-dd
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = date.format(formatter);

    // In ra ngày hiện tại theo định dạng yyyy-MM-dd
    return formattedDate;
  }
}
