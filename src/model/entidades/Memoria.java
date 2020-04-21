package model.entidades;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum Tiposcomando {
		ZERAR, NUMERO, DIV, SOMA, SUB, MULT, IGUAL, VIRGULA
	}

	private static final Memoria instancia = new Memoria();
	private String textoAtual = "";
	private String textoBuffer = "";
	Tiposcomando ultimoComando = null;
	private boolean substituirValor = false;

	private final List<MemoriaObserver> listObs = new ArrayList<>();

	private Memoria() {

	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : this.textoAtual;
	}

	public static Memoria getInstancia() {
		return instancia;
	}

	public void registraObservador(MemoriaObserver obs) {
		listObs.add(obs);
	}

	public void processaComando(String str) {

		Tiposcomando tipocomando = detectarTipoComando(str);

		if (tipocomando == null) {
			return;
		} else if (tipocomando.equals(Tiposcomando.ZERAR)) {
			textoAtual = "";
			textoBuffer = "";
			substituirValor = false;
			ultimoComando = null;
		} else if (tipocomando.equals(Tiposcomando.NUMERO) || tipocomando.equals(Tiposcomando.VIRGULA)) {
			textoAtual = substituirValor ? str : textoAtual + str;
			substituirValor = false;
		} else {
			substituirValor = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimoComando = tipocomando;
		}

		listObs.forEach(obs -> obs.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		if (ultimoComando == null || ultimoComando == Tiposcomando.IGUAL) {
			return textoAtual;
		}

		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));

		Double resultado = 0.0;

		if (ultimoComando.equals(Tiposcomando.SOMA)) {
			resultado = numeroBuffer+numeroAtual;
		}
		if (ultimoComando.equals(Tiposcomando.SUB)) {
			resultado = numeroBuffer-numeroAtual;
		}
		if (ultimoComando.equals(Tiposcomando.MULT)) {
			resultado = numeroBuffer*numeroAtual;
		}
		if (ultimoComando.equals(Tiposcomando.DIV)) {
			resultado = numeroBuffer/numeroAtual;
		}
		
		String resultString = resultado.toString().replace(".", ",");
		
		boolean testInt = resultString.endsWith(",0");
		
		return testInt ? resultString.replace(",0", "") : resultString;
	}

	private Tiposcomando detectarTipoComando(String str) {
		if (textoAtual.isEmpty() && str.equals(0)) {
			return null;
		}

		try {
			Integer.parseInt(str);
			return Tiposcomando.NUMERO;
		} catch (NumberFormatException e) {
			if ("AC".equals(str)) {
				return Tiposcomando.ZERAR;
			} else if ("/".equals(str)) {
				return Tiposcomando.DIV;
			} else if ("*".equals(str)) {
				return Tiposcomando.MULT;
			} else if ("+".equals(str)) {
				return Tiposcomando.SOMA;
			} else if ("-".equals(str)) {
				return Tiposcomando.SUB;
			} else if ("=".equals(str)) {
				return Tiposcomando.IGUAL;
			} else if (",".equals(str) && !textoAtual.contains(",")) {
				return Tiposcomando.VIRGULA;
			}
		}

		return null;
	}

}
