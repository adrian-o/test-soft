
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GeradorObservacao { 

	//Textos pr�-definidos
	static final String TEXTO_UMA_NOTA = "Fatura da nota fiscal de simples remessa: ";
	static final String TEXTO_NOTAS = "Fatura das notas fiscais de simples remessa: ";
	
	//Identificador da entidade
	StringBuilder texto = new StringBuilder();
		
	//Gera observa��es, com texto pre-definido, incluindo os n�meros, das notas fiscais, recebidos no par�metro
	public String geraObservacao(List<Integer> lista) {
		if (!lista.isEmpty()) {
			return retornaCodigos(lista);
		}		
		return texto.toString();		
	}

	//Cria observa��o
	private String retornaCodigos(List<Integer> lista) {
		// Configura texto inicial
		String textoInicial = lista.size()>1 ? TEXTO_NOTAS : TEXTO_UMA_NOTA;
		
		// Monta mensagem a partir da lista
		StringBuilder cod = new StringBuilder();
		AtomicInteger index = new AtomicInteger();
		Arrays.stream(lista.toArray())
			.forEach(item -> {
				cod.append(item).append(defineSeparador(lista, index.getAndIncrement()));
		}); 
		
		return texto.append(textoInicial).append(cod).toString();
	}
	
	//Acha separador
	private String defineSeparador(List<Integer> lista, Integer index) {
		return (index == lista.size()-1) ? "." :  
					((index == lista.size()-2) ? " e " : ", ");
	}
	
}