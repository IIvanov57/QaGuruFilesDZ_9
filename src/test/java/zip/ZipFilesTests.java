package zip;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFilesTests {
  private ClassLoader cl = ZipFilesTests.class.getClassLoader();

  @Test
  @DisplayName("Проверка создателя PDF файла")
  void checkPDF() throws Exception {
    PDF pdf = null;
    try (ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("test.zip")))) {
      ZipEntry entry;

      while ((entry = zipInputStream.getNextEntry()) != null) {
        if (entry.getName().equals("test/sample.pdf")) {
          pdf = new PDF(zipInputStream);
          break;
        }
      }
    }
    assert pdf != null;
    Assertions.assertEquals("Writer", pdf.creator);
  }

  @Test
  @DisplayName("Проверка наличия колонок 'First Name' и 'Last Name' в файле xls")
  void checkXLS() throws Exception {
    XLS xls = null;
    try (ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("test.zip")))) {
      ZipEntry entry;

      while ((entry = zipInputStream.getNextEntry()) != null) {
        if (entry.getName().equals("test/example_xls.xls")) {
          xls = new XLS(zipInputStream);
          break;
        }
      }
    }
    assert xls != null;
    String firstNameColumn = xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
    String lastNameColumn = xls.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue();

    Assertions.assertEquals("First Name", firstNameColumn);
    Assertions.assertEquals("Last Name", lastNameColumn);
  }

  @Test
  @DisplayName("Проверка размера первой строчки csv")
  void checkCSV() throws Exception {
    List<String[]> data = null;
    try (ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("test.zip")))) {
      ZipEntry entry;

      while ((entry = zipInputStream.getNextEntry()) != null) {
        if (entry.getName().equals("test/addresses.csv")) {
          try (CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream));) {
            data = csvReader.readAll();
          }
          break;
        }
      }
    }

    assert data != null;
    Assertions.assertEquals(6, data.size());

  }
}


