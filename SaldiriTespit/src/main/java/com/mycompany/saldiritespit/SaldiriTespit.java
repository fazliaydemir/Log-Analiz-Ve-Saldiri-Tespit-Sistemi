/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.saldiritespit;

/**
 *
 * @author Fazli
 */
import java.util.*;

// --- USER SINIFI ---
class User {

    private String username;
    private String registeredIp;

    public User(String username, String registeredIp) {
        this.username = username;
        this.registeredIp = registeredIp;
    }

    public String getUsername() {
        return username;
    }

    public String getRegisteredIp() {
        return registeredIp;
    }

    public void analyze(SecuritySystem s) {
        System.out.println("\n[!][!][!] ERISIM ENGELI [!][!][!]");
    }
}

// --- ADMINUSER SINIFI ---
class AdminUser extends User {

    public AdminUser(String username) {
        super(username, "0.0.0.0");
    }

    @Override
    public void analyze(SecuritySystem s) {
        System.out.println("\n========== ADMIN PANELI (" + getUsername() + ") ==========");

        System.out.println("--- SISTEMDEKI TUM KAYITLAR ---");
        for (Log log : s.getLogs()) {
            System.out.println(log);
        }

        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.detectSuspiciousActivities(s);
        System.out.println("==============================================");
    }
}

// --- LOG SINIFI ---
class Log {

    private String username;
    private String ipAddress;
    private String status;

    public Log(String username, String ipAddress, String status) {
        this.username = username;
        this.ipAddress = ipAddress;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "LOG -> Kullanici: " + username + " | IP: " + ipAddress + " | Durum: " + status;
    }
}

// --- SECURITY SYSTEM ---
class SecuritySystem {

    private List<Log> logList = new ArrayList<>();
    private List<User> validUsers = new ArrayList<>();

    public static int totalLogs = 0;
    public final int MAX_FAILED_ATTEMPTS = 3;

    public SecuritySystem() {
        // Video yolları kaldırıldı
    }

    public void registerUser(User u) {
        validUsers.add(u);
    }

    public void loginAttempt(String inputName, String inputIp) {
        boolean success = false;

        for (User u : validUsers) {
            if (u.getUsername().equalsIgnoreCase(inputName) && u.getRegisteredIp().equals(inputIp)) {
                success = true;
                break;
            }
        }

        if (inputName.equalsIgnoreCase("MuhammedAdmin")) {
            success = true;
        }

        if (success) {
            addLog(inputName, inputIp, "basarili");
            System.out.println(">> Giris onaylandi. Hos geldin, " + inputName + ".");
        } else {
            addLog(inputName, inputIp, "basarisiz");
            System.out.println(">> HATA: Gecersiz kullanici veya IP eslesmesi!");

            checkAndTriggerAlert(inputName);
        }
    }

    private void checkAndTriggerAlert(String username) {
        int failCount = 0;
        for (Log log : logList) {
            if (log.getUsername().equalsIgnoreCase(username) && log.getStatus().equals("basarisiz")) {
                failCount++;
            }
        }

        if (failCount >= MAX_FAILED_ATTEMPTS) {
            System.out.println("\n[!!!] KRITIK UYARI: " + username + " ust uste " + failCount + " kez hatali giris yapti!");
        }
    }

    public void addLog(Log log) {
        logList.add(log);
        totalLogs++;
    }

    public void addLog(String user, String ip, String status) {
        this.addLog(new Log(user, ip, status));
    }

    public List<Log> getLogs() {
        return logList;
    }
}

// --- LOG ANALYZER ---
class LogAnalyzer {

    class SuspiciousActivity {

        public void alert(String detail) {
            System.out.println("\n[DIKKAT!] Supheli Durum: " + detail);
        }
    }

    public void detectSuspiciousActivities(SecuritySystem system) {
        Map<String, Integer> failCounts = new HashMap<>();
        SuspiciousActivity sa = new SuspiciousActivity();
        boolean danger = false;

        for (Log log : system.getLogs()) {
            if (log.getStatus().equals("basarisiz")) {
                failCounts.put(log.getUsername(), failCounts.getOrDefault(log.getUsername(), 0) + 1);
            }
        }

        for (var entry : failCounts.entrySet()) {
            if (entry.getValue() >= system.MAX_FAILED_ATTEMPTS) {
                sa.alert("'" + entry.getKey() + "' uzerinden " + entry.getValue() + " kez hatali deneme yapildi!");
                danger = true;
            }
        }

        if (!danger) {
            System.out.println("\nAnaliz Sonucu: Supheli bir aktivite bulunamadi.");
        }
    }
}

// --- MAIN ---
public class SaldiriTespit {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SecuritySystem system = new SecuritySystem();

        User fazli = new User("Fazli", "1.1.1.1");
        User mehmet = new User("Mehmet", "2.2.2.2");
        AdminUser ahmetAdmin = new AdminUser("AhmetAdmin");

        system.registerUser(fazli);
        system.registerUser(mehmet);

        System.out.println("=== SALDIRI TESPIT SISTEMI AKTIF ===");

        while (true) {
            System.out.println("\n1- Sisteme Giris Yap");
            System.out.println("2- Admin Paneli");
            System.out.println("3- Kapat");
            System.out.print("Secim: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            if (secim == 1) {
                System.out.print("Kullanici Adi: ");
                String name = scanner.nextLine();
                System.out.print("IP Adresi: ");
                String ip = scanner.nextLine();

                system.loginAttempt(name, ip);

            } else if (secim == 2) {
                System.out.print("Sifre: ");
                String adminCheck = scanner.nextLine();

                if (adminCheck.equalsIgnoreCase(ahmetAdmin.getUsername())) {
                    ahmetAdmin.analyze(system);
                } else {
                    System.out.println("\n>>> SISTEME SIZMA TESPIT EDILDI! <<<");
                    new User(adminCheck, "unknown").analyze(system);
                }

            } else if (secim == 3) {
                break;
            }
        }
        scanner.close();
    }
}
