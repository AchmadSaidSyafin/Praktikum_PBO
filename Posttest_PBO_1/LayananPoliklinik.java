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

    public int getUsia() {
        return usia;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Usia: " + usia + ", Penyakit: " + penyakit;
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
                    tambahPasien();
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

    private void tambahPasien() {
        System.out.print("Masukkan ID Pasien: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Pasien: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Usia Pasien: ");
        int usia = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukkan Penyakit Pasien: ");
        String penyakit = scanner.nextLine();
        
        daftarPasien.add(new Pasien(id, nama, usia, penyakit));
        System.out.println("Pasien berhasil ditambahkan!");
    }

    private void lihatPasien() {
        if (daftarPasien.isEmpty()) {
            System.out.println("Tidak ada data pasien.");
        } else {
            for (Pasien pasien : daftarPasien) {
                System.out.println(pasien);
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
                pasien.setUsia(scanner.nextInt());
                scanner.nextLine();
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
