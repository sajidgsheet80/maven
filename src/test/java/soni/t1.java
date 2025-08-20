package soni;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class t1 {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Path to Excel file
        FileInputStream file = new FileInputStream(new File("src/test/java/soni/myfile.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

   
        WebDriver driver = new ChromeDriver();

        // Loop through Excel rows (skip header row = 0)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();

            System.out.println("Testing login with: " + username + " / " + password);

            // Open login page
            driver.get("https://sajidshaikh.in/st"); // Change to your site

            // Fill in login form
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("username")).sendKeys(username);

            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(password);

            // Submit the form
            driver.findElement(By.xpath("//*[@id=\"login-container\"]/button")).click();

            Thread.sleep(2000); // wait for page to load

            if (driver.getPageSource().contains("Welcome to Home Page")) {
                System.out.println(" Login successful");
            } else {
                System.out.println(" Login failed");
            }
        }

        driver.quit();
        workbook.close();
        file.close();
    }
}


