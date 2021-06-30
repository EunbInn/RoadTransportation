package webParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dbConnect.DBControl;


public class WebParser {
    String WEB_DRIVER_ID = "webdriver.chrome.driver";
    String WEB_DRIVER_PATH = "C:\\Users\\inna\\Desktop\\importFile\\chromedriver.exe";
    WebDriver driver; 
    public static void main(String[] args) {
        
        WebParser wp = new WebParser();
        List<RawData> rawDatas = wp.parser();
        DBControl dc = new DBControl();
        dc.create(rawDatas);
        WriteOnCSV woc = new WriteOnCSV();
        woc.writer(rawDatas);
    
    }
    
    public WebParser() {
        
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
    }
    
    String baseUrl = "http://www.roadplus.co.kr/main/main.do";
    //날짜표시
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
    
    public List<RawData> parser() {
        List<RawData> rawDatas = new ArrayList<RawData>();
        try {
            driver.get(baseUrl);
            Thread.sleep(2000);
            
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/ul[1]/li[4]/a")).click();
            Thread.sleep(2000);
            RawData raw = null;
            int cnt = 1;
            while (true) {
                try {
                    String time = sdf.format(cal.getTime());
                    String road = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/ul/li[" + cnt + "]/a")).getText();
                    String roadNumber = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/ul/li[" + cnt + "]/a/i")).getText();
                    int roadInt = Integer.parseInt(roadNumber);
                    road = road.replace(roadNumber, "");
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/ul/li[" + cnt + "]/a")).click();
                    
                    Thread.sleep(2000);
                    System.out.println("<" + road + ">");
                    int fromNum = 1;
                    String fromTo = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/ul/li[1]/a")).getText();
                    System.out.println("---------------------------" + fromTo);
                    int i = 1;
                    while (true) {
                        try {
                            String roadName = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/div[5]/ul/li[" + i + "]/div[1]/dl/dt")).getText();
                            String color = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/div[5]/ul/li[" + i + "]")).getAttribute("class");
                            double distance = Double.parseDouble(roadName.split(" \\(")[1].replace("km)", "").replace("(", ""));
                            roadName = roadName.split(" \\(")[0];
                            String speed = "";
                            try {
                                speed = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/div[5]/ul/li[" + i + "]/div[1]/dl/dd")).getText();
                                speed = speed.replace("km/h", "");
                            } catch (Exception e) {}
                            
                            if (speed.equals("")) {
                                speed = "0";
                            }
                            double ispeed = Double.parseDouble(speed);
                            raw = new RawData(time, roadInt, road, fromTo, fromNum, roadName, i, distance, ispeed, color);
                            rawDatas.add(raw);
                            i++;
                        } catch (NoSuchElementException e) {
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/ul/li[2]")).click();
                    Thread.sleep(2000);
                    
                    fromTo = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/ul/li[2]/a")).getText();
                    fromNum = 2;
                    System.out.println("---------------------------" + fromTo);
                    int j = 1;
                    while (true) {
                        try {
                            String roadName = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/div[5]/ul/li[" + j + "]/div[1]/dl/dt")).getText();
                            double distance = Double.parseDouble(roadName.split(" \\(")[1].replace("km)", "").replace("(", ""));
                            roadName = roadName.split(" \\(")[0];
                            String color = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/div[5]/ul/li[" + j + "]")).getAttribute("class");
                            String speed = "";
                            try {
                                speed = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/div[5]/ul/li[" + j + "]/div[1]/dl/dd")).getText();
                                speed = speed.replace("km/h", "");
                            } catch (NoSuchElementException e) {}
                            
                            if (speed.equals("")) {
                                speed = "0";
                            }
                            double ispeed = Double.parseDouble(speed);
                            raw = new RawData(time, roadInt, road, fromTo, fromNum, roadName, j, distance, ispeed, color);
                            rawDatas.add(raw);
                            j++;
                        } catch (NoSuchElementException e) {
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    cnt++;
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/ul[1]/li[4]/a")).click();
                    Thread.sleep(2000);
                } catch (NoSuchElementException e) {
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            driver.close();
    
        } catch (Exception e) {
            
        }
        
        return rawDatas;
    }
    
}
