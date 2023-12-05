package co.com.bussine.model.common;
import java.util.function.Supplier;

public class BusinessException extends ApplicationException{

    private static final long serialVersionUID = 1L;

    public enum Type {
        ERROR_BASE_DATOS("ERROR EN LA BASE DE DATOS"),
        ERROR_VALOR_UNICO("ALGUNO DE LOS CAMPOS YA SE ENCUENTRA REGISTRADO EN LA BASE DE DATOS"),
        NO_EXISTE_ESTE_PRODUCTO("ESTE PRODUCTO NO SE ENCUENTRA REGISTRADO"),
        NO_EXISTE_ESTA_CATEGORIA("ESTE CATEGORIA NO EXISTE"),
        NO_EXISTE_ESTE_INGRESO("ESTE INGRESO NO SE ENCUNTRA REGISTRADO"),
        NO_EXISTE_ESTE_PROVEDOR("EL PROVEDOR NO SE ENCUENTRA REGISTRADO"),
        NO_EXISTE_ESTA_EMPRESA("LA EMPRESA NO SE ENCUENTRA REGISTRADO"),
        ERROR_USUARIO_EXISTE("EL CORREO O IDENTIFICACION YA SE ENCUENTRAN REGISTRADOS"),
        ERROR_TIPO_DE_PAGO_NO_EXISTE("EL TIPO DE PAGO NO EXISTE EN EL SISTEMA"),
        ERROR_ENVIO_DE_CORREO("OCURRIO UN ERROR EN EL ENVIO DE UN CORREO ELECTRONICO"),
        ERROR_PRECIO_DE_VENTA_MENOR_A_CERO("OCURRIO UN ERROR, EL PRECIO DE VENTA NO PUEDE SER MENOR AL PRECIO DE COMPRA")
        ;


        private final String message;

        public String getMessage() {
            return message;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new BusinessException(this);
        }

        Type(String message) {
            this.message = message;
        }

    }

    private final Type type;
   // private final int code;

    public BusinessException(Type type) {
        super(type.message);
        this.type = type;
    }

    public BusinessException(Type type, String message) {
        super(message);
        this.type = type;
    }

    @Override
    public String getCode() {
        return type.name();
    }

}
