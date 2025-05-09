import java.util.ArrayList;
import java.util.Scanner;

class Pasien {
    private String id;
    private String nama;
    private int usia;
    private String penyakit;

    public Pasien(String id, String nama, int usia, String penyakit) {
        this.id = id;
        this.nama = nama;
        this.usia = usia;
        this.penyakit = penyakit;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (!nama.isEmpty()) {
            this.nama = nama;
        } else {
            System.out.println("Nama tidak boleh kosong!");
        }
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        if (usia > 0) {
            this.usia = usia;
        } else {
            System.out.println("Usia harus lebih dari 0!");
        }
    }

    public String getPenyakit() {
        return penyakit;
    }

    public void setPenyakit(String penyakit) {
        if (!penyakit.isEmpty()) {
            this.penyakit = penyakit;
        } else {
            System.out.println("Penyakit tidak boleh kosong!");
        }
    }

    // Method yang akan dioverride
    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Usia: " + usia + ", Penyakit: " + penyakit;
    }
}

// Subclass PasienVIP untuk Polymorphism (Override)
class PasienVIP extends Pasien {
    private String fasilitasTambahan;

    public PasienVIP(String id, String nama, int usia, String penyakit, String fasilitasTambahan) {
        super(id, nama, usia, penyakit);
        this.fasilitasTambahan = fasilitasTambahan;
    }

    @Override
    public String toString() {
        return super.toString() + ", Fasilitas Tambahan: " + fasilitasTambahan;
    }
}

public class LayananPoliklinik {
    private static ArrayList<Pasien> daftarPasien = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new LayananPoliklinik().run();
    }

    public void run() {
        while (true) {
            System.out.println("\n=== Layanan Poliklinik Rumah Sakit Abdul Rifai ===");
            System.out.println("1. Tambah Pasien");
            System.out.println("2. Lihat Daftar Pasien");
            System.out.println("3. Perbarui Data Pasien");
            System.out.println("4. Hapus Pasien");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahPasien(); // Polymorphism: Method overloading digunakan di sini
                    break;
                case 2:
                    lihatPasien();
                    break;
                case 3:
                    perbaruiPasien();
                    break;
                case 4:
                    hapusPasien();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan ini.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    // Overloading: dua versi method tambahPasien
    private void tambahPasien() {
        System.out.print("Pasien VIP? (y/n): ");
        String isVIP = scanner.nextLine();

        System.out.print("Masukkan ID Pasien: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Pasien: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Usia Pasien: ");
        int usia = scanner.nextInt();
        scanner.nextLine(); // buang newline
        System.out.print("Masukkan Penyakit Pasien: ");
        String penyakit = scanner.nextLine();

        if (isVIP.equalsIgnoreCase("y")) {
            System.out.print("Masukkan Fasilitas Tambahan: ");
            String fasilitas = scanner.nextLine();
            tambahPasien(id, nama, usia, penyakit, fasilitas); // Versi Overload
        } else {
            daftarPasien.add(new Pasien(id, nama, usia, penyakit));
        }

        System.out.println("Pasien berhasil ditambahkan!");
    }

    // Overloaded method
    private void tambahPasien(String id, String nama, int usia, String penyakit, String fasilitasTambahan) {
        daftarPasien.add(new PasienVIP(id, nama, usia, penyakit, fasilitasTambahan));
    }

    private void lihatPasien() {
        if (daftarPasien.isEmpty()) {
            System.out.println("Tidak ada data pasien.");
        } else {
            for (Pasien pasien : daftarPasien) {
                System.out.println(pasien); // Polymorphism: Dynamic method dispatch ke toString()
            }
        }
    }

    private void perbaruiPasien() {
        System.out.print("Masukkan ID Pasien yang akan diperbarui: ");
        String id = scanner.nextLine();
        for (Pasien pasien : daftarPasien) {
            if (pasien.getId().equals(id)) {
                System.out.print("Masukkan Nama Baru: ");
                pasien.setNama(scanner.nextLine());
                System.out.print("Masukkan Usia Baru: ");
                int usiaBaru = scanner.nextInt();
                scanner.nextLine();
                pasien.setUsia(usiaBaru);
                System.out.print("Masukkan Penyakit Baru: ");
                pasien.setPenyakit(scanner.nextLine());
                System.out.println("Data pasien berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Pasien dengan ID tersebut tidak ditemukan.");
    }

    private void hapusPasien() {
        System.out.print("Masukkan ID Pasien yang akan dihapus: ");
        String id = scanner.nextLine();
        for (Pasien pasien : daftarPasien) {
            if (pasien.getId().equals(id)) {
                daftarPasien.remove(pasien);
                System.out.println("Pasien berhasil dihapus.");
                return;
            }
        }
        System.out.println("Pasien dengan ID tersebut tidak ditemukan.");
    }
}
