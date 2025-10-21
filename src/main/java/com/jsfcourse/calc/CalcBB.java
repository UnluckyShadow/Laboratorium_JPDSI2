package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private String kwota;
	private String lata;
        private String oprocentowanie;
	private Double wynik;

	@Inject
	FacesContext ctx;

	public String getKwota() {
		return kwota;
	}

	public void setKwota(String kwota) {
		this.kwota = kwota;
	}

	public String getLata() {
		return lata;
	}

	public void setLata(String lata) {
		this.lata = lata;
	}
        
        public String getOprocentowanie() {
            return oprocentowanie;
        }

        public void setOprocentowanie(String oprocentowanie) {
            this.oprocentowanie = oprocentowanie;
        }
        
	public Double getWynik() {
		return wynik;
	}

	public void setWynik(Double wynik) {
		this.wynik = wynik;
	}

	public boolean obliczRate() {
                    try {
                        double kwotaVal = Double.parseDouble(kwota);
                        int lataVal = Integer.parseInt(lata);
                        double oprocentowanieVal = Double.parseDouble(oprocentowanie);

                        double miesiecznaStopa = (oprocentowanieVal / 100) / 12;
                        int liczbaMiesiecy = lataVal * 12;

                        double rata = (kwotaVal * miesiecznaStopa) /
                                (1 - Math.pow(1 + miesiecznaStopa, -liczbaMiesiecy));

                        wynik = Math.round(rata * 100.0) / 100.0;

                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Rata obliczona poprawnie", null));

                        return true;

                    } catch (Exception e) {
                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Błąd: wprowadź poprawne dane liczbowe", null));
                        return false;
                    }
                }
	// Go to "showresult" if ok
	public String oblicz() {
		if (obliczRate()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String oblicz_AJAX() {
		if (obliczRate()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Wynik: " + wynik + " PLN", null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
