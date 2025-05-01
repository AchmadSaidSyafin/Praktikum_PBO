import java.util.ArrayList;
import java.util.Scanner;

// Interface
interface Pembayaran {
    void hitungTagihan();
    void cetakStruk();
}

// Abstract Class
abstract class Pasien {
    private final String id;
    private String nama;
    private int usia;
    private String penyakit;

    public Pasien(String id, String nama, int usia, String penyakit) {
        this.id = id;
        this.nama = nama;
        this.usia = usia;
        this.penyakit = penyakit;
    }

    public final String getId() {
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

    public abstract void tampilkanJenisPasien();

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Usia: " + usia + ", Penyakit: " + penyakit;
    }
}

// Subclass PasienVIP
class PasienVIP extends Pasien implements Pembayaran {
    private String fasilitasTambahan;
    private static final int BIAYA_DASAR = 500_000; // static variabel
    private int totalTagihan;

    public PasienVIP(String id, String nama, int usia, String penyakit, String fasilitasTambahan) {
        super(id, nama, usia, penyakit);
        this.fasilitasTambahan = fasilitasTambahan;
        hitungTagihan();
    }

    @Override
    public void hitungTagihan() {
        totalTagihan = BIAYA_DASAR + 200_000;
    }

    @Override
    public void cetakStruk() {
        System.out.println("=== Struk Pembayaran Pasien VIP ===");
        System.out.println("Nama: " + getNama());
        System.out.println("Total Tagihan: Rp" + totalTagihan);
    }

    @Override
    public void tampilkanJenisPasien() {
        System.out.println("Pasien VIP");
    }

    @Override
    public String toString() {
        return super.toString() + ", Fasilitas Tambahan: " + fasilitasTambahan + ", Total Tagihan: Rp" + totalTagihan;
    }
}

// Subclass PasienBiasa
class PasienBiasa extends Pasien {
    public PasienBiasa(String id, String nama, int usia, String penyakit) {
        super(id, nama, usia, penyakit);
    }

    @Override
    public void tampilkanJenisPasien() {
        System.out.println("Pasien Biasa");
    }
}

// Final class
public final class LayananPoliklinik {
    private static final ArrayList<Pasien> daftarPasien = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

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

            try {
                int pilihan = Integer.parseInt(scanner.nextLine());

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
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    private void tambahPasien() {
        try {
            System.out.print("Pasien VIP? (y/n): ");
            String isVIP = scanner.nextLine();

            System.out.print("Masukkan ID Pasien: ");
            String id = scanner.nextLine();
            System.out.print("Masukkan Nama Pasien: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan Usia Pasien: ");
            int usia = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan Penyakit Pasien: ");
            String penyakit = scanner.nextLine();

            if (isVIP.equalsIgnoreCase("y")) {
                System.out.print("Masukkan Fasilitas Tambahan: ");
                String fasilitas = scanner.nextLine();
                daftarPasien.add(new PasienVIP(id, nama, usia, penyakit, fasilitas));
            } else {
                daftarPasien.add(new PasienBiasa(id, nama, usia, penyakit));
            }

            System.out.println("Pasien berhasil ditambahkan!");
            tampilkanJumlahPasien();
        } catch (NumberFormatException e) {
            System.out.println("Usia harus berupa angka!");
        }
    }

    private void lihatPasien() {
        if (daftarPasien.isEmpty()) {
            System.out.println("Tidak ada data pasien.");
        } else {
            for (Pasien pasien : daftarPasien) {
                System.out.println(pasien);
                pasien.tampilkanJenisPasien();
                if (pasien instanceof Pembayaran) {
                    ((Pembayaran) pasien).cetakStruk();
                }
            }
        }
    }

    private void perbaruiPasien() {
        System.out.print("Masukkan ID Pasien yang akan diperbarui: ");
        String id = scanner.nextLine();
        for (Pasien pasien : daftarPasien) {
            if (pasien.getId().equals(id)) {
                try {
                    System.out.print("Masukkan Nama Baru: ");
                    pasien.setNama(scanner.nextLine());
                    System.out.print("Masukkan Usia Baru: ");
                    pasien.setUsia(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Masukkan Penyakit Baru: ");
                    pasien.setPenyakit(scanner.nextLine());
                    System.out.println("Data pasien berhasil diperbarui!");
                } catch (NumberFormatException e) {
                    System.out.println("Usia harus berupa angka!");
                }
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

    // Static method tambahan
    public static void tampilkanJumlahPasien() {
        System.out.println("Total Pasien Saat Ini: " + daftarPasien.size());
    }
}
