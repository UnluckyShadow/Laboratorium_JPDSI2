package com.jsfcourse.calc;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

    @Named
    @RequestScoped
    public class CalcBB {
        private double kwota;
        private int lata;
        private double oprocentowanie;
        private Double wynik;

        public double getKwota() {
            return kwota;
        }

        public void setKwota(double kwota) {
            this.kwota = kwota;
        }

        public int getLata() {
            return lata;
        }

        public void setLata(int lata) {
            this.lata = lata;
        }

        public double getOprocentowanie() {
            return oprocentowanie;
        }

        public void setOprocentowanie(double oprocentowanie) {
            this.oprocentowanie = oprocentowanie;
        }

        public Double getWynik() {
            return wynik;
        }

        public void setWynik(Double wynik) {
            this.wynik = wynik;
        }

        private void obliczRate() {
            double miesiecznaStopa = (oprocentowanie / 100) / 12;
            int liczbaMiesiecy = lata * 12;

            double rata = (kwota * miesiecznaStopa) /
                          (1 - Math.pow(1 + miesiecznaStopa, -liczbaMiesiecy));

            wynik = Math.round(rata * 100.0) / 100.0;
        }

        public String oblicz() {
            obliczRate();
            return "showresult";
        }

        public String oblicz_AJAX() {
            obliczRate();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Wynik: " + wynik + " PLN", null));
            return null;
        }

        public String info() {
            return "info";
        }
}
