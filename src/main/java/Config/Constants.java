package Config;

public final class Constants {

    private Constants(){} // restrict initialization

    public static final class Regex {
        public static final String StringWithSpaces = "([a-zA-Z]| )*";
        public static final String GlobalNumberFormat = "\\+[0-9]{12}";
        public static final String Email = "[a-z\\.]*@[a-z]*\\.[a-z]{2,4}";
        public static final String Password = "[a-zA-z]*[0-9]*[a-zA-z]*";
    }

}
