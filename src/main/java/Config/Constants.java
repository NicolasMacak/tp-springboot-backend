package Config;

public final class Constants {

    private Constants() {} // restrict initialization

    public static final class Regex {
        public static final String StringWithSpaces = "([a-zA-Z]| )*";
        public static final String GlobalNumberFormat = "\\+[0-9]{12}";
        public static final String Email = "[a-z\\.]*@[a-z]*\\.[a-z]{2,4}";
        public static final String Password = "[a-zA-z]*[0-9]*[a-zA-z]*";
        public static final String PostalCode = "(^\\d{5}$)|(^\\d\\d\\d \\d\\d$)";
        public static final String Ico = "\\d{8}$";
        public static final String Dic = "\\d{10}$";
        public static final String IcDPH = "^\\D{2,5}\\d{10}$";
    }

    public enum CompanyType {
        VOS("Verejná obchodná spoločnosť"),
        KS("Komanditná spoločnosť"),
        SRO("Spoločnosť s ručením obmedzeným"),
        AS("Akciová spoločnosť");

        // -----------------------------------------

        private final String name;
        CompanyType(String name) {this.name = name;}

        public String getName() {
            return name;
        }
        @Override
        public String toString() {return name;}
    }

    public enum ProductState {
        inStorage,
        sold,
        ReadyToOrder,
        presale
    }

    public enum ProductCategory {
        electronics,
        whiteGoods,
        houseAndGarden,
        breeding,
        babyGoods,
        booksMoviesGames,
        groceries,
        cosmeticsAndHealth,
        clothes,
        sport,
        furniture
    }

    public enum SearchOperation {
        GREATER_THAN,LESS_THAN,NOT_EQUAL,EQUAL;
        /*
        GREATER_THAN_EQUAL,LESS_THAN_EQUAL,
        MATCH,MATCH_START,MATCH_END,
        IN,NOT_IN
        */
        public static SearchOperation getOperation(String input) {
            return switch (input) {
                case ">" -> GREATER_THAN;
                case "<" -> LESS_THAN;
                case "!" -> NOT_EQUAL;
                case ":" -> EQUAL;
                default -> null;
            };
        }
    }
}
