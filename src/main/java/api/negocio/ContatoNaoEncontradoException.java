package api.negocio;

@SuppressWarnings("serial")
public class ContatoNaoEncontradoException extends RuntimeException {

    public ContatoNaoEncontradoException(String msg) {
        super(msg);
    }

}
