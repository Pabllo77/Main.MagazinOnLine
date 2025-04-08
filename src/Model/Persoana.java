package Model;


//Clasa abstracta cu variabilele
    public abstract class Persoana {
        private String nume;
        private int id;
        private int varsta;
        private static int nrTotalPersoane = 0;
        private int idMagazin;

        //Constructor pt clasa Persoana


        public Persoana(String nume, int varsta) {
            this.nume = nume;
            this.varsta = varsta;
            this.id = ++nrTotalPersoane;
        }
        // Getter si setteri pt a permite accesul la variabilele private

        public String getNume() {
            return nume;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public int getVarsta() {
            return varsta;
        }

        public void setVarsta(int varsta) {
            this.varsta = varsta;
        }

        public int getId() {
            return id;
        }

        public int getIdMagazin() {
            return idMagazin;
        }

        public void setIdMagazin(int idMagazin) {
            this.idMagazin = idMagazin;
        }

        public abstract void afiseazaDetalii();
    }

