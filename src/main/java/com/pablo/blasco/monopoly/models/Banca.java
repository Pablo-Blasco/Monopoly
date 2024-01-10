package com.pablo.blasco.monopoly.models;


public class Banca {
          private double dinero = 0.0f;
          
          private String causaHistorico = "";

          private boolean enQuiebra =false; // En un futuro es un ArrayList
          
		public Banca(double dinero, String causaHistorico, boolean enQuiebra) {
			this.dinero = dinero;
			this.causaHistorico = causaHistorico;
			this.enQuiebra = enQuiebra;
		}

		public boolean isEnQuiebra() {
			return enQuiebra;
		}

		public void setEnQuiebra(boolean enQuiebra) {
			this.enQuiebra = enQuiebra;
		}

		public double getDinero() {
			return dinero;
		}

		public void setDinero(double dinero) {
			this.dinero = dinero;
		}

		public String getCausaHistorico() {
			return causaHistorico;
		}

		public void setCausaHistorico(String causaHistorico) {
			this.causaHistorico = causaHistorico;
		}
		
		
}
