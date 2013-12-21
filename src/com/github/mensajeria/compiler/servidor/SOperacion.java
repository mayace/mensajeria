class SOperacion{

	TIPO tipo;
	Object valor;

	public static enum TIPO{
		LOGIN,CUENTA,MENSAJE,BUSQUEDA,SOLICITUD_AMISTAD,SOLICITUD_RESPUESTA,LISTA_CONTACTOS
	}


	public SOperacion(Object valor){
		this.valor = valor;
	}



	public void exec(){
		Object obj = getValor();
		if(obj instance of Cuenta){
			Cuenta val = (Cuenta)obj;
			exec_cuenta(val);
		} else if(obj instance of Login){
			Login val = (Login)obj;
			exec_login(val);
		} else if(obj instance of Mensaje){
			Mensaje val = (Login)obj;
			exec_mensaje(val);
		} else if(obj instance of Busqueda){
			Busqueda val = (Busqueda)obj;
			exec_busqueda(val);
		} else if (obj instance of Solicitud_amistad){
			Solicitud_amistad val = (Solicitud_amistad)obj;
			exec_solicitud_amistad(val);
		} else if (obj instance of Solicitud_respuesta){
			Solicitud_respuesta val = (Solicitud_respuesta)obj;
			exec_solicitud_respuesta(val);
		} else if (obj instace of Lista_contactos){
			Lista_contactos val = (Lista_contactos)obj;
			exec_lista_contactos(val);
		} else {
			System.err.println("Operación no soportada...");
		}
	}

	void exec_login(Login val){

	}
	void exec_cuenta(Cuenta val){

	}
	void exec_mensaje(Mensaje val){

	}
	void exec_busqueda(Busqueda val){

	}
	void exec_solicitud_amistad(Solicitud_amistad val){

	}
	void exec_solicitud_respuesta(Solicitud_respuesta val){

	}
	void exec_lista_contactos(Lista_contactos val){

	}


	public TIPO getTipo(){
		return this.tipo;
	}
	public void setTipo(TIPO tipo){
		this.tipo = tipo;
	}
	public Object getValor(){
		return this.valor;
	}
	public void setValor(Object valor){
		this.valor = valor;
	}

	Public static class Login{
		String cuenta;
        String password;

        public Cuenta(String cuenta, String password) {
            this.cuenta = cuenta;
            this.password = password;
        }		
	}

	public static class Cuenta {

        String name;
        String password;

        public Cuenta(String name, String password) {
            this.name = name;
            this.password = password;
        }

    }

    public static class Mensaje {

        String mensaje;
        String emisor;
        HashSet<String> receptores;

        public Mensaje(String mensaje, String emisor, String... receptor) {
            this.mensaje = mensaje;
            this.emisor = emisor;
            this.receptores = new HashSet<>();

            for (String str : receptor) {
                boolean added = this.receptores.add(str);
            }
        }

    }

    public static class Solicitud_amistad{

        String emisor;
        HashSet<String> receptores;

        public Solicitud_amistad(String emisor, String... receptor) {
            this.cuenta = cuenta;
            this.receptores = new HashSet<>(Arrays.asList(receptores));
        }

    }

    public static class Solicitud_respuesta{
    	String emisor;
    	String receptor;
    	String respuesta;

    	public Solicitud_respuesta(String emisor,String receptor,String respuesta){
    		this.emisor = emisor;
    		this.receptor = receptor;
    		this.respuesta = respuesta;
    	}
    }

    public static class Lista_contactos{
    	String cuenta;
    	public Lista_contactos(String cuenta){
    		this.cuenta;
    	}
    }


    public static class MData{
    	Path pcuenta;
    	Path pmensaje;
    	Path pcontactos;
    }
}
