# 🛡️ Log Analiz ve Saldırı Tespit Sistemi

Bu proje; nesne yönelimli programlama (OOP) ilkeleri kullanılarak Java ile geliştirilmiş, kullanıcı girişlerini ve IP adreslerini doğrulayan, hatalı denemeleri kayıt altına alarak şüpheli aktiviteleri ve yetkisiz sızma girişimlerini tespit eden konsol tabanlı bir güvenlik uygulamasıdır.

---

## 🚀 Özellikler

* **Nesne Yönelimli Mimari (OOP):** `User`, `AdminUser`, `Log`, `SecuritySystem` ve `LogAnalyzer` sınıfları arasında kalıtım (inheritance) ve kapsülleme (encapsulation) prensipleri.
* **Kullanıcı & IP Doğrulama:** Kayıtlı kullanıcıların kullanıcı adı ve IP eşleşmeleri üzerinden güvenli oturum açma denetimi.
* **Loglama ve Takip:** Yapılan tüm başarılı ve başarısız giriş denemelerini anlık olarak loglama.
* **Kritik Eşik Uyarıları:** Belirlenen sınırın (maksimum hatalı deneme) üzerindeki başarısız girişleri tespit ederek uyarı üretme.
* **Admin Paneli:** Yetkili yöneticilerin sistemdeki tüm log kayıtlarını inceleyebilmesi ve otomatik güvenlik analizi çalıştırabilmesi.

---

## 🛠️ Kullanılan Teknolojiler

* **Java (JDK)**
* **Apache Maven** (Proje yapılandırma ve yönetim aracı)
* **Java Collections Framework** (`ArrayList`, `HashMap`)

---

## 📂 Proje Yapısı

```
saldiriTespit/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── mycompany/
│                   └── saldiritespit/
│                       └── SaldiriTespit.java
│
├── pom.xml
└── README.md
```
---

##  ⚙️ Kurulum ve Çalıştırma

1. Projeyi bilgisayarınıza klonlayın veya indirin.

2. Projeyi Apache NetBeans veya tercih ettiğiniz bir Java IDE'sinde açın.

3. SaldiriTespit.java dosyasını çalıştırın.

4. Konsol üzerinden sunulan menüyü kullanarak giriş yapabilir, hatalı denemeler yapabilir ve admin girişi ile logları inceleyebilirsiniz.

##  💻 Kullanım Senaryoları

Normal Kullanıcı Girişi: Tanımlı kullanıcılar (Fazli - IP: 1.1.1.1 veya Mehmet - IP: 2.2.2.2) ile sisteme giriş yapmayı deneyebilirsiniz.

Admin Paneli: Şifre kısmına AhmetAdmin yazarak sistem loglarını listeleyebilir ve güvenlik analizini çalıştırabilirsiniz.
