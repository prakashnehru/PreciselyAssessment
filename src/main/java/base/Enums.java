package base;

public class Enums {

    public enum PageTitle {
        LOGIN_PAGE_TITLE("Infogix is now part of Precisely, bringing its Data360 products"),
        GOVERNANCETEAMPAGE_TITLE("Governance Team: Four Steps to Building a Successful One"),
        MY_STORE_PAGE_TITLE("My account - My Store");

        public final String title;

        PageTitle(String title) {
            this.title = title;
        }

        public String asString() {
            return title;
        }
    }

}