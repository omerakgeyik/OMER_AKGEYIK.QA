# OMER_AKGEYIK.QA

Selenium + **TestNG** ile yazılmış, **Page Object Model (POM)** mimarisi kullanan web UI test otomasyon projesi.  
Proje; tekil test sınıfından veya **testng.xml** üzerinden senaryo/suite bazlı olarak çalıştırılabilir.

## Teknolojiler
- **Java** (JDK 11+ önerilir)
- **Selenium WebDriver**
- **TestNG**
- **Maven** (bağımlılık yönetimi ve çalıştırma)
- (Opsiyonel) **WebDriverManager** — sürücü yönetimini otomatikleştirmek için

> Not: Maven kullanmıyorsanız Gradle ile benzer yapılandırma yapılabilir; bu README Maven akışı üzerinden örnekler içerir.

---

## Gereksinimler
- **JDK 11+** (Java 17 önerilir)
- **Maven 3.8+**
- IDE (IntelliJ IDEA / Eclipse / VS Code + Java eklentileri)
- İnternet bağlantısı (bağımlılıkların indirilmesi için)

---

# Çalıştırma Yöntemleri

Bu proje **Selenium + TestNG + POM** yapısındadır. Testler hem IDE içerisinden hem de komut satırından çalıştırılabilir.

## 1) Test sınıfından (IDE)
- IDE’nizde (IntelliJ/Eclipse) çalıştırmak istediğiniz test sınıfını veya metodu açın (ör. `src/test/java/tests/InsiderCareersTest.java`).
- Sınıf/metot adının solundaki **Run (▶)** ikonuna tıklayın.
- Bu yöntem tekil sınıf/metot debug’ı ve hızlı geri bildirim için idealdir.

---

## 2) `testng.xml` üzerinden (IDE)
- Proje kökündeki veya `src/xmlFiles` altındaki `testng.xml` dosyasını açın.
- Dosyayı **Run (▶)** ile çalıştırın.
