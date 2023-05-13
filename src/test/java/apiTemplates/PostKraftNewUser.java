package apiTemplates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostKraftNewUser {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("about")
        @Expose
        private String about;
        @SerializedName("terms")
        @Expose
        private String terms;

        /**
         * No args constructor for use in serialization
         */
        public PostKraftNewUser() {
        }

        /**
         * @param password
         * @param terms
         * @param name
         * @param about
         * @param email
         */
        public PostKraftNewUser(String name, String email, String password, String about, String terms) {
            super();
            this.name = name;
            this.email = email;
            this.password = password;
            this.about = about;
            this.terms = terms;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getTerms() {
            return terms;
        }

        public void setTerms(String terms) {
            this.terms = terms;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(PostKraftNewUser.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null) ? "<null>" : this.name));
            sb.append(',');
            sb.append("email");
            sb.append('=');
            sb.append(((this.email == null) ? "<null>" : this.email));
            sb.append(',');
            sb.append("password");
            sb.append('=');
            sb.append(((this.password == null) ? "<null>" : this.password));
            sb.append(',');
            sb.append("about");
            sb.append('=');
            sb.append(((this.about == null) ? "<null>" : this.about));
            sb.append(',');
            sb.append("terms");
            sb.append('=');
            sb.append(((this.terms == null) ? "<null>" : this.terms));
            sb.append(',');
            if (sb.charAt((sb.length() - 1)) == ',') {
                sb.setCharAt((sb.length() - 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }
}
