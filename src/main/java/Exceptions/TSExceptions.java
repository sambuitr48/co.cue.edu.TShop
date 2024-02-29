package Exceptions;

public class TSExceptions extends Exception {
    public TSExceptions(String message) {
        super(message);
    }
    public static class ToyNotFoundException extends TSExceptions {

        public ToyNotFoundException(String message) {
            super(message);
        }
        public static class InvalidQuantityException extends TSExceptions {
            public InvalidQuantityException(String message) {
                super(message);
            }
        }

        public static class InvalidPriceException extends TSExceptions {
            public InvalidPriceException(String message) {
                super(message);
            }
        }
        public static class InvalidQuantityExceptions extends TSExceptions {
            public InvalidQuantityException(String message){
                super(message)
            }
        }
    }
}
