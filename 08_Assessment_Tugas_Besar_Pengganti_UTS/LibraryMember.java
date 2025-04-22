import java.util.Date;

public class LibraryMember {

    private static class PersonalInfo {
        String namaLengkap;
        String alamat;
        String nomorTelepon;
        String email;
        String jenisKelamin;

        PersonalInfo(String namaLengkap, String alamat, String nomorTelepon, String email, String jenisKelamin) {
            this.namaLengkap = namaLengkap;
            this.alamat = alamat;
            this.nomorTelepon = nomorTelepon;
            this.email = email;
            this.jenisKelamin = jenisKelamin;
        }
    }

    private static class MembershipInfo {
        String kodeAnggota;
        Date tanggalGabung;
        boolean statusAktif;
        String tingkatKeanggotaan;

        MembershipInfo(String kodeAnggota, Date tanggalGabung, boolean statusAktif, String tingkatKeanggotaan) {
            this.kodeAnggota = kodeAnggota;
            this.tanggalGabung = tanggalGabung;
            this.statusAktif = statusAktif;
            this.tingkatKeanggotaan = tingkatKeanggotaan;
        }
    }

    private static class ActivityStats {
        int jumlahBukuDipinjam;
        int jumlahTerlambat;
        int jumlahDenda;
        int poinLoyalitas;

        ActivityStats(int jumlahBukuDipinjam, int jumlahTerlambat, int jumlahDenda, int poinLoyalitas) {
            this.jumlahBukuDipinjam = jumlahBukuDipinjam;
            this.jumlahTerlambat = jumlahTerlambat;
            this.jumlahDenda = jumlahDenda;
            this.poinLoyalitas = poinLoyalitas;
        }
    }

    private static class MemberPreferences {
        String kodeReferal;
        boolean langgananBuletin;

        MemberPreferences(String kodeReferal, boolean langgananBuletin) {
            this.kodeReferal = kodeReferal;
            this.langgananBuletin = langgananBuletin;
        }
    }

    private PersonalInfo personalInfo;
    private MembershipInfo membershipInfo;
    private ActivityStats activityStats;
    private MemberPreferences memberPreferences;

    private LibraryMember(Builder builder) {
        this.personalInfo = builder.personalInfo;
        this.membershipInfo = builder.membershipInfo;
        this.activityStats = builder.activityStats;
        this.memberPreferences = builder.memberPreferences;
    }

    public static class Builder {
        private PersonalInfo personalInfo;
        private MembershipInfo membershipInfo;
        private ActivityStats activityStats;
        private MemberPreferences memberPreferences;

        public Builder personalInfo(String nama, String alamat, String telp, String email, String gender) {
            this.personalInfo = new PersonalInfo(nama, alamat, telp, email, gender);
            return this;
        }

        public Builder membershipInfo(String kode, Date gabung, boolean aktif, String tingkat) {
            this.membershipInfo = new MembershipInfo(kode, gabung, aktif, tingkat);
            return this;
        }

        public Builder activityStats(int buku, int terlambat, int denda, int poin) {
            this.activityStats = new ActivityStats(buku, terlambat, denda, poin);
            return this;
        }

        public Builder memberPreferences(String referal, boolean buletin) {
            this.memberPreferences = new MemberPreferences(referal, buletin);
            return this;
        }

        public LibraryMember build() {
            return new LibraryMember(this);
        }
    }

    public void cetakProfilLengkap() {
        System.out.println("===== PROFIL ANGGOTA =====");
        System.out.println("Nama         : " + personalInfo.namaLengkap);
        System.out.println("Jenis Kelamin: " + personalInfo.jenisKelamin);
        System.out.println("Alamat       : " + personalInfo.alamat);
        System.out.println("Telepon      : " + personalInfo.nomorTelepon);
        System.out.println("Email        : " + personalInfo.email);
        System.out.println("Kode Anggota : " + membershipInfo.kodeAnggota);
        System.out.println("Tanggal Gabung: " + membershipInfo.tanggalGabung);
        System.out.println("Status Aktif : " + membershipInfo.statusAktif);
        System.out.println("Tingkat      : " + membershipInfo.tingkatKeanggotaan);
        System.out.println("Buku Dipinjam: " + activityStats.jumlahBukuDipinjam);
        System.out.println("Terlambat    : " + activityStats.jumlahTerlambat);
        System.out.println("Denda        : Rp " + activityStats.jumlahDenda);
        System.out.println("Poin         : " + activityStats.poinLoyalitas);
        System.out.println("Kode Referal : " + memberPreferences.kodeReferal);
        System.out.println("Langganan Buletin: " + memberPreferences.langgananBuletin);
        System.out.println("Skor Risiko : " + hitungSkorRisiko());
        System.out.println("Layak Upgrade?: " + periksaKelayakanUpgrade());
        System.out.println("===========================");
    }

    public boolean periksaKelayakanUpgrade() {
        return membershipInfo.tingkatKeanggotaan.equals("DASAR") && activityStats.poinLoyalitas > 100;
    }

    public double hitungSkorRisiko() {
        double skor = 0;
        skor += activityStats.jumlahTerlambat * 1.5;
        skor += activityStats.jumlahDenda * 0.1;
        if (!membershipInfo.statusAktif) skor += 5;
        if (membershipInfo.tingkatKeanggotaan.equals("DASAR")) skor += 2;
        if (activityStats.jumlahBukuDipinjam > 50) skor -= 1.5;
        return skor;
    }
}
